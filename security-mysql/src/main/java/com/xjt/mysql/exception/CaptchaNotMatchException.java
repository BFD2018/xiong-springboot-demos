package com.xjt.mysql.exception;

import javax.naming.AuthenticationException;

//自定义验证码认证异常
public class CaptchaNotMatchException extends AuthenticationException {
    public CaptchaNotMatchException(String explanation) {
        super(explanation);
    }

    public CaptchaNotMatchException() {
        super();
    }
}
