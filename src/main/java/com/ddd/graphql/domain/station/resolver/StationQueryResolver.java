package com.ddd.graphql.domain.station.resolver;

import com.ddd.graphql.domain.station.graphql.StationMapper;
import com.ddd.graphql.domain.station.graphql.type.StationType;
import com.ddd.graphql.domain.station.service.StationQueryService;
import com.ddd.graphql.exception.StationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class StationQueryResolver {

	private final StationQueryService stationQueryService;
	private final StationMapper stationMapper;

	@QueryMapping
	public Mono<StationType> testStation() {
		return Mono.just(StationType.builder()
				.id("testStationId")
				.name("testStationName")
				.address("testAddress")
				.latitude(37.0)
				.longitude(128.0)
				.stopTime("08:35")
				.isDeparture(true)
				.build());
	}

	@QueryMapping
	public Mono<StationType> testStationError() {
		return Mono.error(StationNotFoundException::new);
	}

	@QueryMapping
	public Flux<StationType> getStations() {
		return stationQueryService.getStations().map(stationMapper::toStationType);
	}

	@QueryMapping
	public Mono<StationType> getStationById(@Argument String id) {
		return stationQueryService.getStationById(id).map(stationMapper::toStationType);
	}

	@QueryMapping
	public Flux<StationType> searchStationsByKeyword(@Argument String keyword) {
		return stationQueryService.searchStationsByKeyword(keyword)
				.map(stationMapper::toStationType);
	}

}
