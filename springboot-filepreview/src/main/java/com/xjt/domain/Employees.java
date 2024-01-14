package com.xjt.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/1/14 15:47
 */
@Data
@TableName("employees")
public class Employees {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private Double salary;
    private Double commissionPct;
    private int managerId;
    private int departmentId;

}
