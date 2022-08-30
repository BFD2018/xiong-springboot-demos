package com.xjt.authority.entity;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class TMenu {
    private Integer id;
    private String pattern;

    private List<TRole> roleList;
}
