package com.ddd.graphql.exception;

public class RouteNotFoundException extends RuntimeException {

	public RouteNotFoundException() {
		super("Route not found");
	}
}
