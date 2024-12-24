package com.ddd.graphql.domain.station.service;

import com.ddd.graphql.domain.station.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class StationQueryServiceImpl implements StationQueryService {

	private final StationRepository stationRepository;

	@Override
	public Flux<Station> getStations() {
		return stationRepository.findAll();
	}

	@Override
	public Mono<Station> getStationById(String id) {
		return stationRepository.findById(id);
	}

	@Override
	public Flux<Station> searchStationsByKeyword(String keyword) {
		return stationRepository.findByKeyword(keyword);
	}

}
