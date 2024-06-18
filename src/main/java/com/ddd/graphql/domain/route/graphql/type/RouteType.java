package com.ddd.graphql.domain.route.graphql.type;

import com.ddd.graphql.domain.station.Station;
import java.util.List;
import lombok.Builder;

@Builder
public record RouteType(String id, String name, List<Station> stations) {

}

