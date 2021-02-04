package com.sharmila.bowling.dto;

import java.util.ArrayList;
import java.util.List;

public class GameResponseDto {

	private List<BowlerGameDetailsDto> details = new ArrayList<>();

	public List<BowlerGameDetailsDto> getDetails() {
		return details;
	}

	public void setDetails(List<BowlerGameDetailsDto> details) {
		this.details = details;
	}
}
