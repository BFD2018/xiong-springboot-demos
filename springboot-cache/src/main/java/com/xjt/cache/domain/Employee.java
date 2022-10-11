package com.xjt.cache.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable{
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender; //性别 1男 0女
    private Integer dId;
}
