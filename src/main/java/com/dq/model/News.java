package com.dq.model;

import java.util.Date;

/**
 * Created by DQ on 2016/8/20.
 *
 * 新闻的字段有：id,title（标题），link(连接），image（新闻的首图），like_count(点赞数)，
 * comment_count(评论数）,user_id(新闻是谁发的）,create_date(新闻的创建时间）
 * 新闻的字段可根据需要自行添加
 */
public class News {
    private int id;
    private String title;
    private String link;
    private String image;
    private int likeCount;
    private int commentCount;
    private Date createdDate;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}


