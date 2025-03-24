package com.ddd.graphql.domain.station.service;

import java.util.List;
import lombok.Builder;

@Builder
public record Station(String id, String name, String description, String address, Double latitude,
					  Double longitude, Boolean isDeparture, List<String> routes) {

}
