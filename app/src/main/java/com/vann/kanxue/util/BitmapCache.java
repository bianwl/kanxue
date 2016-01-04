package com.vann.kanxue.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * @Author: wenlong.bian 2015-12-22
 * @E-mail: bxl049@163.com
 */
public class BitmapCache implements ImageLoader.ImageCache{

    private LruCache<String,Bitmap>mCache;

    public BitmapCache(){
        int maxSize = 10*1024*1024;
        mCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return mCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        mCache.put(s,bitmap);
    }
}
