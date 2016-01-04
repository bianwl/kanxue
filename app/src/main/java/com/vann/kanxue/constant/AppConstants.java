package com.vann.kanxue.constant;

/**
 * @Author: wenlong.bian 2015-12-11
 * @E-mail: bxl049@163.com
 */
public class AppConstants {

    /** 通信接口请求地址*/
    public static final String GET_TOKEN_URL="http://bbs.pediy.com/getsecuritytoken.php?styleid=12";
    /**主页帖子分类地址*/
    public static final String GET_FORUMTITLE="http://bbs.pediy.com/index.php?styleid=12";
    /** 图片链接*/
    public static final String USER_ICON_PATH = "http://bbs.pediy.com/image.php?u=";
    /** 新帖集合板块ID*/
    public static final int NEW_FORUM_ID =153;
    /**安全资讯板块ID*/
    public static final int SECRITY_FORUM_ID=61;

    /** 数据存储文件 */
    public static final String PREFERENCE_NAME ="config";
    /** 用户名 */
    public static final String USER_NAME="userName";
    /** 用户密码 */
    public static final String USER_PASSWORD="password";
    /** 用户securitytoken */
    public static final String SECURITY_TOKEN="token";
    /** 全局置顶*/
    public static final int GLOBLE_TOP_FORUM=-1;
    /**置顶*/
    public static final int TOP_FORUM=1;

    // volley 请求标签
    public static final String FORUMTITLE_REQUEST_TAG ="forumtitletag";
    public static final String NEWFORUM_REQUEST_TAG ="newforumtag";
    public static final String SECURITY_REQUEST_TAG ="securitytag";
    public static final String FORUMSHOWACTIVITY_REQUEST_TAG="forumshowactivitytag";
    public static final String POSTACTIVITY_REQUEST_TAG="postactivitytag";
    public static final String USERINFO_REQUEST_TAG="userinfotag";

}
