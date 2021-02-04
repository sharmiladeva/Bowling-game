package com.sharmila.bowling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharmila.bowling.dto.BowlersDto;
import com.sharmila.bowling.dto.GameResponseDto;
import com.sharmila.bowling.exception.BowlingServiceException;
import com.sharmila.bowling.service.GameControllerService;

@RestController
@RequestMapping("/game")
public class GameController
{
	@Autowired
	GameControllerService gameControllerService;


	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String test()
	{
		return "RUNNING";
	}


	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public ResponseEntity<?> startGame(@RequestBody BowlersDto bowlers) throws BowlingServiceException
	{
		GameResponseDto  response =gameControllerService.createBowlersAndAssignLanes(bowlers.getBowlers());
		System.out.println(response.getDetails().size());
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/score/{bowlerId}/{gameId}", method = RequestMethod.GET)
	public Integer getScoreOfBowler(@PathVariable Long bowlerId,@PathVariable Long gameId) throws BowlingServiceException
	{
		return gameControllerService.calculateScore(bowlerId,gameId);
		 
	}
	
	@RequestMapping(value = "/winner/{gameId}", method = RequestMethod.GET)
	public Long getWinnerOfGame(@PathVariable Long gameId) throws BowlingServiceException
	{
		return gameControllerService.getWinner(gameId);
		 
	}
	
	@RequestMapping(value = "/allocatedlane/{bowlerId}/{gameId}", method = RequestMethod.GET)
	public Integer getAllocatedLane(@PathVariable Long bowlerId,@PathVariable Long gameId) throws BowlingServiceException
	{
		return gameControllerService.getAllocatedLane(bowlerId,gameId);
		 
	}
	
	@RequestMapping(value = "/strikes/{bowlerId}/{gameId}", method = RequestMethod.GET)
	public Integer getTotalStrikesInGame(@PathVariable Long bowlerId,@PathVariable Long gameId) throws BowlingServiceException
	{
		return gameControllerService.getTotalStrikes(bowlerId,gameId);
		 
	}
	
	@RequestMapping(value = "/strikes/{bowlerId}", method = RequestMethod.GET)
	public Integer getTotalStrikes(@PathVariable Long bowlerId) throws BowlingServiceException
	{
		return gameControllerService.getTotalStrikes(bowlerId,null);
		 
	}

}
