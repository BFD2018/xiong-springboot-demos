package com.xiong.mybatis.controller;

import com.xiong.mybatis.common.responseResult.ResponseResult;
import com.xiong.mybatis.entity.Role;
import com.xiong.mybatis.entity.query.RoleQueryBean;
import com.xiong.mybatis.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/3/31 16:54
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * @return user list
     */
    @GetMapping("list")
    public ResponseResult<List<Role>> list(RoleQueryBean roleQueryBean) {
        List<Role> roleList = roleService.findList(roleQueryBean);
        return ResponseResult.success(roleList);
    }

    @PostMapping("add")
    public ResponseResult<Integer> add(RoleQueryBean roleQueryBean) {
        int ret = roleService.add(roleQueryBean);
        return ret > 0 ? ResponseResult.success():ResponseResult.fail("role add fail.");
    }
}
