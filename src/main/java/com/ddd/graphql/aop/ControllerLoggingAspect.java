package com.ddd.graphql.aop;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerLoggingAspect {

	@Before("@annotation(org.springframework.graphql.data.method.annotation.QueryMapping)")
	public void logBeforeQueryMappingMethods(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		log.info("GraphQL query invoked: [{}], with arguments: {}", method, Arrays.toString(args));
	}
}