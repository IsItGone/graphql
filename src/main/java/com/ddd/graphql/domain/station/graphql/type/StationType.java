package com.ddd.graphql.domain.station.graphql.type;

import lombok.Builder;

@Builder
public record StationType(String id, String name, String description, String address,
						  Double latitude, Double longitude, String stopTime,
						  Boolean isDeparture) implements Comparable<StationType> {

	@Override
	public int compareTo(StationType stationType) {
		return stopTime.compareTo(stationType.stopTime());
	}
}
