package com.vann.kanxue.bean;

import java.io.Serializable;

/**
 * @Author: wenlong.bian 2015-12-14
 * @E-mail: bxl049@163.com
 */
public class User implements Serializable {
    /**用户名称*/
    private String username;
    /**用户id*/
    private int userid;
    /**用户头衔*/
    private String usertitle;
    /**发帖数*/
    private int posts;
    /**用户浏览语音*/
    private int languageid;
    /**用户金钱数*/
    private int money;
    /**用户精华数*/
    private int goodness;
    /**是否有无头像，0：无头像，1：有头像*/
    private int avatar;
    /**头像上传日期*/
    private int avatardateline;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertitle() {
        return usertitle;
    }

    public void setUsertitle(String usertitle) {
        this.usertitle = usertitle;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    public int getLanguageid() {
        return languageid;
    }

    public void setLanguageid(int languageid) {
        this.languageid = languageid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getGoodness() {
        return goodness;
    }

    public void setGoodness(int goodness) {
        this.goodness = goodness;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getAvatardateline() {
        return avatardateline;
    }

    public void setAvatardateline(int avatardateline) {
        this.avatardateline = avatardateline;
    }

    @Override
    public String toString() {
        return "用户名："+username+",用户id:"+userid;
    }
}
