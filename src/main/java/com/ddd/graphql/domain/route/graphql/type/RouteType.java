package com.ddd.graphql.domain.route.graphql.type;

import com.ddd.graphql.domain.station.graphql.type.StationType;
import java.util.List;
import lombok.Builder;

@Builder
public record RouteType(String id, String name, List<StationType> departureStations,
						List<StationType> arrivalStations) {

}

