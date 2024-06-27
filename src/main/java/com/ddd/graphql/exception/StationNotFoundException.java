package com.ddd.graphql.exception;

public class StationNotFoundException extends RuntimeException {

	public StationNotFoundException() {
		super("Station not found");
	}
}
