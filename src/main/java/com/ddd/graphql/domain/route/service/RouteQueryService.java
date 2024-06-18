package com.ddd.graphql.domain.route.service;

import com.ddd.graphql.domain.route.graphql.type.RouteType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteQueryService {

	Flux<RouteType> getRoutes();

	Mono<RouteType> getRouteById(String id);

	Mono<RouteType> getRouteByName(String name);

	Flux<RouteType> getRoutesByStationId(String name);

	Flux<RouteType> searchRoutesByKeyword(String keyword);
}

