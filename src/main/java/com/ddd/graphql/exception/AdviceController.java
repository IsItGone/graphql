package com.ddd.graphql.exception;

import graphql.GraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import reactor.core.publisher.Mono;

@ControllerAdvice
@Slf4j
public class AdviceController {

	@GraphQlExceptionHandler
	public Mono<GraphQLError> routeNotFoundException(RouteNotFoundException e) {
		log.error(ErrorCode.ROUTE_NOT_FOUND.getMessage(), e.fillInStackTrace());
		return Mono.just(GraphQLError.newError()
				.message(ErrorCode.ROUTE_NOT_FOUND.getMessage())
				.errorType(ErrorType.NOT_FOUND)
				.build());
	}

	@GraphQlExceptionHandler
	public Mono<GraphQLError> stationNotFoundException(StationNotFoundException e) {
		log.error(ErrorCode.STATION_NOT_FOUND.getMessage(), e.fillInStackTrace());
		return Mono.just(GraphQLError.newError()
				.message(ErrorCode.STATION_NOT_FOUND.getMessage())
				.errorType(ErrorType.NOT_FOUND)
				.build());
	}

	@GraphQlExceptionHandler
	public Mono<GraphQLError> exception(Exception e) {
		log.error(ErrorCode.UNKNOWN_ERROR.getMessage(), e.fillInStackTrace());
		return Mono.just(GraphQLError.newError()
				.message(ErrorCode.UNKNOWN_ERROR.getMessage())
				.errorType(ErrorType.BAD_REQUEST)
				.build());
	}

}
