package com.ddd.graphql.domain.route.resolver;

import com.ddd.graphql.domain.route.graphql.RouteMapper;
import com.ddd.graphql.domain.route.graphql.type.RouteType;
import com.ddd.graphql.domain.route.service.RouteQueryService;
import com.ddd.graphql.domain.station.graphql.type.StationType;
import com.ddd.graphql.exception.RouteNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class RouteQueryResolver {

	private final RouteQueryService routeQueryService;
	private final RouteMapper routeMapper;

	@QueryMapping
	public Mono<RouteType> testRoute() {
		return Mono.just(RouteType.builder()
				.id("testRouteId")
				.name("testRouteName")
				.departureStations(List.of(StationType.builder()
						.id("testStationId")
						.name("testStationName")
						.address("testAddress")
						.latitude(37.0)
						.longitude(128.0)
						.stopTime("08:35")
						.isDeparture(true)
						.routes(List.of("1", "2", "3"))
						.build()))
				.arrivalStations(List.of(StationType.builder()
						.id("testStationId")
						.name("testStationName")
						.address("testAddress")
						.latitude(37.0)
						.longitude(128.0)
						.stopTime("08:35")
						.isDeparture(true)
						.routes(List.of("1", "2", "3"))
						.build()))
				.build());
	}

	@QueryMapping
	public Mono<RouteType> testRouteError() {
		return Mono.error(RouteNotFoundException::new);
	}

	@QueryMapping
	public Flux<RouteType> getRoutes() {
		return routeQueryService.getRoutes().map(routeMapper::toRouteType);
	}

	@QueryMapping
	public Mono<RouteType> getRouteById(@Argument String id) {
		return routeQueryService.getRouteById(id).map(routeMapper::toRouteType);
	}

	@QueryMapping
	public Mono<RouteType> getRouteByName(@Argument String name) {
		return routeQueryService.getRouteByName(name).map(routeMapper::toRouteType);
	}

	@QueryMapping
	public Flux<RouteType> getRoutesByStationId(@Argument String stationId) {
		return routeQueryService.getRoutesByStationId(stationId).map(routeMapper::toRouteType);
	}

}
