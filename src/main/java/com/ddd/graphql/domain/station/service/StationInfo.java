package com.ddd.graphql.domain.station.service;

import lombok.Builder;

@Builder
public record StationInfo(Station station, String stopTime) {

}
