package com.ddd.graphql.domain.station.repository;

import com.ddd.graphql.domain.station.graphql.entity.Station;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface StationRepository extends ReactiveMongoRepository<Station, String>,
		StationTemplate {

	Flux<Station> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseOrAddressContainsIgnoreCase(
			String keyword, String keyword2, String keyword3);

	default Flux<Station> findByKeyword(String keyword) {
		return findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseOrAddressContainsIgnoreCase(
				keyword, keyword, keyword);
	}
}
