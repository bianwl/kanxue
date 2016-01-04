package com.vann.kanxue.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wenlong.bian 2015-12-17
 * @E-mail: bxl049@163.com
 */
public class ForumBean implements Serializable {

    private List<ForumSubBean> forumSubTitle;
    private String forumTitle;

    public List<ForumSubBean> getForumSubTitle() {
        return forumSubTitle;
    }

    public void setForumSubTitle(List<ForumSubBean> forumSubTitle) {
        this.forumSubTitle = forumSubTitle;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }
}
