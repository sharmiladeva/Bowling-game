package com.sharmila.bowling.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class GameBowlerID implements Serializable{

	private long gameId;

	private long bowlerId;

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public long getBowlerId() {
		return bowlerId;
	}

	public void setBowlerId(long bowlerId) {
		this.bowlerId = bowlerId;
	}
	
	public GameBowlerID()
	{
		
	}
	
	public GameBowlerID(long gameId, long bowlerId)
	{
		this.gameId=gameId;
		this.bowlerId=bowlerId;
	}
}
