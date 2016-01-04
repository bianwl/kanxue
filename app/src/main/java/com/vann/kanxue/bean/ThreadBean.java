package com.vann.kanxue.bean;

import java.io.Serializable;

/** 主题帖实体
 * @Author: wenlong.bian 2015-12-22
 * @E-mail: bxl049@163.com
 */
public class ThreadBean implements Serializable {

    /** 帖子标题 */
    private String threadtitle;
    /** 帖子id*/
    private int threadid;
    /** 帖子作者 */
    private String postusername;
    /** 帖子作者id */
    private int postuserid;
    /** 作者是否有头像 0，没有；1，有*/
    private int avatar;
    /**作者上传头像时间戳  */
    private String avatardateline;
    /** 帖子被回复时间*/
    private String lastpostdate;
    /** 帖子查看数 */
    private int views;
    /** 帖子被回复数 */
    private int replycount;
    /**是否全局置顶，是为-1，否为0*/
    private int globalsticky;
    /** 是否置顶，是为1，否为0*/
    private int sticky;
    /**是否为精华帖 0：表示不是精华帖
     1：转帖
     2：关注
     10：优秀
     40：精华
     80：酷帖*/
    private int goodnees;

    private int open;

    public String getThreadtitle() {
        return threadtitle;
    }

    public void setThreadtitle(String threadtitle) {
        this.threadtitle = threadtitle;
    }

    public int getThreadid() {
        return threadid;
    }

    public void setThreadid(int threadid) {
        this.threadid = threadid;
    }

    public String getPostusername() {
        return postusername;
    }

    public void setPostusername(String postusername) {
        this.postusername = postusername;
    }

    public int getPostuserid() {
        return postuserid;
    }

    public void setPostuserid(int postuserid) {
        this.postuserid = postuserid;
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

    public String getLastpostdate() {
        return lastpostdate;
    }

    public void setLastpostdate(String lastpostdate) {
        this.lastpostdate = lastpostdate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getReplycount() {
        return replycount;
    }

    public void setReplycount(int replycount) {
        this.replycount = replycount;
    }

    public int getGlobalsticky() {
        return globalsticky;
    }

    public void setGlobalsticky(int globalsticky) {
        this.globalsticky = globalsticky;
    }

    public int getSticky() {
        return sticky;
    }

    public void setSticky(int sticky) {
        this.sticky = sticky;
    }

    public int getGoodnees() {
        return goodnees;
    }

    public void setGoodnees(int goodnees) {
        this.goodnees = goodnees;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }
}
