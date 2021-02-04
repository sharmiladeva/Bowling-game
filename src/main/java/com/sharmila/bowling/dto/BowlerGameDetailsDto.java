package com.sharmila.bowling.dto;

import java.util.List;

public class BowlerGameDetailsDto {

	private long bowlerId;
	
	private long gameId;
	
	private int laneNum;
	
	private List<Integer> scores;

	public Long getBowlerId() {
		return bowlerId;
	}

	public void setBowlerId(Long bowlerId) {
		this.bowlerId = bowlerId;
	}

	public int getLaneNum() {
		return laneNum;
	}

	public void setLaneNum(int laneNum) {
		this.laneNum = laneNum;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public List<Integer> getScores() {
		return scores;
	}

	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
}
