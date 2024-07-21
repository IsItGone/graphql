package com.ddd.graphql.domain.route.repository;

import com.ddd.graphql.domain.route.graphql.entity.Route;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteRepository extends ReactiveMongoRepository<Route, String>, RouteTemplate {

	@NonNull
	@Override
	Mono<Route> findById(@NonNull String id);

	@NonNull
	@Override
	Flux<Route> findAll();

	Mono<Route> findByName(String name);


}
