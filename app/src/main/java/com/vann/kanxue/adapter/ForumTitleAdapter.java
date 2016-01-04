package com.vann.kanxue.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.vann.kanxue.R;
import com.vann.kanxue.bean.ForumBean;
import com.vann.kanxue.bean.ForumSubBean;

import java.util.List;

/**
 * @Author: wenlong.bian 2015-12-17
 * @E-mail: bxl049@163.com
 */
public class ForumTitleAdapter extends BaseExpandableListAdapter{

    private Context mContext;
    private List<ForumBean> groups;

    public ForumTitleAdapter(Context context,List<ForumBean> beans){
        this.mContext = context;
        this.groups =beans;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return groups.get(i).getForumSubTitle().size();
    }

    @Override
    public Object getGroup(int i) {
        return groups.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return groups.get(i).getForumSubTitle().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.forum_group_title_item, null);
        TextView group= (TextView) view.findViewById(R.id.id_tv_forum_group_title);
        ForumBean bean = groups.get(i);
        group.setText(bean.getForumTitle());
        Log.e("KEY","***getGroupView()**");
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.forum_title_item,null);
        TextView title = (TextView) view.findViewById(R.id.id_tv_forum_title);
        ForumSubBean subBean = (ForumSubBean) getChild(i,i1);
        title.setText(subBean.getName());
        Log.e("KEY", "***getChildView()**");
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
