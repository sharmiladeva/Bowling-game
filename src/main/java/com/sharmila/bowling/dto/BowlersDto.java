package com.sharmila.bowling.dto;

import java.util.List;

import com.sharmila.bowling.models.Bowler;

public class BowlersDto
{
	private List<Bowler> bowlers;


	public List<Bowler> getBowlers()
	{
		return bowlers;
	}


	public void setBowlers(List<Bowler> bowlers)
	{
		this.bowlers = bowlers;
	}
}
