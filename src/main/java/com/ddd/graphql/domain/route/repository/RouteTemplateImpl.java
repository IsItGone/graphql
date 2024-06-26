package com.ddd.graphql.domain.route.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;
import static org.springframework.data.mongodb.core.aggregation.LookupOperation.newLookup;

import com.ddd.graphql.domain.route.graphql.entity.Route;
import java.util.Collections;
import java.util.LinkedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Repository
public class RouteTemplateImpl implements RouteTemplate {

	private final ReactiveMongoTemplate mongoTemplate;

	private static LinkedList<AggregationOperation> getStationPopulationOperations() {
		LinkedList<AggregationOperation> aggregationOperations = new LinkedList<>();

		LookupOperation lookupOperation = newLookup()
				.from("stations")
				.localField("stations")
				.foreignField("_id")
				.as("stationsDetails");

		UnwindOperation unwindOperation = unwind("stationsDetails", true);

		GroupOperation groupOperation = group("_id")
				.first("name").as("name")
				.push("stationsDetails").as("stations");

		aggregationOperations.add(lookupOperation);
		aggregationOperations.add(unwindOperation);
		aggregationOperations.add(groupOperation);

		return aggregationOperations;
	}

	@Override
	public Mono<Route> findById(String id) {
		MatchOperation matchOperation = match(Criteria.where("_id").is(new ObjectId(id)));

		LinkedList<AggregationOperation> aggregationOperations = getStationPopulationOperations();
		aggregationOperations.addFirst(matchOperation);

		TypedAggregation<Route> aggregation = newAggregation(Route.class, aggregationOperations);

		return Mono.from(mongoTemplate.aggregate(aggregation, Route.class));
	}

	@Override
	public Flux<Route> findAll() {
		TypedAggregation<Route> aggregation = newAggregation(Route.class,
				getStationPopulationOperations());

		return mongoTemplate.aggregate(aggregation, Route.class);
	}

	@Override
	public Mono<Route> findByName(String name) {
		MatchOperation matchOperation = match(Criteria.where("name").is(name));

		LinkedList<AggregationOperation> aggregationOperations = getStationPopulationOperations();
		aggregationOperations.addFirst(matchOperation);

		TypedAggregation<Route> aggregation = newAggregation(Route.class, aggregationOperations);

		return Mono.from(mongoTemplate.aggregate(aggregation, Route.class));
	}

}