package com.ddd.graphql.domain.station.repository;

import com.ddd.graphql.domain.station.graphql.entity.Station;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface StationReactiveMongoRepository extends ReactiveMongoRepository<Station, String> {

	Flux<Station> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseOrAddressContainsIgnoreCase(
			String keyword, String keyword2, String keyword3);
}
