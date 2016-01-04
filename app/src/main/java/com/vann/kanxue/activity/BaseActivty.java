package com.vann.kanxue.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.vann.kanxue.util.ActivityManager;

/**
 * @Author: wenlong.bian 2015-10-13
 * @E-mail: bxl049@163.com
 */
public class BaseActivty extends AppCompatActivity {

    public static final String TAG = BaseActivty.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override

    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

}
