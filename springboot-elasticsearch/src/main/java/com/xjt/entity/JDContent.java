package com.xjt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JDContent implements Serializable {
    private static final long serialVersionUID = -8049497962627482693L;
    private String name;
    private String img;
    private String price;
}
