package com.xjt.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class PersonProperties {
    @Value("${person.name}")
    private String name;

    @Value("${person.age}")
    private String age;
}
