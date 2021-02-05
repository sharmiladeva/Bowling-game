package com.sharmila.bowling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharmila.bowling.dto.BowlersDto;
import com.sharmila.bowling.dto.GameResponseDto;
import com.sharmila.bowling.exception.BowlingServiceException;
import com.sharmila.bowling.exception.ErrorResponse;
import com.sharmila.bowling.service.GameControllerService;

@RestController
@RequestMapping("/game")
public class GameController
{
	@Autowired
	private GameControllerService gameControllerService;


	@GetMapping(value = "/health")
	public String test()
	{
		return "RUNNING";
	}


	@PostMapping(value = "/start")
	public ResponseEntity<GameResponseDto> startGame(@RequestBody BowlersDto bowlers)
	{
		GameResponseDto  response =gameControllerService.createBowlersAndAssignLanes(bowlers.getBowlers());
		System.out.println(response.getDetails().size());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/score/{bowlerId}/{gameId}")
	public Integer getScoreOfBowler(@PathVariable Long bowlerId,@PathVariable Long gameId)
	{
		return gameControllerService.calculateScore(bowlerId,gameId);
		 
	}
	
	@GetMapping(value = "/winner/{gameId}")
	public Long getWinnerOfGame(@PathVariable Long gameId)
	{
		return gameControllerService.getWinner(gameId);
		 
	}
	
	@GetMapping(value = "/allocatedlane/{bowlerId}/{gameId}")
	public Integer getAllocatedLane(@PathVariable Long bowlerId,@PathVariable Long gameId)
	{
		return gameControllerService.getAllocatedLane(bowlerId,gameId);
		 
	}
	
	@GetMapping(value = "/strikes/{bowlerId}/{gameId}")
	public Integer getTotalStrikesInGame(@PathVariable Long bowlerId,@PathVariable Long gameId)
	{
		return gameControllerService.getTotalStrikes(bowlerId,gameId);
		 
	}
	
	@GetMapping(value = "/strikes/{bowlerId}")
	public Integer getTotalStrikes(@PathVariable Long bowlerId)
	{
		return gameControllerService.getTotalStrikes(bowlerId,null);
		 
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> bowlingServiceException(BowlingServiceException ex)
	{
		ErrorResponse error = new ErrorResponse(ex.getErrormsg());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
