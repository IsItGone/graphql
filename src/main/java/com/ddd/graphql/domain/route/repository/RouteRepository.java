package com.ddd.graphql.domain.route.repository;

import com.ddd.graphql.domain.route.service.Route;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteRepository {

	Mono<Route> findById(String id);

	Flux<Route> findAll();

	Mono<Route> findByName(String name);

	Flux<Route> findByStationId(String stationId);
}
