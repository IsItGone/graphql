package com.ddd.graphql.domain.station.graphql.type;

import java.util.List;
import java.util.Optional;
import lombok.Builder;

@Builder
public record StationType(String id, String name, String description, String address,
						  Double latitude, Double longitude, String stopTime,
						  Boolean isDeparture, List<String> routes) implements
		Comparable<StationType> {

	@Override
	public int compareTo(StationType stationType) {
		String stopTimeA = Optional.ofNullable(this.stopTime).orElse("");
		String stopTimeB = Optional.ofNullable(stationType.stopTime).orElse("");
		return stopTimeA.compareTo(stopTimeB);
	}
}
