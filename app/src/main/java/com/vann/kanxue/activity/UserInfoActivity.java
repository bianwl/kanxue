package com.vann.kanxue.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.vann.kanxue.R;
import com.vann.kanxue.api.VolleyInterface;
import com.vann.kanxue.api.VolleyRequest;
import com.vann.kanxue.bean.User;
import com.vann.kanxue.constant.AppConstants;
import com.vann.kanxue.util.UrlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @Author: wenlong.bian 2015-12-15
 * @E-mail: bxl049@163.com
 */
public class UserInfoActivity extends BaseActivty {
    public static final String KEY = "key";
    private ImageView user_img;
    private TextView name;
    private TextView title;
    private TextView postsCount;
    private TextView moneyCount;
    private TextView goodnessCount;
    private TextView language;
    private TextView avatartDate;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        getSupportActionBar().setTitle(R.string.user_infomation);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        initData();
        initView();
    }

    private void initData() {
        int userid = getIntent().getIntExtra(KEY,0);
        String url = UrlUtil.getUserUrl(userid);
        VolleyRequest.requestGet(url, AppConstants.USERINFO_REQUEST_TAG, new VolleyInterface(this, VolleyInterface.mListener
        ,VolleyInterface.mErrorListener) {
            @Override
            public void success(JSONObject result) {
                user = new Gson().fromJson(result.toString(), User.class);
                updateuserInfo();
            }

            @Override
            public void error(VolleyError volleyError) {

            }
        });
    }

    private void initView() {
        user_img = (ImageView) findViewById(R.id.id_imv_header_icon);
        name = (TextView) findViewById(R.id.id_tv_name);
        title = (TextView) findViewById(R.id.id_tv_title_content);
        postsCount = (TextView) findViewById(R.id.id_tv_posts_count);
        moneyCount = (TextView) findViewById(R.id.id_tv_money_count);
        goodnessCount = (TextView) findViewById(R.id.id_tv_goodness_count);
        language = (TextView) findViewById(R.id.id_tv_language_content);
        avatartDate = (TextView) findViewById(R.id.id_tv_avatar_content);
    }

    public static void actionStart(Context context, int userid) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra(KEY,userid);
        context.startActivity(intent);
    }

    private void updateuserInfo() {
        if (user != null) {
            name.setText(user.getUsername());
            title.setText(user.getUsertitle());
            postsCount.setText(user.getPosts());
            moneyCount.setText(user.getMoney());
            goodnessCount.setText(user.getGoodness());
            language.setText(user.getLanguageid());
            avatartDate.setText(user.getAvatardateline());
        }
    }
}
