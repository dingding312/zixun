package com.dq.model;

import java.util.Date;

/**
 * Created by DQ on 2016/8/21.
 * 增加一个登陆状态表，包括字段id号, 用户id，登陆过期时间，登录状态是有效还是无效，下发的ticket
 */
public class LoginTicket {
    private int id;
    private int userId;
    private Date expired;
    private int status;   //0有效，1无效
    private String ticket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
