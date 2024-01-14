package com.xjt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjt.entity.User;
import com.xjt.mapper.UserMapper;
import com.xjt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final static String CACHE_KEY_USER = "compose-user:";

    @Autowired
    private RedisTemplate myRedisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        //1.先从redis中查
        User user = null;
        String key = CACHE_KEY_USER + id;

        //1 先从redis里面查询，如果有直接返回结果，如果没有再去查询mysql
        user = (User) myRedisTemplate.opsForValue().get(key);

        if (user == null) {
            //2 redis里面无，继续查询mysql
            user = userMapper.selectById(id);
            if (user == null) {
                //3.1 redis+mysql 都无数据
                //你具体细化，防止多次穿透，我们规定，记录下导致穿透的这个key回写redis
                return user;
            } else {
                //3.2 mysql有，需要将数据写回redis，保证下一次的缓存命中率
                myRedisTemplate.opsForValue().set(key, user);
            }
        }
        return user;
    }

    @Override
    public int insertUser(User user) {
        //1 先插入mysql并成功
        int i = userMapper.insert(user);

        if (i > 0) {
            //2 需要再次查询一下mysql将数据捞回来并ok
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", user.getUsername());
            wrapper.eq("password", user.getPassword());
            user = userMapper.selectOne(wrapper);
            //3 将捞出来的user存进redis，完成新增功能的数据一致性。
            String key = CACHE_KEY_USER + user.getId();
            myRedisTemplate.opsForValue().set(key, user);

            return i;
        }

        return 0;
    }
}
