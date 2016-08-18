package com.dq.model;

/**
 * Created by DQ on 2016/8/17.
 * 定义用户表的实体类,放在model层
 */
public class User {
    private int id;
    private String name;
    private String password;
    private String salt;         //盐加密，加强密码
    private String headUrl;     //用户头像，驼峰表示法，对应数据库中head_url

    //空构造函数
    public User(){

    }

    //带参数的构造函数，参数是用户名
    public User(String name){
        this.name = name;
        this.password = "";
        this.salt = "";
        this.headUrl = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
