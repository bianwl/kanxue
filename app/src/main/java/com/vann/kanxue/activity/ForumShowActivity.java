package com.vann.kanxue.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.vann.kanxue.R;
import com.vann.kanxue.adapter.ThreadAdapter;
import com.vann.kanxue.api.VolleyInterface;
import com.vann.kanxue.api.VolleyRequest;
import com.vann.kanxue.bean.ForumSubBean;
import com.vann.kanxue.bean.ThreadBean;
import com.vann.kanxue.bean.ThreadListBean;
import com.vann.kanxue.constant.AppConstants;
import com.vann.kanxue.util.UrlUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @Author: wenlong.bian 2015-12-18
 * @E-mail: bxl049@163.com
 */
public class ForumShowActivity extends BaseActivty {

    public static final String TAG = ForumShowActivity.class.getSimpleName();
    public static final String KEY = "key";
    private ForumSubBean subBean;
    private PullToRefreshListView pListView;
    private ThreadAdapter mAdapter;
    private List<ThreadBean> datas = new ArrayList<>();
    private int curPage = 1;
    private LinearLayout mBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent input = getIntent();
        if (input != null) {
            subBean = (ForumSubBean) input.getSerializableExtra(KEY);
        }
        if (subBean != null) {
            getSupportActionBar().setTitle(subBean.getName());
        }
        setContentView(R.layout.acitivity_forum_show);
        pListView = (PullToRefreshListView) findViewById(R.id.id_pull_refresh_listview);
        mBar = (LinearLayout) findViewById(R.id.id_layout_progressbar);
        mBar.setVisibility(View.VISIBLE);
        initData();
        getDatas(curPage);
        initEvent();
        mAdapter = new ThreadAdapter(ForumShowActivity.this, datas,0);
        ListView mListView = pListView.getRefreshableView();
        mListView.setAdapter(mAdapter);
    }

    private void getDatas(int page) {
        String url = UrlUtil.getThreadUrl(subBean.getId(), page);
        VolleyRequest.requestGet(url, AppConstants.FORUMSHOWACTIVITY_REQUEST_TAG, new VolleyInterface(this,
                VolleyInterface.mListener,VolleyInterface.mErrorListener) {
            @Override
            public void success(JSONObject result) {
                ThreadListBean threadList = new Gson().fromJson(result.toString(), ThreadListBean.class);
                if (threadList != null) {
                    datas.addAll(threadList.getThreadList());
                    mAdapter.notifyDataSetChanged();
                }
                if(mBar != null && mBar.isShown() ){
                    mBar.setVisibility(View.GONE);
                }
                pListView.onRefreshComplete();
            }

            @Override
            public void error(VolleyError volleyError) {

            }
        });
    }

    private void initEvent() {
        pListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String str = DateUtils.formatDateTime(ForumShowActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                pListView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间：" + str);
                datas.clear();
                curPage = 1;
                new DownLoadAsyncTask().execute(curPage);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                curPage++;
                new DownLoadAsyncTask().execute(curPage);
            }
        });
        pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position  = (int) l;
                ThreadBean bean = datas.get(position);
                PostActivity.actionStart(ForumShowActivity.this,bean);
            }
        });
    }

    private void initData() {
        pListView.setMode(PullToRefreshBase.Mode.BOTH);
        pListView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在刷新");
        pListView.getLoadingLayoutProxy(true, false).setLastUpdatedLabel("下拉刷新");
        pListView.getLoadingLayoutProxy(true, false).setPullLabel("");
        pListView.getLoadingLayoutProxy(true, false).setReleaseLabel("释放开始刷新");
        pListView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在刷新");
        pListView.getLoadingLayoutProxy(false, true).setLastUpdatedLabel("上拉加载");
        pListView.getLoadingLayoutProxy(false, true).setPullLabel("");
        pListView.getLoadingLayoutProxy(false, true).setReleaseLabel("释放开始加载");

    }

    class DownLoadAsyncTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            Integer page = integers[0];
            getDatas(page);
            return null;
        }
    }

    public static void actionStart(Context context, ForumSubBean subBean) {
        Intent intent = new Intent(context, ForumShowActivity.class);
        intent.putExtra(KEY, subBean);
        context.startActivity(intent);
    }

}
