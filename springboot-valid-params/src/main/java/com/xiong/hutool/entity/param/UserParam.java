package com.xiong.hutool.entity.param;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/2/28 21:01
 */
@Data
@Builder
public class UserParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "could not be empty")
    private Long id;

    @NotNull(message = "could not be empty")
    private String username;

    @NotNull(message = "could not be empty")
    private String password;

    @Range(min = 0, max = 1, message = "sex should be 0-1")
    private int sex;

    @Range(min = 0, max = 1, message = "deleted should be 0-1")
    private int deleted;

    private Date createTime;
    private Date updateTime;
}
