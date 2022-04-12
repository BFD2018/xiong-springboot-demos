package com.xjt.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Author implements Serializable {
    private String name;
    private Integer age;
}
