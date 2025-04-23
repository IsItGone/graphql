package com.ddd.graphql.domain.route.service;

import com.ddd.graphql.domain.route.graphql.type.Location;
import com.ddd.graphql.domain.station.service.StationInfo;
import java.util.List;
import lombok.Builder;

@Builder
public record Route(String id, String name, List<StationInfo> departureStations,
					List<StationInfo> arrivalStations, List<Location> departurePath,
					List<Location> arrivalPath) {

}
