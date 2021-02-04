package com.sharmila.bowling.models;

import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Slot
{
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	@NotNull
	private long gameId;

	@NotNull
	private long bowlerId;
	
	@NotNull
	private long slotId;
	
	@NotNull
	private int slotNumber;

	@NotNull
	private int rollOne;

	@NotNull
	private int rollTwo;



	public Slot()
	{
	}


	public Slot(long id,long gameId, long bowlerId, long slotId, int slotNumber,int rollOne, int rollTwo )
	{
		this.id = id;
		this.gameId = gameId;
		this.bowlerId=bowlerId;
		this.rollOne=rollOne;
		this.rollTwo=rollTwo;
		this.slotId=slotId;
		this.slotNumber=slotNumber;
		
	}


	public int getRollOne()
	{
		return this.rollOne;
	}


	public void setRollOne(final int rollOne)
	{
		this.rollOne = rollOne;
	}


	public int getRollTwo()
	{
		return this.rollTwo;
	}


	public void setRollTwo(final int rollTwo)
	{
		this.rollTwo = rollTwo;
	}

	public int getSlotNumber()
	{
		return this.slotNumber;
	}


	public void setSlotNumber(final int slotNumber)
	{
		this.slotNumber = slotNumber;
	}


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


	public long getSlotId() {
		return slotId;
	}


	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}


	public long getId() {
		return this.id;
	}


	public void setId(long id) {
		this.id = id;
	}


	
}
