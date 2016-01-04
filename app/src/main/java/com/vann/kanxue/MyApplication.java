package com.vann.kanxue;

import android.app.Application;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.vann.kanxue.constant.AppConstants;

import java.util.Map;

/**
 * @Author: wenlong.bian 2016-01-04
 * @E-mail: bxl049@163.com
 */
public class MyApplication extends Application {

    private static final String SET_COOKIE_KEY="Set-Cookie";
    private static final String COOKIE_KEY="Cookie";
    private static final String COOKIE_USERNAME="username";

    public static RequestQueue mqueues;
    private SharedPreferences pref;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mqueues = Volley.newRequestQueue(getApplicationContext());
        pref = getSharedPreferences(AppConstants.PREFERENCE_NAME, 0);
    }

    public static RequestQueue getRequestQueue() {
        return mqueues;
    }

    /**
     * 检测返回头中包含的cookie
     * 并更新本地存储的cookie
     * @param headers
     */

    public void checkSessionCookie(Map<String,String> headers){
        if(headers.containsKey(SET_COOKIE_KEY)){
            String cookie = headers.get(SET_COOKIE_KEY);
            if(cookie.length()>0 && !cookie.contains("saeut")){
                String[] cookieSplit  = cookie.split(";");
                String[] splitSessionId= cookieSplit[0].split("=");
                cookie = splitSessionId[1];
                saveSharedPreferencesString(COOKIE_USERNAME,cookie);
            }
        }
    }
    public  void saveSharedPreferencesString(String key,String value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
