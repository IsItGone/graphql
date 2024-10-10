package com.ddd.graphql.domain.station.repository;

import com.ddd.graphql.domain.station.graphql.entity.Station;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StationRepository {

	Mono<Station> findById(String id);

	Flux<Station> findAll();

	Flux<Station> findByKeyword(String keyword);

}
