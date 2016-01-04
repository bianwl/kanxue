package com.vann.kanxue.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.vann.kanxue.R;
import com.vann.kanxue.constant.AppConstants;
import com.vann.kanxue.fragment.ForumDisplayFragment;
import com.vann.kanxue.fragment.ForumHomeFragment;
import com.vann.kanxue.fragment.ForumSettingFragment;
import com.vann.kanxue.util.ActivityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wenlong.bian 2015-10-13
 * @E-mail: bxl049@163.com
 */
public class MainActivity extends BaseActivty implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    public static final String TAG=MainActivity.class.getSimpleName();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private RadioGroup mRg;
    String[] pages;
    private ImageButton mEdit;
    private TextView mTitle;
    private long exitTime =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
        mRg.setOnCheckedChangeListener(this);
    }



    private void initView() {
        mEdit = (ImageButton) findViewById(R.id.id_imb_edit);
        mTitle = (TextView) findViewById(R.id.title);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mViewPager = (ViewPager) this.findViewById(R.id.vp_content);
    }

    private void initData() {
        pages = new String[]{
                getResources().getString(R.string.forum_home),
                getResources().getString(R.string.forum_posts),
                getResources().getString(R.string.forum_safe),
                getResources().getString(R.string.forum_setting)
        };
        ForumHomeFragment home = new ForumHomeFragment();
        ForumDisplayFragment posts = ForumDisplayFragment.newInstance(AppConstants.NEW_FORUM_ID);
        ForumDisplayFragment safe = ForumDisplayFragment.newInstance(AppConstants.SECRITY_FORUM_ID);
        ForumSettingFragment setting = new ForumSettingFragment();
        fragments.add(home);
        fragments.add(posts);
        fragments.add(safe);
        fragments.add(setting);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        };
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mRg.check(R.id.rb_home);
                break;
            case 1:
                mRg.check(R.id.rb_posts);
                break;
            case 2:
                mRg.check(R.id.rb_safe);
                break;
            case 3:
                mRg.check(R.id.rb_setting);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_home:
                mTitle.setText(R.string.forum_home);
                mEdit.setVisibility(View.GONE);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rb_posts:
                mTitle.setText(R.string.forum_posts);
                mEdit.setVisibility(View.VISIBLE);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb_safe:
                mTitle.setText(R.string.forum_safe);
                mEdit.setVisibility(View.VISIBLE);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb_setting:
                mTitle.setText(R.string.forum_setting);
                mEdit.setVisibility(View.GONE);
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis() - exitTime>2000){
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                ActivityManager.finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"***onStart()***");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"***onResume()***");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "***onRestart()***");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "***onPause()***");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "***onStop()***");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "***onDestroy()***");
    }
}
