package com.vann.kanxue.api;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * @Author: wenlong.bian 2016-01-04
 * @E-mail: bxl049@163.com
 */
public abstract class VolleyInterface {

    public Context mContext;
    public  static Response.Listener mListener;
    public static Response.ErrorListener mErrorListener;

    public abstract  void success(JSONObject result);
    public abstract  void error(VolleyError volleyError);

    public VolleyInterface(Context context, Response.Listener<JSONObject> listener,
                           Response.ErrorListener errorListener) {
        this.mContext = context;
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    public Response.Listener<JSONObject> loadListener(){
        mListener  = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject o) {
                success(o);
            }
        };
     return mListener;
    }

    public Response.ErrorListener errorListener(){
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                error(volleyError);
            }
        };
        return mErrorListener;
    }
}
