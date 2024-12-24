package com.ddd.graphql.domain.station.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StationQueryService {

	Flux<Station> getStations();

	Mono<Station> getStationById(String id);

	Flux<Station> searchStationsByKeyword(String keyword);

}
