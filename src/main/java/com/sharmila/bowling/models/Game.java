package com.sharmila.bowling.models;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import java.util.List;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Game
{
	@EmbeddedId
	private GameBowlerID gameBowlerId;

	private int laneNum;

	private boolean ongoing;
	
	@NotNull
	private int scoreInLastBowl;

	public int getLaneNum()
	{
		return this.laneNum;
	}


	public void setLaneNum(final int laneNum)
	{
		this.laneNum = laneNum;
	}


	public boolean isOngoing()
	{
		return this.ongoing;
	}


	public void setOngoing(final boolean ongoing)
	{
		this.ongoing = ongoing;
	}


	public GameBowlerID getGameBowlerId() {
		return gameBowlerId;
	}


	public void setGameBowlerId(GameBowlerID gameBowlerId) {
		this.gameBowlerId = gameBowlerId;
	}


	public int getScoreInLastBowl() {
		return scoreInLastBowl;
	}


	public void setScoreInLastBowl(int scoreInLastBowl) {
		this.scoreInLastBowl = scoreInLastBowl;
	}
}

