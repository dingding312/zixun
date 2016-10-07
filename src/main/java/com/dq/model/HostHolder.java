package com.dq.model;

import org.springframework.stereotype.Component;

/**
 * Created by DQ on 2016/8/21.
 * HostHolder这个类就是表示当前的用户是谁
 */
@Component
public class HostHolder {
    //线程局部变量，因为服务器不是一个用户在用，可能有好几个用户在同时访问，当这个类只有一个component
    // 当很多用户访问时，采用ThreadLocal存每个用户自己的东西，保证set 和 get 只对本用户内有效
    //解决多用户访问的并发问题

    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser(){
        return users.get();
    }

    public void setUser(User user){
        users.set(user);
    }

    public void clear(){
        users.remove();
    }
}
