package com.xjt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TStudent {
    private Integer id;
    private String name;
    private Integer age;
    private String phone;
    private Date birthday;
}
