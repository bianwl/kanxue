package com.vann.kanxue.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @Author: wenlong.bian 2015-12-28
 * @E-mail: bxl049@163.com
 */
public class ToastUtil {
    public static void showError(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
