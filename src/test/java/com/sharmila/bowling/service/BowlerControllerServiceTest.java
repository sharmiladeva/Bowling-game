package com.sharmila.bowling.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.sharmila.bowling.exception.BowlingServiceException;
import com.sharmila.bowling.models.Bowler;
import com.sharmila.bowling.repositories.BowlerRepo;

public class BowlerControllerServiceTest {

	@InjectMocks
	BowlerControllerService bowlerControllerService;
	
	@Mock
	BowlerRepo bowlerRepo;
	
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
		assertTrue(bowlerControllerService.getBowlerDetails().getBowlers().size()==bowlers.size());
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
		assertTrue(bowlerControllerService.getBowlerDetails(1L).getFirstName().equals("fName1"));
	}
}
