package com.xjt.shiro.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private Integer status = 0;
    private String msg;
    private Object obj;

    public static com.xjt.myshiro.utils.RespBean build(){
        return new com.xjt.myshiro.utils.RespBean();
    }

    public static com.xjt.myshiro.utils.RespBean success(String msg){
        return new com.xjt.myshiro.utils.RespBean(200,msg,null);
    }

    public static com.xjt.myshiro.utils.RespBean success(String msg, Object obj){
        return new com.xjt.myshiro.utils.RespBean(200,msg,obj);
    }

    public static com.xjt.myshiro.utils.RespBean success(Object obj){
        return new com.xjt.myshiro.utils.RespBean(200,"ok",obj);
    }

    public static com.xjt.myshiro.utils.RespBean warn(String msg){
        return new com.xjt.myshiro.utils.RespBean(204,msg,null);
    }

    public static com.xjt.myshiro.utils.RespBean warn(String msg, Object obj){
        return new com.xjt.myshiro.utils.RespBean(204,msg,obj);
    }

    public static com.xjt.myshiro.utils.RespBean error(String msg){
        return new com.xjt.myshiro.utils.RespBean(500,msg,null);
    }

    public static com.xjt.myshiro.utils.RespBean error(String msg, Object obj){
        return new com.xjt.myshiro.utils.RespBean(500,msg,obj);
    }
}
