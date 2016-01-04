package com.vann.kanxue.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.vann.kanxue.MyApplication;
import com.vann.kanxue.R;
import com.vann.kanxue.bean.ThreadBean;
import com.vann.kanxue.constant.AppConstants;
import com.vann.kanxue.util.BitmapCache;

import java.util.List;

/**
 * @Author: wenlong.bian 2015-12-22
 * @E-mail: bxl049@163.com
 */
public class ThreadAdapter extends BaseAdapter {


    private List<ThreadBean> list;
    private Context mContext;
    private ImageLoader imageLoader;
    private int threadType;

    public ThreadAdapter(Context context, List<ThreadBean> datas, int type) {
        this.mContext = context;
        this.list = datas;
        this.threadType = type;
        imageLoader = new ImageLoader(MyApplication.getRequestQueue(), new BitmapCache());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.forum_listview_item, null);
            holder = new ViewHolder();
            holder.imv = (ImageView) view.findViewById(R.id.id_imv_user_icon);
            holder.title = (TextView) view.findViewById(R.id.id_tv_title);
            holder.readCount = (TextView) view.findViewById(R.id.id_tv_readNum);
            holder.replyCount = (TextView) view.findViewById(R.id.id_tv_replyNum);
            holder.username = (TextView) view.findViewById(R.id.id_tv_threadusername);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ThreadBean bean = list.get(i);
        if (bean != null) {
            String title = bean.getThreadtitle();
            if (AppConstants.NEW_FORUM_ID != threadType) {
                if (AppConstants.GLOBLE_TOP_FORUM == bean.getGlobalsticky()) {
                    title = "<font color=\"red\">【总顶】</font>" + bean.getThreadtitle();

                } else if (AppConstants.TOP_FORUM == bean.getSticky()) {
                    title = "<font color=\"red\">【置顶】</font>" + bean.getThreadtitle();
                }
            }
            if (bean.getAvatar() == 0) {
                holder.imv.setBackgroundResource(R.mipmap.nopic);
            } else if (bean.getAvatar() == 1) {
                int userId = bean.getPostuserid();
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.imv, R.mipmap.nopic, R.mipmap.nopic);
                imageLoader.get(AppConstants.USER_ICON_PATH + userId, listener);
            }
            holder.title.setText(Html.fromHtml(title));
            holder.replyCount.setText(bean.getReplycount() + "");
            holder.readCount.setText(bean.getViews() + "");
            holder.username.setText(bean.getPostusername());
        }

        return view;
    }

    class ViewHolder {
        ImageView imv;
        TextView title;
        TextView username;
        TextView replyCount;
        TextView readCount;
    }
}
