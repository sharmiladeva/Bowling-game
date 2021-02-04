package com.sharmila.bowling.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.sharmila.bowling.dto.BowlerGameDetailsDto;
import com.sharmila.bowling.dto.BowlersDto;
import com.sharmila.bowling.dto.GameResponseDto;
import com.sharmila.bowling.exception.BowlingServiceException;
import com.sharmila.bowling.models.Bowler;
import com.sharmila.bowling.models.Game;
import com.sharmila.bowling.models.Slot;
import com.sharmila.bowling.repositories.BowlerRepo;
import com.sharmila.bowling.repositories.GameRepo;
import com.sharmila.bowling.repositories.SlotRepo;
import com.sharmila.bowling.service.GameControllerService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class GameControllerServiceTest {

	@InjectMocks
	GameControllerService gameControllerService;
	
	@Mock 
	private GameRepo gameRepo;
	
	@Mock
	private BowlerRepo bowlerRepo;

	@Mock
	private SlotRepo slotRepo;

	
	@Test
	public void startGameTest() throws BowlingServiceException
	{
		List<Bowler> bowlers = new ArrayList<>();
		Bowler b1 = new Bowler();
		b1.setId(1L);
		b1.setFirstName("fName1");
		b1.setLastName("lName1");
		Bowler b2 = new Bowler();
		b2.setId(2L);
		b2.setFirstName("fName2");
		b2.setLastName("lName2");
		bowlers.add(b1);
		bowlers.add(b2);
		Mockito.when(gameRepo.findByOngoing(true)).thenReturn(new ArrayList<Game>());
		Mockito.when(bowlerRepo.findByFirstNameAndLastName("fName1","lName1")).thenReturn(b1);
		Mockito.when(bowlerRepo.findByFirstNameAndLastName("fName2","lName2")).thenReturn(b2);
		GameResponseDto resp = gameControllerService.createBowlersAndAssignLanes(bowlers);
		System.out.println(resp);
		boolean result = true && resp.getDetails().size()==2;
		for(BowlerGameDetailsDto detail : resp.getDetails())
		{
			result = result && (detail.getLaneNum()==1) && (detail.getGameId() ==1);
		}
		gameControllerService.getWinner(1L);
		assertTrue(result);
	}
	
	@Test
	public void calculateScoreTest() throws BowlingServiceException
	{
		Mockito.when(gameRepo.findByBowlerIdAndGameId(1L,1L)).thenReturn(new Game());
		Slot slot1 = new Slot();
		slot1.setRollOne(9);
		slot1.setRollTwo(1);
		Slot slot2 = new Slot();
		slot2.setRollOne(3);
		slot2.setRollTwo(2);
		Slot slot3 = new Slot();
		slot3.setRollOne(1);
		slot3.setRollTwo(8);
		Slot slot4 = new Slot();
		slot4.setRollOne(7);
		slot4.setRollTwo(1);
		Slot slot5 = new Slot();
		slot5.setRollOne(3);
		slot5.setRollTwo(2);
		Slot slot6 = new Slot();
		slot6.setRollOne(7);
		slot6.setRollTwo(2);
		Slot slot7 = new Slot();
		slot7.setRollOne(1);
		slot7.setRollTwo(3);
		Slot slot8 = new Slot();
		slot8.setRollOne(3);
		slot8.setRollTwo(1);
		Slot slot9 = new Slot();
		slot9.setRollOne(10);
		slot9.setRollTwo(0);
		Slot slot10 = new Slot();
		slot10.setRollOne(2);
		slot10.setRollTwo(3);
		List<Slot> slots = new ArrayList<>();
		slots.add(slot10);
		slots.add(slot9);
		slots.add(slot8);
		slots.add(slot7);
		slots.add(slot6);
		slots.add(slot5);
		slots.add(slot4);
		slots.add(slot3);
		slots.add(slot2);
		slots.add(slot1);
		Mockito.when(slotRepo.findByBowlerIdAndGameId(1L, 1L)).thenReturn(slots);
		assertTrue(gameControllerService.calculateScore(1L, 1L)==84);
	}
	
	@Test
	public void getBowlersTest()
	{
		List<Bowler> bowlers = new ArrayList<>();
		Bowler b1 = new Bowler();
		b1.setId(1L);
		b1.setFirstName("fName1");
		b1.setLastName("lName1");
		Bowler b2 = new Bowler();
		b2.setId(2L);
		b2.setFirstName("fName2");
		b2.setLastName("lName2");
		bowlers.add(b1);
		bowlers.add(b2);
		Mockito.when(bowlerRepo.findAll()).thenReturn(bowlers);
		assertTrue(gameControllerService.getBowlerDetails().getBowlers().size()==bowlers.size());
	}
	
	@Test
	public void getBowlerByIdTest() throws BowlingServiceException
	{
		Bowler b1 = new Bowler();
		b1.setId(1L);
		b1.setFirstName("fName1");
		b1.setLastName("lName1");
		Optional<Bowler> optionalBowler = Optional.of(b1);
		Mockito.when(bowlerRepo.findById(1L)).thenReturn(optionalBowler);
		assertTrue(gameControllerService.getBowlerDetails(1L).getFirstName().equals("fName1"));
	}
}
