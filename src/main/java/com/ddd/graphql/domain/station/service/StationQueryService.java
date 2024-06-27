package com.ddd.graphql.domain.station.service;

import com.ddd.graphql.domain.station.graphql.type.StationType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StationQueryService {

	Flux<StationType> getStations();

	Mono<StationType> getStationById(String id);

	Flux<StationType> searchStationsByKeyword(String keyword);

}
