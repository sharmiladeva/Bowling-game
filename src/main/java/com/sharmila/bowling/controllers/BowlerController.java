package com.sharmila.bowling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharmila.bowling.dto.BowlersDto;
import com.sharmila.bowling.exception.BowlingServiceException;
import com.sharmila.bowling.models.Bowler;
import com.sharmila.bowling.repositories.BowlerRepo;
import com.sharmila.bowling.service.GameControllerService;

@RestController
@RequestMapping("/bowler")
public class BowlerController {
	@Autowired
	GameControllerService gameControllerService;
	
	
	@RequestMapping(value = "/bowler/{bowlerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getBowlerdetailsByID(@PathVariable Long bowlerId) throws BowlingServiceException
	{
		Bowler bowler = gameControllerService.getBowlerDetails(bowlerId);
		return ResponseEntity.ok(bowler);
		 
	}
	
	@RequestMapping(value = "/bowler/list", method = RequestMethod.GET)
	public ResponseEntity<?> getBowlerdetails() throws BowlingServiceException
	{
		BowlersDto bowlers = gameControllerService.getBowlerDetails();
		return ResponseEntity.ok(bowlers);
		 
	}
	
	
}
