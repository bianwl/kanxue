package com.vann.kanxue.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vann.kanxue.R;
import com.vann.kanxue.activity.ForumShowActivity;
import com.vann.kanxue.adapter.ForumTitleAdapter;
import com.vann.kanxue.api.VolleyInterface;
import com.vann.kanxue.api.VolleyRequest;
import com.vann.kanxue.bean.ForumBean;
import com.vann.kanxue.bean.ForumSubBean;
import com.vann.kanxue.constant.AppConstants;
import com.vann.kanxue.db.MyDbHelper;
import com.vann.kanxue.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wenlong.bian 2015-10-13
 * @E-mail: bxl049@163.com
 */
public class ForumHomeFragment extends Fragment {

    private static final String TAG = ForumHomeFragment.class.getSimpleName();
    private List<ForumBean> datas = new ArrayList<>();
    private ForumTitleAdapter mAdapter;
    private ExpandableListView mListView;
    private MyDbHelper myHelper;
    private LinearLayout mBar;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext =context;
        Log.e(TAG,"***onAttach()****");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mContext == null ){
            mContext = getActivity();
        }
        myHelper = new MyDbHelper(mContext);
        mListView = (ExpandableListView) getView().findViewById(R.id.id_lv_forum);
        mListView.setGroupIndicator(null);
        mBar = (LinearLayout) getView().findViewById(R.id.id_layout_progressbar);
        mBar.setVisibility(View.VISIBLE);
        initData();
        mAdapter = new ForumTitleAdapter(mContext, datas);
        mListView.setAdapter(mAdapter);
        initEvent();
        expandListView();
        if(mBar != null && mBar.isShown()){
            mBar.setVisibility(View.GONE);
        }
    }

    private void expandListView() {
        if (!datas.isEmpty() && mListView != null) {
            for (int i = 0; i < datas.size(); i++) {
                mListView.expandGroup(i);
            }
        }
    }

    private void loadJson() {

        VolleyRequest.requestGet(AppConstants.GET_FORUMTITLE, AppConstants.FORUMTITLE_REQUEST_TAG, new VolleyInterface(mContext,VolleyInterface.mListener,VolleyInterface.mErrorListener) {
            @Override
            public void success(JSONObject result) {
                try {
                    JSONArray json = result.getJSONArray("forumbits");
                    List<ForumBean> beans  = new Gson().fromJson(json.toString(), new TypeToken<List<ForumBean>>() {
                    }.getType());
                    datas.addAll(beans);
                    mAdapter.notifyDataSetChanged();
                    if (mBar != null && mBar.isShown()) {
                        mBar.setVisibility(View.GONE);
                    }
                    myHelper.saveForumGroupList(datas);
                    expandListView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(VolleyError volleyError) {
                ToastUtil.showError(mContext, "发生未知网络错误！");
            }
        });
    }

    private void initEvent() {
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                ForumSubBean bean = datas.get(i).getForumSubTitle().get(i1);
                ForumShowActivity.actionStart(mContext, bean);
                return false;
            }
        });

    }

    private void initData() {
        datas.clear();
        if (myHelper.isEmpty(MyDbHelper.TABLE_FORUMTITLE_GROUP)) {
            loadJson();
        } else {
            datas.addAll(myHelper.getForums());
            if(mAdapter != null ){
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
