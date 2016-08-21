package com.dq.service;

import com.dq.dao.UserDAO;
import com.dq.model.User;
import com.dq.util.ZixunUtil;
import org.apache.commons.collections.iterators.ObjectArrayIterator;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by DQ on 2016/8/17.
 */
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public User getUser(int id){
        return userDAO.selectById(id);
    }

    //注册用户，即在user表里新增用户
    //检测用户名的合法性,把检测结果放在map中返回

    public Map<String, Object> register(String username, String password){
        Map<String, Object> map = new HashMap<String, Object>();
        //检测用户名是否为空，长度是否满足要求，是否含有除数字外的特殊字符，是否已经被注册过了
        //判断用户名是否为空
        if(StringUtils.isBlank(username)){
            map.put("msgusername", "用户名不能为空");
            return map;
        }

        //用户名不能超过6-12个字符
        if(username.length() < 6){
            map.put("msgusername", "用户名不能小于6位");
            return map;
        }

        if(username.length() > 15){
            map.put("msgusername", "用户名不能大于15位");
            return map;
        }

        //用户名是否包含特殊字符,用户名只能包含数字和字母
        if(!username.matches("^[A-Za-z0-9]+$")) {
            map.put("msgusername", "用户名不能包含特殊字符");
            return map;
        }

        //判断用户名是否已经本注册过
        User user = userDAO.selectByName(username);
        if(user != null){
            map.put("msgusername", "用户名已经被注册");
            return map;
        }

        //判断密码是否符合要求
        //判断密码是否为空
        if(StringUtils.isBlank(password)){
            map.put("magpassword", "密码不能为空");
            return map;
        }

        //密码是否为纯数字或者纯字母
        if(password.matches("^[A-Za-z]+$") || password.matches("[0-9]+$")){
            map.put("msgpassword", "密码不能为纯数字或纯字母");
            return map;
        }

        //密码长度不能小于6位
        if(password.length() < 6){
            map.put("msgpassword", "密码长度不能小于6位");
            return map;
        }
        //密码的长度不能大于15位
        if(password.length() > 15){
            map.put("msgpassword", "密码长度不能大于15位");
            return map;
        }

        //用户名和密码符合要求后，添加用户到数据库，即注册成功
        user = new User();
        user.setName(username);
        //盐加密加强密码
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setPassword(ZixunUtil.MD5(password + user.getSalt()));
        user.setHeadUrl("");
        userDAO.addUser(user);
        return map;
    }

}
