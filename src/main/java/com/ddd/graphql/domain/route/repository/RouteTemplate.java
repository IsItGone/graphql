package com.ddd.graphql.domain.route.repository;

import com.ddd.graphql.domain.route.repository.document.RouteDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteTemplate {

	Mono<RouteDocument> findById(String id);

	Flux<RouteDocument> findAll();

	Mono<RouteDocument> findByName(String name);

	Flux<RouteDocument> findByStationId(String stationId);

}
