package com.vann.kanxue.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.vann.kanxue.R;
import com.vann.kanxue.activity.PostActivity;
import com.vann.kanxue.adapter.ThreadAdapter;
import com.vann.kanxue.api.VolleyInterface;
import com.vann.kanxue.api.VolleyRequest;
import com.vann.kanxue.bean.ThreadBean;
import com.vann.kanxue.bean.ThreadListBean;
import com.vann.kanxue.constant.AppConstants;
import com.vann.kanxue.util.ToastUtil;
import com.vann.kanxue.util.UrlUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wenlong.bian 2015-10-13
 * @E-mail: bxl049@163.com
 */
public class ForumDisplayFragment extends Fragment {


    private static final String TAG = "ForumDisplayFragment";

    public static final String TAB_TYPE = "type";
    private ThreadAdapter mAdapter;
    private int page = 1;
    public PullToRefreshListView pullToRefreshListView;
    private List<ThreadBean> datas = new ArrayList<>();
    private int threadId;
    private LinearLayout mBar;
    private String url;
    private String tag;
    private Context mContext;

    public ForumDisplayFragment() {
    }

    public static ForumDisplayFragment newInstance(int threadId) {
        Bundle bundle = new Bundle();
        bundle.putInt(TAB_TYPE, threadId);
        ForumDisplayFragment fragment = new ForumDisplayFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.acitivity_forum_show, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int type = getArguments().getInt(TAB_TYPE);
        mContext = getActivity();
        if (AppConstants.NEW_FORUM_ID == type) {
            threadId = AppConstants.NEW_FORUM_ID;
            tag =AppConstants.NEWFORUM_REQUEST_TAG;
        } else {
            threadId = AppConstants.SECRITY_FORUM_ID;
            tag = AppConstants.SECURITY_REQUEST_TAG;
        }
        url = UrlUtil.getThreadUrl(threadId, 1);
        mBar  = (LinearLayout) getView().findViewById(R.id.id_layout_progressbar);
        mBar.setVisibility(View.VISIBLE);
        pullToRefreshListView = (PullToRefreshListView) getView().findViewById(R.id.id_pull_refresh_listview);
        initData();
        getDatas();
        initEvent();

        mAdapter = new ThreadAdapter(mContext,datas,type);
        ListView mListView = pullToRefreshListView.getRefreshableView();
        mListView.setAdapter(mAdapter);
    }

    private void initEvent() {
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String lable = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                pullToRefreshListView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间：" + lable);
                datas.clear();
                page = 1;
                url = UrlUtil.getThreadUrl(threadId,page);
                new DownLoadAsyncTask().execute(page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                url = UrlUtil.getThreadUrl(threadId,page);
                new DownLoadAsyncTask().execute(page);
            }
        });
        pullToRefreshListView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position = (int) l;
                ThreadBean bean = datas.get(position);
                PostActivity.actionStart(getActivity(),bean);
            }
        });
    }

    private void initData() {
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在刷新");
        pullToRefreshListView.getLoadingLayoutProxy(true, false).setLastUpdatedLabel("下拉刷新");
        pullToRefreshListView.getLoadingLayoutProxy(true, false).setPullLabel("");
        pullToRefreshListView.getLoadingLayoutProxy(true, false).setReleaseLabel("释放开始刷新");
        pullToRefreshListView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在刷新");
        pullToRefreshListView.getLoadingLayoutProxy(false, true).setLastUpdatedLabel("上拉加载");
        pullToRefreshListView.getLoadingLayoutProxy(false, true).setPullLabel("");
        pullToRefreshListView.getLoadingLayoutProxy(false, true).setReleaseLabel("释放开始加载");
    }

    private void getDatas(){
        VolleyRequest.requestGet(url, tag, new VolleyInterface(mContext,VolleyInterface.mListener,
                VolleyInterface.mErrorListener) {
            @Override
            public void success(JSONObject resutl) {
                ThreadListBean threadList = new Gson().fromJson(resutl.toString(), ThreadListBean.class);
                datas.addAll(threadList.getThreadList());
                mAdapter.notifyDataSetChanged();
                if(mBar != null && mBar.isShown()){
                    mBar.setVisibility(View.GONE);
                }
                pullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void error(VolleyError volleyError) {
                ToastUtil.showError(getActivity(),"未知网络错误！");
                if(mBar != null && mBar.isShown()){
                    mBar.setVisibility(View.GONE);
                }
                pullToRefreshListView.onRefreshComplete();
            }
        });

//        HttpService.doRequest(Request.Method.POST, url, new HashMap<String, String>(), new HttpService.OnRequestListener() {
//            @Override
//            public void successResponse(JSONObject jsonObject) {
//                ThreadListBean threadList = new Gson().fromJson(jsonObject.toString(), ThreadListBean.class);
//                datas.addAll(threadList.getThreadList());
//                mAdapter.notifyDataSetChanged();
//                if(mBar != null && mBar.isShown()){
//                    mBar.setVisibility(View.GONE);
//                }
//                pullToRefreshListView.onRefreshComplete();
//            }
//
//            @Override
//            public void failedResponse(String errorMsg) {
//                ToastUtil.showError(getActivity(),"未知网络错误！");
//                if(mBar != null && mBar.isShown()){
//                    mBar.setVisibility(View.GONE);
//                }
//                pullToRefreshListView.onRefreshComplete();
//            }
//        });
    }


    class DownLoadAsyncTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            Integer page = integers[0];
            url = UrlUtil.getThreadUrl(threadId,page);
            getDatas();
            return null;
        }
    }
}
