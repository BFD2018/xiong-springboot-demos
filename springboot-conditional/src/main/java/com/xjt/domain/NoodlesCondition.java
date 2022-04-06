package com.xjt.domain;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class NoodlesCondition implements Condition{

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        System.out.println("==============>");
        System.out.println(conditionContext);
        System.out.println(annotatedTypeMetadata);
        return conditionContext.getEnvironment().getProperty("people").equals("北方人");
    }
}
