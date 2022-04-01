package com.xjt.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:props/person.properties"})
public class MyPeople {
    @Value("${person.name}")
    private String name;

    @Value("${person.sex}")
    private Integer sex;

    @Value("${person.age}")
    private Integer age;

    @Value("${person.city}")
    private String city;

    @Value("${person.school}")
    private String school;

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
