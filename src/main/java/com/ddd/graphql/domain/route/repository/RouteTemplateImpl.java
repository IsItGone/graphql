package com.ddd.graphql.domain.route.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.aggregation.LookupOperation.newLookup;

import com.ddd.graphql.domain.route.repository.document.RouteDocument;
import java.util.Collections;
import java.util.LinkedList;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class RouteTemplateImpl implements RouteTemplate {

	private static final String STATION_COLLECTION_NAME = "stations";
	private static final String DEPARTURE_STATION_LIST_NAME = "departureStations";
	private static final String ARRIVAL_STATION_LIST_NAME = "arrivalStations";
	private static final String STATION_DOCUMENT_NAME = "stationDocument";
	private static final String DEPARTURE_PATH_LIST_NAME = "departurePath";
	private static final String ARRIVAL_PATH_LIST_NAME = "arrivalPath";
	private final ReactiveMongoTemplate mongoTemplate;

	private static LinkedList<AggregationOperation> getStationPopulationOperations() {
		LinkedList<AggregationOperation> aggregationOperations = new LinkedList<>();

		UnwindOperation departureStationsUnwindOperation = unwind(DEPARTURE_STATION_LIST_NAME,
				true);
		LookupOperation departureLookupOperation = newLookup().from(STATION_COLLECTION_NAME)
				.localField(DEPARTURE_STATION_LIST_NAME + "." + "stationId").foreignField("_id")
				.as(DEPARTURE_STATION_LIST_NAME + "." + STATION_DOCUMENT_NAME);
		UnwindOperation departureStationsDocUnwindOperation = unwind(
				DEPARTURE_STATION_LIST_NAME + "." + STATION_DOCUMENT_NAME, true);

		GroupOperation departureGroupOperation = group("_id").first("name").as("name")
				.push(DEPARTURE_STATION_LIST_NAME).as(DEPARTURE_STATION_LIST_NAME)
				.first(ARRIVAL_STATION_LIST_NAME).as(ARRIVAL_STATION_LIST_NAME)
				.first(DEPARTURE_PATH_LIST_NAME).as(DEPARTURE_PATH_LIST_NAME)
				.first(ARRIVAL_PATH_LIST_NAME).as(ARRIVAL_PATH_LIST_NAME);

		UnwindOperation arrivalStationsUnwindOperation = unwind(ARRIVAL_STATION_LIST_NAME, true);
		LookupOperation arrivalLookupOperation = newLookup().from(STATION_COLLECTION_NAME)
				.localField(ARRIVAL_STATION_LIST_NAME + ".stationId").foreignField("_id")
				.as(ARRIVAL_STATION_LIST_NAME + "." + STATION_DOCUMENT_NAME);
		UnwindOperation arrivalStationsDocUnwindOperation = unwind(
				ARRIVAL_STATION_LIST_NAME + "." + STATION_DOCUMENT_NAME, true);

		GroupOperation arrivalGroupOperation = group("_id").first("name").as("name")
				.first(DEPARTURE_STATION_LIST_NAME).as(DEPARTURE_STATION_LIST_NAME)
				.push(ARRIVAL_STATION_LIST_NAME).as(ARRIVAL_STATION_LIST_NAME)
				.first(DEPARTURE_PATH_LIST_NAME).as(DEPARTURE_PATH_LIST_NAME)
				.first(ARRIVAL_PATH_LIST_NAME).as(ARRIVAL_PATH_LIST_NAME);

		SortOperation sortOperation = sort(Sort.by(Sort.Direction.ASC, "name"));

		Collections.addAll(aggregationOperations, departureStationsUnwindOperation,
				departureLookupOperation, departureStationsDocUnwindOperation,
				departureGroupOperation, arrivalStationsUnwindOperation, arrivalLookupOperation,
				arrivalStationsDocUnwindOperation, arrivalGroupOperation, sortOperation);

		return aggregationOperations;
	}

	@Override
	public Mono<RouteDocument> findById(String id) {
		MatchOperation matchOperation = match(Criteria.where("_id").is(new ObjectId(id)));

		LinkedList<AggregationOperation> aggregationOperations = getStationPopulationOperations();
		aggregationOperations.addFirst(matchOperation);

		TypedAggregation<RouteDocument> aggregation = newAggregation(RouteDocument.class,
				aggregationOperations);

		return Mono.from(mongoTemplate.aggregate(aggregation, RouteDocument.class));
	}

	@Override
	public Flux<RouteDocument> findAll() {
		TypedAggregation<RouteDocument> aggregation = newAggregation(RouteDocument.class,
				getStationPopulationOperations());

		return mongoTemplate.aggregate(aggregation, RouteDocument.class);
	}

	@Override
	public Mono<RouteDocument> findByName(String name) {
		MatchOperation matchOperation = match(Criteria.where("name").is(name));

		LinkedList<AggregationOperation> aggregationOperations = getStationPopulationOperations();
		aggregationOperations.addFirst(matchOperation);

		TypedAggregation<RouteDocument> aggregation = newAggregation(RouteDocument.class,
				aggregationOperations);

		return Mono.from(mongoTemplate.aggregate(aggregation, RouteDocument.class));
	}

	@Override
	public Flux<RouteDocument> findByStationId(String stationId) {
		MatchOperation matchOperation = match(
				Criteria.where(DEPARTURE_STATION_LIST_NAME + ".station._id").is(stationId)
						.orOperator(Criteria.where(ARRIVAL_STATION_LIST_NAME + ".station._id")
								.is(stationId)));

		LinkedList<AggregationOperation> aggregationOperations = getStationPopulationOperations();
		aggregationOperations.addFirst(matchOperation);

		TypedAggregation<RouteDocument> aggregation = newAggregation(RouteDocument.class,
				aggregationOperations);

		return Flux.from(mongoTemplate.aggregate(aggregation, RouteDocument.class));
	}
}