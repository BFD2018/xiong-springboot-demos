package com.xjt.service.impl;

import com.alibaba.fastjson.JSON;
import com.xjt.domain.RespBean;
import com.xjt.domain.TStudent;
import com.xjt.mapper.StudentMapper;
import com.xjt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean getAllStudent() {
        List<TStudent> studentList = null;
        Object arr = redisTemplate.opsForValue().get("studentList");
        System.out.println(arr);

        if (ObjectUtils.isEmpty(arr) || arr == null || arr == "null") {
            System.out.println("===============>查询数据库");
            studentList = studentMapper.selectList(null);
            redisTemplate.opsForValue().set("studentList", JSON.toJSONString(studentList), 15, TimeUnit.SECONDS);

            return RespBean.ok("ok", studentList);
        }

        System.out.println("===============>从redis缓存中取值");
        return RespBean.ok("ok", JSON.parse((String) arr));
    }
}
