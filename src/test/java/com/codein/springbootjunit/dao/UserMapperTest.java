package com.codein.springbootjunit.dao;

import com.codein.springbootjunit.configuration.MapperScanConfiguration;
import com.codein.springbootjunit.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lxh on 2018/3/29.
 */
@RunWith(SpringRunner.class)
/**
 *  这里指定的classes是可选的。如果不指定classes，则spring boot会启动整个spring容器，很慢（比如说会执行一些初始化，
 *  ApplicationRunner、CommandLineRunner等等）。不推荐
 *  指定的话，就只会初始化指定的bean，速度快，推荐
 */
@SpringBootTest(classes={DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class, MapperScanConfiguration.class})
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() throws Exception {

        int count = 0;
        User user = new User(null, "xhuanlee", "wowwow", 1);
        count = userMapper.insert(user);
        assertEquals(1, count);
        System.out.printf("%s", user);

    }

    @Test
    public void deleteById() throws Exception {

        int count = 0;
        User user = new User(null, "will_be_deleted", "delete_pwd", 0);
        userMapper.insert(user);
        System.out.println(user);
        count = userMapper.deleteById(user.getId());
        assertEquals(1, count);

    }

    @Test
    public void update() throws Exception {

        User user = new User(null, "eric", "ericpwd", 0);
        userMapper.insert(user);
        user.setUsername("eric_new");
        userMapper.update(user);
        user = userMapper.queryById(user.getId());
        assertEquals("eric_new", user.getUsername());

    }

    @Test
    public void queryById() throws Exception {

        User user = new User(null, "alex", "alex_pwd", 1);
        userMapper.insert(user);
        User dbUser = userMapper.queryById(user.getId());

        assertEquals("alex", dbUser.getUsername());
        assertEquals("alex_pwd", dbUser.getPassword());
        assertEquals(new Integer(1), dbUser.getStatus());

    }

    @Test
    public void queryList() throws Exception {

        List<User> list = userMapper.queryList();
        assertNotNull(list);
        assertTrue(list.size() > 0);

    }

}