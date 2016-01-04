package com.vann.kanxue.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.vann.kanxue.MyApplication;
import com.vann.kanxue.R;
import com.vann.kanxue.bean.PostBitsBean;
import com.vann.kanxue.constant.AppConstants;
import com.vann.kanxue.util.BitmapCache;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wenlong.bian 2015-12-28
 * @E-mail: bxl049@163.com
 */
public class PostReplyAdapter extends BaseAdapter {
    private Context mContext;
    private List<PostBitsBean> list = new ArrayList<>();
    private ImageLoader mImageLoad;

    public PostReplyAdapter(Context context,List<PostBitsBean> datas) {
        this.mContext = context;
        this.list = datas;
        mImageLoad = new ImageLoader(MyApplication.getRequestQueue(), new BitmapCache());
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
        ViewHolder holder = null;

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.posts_listview_item, null);
            holder = new ViewHolder();
            holder.imb_icon = (ImageButton) view.findViewById(R.id.id_imb_user_icon);
            holder.username = (TextView) view.findViewById(R.id.id_post_username);
            holder.time = (TextView) view.findViewById(R.id.id_post_date);
            holder.comment = (TextView) view.findViewById(R.id.id_tv_post_reply_content);
            holder.floor = (TextView) view.findViewById(R.id.id_tv_reply_floor);
            holder.title = (TextView) view.findViewById(R.id.id_tv_post_title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        StringBuilder sb= new StringBuilder();
        sb.append(i+1).append("#");
        holder.floor.setText(sb.toString());
        PostBitsBean bean = list.get(i);
        if (i == 0) {
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(bean.getTitle());
        } else {
            holder.title.setVisibility(View.GONE);
        }
        holder.username.setText(bean.getUsername());
        holder.comment.setText(Html.fromHtml(bean.getMessage()));
        holder.time.setText(bean.getPostdate() + " " + bean.getPosttime());
        int userid = bean.getUserid();
        if(bean.getAvatar()==1){
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.imb_icon, R.mipmap.user_default_icon, R.mipmap.user_default_icon);
            mImageLoad.get(AppConstants.USER_ICON_PATH+userid,listener);
        }else{
            holder.imb_icon.setBackgroundResource(R.mipmap.user_default_icon);
        }

        return view;
    }

    class ViewHolder {
        ImageButton imb_icon;
        TextView username;
        TextView time;
        TextView comment;
        TextView floor;
        TextView title;
    }
}
