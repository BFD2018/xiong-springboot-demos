package com.xjt.shiro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TUserFile {
    private String id;

    private String name;
    private String filePath;
    private Date updateTime;
}
