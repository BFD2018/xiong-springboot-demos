package com.xjt.config;

import com.xjt.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean("food")
    @Conditional(RiceCondition.class)
    Food rice() {
        return new Rice();
    }

    @Bean("food")
    @Conditional(NoodlesCondition.class)
    Food noodles() {
        return new Noodles();
    }
}
