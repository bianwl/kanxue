package com.vann.kanxue.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vann.kanxue.R;
import com.vann.kanxue.activity.AboutActivity;
import com.vann.kanxue.activity.UserInfoActivity;
import com.vann.kanxue.util.ActivityManager;

/**
 * @Author: wenlong.bian 2015-10-13
 * @E-mail: bxl049@163.com
 */
public class ForumSettingFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ForumSettingFragment";
    private TextView mCheck;
    private TextView mFeedback;
    private TextView mAbout;
    private TextView mExit;
    private RelativeLayout mLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "****onCreateView()****");
        return inflater.inflate(R.layout.fragment_setttings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "***onActivityCreated()*****");
        mCheck = (TextView) getView().findViewById(R.id.id_btn_version);
        mFeedback = (TextView) getView().findViewById(R.id.id_btn_feedback);
        mAbout = (TextView) getView().findViewById(R.id.id_btn_about);
        mExit = (TextView) getView().findViewById(R.id.id_btn_exit);
        mLayout = (RelativeLayout) getView().findViewById(R.id.id_layout_account);
        mCheck.setOnClickListener(this);
        mFeedback.setOnClickListener(this);
        mAbout.setOnClickListener(this);
        mExit.setOnClickListener(this);
        mLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_layout_account:
                UserInfoActivity.actionStart(getActivity(),236791);
                Toast.makeText(getActivity(), "to learn the bbs", Toast.LENGTH_SHORT).show();
            case R.id.id_btn_version:
                Toast.makeText(getActivity(), "check version", Toast.LENGTH_LONG).show();
                break;
            case R.id.id_btn_feedback:
                Toast.makeText(getActivity(), "feed back", Toast.LENGTH_LONG).show();
                break;
            case R.id.id_btn_about:
                AboutActivity.actionStart(getActivity());
                break;
            case R.id.id_btn_exit:
                ActivityManager.finish();
                break;
        }
    }
}
