package com.vann.kanxue.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wenlong.bian 2015-10-13
 * @E-mail: bxl049@163.com
 */
public class ActivityManager {

    public static List<Activity> activities = new ArrayList<>();

    /**
     * 添加activity
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (activity != null) {
            activities.add(activity);
        }
    }

    /**
     * 移除activity
     * @param activity
     */
    public static void removeActivity(Activity activity){
        if(activity != null ){
            activities.remove(activity);
        }
    }

    /**
     * 关闭所有activity
     */
    public static void finish(){
        for(Activity activity :activities){
            activity.finish();
        }
    }

}
