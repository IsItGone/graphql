package com.ddd.graphql.domain.route.repository;

import com.ddd.graphql.domain.route.graphql.entity.Route;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteTemplate {

	Mono<Route> findById(String id);

	Flux<Route> findAll();

	Mono<Route> findByName(String name);

}
