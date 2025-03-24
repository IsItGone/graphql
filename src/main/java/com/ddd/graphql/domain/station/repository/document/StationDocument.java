package com.ddd.graphql.domain.station.repository.document;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stations")
@Getter
@Builder
public class StationDocument {

	@Id
	private String id;
	private String name;
	private String description;
	private String address;
	private Double latitude;
	private Double longitude;
	private Boolean isDeparture;
	private List<String> routes;
}
