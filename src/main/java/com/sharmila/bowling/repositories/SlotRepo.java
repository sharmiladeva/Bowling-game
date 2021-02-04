package com.sharmila.bowling.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sharmila.bowling.models.Slot;

public interface SlotRepo extends JpaRepository<Slot, Long> {

	@Query("SELECT s from Slot s WHERE s.bowlerId = ?1 and s.gameId = ?2")
	List<Slot> findByBowlerIdAndGameId(Long bowlerId, Long gameId);

	@Query("SELECT COUNT(*) FROM Slot where bowlerId = ?1 and gameId = ?2 and (rollOne = 10 or rollTwo = 10)")
	int findStrikesByBowlerIdAndGameId(Long bowlerId, Long gameId);

	@Query("SELECT COUNT(*) FROM Slot where bowlerId = ?1 and (rollOne = 10 or rollTwo = 10)")
	Integer findStrikesByBowlerId(Long bowlerId);


}
