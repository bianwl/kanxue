package com.vann.kanxue.activity;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

import com.vann.kanxue.R;

/**
 * @Author: wenlong.bian 2015-12-30
 * @E-mail: bxl049@163.com
 */
public class AboutActivity extends BaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        bar.setTitle(R.string.about);
        bar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_about);
        TextView version = (TextView) findViewById(R.id.id_tv_version);
        PackageInfo pInfo;
        try {
            pInfo = getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            version.setText("V" + pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }
}
