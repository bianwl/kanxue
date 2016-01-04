package com.vann.kanxue.util;

/**
 * @Author: wenlong.bian 2015-12-22
 * @E-mail: bxl049@163.com
 */
public class UrlUtil {

    public static final String THREAD_URL = "http://bbs.pediy.com/forumdisplay.php?styleid=12";
    public static final String POSTS_URL = "http://bbs.pediy.com/showthread.php?styleid=12";
    public static final String USER_URL = "http://bbs.pediy.com/member.php?styleid=12";

    public static String getThreadUrl(int threadId, int page) {
        return THREAD_URL + "&f=" + threadId + "&page=" + page + "&order = desc";
    }

    public static String getPostsUrl(int threadId, int page) {
        return POSTS_URL + "&t=" + threadId + "&page=" + page;
    }

    public static String getUserUrl(int userId) {
        return USER_URL + "&u=" + userId;
    }

}
