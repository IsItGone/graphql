package com.ddd.graphql.domain.route.graphql.entity;

import com.ddd.graphql.domain.station.graphql.entity.Station;
import lombok.Builder;

@Builder
public record StationInfo(Station station, String stopTime) {

}
