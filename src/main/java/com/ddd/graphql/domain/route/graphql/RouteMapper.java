package com.ddd.graphql.domain.route.graphql;

import com.ddd.graphql.domain.route.graphql.entity.Route;
import com.ddd.graphql.domain.route.graphql.entity.StationInfo;
import com.ddd.graphql.domain.route.graphql.type.RouteType;
import com.ddd.graphql.domain.station.graphql.StationMapper;
import com.ddd.graphql.domain.station.graphql.type.StationType;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
		StationMapper.class})
public interface RouteMapper {

	RouteType toRouteType(Route route);

	default List<StationType> toStationTypes(List<StationInfo> stations) {
		return Optional.ofNullable(stations).orElse(Collections.emptyList()).stream()
				.map(this::toStationType).filter(Objects::nonNull).sorted().toList();
	}

	default StationType toStationType(StationInfo stationInfo) {
		return Optional.ofNullable(stationInfo.station())
				.map(__ -> toStationTypeImpl(stationInfo))
				.orElse(null);
	}

	@Mapping(source = "station.", target = ".")
	StationType toStationTypeImpl(StationInfo stationInfo);

}
