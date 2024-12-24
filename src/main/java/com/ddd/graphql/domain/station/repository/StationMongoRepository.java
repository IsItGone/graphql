package com.ddd.graphql.domain.station.repository;

import com.ddd.graphql.domain.station.graphql.StationMapper;
import com.ddd.graphql.domain.station.service.Station;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Repository
public class StationMongoRepository implements StationRepository {

	private final StationReactiveMongoRepository stationReactiveMongoRepository;
	private final StationMapper stationMapper;

	@Override
	public Mono<Station> findById(String id) {
		return stationReactiveMongoRepository.findById(id).map(stationMapper::toStation);
	}

	@Override
	public Flux<Station> findAll() {
		return stationReactiveMongoRepository.findAll().map(stationMapper::toStation);
	}

	@Override
	public Flux<Station> findByKeyword(String keyword) {
		return stationReactiveMongoRepository.findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseOrAddressContainsIgnoreCase(
				keyword, keyword, keyword).map(stationMapper::toStation);
	}
}
