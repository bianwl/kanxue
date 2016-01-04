package com.vann.kanxue.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.vann.kanxue.R;
import com.vann.kanxue.adapter.PostReplyAdapter;
import com.vann.kanxue.api.VolleyInterface;
import com.vann.kanxue.api.VolleyRequest;
import com.vann.kanxue.bean.PostBean;
import com.vann.kanxue.bean.PostBitsBean;
import com.vann.kanxue.bean.ThreadBean;
import com.vann.kanxue.constant.AppConstants;
import com.vann.kanxue.util.ToastUtil;
import com.vann.kanxue.util.UrlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: wenlong.bian 2015-12-24
 * @E-mail: bxl049@163.com
 */
public class PostActivity extends BaseActivty {

    public static final String ACTIVITY_KEY = "key";
    private PullToRefreshListView mListView;
    private ThreadBean threadBean;
    private List<PostBitsBean> datas = new ArrayList<>();
    private PostReplyAdapter mAdapter;
    private int page = 1;
    private int pageCount = 0;
    private LinearLayout progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        threadBean = (ThreadBean) getIntent().getSerializableExtra(ACTIVITY_KEY);
        getSupportActionBar().setTitle(R.string.post_comments);
        threadBean = (ThreadBean) getIntent().getSerializableExtra(ACTIVITY_KEY);
        getDatas();
        mListView = (PullToRefreshListView) findViewById(R.id.id_pullListView);
        progressbar = (LinearLayout) findViewById(R.id.id_layout_progressbar);
        progressbar.setVisibility(View.VISIBLE);
        initPullListView();
        initEvent();
        mAdapter = new PostReplyAdapter(this, datas);
        mListView.setAdapter(mAdapter);
    }


    private void initEvent() {
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                datas.clear();
                new MyTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                if (pageCount <= page - 1) {
                    mListView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mListView.onRefreshComplete();
                        }
                    }, 1000);
                } else {
                    new MyTask().execute();
                }
            }
        });
    }

    private void initPullListView() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在刷新");
        mListView.getLoadingLayoutProxy(true, false).setLastUpdatedLabel("下拉刷新");
        mListView.getLoadingLayoutProxy(true, false).setPullLabel("");
        mListView.getLoadingLayoutProxy(true, false).setReleaseLabel("释放开始刷新");
        mListView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在刷新");
        mListView.getLoadingLayoutProxy(false, true).setLastUpdatedLabel("上拉加载");
        mListView.getLoadingLayoutProxy(false, true).setPullLabel("");
        mListView.getLoadingLayoutProxy(false, true).setReleaseLabel("释放开始加载");

    }

    private void getDatas() {
        String url = UrlUtil.getPostsUrl(threadBean.getThreadid(), page);
        VolleyRequest.requestGet(url, AppConstants.POSTACTIVITY_REQUEST_TAG, new VolleyInterface(this, VolleyInterface.mListener
        ,VolleyInterface.mErrorListener) {
            @Override
            public void success(JSONObject result) {
                try {
                    pageCount = result.getInt("pagenav");
                    PostBean posts = new Gson().fromJson(result.toString(), PostBean.class);
                    datas.addAll(posts.getPostbits());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                    if (progressbar != null && progressbar.isShown()) {
                        progressbar.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(VolleyError volleyError) {
                ToastUtil.showError(PostActivity.this, "网络连接出错！");
                if (progressbar != null && progressbar.isShown()) {
                    progressbar.setVisibility(View.GONE);
                }
                mListView.onRefreshComplete();
            }
        });
    }

    public static void actionStart(Context context, ThreadBean bean) {
        Intent intent = new Intent(context, PostActivity.class);
        intent.putExtra(ACTIVITY_KEY, bean);
        context.startActivity(intent);
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            getDatas();
            return null;
        }
    }
}
