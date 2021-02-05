package com.sharmila.bowling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharmila.bowling.dto.BowlersDto;
import com.sharmila.bowling.exception.BowlingServiceException;
import com.sharmila.bowling.exception.ErrorResponse;
import com.sharmila.bowling.models.Bowler;
import com.sharmila.bowling.service.BowlerControllerService;

@RestController
@RequestMapping("/bowler")
public class BowlerController {
	@Autowired
	private BowlerControllerService bowlerControllerService;
	
	
	@RequestMapping(value = "/{bowlerId}", method = RequestMethod.GET)
	public ResponseEntity<Bowler> getBowlerdetailsByID(@PathVariable Long bowlerId) throws BowlingServiceException
	{
		Bowler bowler = bowlerControllerService.getBowlerDetails(bowlerId);
		return ResponseEntity.ok(bowler);
		 
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<BowlersDto> getBowlerdetails()
	{
		BowlersDto bowlers = bowlerControllerService.getBowlerDetails();
		return ResponseEntity.ok(bowlers);
		 
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> bowlingServiceException(BowlingServiceException ex)
	{
		ErrorResponse error = new ErrorResponse(ex.getErrormsg());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}
