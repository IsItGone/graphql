package com.ddd.graphql.domain.route.repository;

import com.ddd.graphql.domain.route.graphql.RouteMapper;
import com.ddd.graphql.domain.route.service.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class RouteMongoRepository implements RouteRepository {

	private final RouteTemplate routeTemplate;
	private final RouteMapper routeMapper;

	@Override
	public Mono<Route> findById(String id) {
		return routeTemplate.findById(id).map(routeMapper::toRoute);
	}

	@Override
	public Flux<Route> findAll() {
		return routeTemplate.findAll().map(routeMapper::toRoute);
	}

	@Override
	public Mono<Route> findByName(String name) {
		return routeTemplate.findByName(name).map(routeMapper::toRoute);
	}

	@Override
	public Flux<Route> findByStationId(String stationId) {
		return routeTemplate.findByStationId(stationId).map(routeMapper::toRoute);
	}
}
