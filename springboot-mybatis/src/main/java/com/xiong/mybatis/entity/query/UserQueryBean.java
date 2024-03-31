package com.xiong.mybatis.entity.query;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/3/31 11:41
 */
@Data
@NoArgsConstructor
public class UserQueryBean {
    /**
     * contains name pattern.
     */
    private String userName;

    /**
     * contains desc pattern.
     */
    private String description;

    private String phoneNumber;

    private String email;
}
