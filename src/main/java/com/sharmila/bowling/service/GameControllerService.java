package com.sharmila.bowling.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sharmila.bowling.dto.BowlerGameDetailsDto;
import com.sharmila.bowling.dto.BowlersDto;
import com.sharmila.bowling.dto.GameResponseDto;
import com.sharmila.bowling.exception.BowlingServiceException;
import com.sharmila.bowling.models.Bowler;
import com.sharmila.bowling.models.Game;
import com.sharmila.bowling.models.GameBowlerID;
import com.sharmila.bowling.models.Slot;
import com.sharmila.bowling.repositories.BowlerRepo;
import com.sharmila.bowling.repositories.GameRepo;
import com.sharmila.bowling.repositories.SlotRepo;

@Service
public class GameControllerService {
	@Autowired
	private GameRepo gameRepo;

	@Autowired
	private BowlerRepo bowlerRepo;

	@Autowired
	private SlotRepo slotRepo;

	private static long gameNum = 1L;

	public GameResponseDto createBowlersAndAssignLanes(List<Bowler> bowlers) throws BowlingServiceException {
		GameResponseDto response = new GameResponseDto();
		List<BowlerGameDetailsDto> res = new ArrayList<>();
		List<BowlerGameDetailsDto> details = new ArrayList<>();
		List<Game> ongoingGames = validateRequestedBowlers(bowlers);
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (Game game : ongoingGames) {
			int laneNum = game.getLaneNum();
			if (hm.get(laneNum) != null) {
				int count = hm.get(laneNum);
				hm.put(laneNum, count + 1);
			} else
				hm.put(laneNum, 1);
		}
		System.out.print("game Id :" + gameNum);
		for (int i = 1, j = 0; i <= Constants.NUM_OF_LANES && j < bowlers.size(); i++) {
			//HashMap<Long, Long> bowlerGameIdMap = new HashMap<>();
			List<Long> listOfBowlerIDs = new ArrayList<>();
			int availableBowlersPerLane;
			if (hm.get(i) != null)
				availableBowlersPerLane = Constants.BOWLERS_PER_LANE - hm.get(i);
			else
				availableBowlersPerLane = Constants.BOWLERS_PER_LANE;
			while (availableBowlersPerLane > 0 && j < bowlers.size()) {
				Bowler bowler = bowlers.get(j);
				Bowler bowlerFromDB = bowlerRepo.findByFirstNameAndLastName(bowler.getFirstName(),
						bowler.getLastName());
				long bowlerId;
				if (bowlerFromDB == null) {
					Bowler savedBowler = bowlerRepo.save(bowler);
					bowlerId = savedBowler.getId();
					System.out.println("Created new bowler with Id : " + bowlerId);
				} else {
					bowlerId = bowlerFromDB.getId();
					System.out.println("Bowler already exists.. id is " + bowlerId);
				}
				System.out.println("creating a game for bowler " + bowlerId + "in lane " + i);
				Game game = new Game();
				game.setGameBowlerId(new GameBowlerID(gameNum, bowlerId));
				game.setLaneNum(i);
				game.setOngoing(true);
				gameRepo.save(game);
				listOfBowlerIDs.add(bowlerId);
				j++;
				availableBowlersPerLane--;
			}
			if (!listOfBowlerIDs.isEmpty()) {
				res = playGame(listOfBowlerIDs, i);
				for (BowlerGameDetailsDto d : res) {
					System.out.println("adding");
					details.add(d);
				}
			}
		}
		gameNum++;
		response.setDetails(details);
		return response;
	}

	private List<Game> validateRequestedBowlers(List<Bowler> bowlers) throws BowlingServiceException {
		List<Game> ongoingGames = gameRepo.findByOngoing(true);
		System.out.println("ongoingGames size :" + ongoingGames.size());
		int availableSlots = Constants.BOWLERS_PER_LANE * Constants.NUM_OF_LANES - ongoingGames.size();
		if (availableSlots == 0 || availableSlots < bowlers.size()) {
			throw new BowlingServiceException("All the lanes are occupied !!");
		}
		return ongoingGames;
	}

