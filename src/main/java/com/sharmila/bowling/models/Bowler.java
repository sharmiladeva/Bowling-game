package com.sharmila.bowling.models;

import javax.validation.constraints.NotNull;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Bowler
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bowlerId;

	@NotNull
	private String lastName;

	@NotNull
	private String firstName;

	public Bowler()
	{
	}


	public Bowler(final Long id, final String firstName, final String lastName)
	{
		this.bowlerId = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public Long getId()
	{
		return this.bowlerId;
	}


	public void setId(final Long id)
	{
		this.bowlerId = id;
	}


	public String getLastName()
	{
		return this.lastName;
	}


	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}


	public String getFirstName()
	{
		return this.firstName;
	}


	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}


}