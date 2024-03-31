package com.xiong.mybatis.entity.query;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/3/31 16:53
 */

@Data
@NoArgsConstructor
public class RoleQueryBean {

    /**
     * contains name pattern.
     */
    private String name;

    /**
     * contains desc pattern.
     */
    private String description;

    private String roleKey;
}
