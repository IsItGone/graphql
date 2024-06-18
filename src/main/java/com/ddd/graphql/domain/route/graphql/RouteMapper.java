package com.ddd.graphql.domain.route.graphql;

import com.ddd.graphql.domain.route.Route;
import com.ddd.graphql.domain.route.graphql.type.RouteType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RouteMapper {

	RouteType toRouteType(Route route);

}
