package com.ddd.graphql.domain.station.repository.document;

import lombok.Builder;

@Builder
public record StationDocumentInfo(StationDocument stationDocument, String stopTime) {

}
