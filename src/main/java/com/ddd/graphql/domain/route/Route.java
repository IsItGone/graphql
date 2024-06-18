package com.ddd.graphql.domain.route;

import com.ddd.graphql.domain.station.Station;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "routes")
@Getter
@Builder
public class Route {

    @Id
    private String id;
    private String name;
    private List<Station> stations;
}
