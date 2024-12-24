package com.ddd.graphql.domain.route.service;

import com.ddd.graphql.domain.route.repository.RouteRepository;
import com.ddd.graphql.exception.RouteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class RouteQueryServiceImpl implements RouteQueryService {

	private final RouteRepository routeRepository;

	@Override
	public Flux<Route> getRoutes() {
		return routeRepository.findAll();
	}

	@Override
	public Mono<Route> getRouteById(String id) {
		return routeRepository.findById(id)
				.switchIfEmpty(Mono.error(RouteNotFoundException::new));
	}

	@Override
	public Mono<Route> getRouteByName(String name) {
		return routeRepository.findByName(name)
				.switchIfEmpty(Mono.error(RouteNotFoundException::new));
	}

	@Override
	public Flux<Route> getRoutesByStationId(String stationId) {
		return routeRepository.findByStationId(stationId);
	}
}