	@Async
	private List<BowlerGameDetailsDto> playGame(List<Long> listOfBowlerIDs, int laneNum) {
		Random rand = new Random();
		List<BowlerGameDetailsDto> listOfGameDetails = new ArrayList<>();
		HashMap<Long, List<Integer>> bowlerScoreMap = new HashMap<>();
		for (int i = 1; i <= Constants.NUM_OF_SLOTS; i++) {
			for (Long bowlerId : listOfBowlerIDs) {
				boolean isStrike = false;
				int score1 = rand.nextInt(11);
				int score2 = 0;
				if (score1 == 10) {
					isStrike = true;
				} else {
					score2 = rand.nextInt(11 - score1);
					if (score1 + score2 == 10) {
						if (score1 == 0) {// Strike in second roll
							isStrike = true;
						}

					} 
				}
				Slot slot = new Slot();
				slot.setSlotNumber(i);
				slot.setGameId(gameNum);
				slot.setBowlerId(bowlerId);
				slot.setRollOne(score1);
				slot.setRollTwo(score2);
				slotRepo.save(slot);
				if (isStrike && i == Constants.NUM_OF_SLOTS) {
					
						int scoreInExtraBowl = rand.nextInt(11);
						gameRepo.updateScoreInLastbowl(scoreInExtraBowl, gameNum, bowlerId);
					
				}
				
				if (bowlerScoreMap.get(bowlerId) == null) {
					List<Integer> firstScores = new ArrayList<>();
					firstScores.add(score1);
					firstScores.add(score2);
					bowlerScoreMap.put(bowlerId, firstScores);
				} else {
					List<Integer> latestScores = bowlerScoreMap.get(bowlerId);
					latestScores.add(score1);
					latestScores.add(score2);
					bowlerScoreMap.put(bowlerId, latestScores);
				}
			}

		}
		for (Long bowlerId : listOfBowlerIDs) {
			BowlerGameDetailsDto gameDetails = new BowlerGameDetailsDto();
			gameDetails.setBowlerId(bowlerId);
			gameDetails.setGameId(gameNum);
			gameDetails.setLaneNum(laneNum);
			gameDetails.setScores(bowlerScoreMap.get(bowlerId));
			listOfGameDetails.add(gameDetails);
		}
		gameRepo.updateOngingToFalse();
		return listOfGameDetails;
	}

	public int calculateScore(Long bowlerId, Long gameId) throws BowlingServiceException {
		int score = 0;
		Game game = gameRepo.findByBowlerIdAndGameId(bowlerId, gameId);
		if (game == null) {
			throw new BowlingServiceException("There is no such bowlerId and gameId");
		} else {
			List<Slot> slots =slotRepo.findByBowlerIdAndGameId(bowlerId, gameId);
			for (Slot slot : slots) {
				score += slot.getRollOne() + slot.getRollTwo();
				if (slot.getRollOne()==10 || slot.getRollTwo()==10) {
					score += 10;
				} else if (slot.getRollOne()+slot.getRollTwo()==10) {
					score += 5;
				}
			}
			int scoreInLastBowl = gameRepo.getScoreInLastbowl(gameId, bowlerId);
			System.out.println("gameId = "+gameId+" bowlerId = "+bowlerId+" scoreInLastBowl = "+scoreInLastBowl);
			score+=scoreInLastBowl;
		}
		return score;
	

	}

	public Long getWinner(Long gameId) throws BowlingServiceException {
		List<Long> bowlerIds = gameRepo.findBowlersPlayedInGame(gameId);
		Integer maxScore = Integer.MIN_VALUE;
		Long maxScoredBowler=0L;
		for(Long bowlerId : bowlerIds)
		{
			int score =calculateScore(bowlerId, gameId);
			if(score> maxScore)
			{
				maxScore=score;
				maxScoredBowler=bowlerId;
			}
		}
		return maxScoredBowler;
	}

	public Bowler getBowlerDetails(Long bowlerId) throws BowlingServiceException {
		Optional<Bowler> bowler = bowlerRepo.findById(bowlerId);
		if(bowler.isPresent())
			return bowler.get();
		else
			throw new BowlingServiceException("No such bowler");
	}

	public BowlersDto getBowlerDetails() {
		List<Bowler> bowlers =bowlerRepo.findAll();
		BowlersDto dto = new BowlersDto();
		dto.setBowlers(bowlers);
		return dto;
	}

	public Integer getAllocatedLane(Long bowlerId, Long gameId) {
		Game game =gameRepo.findByBowlerIdAndGameId(bowlerId, gameId);
		return game.getLaneNum();
	}

	public Integer getTotalStrikes(Long bowlerId, Long gameId) {
		if (gameId != null) {
			return slotRepo.findStrikesByBowlerIdAndGameId(bowlerId, gameId);
		} else {
			return slotRepo.findStrikesByBowlerId(bowlerId);
		}
		

	}
}
