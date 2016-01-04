package com.vann.kanxue.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vann.kanxue.bean.ForumBean;
import com.vann.kanxue.bean.ForumSubBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wenlong.bian 2015-12-18
 * @E-mail: bxl049@163.com
 */
public class MyDbHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "kanxue.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_FORUMTITLE_GROUP = "forumGroup";
    public static final String TABLE_FORUMTITLE = "forum";

    public static final String CREATE_FORUMGROUP_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_FORUMTITLE_GROUP
            + "(_ID INTEGER PRIMARY KEY,NAME TEXT NOT NULL)";
    public static final String CREATE_FORUM_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_FORUMTITLE
            + "(_ID INTEGER PRIMARY KEY,PARENTNAME TEXT NOT NULL,"
            + "IMAGEID INTEGER ,NAME TEXT NOT NULL)";

    private SQLiteDatabase db;

    public MyDbHelper(Context context) {
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getReadableDatabase();
    }

    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_FORUMGROUP_SQL);
        sqLiteDatabase.execSQL(CREATE_FORUM_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.delete(CREATE_FORUM_SQL, null, null);
        sqLiteDatabase.delete(CREATE_FORUMGROUP_SQL, null, null);
        onCreate(sqLiteDatabase);
    }

    /**
     * 存储帖子类型分组
     *
     * @param group
     */
    public void saveForumGroup(ForumBean group) {
        if (group != null) {
            ContentValues values = new ContentValues();
            values.put("NAME", group.getForumTitle());
            db.insert(TABLE_FORUMTITLE_GROUP, null, values);
        }
    }

    /**
     * 存储ForumBean集合
     *
     * @param list
     */
    public void saveForumGroupList(List<ForumBean> list) {
        if (list != null && !list.isEmpty()) {
            db.delete(TABLE_FORUMTITLE_GROUP, null, null);
            db.delete(TABLE_FORUMTITLE, null, null);
            for (ForumBean bean : list) {
                saveForumGroup(bean);
                if (bean.getForumSubTitle().size() > 0) {
                    saveForumList(bean.getForumSubTitle(), bean.getForumTitle());
                }
            }
        }
    }

    /**
     * 存储帖子类型
     *
     * @param subBean
     */
    public void saveForum(ForumSubBean subBean, String parentName) {
        if (subBean != null) {
            ContentValues values = new ContentValues();
            values.put("_ID", subBean.getId());
            values.put("IMAGEID", subBean.getId());
            values.put("NAME", subBean.getName());
            values.put("PARENTNAME", parentName);
            db.insert(TABLE_FORUMTITLE, null, values);
        }
    }

    /**
     * 存储ForumSubBean集合
     *
     * @param list
     */
    public void saveForumList(List<ForumSubBean> list, String parentName) {
        if (list != null && !list.isEmpty()) {
            for (ForumSubBean bean : list) {
                saveForum(bean, parentName);
            }
        }
    }

    /**
     * 获取类别分组
     *
     * @return
     */
    public List<ForumBean> getForums() {
        List<ForumBean> forumBeans = new ArrayList<>();
        Cursor cursor = db.query(TABLE_FORUMTITLE_GROUP, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ForumBean bean = new ForumBean();
                String name = cursor.getString(1);
                bean.setForumTitle(name);
                bean.setForumSubTitle(getForumSubBeans(name));
                forumBeans.add(bean);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return forumBeans;
    }

    /**
     * 判断数据表是否为空
     *
     * @param tableName
     * @return
     */
    public boolean isEmpty(String tableName) {
        return db.query(tableName, null, null, null, null, null, null).getCount() > 0 ? false : true;
    }

    /**
     * 根据ForumBean 名称获取类别集合
     *
     * @param name
     * @return
     */
    public List<ForumSubBean> getForumSubBeans(String name) {
        List<ForumSubBean> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_FORUMTITLE, null, "PARENTNAME=?", new String[]{name}, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ForumSubBean bean = new ForumSubBean();
                bean.setId(cursor.getInt(0));
                bean.setImgId(cursor.getInt(2));
                bean.setName(cursor.getString(3));
                list.add(bean);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }

}
