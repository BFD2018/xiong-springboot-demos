package com.xjt.exception.service;

import com.xjt.exception.utils.RespBean;

public interface TUserService {
    RespBean toLogin(String username, String password);
}
