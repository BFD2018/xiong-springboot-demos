package com.xiong.mybatis.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/3/31 11:22
 */
@Data
public class Role implements BaseEntity {

    /**
     * role id.
     */
    private Long id;

    /**
     * role name.
     */
    private String name;

    /**
     * role key.
     */
    private String roleKey;

    /**
     * description.
     */
    private String description;

    /**
     * create date time.
     */
    private LocalDateTime createTime;

    /**
     * update date time.
     */
    private LocalDateTime updateTime;

}

