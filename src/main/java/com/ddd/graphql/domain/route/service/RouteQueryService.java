package com.ddd.graphql.domain.route.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteQueryService {

	Flux<Route> getRoutes();

	Mono<Route> getRouteById(String id);

	Mono<Route> getRouteByName(String name);

	Flux<Route> getRoutesByStationId(String stationId);
}

