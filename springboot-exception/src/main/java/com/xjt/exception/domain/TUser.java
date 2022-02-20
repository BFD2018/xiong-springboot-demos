package com.xjt.exception.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class TUser implements Serializable {
    private Integer id;
    private String username;
    private String password;
}
