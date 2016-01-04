package com.vann.kanxue.bean;

import java.io.Serializable;

/**
 * @Author: wenlong.bian 2015-12-17
 * @E-mail: bxl049@163.com
 */
public class ForumSubBean implements Serializable {

    private int id;
    private int imgId;
    private String name;

    public ForumSubBean(){}

    public ForumSubBean(int _id,int _imgId,String _name){
        this.id = _id;
        this.imgId = _imgId;
        this.name = _name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
