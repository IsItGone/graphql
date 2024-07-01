package com.ddd.graphql.exception;

public class StationNotFoundException extends RuntimeException {

	public StationNotFoundException() {
		super(ErrorCode.STATION_NOT_FOUND.getMessage());
	}
}
