package com.ddd.graphql.domain.route.repository;

import com.ddd.graphql.domain.route.graphql.entity.Route;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Repository
public class RouteMongoRepository implements RouteRepository {

	private final RouteTemplate routeTemplate;

	@Override
	public Mono<Route> findById(String id) {
		return routeTemplate.findById(id);
	}

	@Override
	public Flux<Route> findAll() {
		return routeTemplate.findAll();
	}

	@Override
	public Mono<Route> findByName(String name) {
		return routeTemplate.findByName(name);
	}

	@Override
	public Flux<Route> findByStationId(String stationId) {
		return routeTemplate.findByStationId(stationId);
	}
}
