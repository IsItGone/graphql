package com.ddd.graphql.exception;

public class RouteNotFoundException extends RuntimeException {

	public RouteNotFoundException() {
		super(ErrorCode.ROUTE_NOT_FOUND.getMessage());
	}
}
