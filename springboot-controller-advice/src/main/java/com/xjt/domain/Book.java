package com.xjt.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private String name;
    private Long price;
}
