package com.ddd.graphql.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	ROUTE_NOT_FOUND("Route not found"),
	STATION_NOT_FOUND("Station not found"),
	UNKNOWN_ERROR("Unknown error");

	private final String message;

}
