package com.xjt.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "person")
@Component
@Data
public class Person {
    @NotNull
    private String name;

    private Integer age;
}
