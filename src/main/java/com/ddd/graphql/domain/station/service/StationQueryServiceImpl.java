package com.ddd.graphql.domain.station.service;

import com.ddd.graphql.domain.station.graphql.StationMapper;
import com.ddd.graphql.domain.station.graphql.type.StationType;
import com.ddd.graphql.domain.station.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class StationQueryServiceImpl implements StationQueryService {

	private final StationRepository stationRepository;
	private final StationMapper stationMapper;

	@Override
	public Flux<StationType> getStations() {
		return stationRepository.findAll().map(stationMapper::toStationType);
	}

	@Override
	public Mono<StationType> getStationById(String id) {
		return stationRepository.findById(id).map(stationMapper::toStationType);
	}

	@Override
	public Flux<StationType> searchStationsByKeyword(String keyword) {
		return stationRepository.findByKeyword(keyword).map(stationMapper::toStationType);
	}

}
