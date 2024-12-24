package com.ddd.graphql.domain.route.graphql;

import com.ddd.graphql.domain.route.graphql.type.RouteType;
import com.ddd.graphql.domain.route.repository.document.RouteDocument;
import com.ddd.graphql.domain.route.service.Route;
import com.ddd.graphql.domain.station.graphql.StationMapper;
import com.ddd.graphql.domain.station.graphql.type.StationType;
import com.ddd.graphql.domain.station.repository.document.StationDocumentInfo;
import com.ddd.graphql.domain.station.service.StationInfo;
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

	Route toRoute(RouteDocument routeDocument);

	RouteType toRouteType(Route route);

	@Mapping(source = "stationDocument", target = "station")
	StationInfo stationDocumentInfoToStationInfo(StationDocumentInfo stationDocumentInfo);

	default List<StationType> toStationTypes(List<StationInfo> stationInfos) {
		return Optional.ofNullable(stationInfos).orElse(Collections.emptyList()).stream()
				.map(this::toStationType).filter(Objects::nonNull).sorted().toList();
	}


	default StationType toStationType(StationInfo stationInfo) {
		return Optional.ofNullable(stationInfo.station()).map(__ -> toStationTypeImpl(stationInfo))
				.orElse(null);
	}

	@Mapping(source = "station.", target = ".")
	StationType toStationTypeImpl(StationInfo stationInfo);

}
