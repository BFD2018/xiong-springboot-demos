package com.xjt.javase.collections.list.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog implements Serializable {
    private String name;
    private Integer age;
    private Job job;
}
