package com.sharmila.bowling.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharmila.bowling.dto.BowlersDto;
import com.sharmila.bowling.exception.BowlingServiceException;
import com.sharmila.bowling.models.Bowler;
import com.sharmila.bowling.repositories.BowlerRepo;

@Service
public class BowlerControllerService {

	
	@Autowired
	private BowlerRepo bowlerRepo;

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
}
