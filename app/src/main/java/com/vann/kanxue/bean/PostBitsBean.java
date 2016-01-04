package com.vann.kanxue.bean;

import java.io.Serializable;

/**
 * @Author: wenlong.bian 2015-12-28
 * @E-mail: bxl049@163.com
 */
public class PostBitsBean implements Serializable {
    /** 帖子ID */
    private String postid;
    /** 是否含有缩略图，暂时没启有，默认为0*/
    private int thumbnail;
    /** 帖子用户名*/
    private String username;
    /** 帖子用户id*/
    private int userid;
    /**帖子作者是否有头像，0没有头像，1有头像 */
    private int avatar;
    /** 帖子作者头像的时间戳，如果没头像则为0*/
    private String avatardateline  ;
    /** 帖子的时间，格式如：12:13:14*/
    private String posttime ;
    /** 帖子的日期，格式如：2015-12-28*/
    private String postdate ;
    /**  帖子的标题内容*/
    private String title;
    /** 帖子的主体内容*/
    private String message;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getAvatardateline() {
        return avatardateline;
    }

    public void setAvatardateline(String avatardateline) {
        this.avatardateline = avatardateline;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
