package com.vann.kanxue.bean;

import java.util.List;

/**
 * @Author: wenlong.bian 2015-12-28
 * @E-mail: bxl049@163.com
 */
public class PostBean {
    private String time;
    private  int pagenav;
    private List<PostBitsBean> postbits;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPagenav() {
        return pagenav;
    }

    public void setPagenav(int pagenav) {
        this.pagenav = pagenav;
    }

    public List<PostBitsBean> getPostbits() {
        return postbits;
    }

    public void setPostbits(List<PostBitsBean> postbits) {
        this.postbits = postbits;
    }
}
