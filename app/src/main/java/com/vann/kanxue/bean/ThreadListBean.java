package com.vann.kanxue.bean;

import java.util.List;

/**
 * @Author: wenlong.bian 2015-12-22
 * @E-mail: bxl049@163.com
 */
public class ThreadListBean {
    private int time;
    private int pagenav;
    private List<ThreadBean> threadList;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPagenav() {
        return pagenav;
    }

    public void setPagenav(int pagenav) {
        this.pagenav = pagenav;
    }

    public List<ThreadBean> getThreadList() {
        return threadList;
    }

    public void setThreadList(List<ThreadBean> threadList) {
        this.threadList = threadList;
    }
}
