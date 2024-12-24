package com.ddd.graphql.domain.route.repository.document;

import com.ddd.graphql.domain.station.repository.document.StationDocumentInfo;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "routes")
@Getter
@Builder
public class RouteDocument {

	@Id
	private String id;
	private String name;
	private List<StationDocumentInfo> departureStations;
	private List<StationDocumentInfo> arrivalStations;
}
