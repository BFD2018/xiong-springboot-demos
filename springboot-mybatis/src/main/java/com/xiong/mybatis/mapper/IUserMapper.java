package com.xiong.mybatis.mapper;

import com.xiong.mybatis.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/3/31 11:35
 */
public interface IUserMapper {

    List<User> findList();

    int saveUser(User user);

    int update(User user);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    User findById(Long id);

    @Results(
            id = "UserResult1",
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "userName", column = "user_name"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "email", column = "email"),
                    @Result(property = "phoneNumber", column = "phone_number"),
                    @Result(property = "description", column = "description"),
                    @Result(property = "createTime", column = "create_time"),
                    @Result(property = "updateTime", column = "update_time")
            }
    )
    @Select("select u.id, u.password, u.user_name, u.email, u.phone_number, u.description, u.create_time, u.update_time from tb_user u where id = #{id}")
    User findUserById(Long id);


    @ResultMap("UserResult1")
    @Select("select u.id, u.password, u.user_name, u.email, u.phone_number, u.description, u.create_time, u.update_time from tb_user u")
    List<User> findAllUser();
}
