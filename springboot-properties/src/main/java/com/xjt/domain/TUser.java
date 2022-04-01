package com.xjt.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class TUser {
    @Value("${xjt.name}")
    private String name;

    @Value("${xjt.age}")
    private Integer age;

    @Value("${xjt.city}")
    private String city;
}
