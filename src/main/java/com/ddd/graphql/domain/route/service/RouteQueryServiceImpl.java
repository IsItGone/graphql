package com.ddd.graphql.domain.route.service;

import com.ddd.graphql.domain.route.graphql.RouteMapper;
import com.ddd.graphql.domain.route.graphql.type.RouteType;
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
	private final RouteMapper routeMapper;

	@Override
	public Flux<RouteType> getRoutes() {
		return routeRepository.findAll().map(routeMapper::toRouteType);
	}

	@Override
	public Mono<RouteType> getRouteById(String id) {
		return routeRepository.findById(id)
				.switchIfEmpty(Mono.error(RouteNotFoundException::new))
				.map(routeMapper::toRouteType);
	}

	@Override
	public Mono<RouteType> getRouteByName(String name) {
		return routeRepository.findByName(name)
				.switchIfEmpty(Mono.error(RouteNotFoundException::new))
				.map(routeMapper::toRouteType);
	}

	@Override
	public Flux<RouteType> getRoutesByStationId(String name) {
		// TODO
		return null;
	}

	@Override
	public Flux<RouteType> searchRoutesByKeyword(String keyword) {
		// TODO
		return null;
	}


}
