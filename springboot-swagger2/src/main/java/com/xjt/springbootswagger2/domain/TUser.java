package com.xjt.springbootswagger2.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TUser {
    private Integer id;
    private String name;
    private Integer age;
    private Boolean sex;
}
