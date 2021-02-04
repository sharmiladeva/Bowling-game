package com.sharmila.bowling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sharmila.bowling.models.Bowler;

public interface BowlerRepo extends JpaRepository<Bowler, Long>
{
	@Query("SELECT b from Bowler b where b.firstName = ?1 and b.lastName = ?2 ")
	Bowler findByFirstNameAndLastName(String firstName, String lastName);
}
