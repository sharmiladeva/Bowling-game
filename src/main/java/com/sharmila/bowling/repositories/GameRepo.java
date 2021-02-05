package com.sharmila.bowling.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.sharmila.bowling.models.Game;

public interface GameRepo extends JpaRepository<Game, Long> {
	@Query("SELECT g from Game g where g.ongoing = ?1")
	List<Game> findByOngoing(boolean ongoing);

	@Query("SELECT g from Game g where g.gameBowlerId.bowlerId = ?1 and g.gameBowlerId.gameId =?2")
	Game findByBowlerIdAndGameId(Long bowlerId, Long gameId);

	@Transactional
	@Modifying
	@Query("UPDATE Game SET ongoing = false WHERE ongoing = true")
	void updateOngingToFalse();

	@Query("SELECT g.gameBowlerId.bowlerId FROM Game g WHERE g.gameBowlerId.gameId = ?1")
	List<Long> findBowlersPlayedInGame(Long gameId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Game SET scoreInLastBowl = ?1 WHERE gameBowlerId.gameId = ?2 and gameBowlerId.bowlerId = ?3")
	void updateScoreInLastbowl(int score,Long gameId, Long bowlerId);
	
	@Query("SELECT scoreInLastBowl from Game where gameBowlerId.gameId = ?1 and gameBowlerId.bowlerId = ?2")
	int getScoreInLastbowl(Long gameId, Long bowlerId);
}
