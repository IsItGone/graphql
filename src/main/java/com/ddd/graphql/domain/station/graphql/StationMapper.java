package com.ddd.graphql.domain.station.graphql;

import com.ddd.graphql.domain.station.graphql.entity.Station;
import com.ddd.graphql.domain.station.graphql.type.StationType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StationMapper {

	StationType toStationType(Station station);
}
