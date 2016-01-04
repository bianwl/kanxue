package com.vann.kanxue.api;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vann.kanxue.MyApplication;

import java.util.Map;

/**
 * @Author: wenlong.bian 2016-01-04
 * @E-mail: bxl049@163.com
 */
public class VolleyRequest {

    public static JsonObjectRequest mRequest;
    public static Context mContext;

    public static void requestGet(String url, String tag, VolleyInterface vif) {
        MyApplication.getRequestQueue().cancelAll(tag);
        mRequest = new JsonObjectRequest(Request.Method.GET,url, null,vif.loadListener(),vif.errorListener());
        mRequest.setTag(tag);
        MyApplication.getRequestQueue().add(mRequest);
        MyApplication.getRequestQueue().start();
    }

    public static void requestPost(String url, String tag, final Map<String, String> params, VolleyInterface vif) {
        MyApplication.getRequestQueue().cancelAll(tag);
       mRequest = new JsonObjectRequest(url,null,vif.loadListener(),vif.errorListener()){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               return params;
           }
       };
        mRequest.setTag(tag);
        MyApplication.getRequestQueue().add(mRequest);
        MyApplication.getRequestQueue().start();
    }


}
