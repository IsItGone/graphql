package com.ddd.graphql.domain.station.repository;

import com.ddd.graphql.domain.station.repository.document.StationDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface StationReactiveMongoRepository extends
		ReactiveMongoRepository<StationDocument, String> {

	Flux<StationDocument> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseOrAddressContainsIgnoreCase(
			String keyword, String keyword2, String keyword3);
}
