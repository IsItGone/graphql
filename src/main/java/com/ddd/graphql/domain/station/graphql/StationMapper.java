package com.ddd.graphql.domain.station.graphql;

import com.ddd.graphql.domain.station.graphql.type.StationType;
import com.ddd.graphql.domain.station.repository.document.StationDocument;
import com.ddd.graphql.domain.station.service.Station;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StationMapper {

	Station toStation(StationDocument stationDocument);

	StationType toStationType(Station station);
}
