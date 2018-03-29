package com.codein.springbootjunit.dao;

import com.codein.springbootjunit.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lxh on 2018/3/29.
 */
@Repository
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into users (username, password, status) values (#{username}, #{password}, #{status})")
    int insert(User user);

    @Delete("delete from users where id = #{0}")
    int deleteById(int id);

    @Update("update users set username = #{username}, password = #{password}, status = #{status}")
    int update(User user);

    @Select("select * from users where id = #{0}")
    User queryById(int id);

    @Select("select * from users;")
    List<User> queryList();
}
