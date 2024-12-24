package com.ddd.graphql.domain.station.service;

import lombok.Builder;

@Builder
public record Station(String id, String name, String description, String address, Double latitude,
					  Double longitude, Boolean isDeparture) {

}
