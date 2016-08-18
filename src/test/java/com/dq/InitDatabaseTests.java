package com.dq;

;import com.dq.dao.UserDAO;
import com.dq.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Created by DQ on 2016/8/17.
 * 用户表测试用例，检测数据能否传到本地数据库
 *
 * @RunWith(SpringJUnit4ClassRunner.class)：让测试运行于Spring测试环境
 * @SpringApplicationConfiguration与@ContextConfiguration：用来加载Spring的配置。在使用SpringBoot这种微框架的时候即通过@SpringApplicationConfiguration
 * 来标识启动类，而对于存在Spring的配置文件的项目则使用@ContextConfiguration注解来指定配置文件。
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZixunApplication.class)
@Sql("/init-schema.sql")
public class InitDatabaseTests {
    @Autowired
    UserDAO userDAO;

    @Test
    public void initData(){
        //新增一个用户，设置名字、密码等字段
        User user = new User();
        user.setName("dq");
        user.setPassword("123456");
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setHeadUrl("http://images.nowcoder.com/head/%dt.png");
        userDAO.addUser(user);

    }
}