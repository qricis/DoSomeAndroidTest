package com.example.contactstest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/8/20 16:54
 * @version 1.0.0
 */
public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;

    public static final int TABLE1_ITEM = 1;

    public static final int TABLE2_DIR = 2;

    public static final int TABLE2_ITEM = 3;

    private static UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI("com.example.contactstest.provider","table1",TABLE1_DIR);
        mUriMatcher.addURI("com.example.contactstest.provider","table1",TABLE1_ITEM);
        mUriMatcher.addURI("com.example.contactstest.provider","table2",TABLE2_DIR);
        mUriMatcher.addURI("com.example.contactstest.provider","table2",TABLE2_ITEM);

    }

    //初始化内容提供器的时候调用，通常在这里完成对数据库的创建和升级，返回true表示成功，false表示失败
    @Override
    public boolean onCreate() {
        return false;
    }

    //从内容提供器中添加一条数据，uri确定表，projection确定列，selection确定where，selectionArgs确定where参数，sortOrder确定排序方式
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        switch (mUriMatcher.match(uri)) {
            case TABLE1_DIR:
                //查询table1表中的所有数据
                break;
            case TABLE1_ITEM:
                //查询table1表中的单条数据
                break;
            case TABLE2_DIR:
                //查询table2表中的所有数据
                break;
            case TABLE2_ITEM:
                //查询table2表中的单条数据
                break;
            default:
                break;
        }

        return null;
    }

    //向内容提供器添加数据
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    //更新内容提供器中已有的数据
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    //从内容提供器中删除数据
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    //根据传入的内容URI返回相应的MIME类型,一个内容URI所对应的MIME由三部分组成
    //必须以vnd开头
    //如果内容URI以路径结尾，则后接android.cursor.dir/，如果以id结尾，则后接android.cursor.item/
    //最后接上vnd.<authority>.<path>
    //对应content://com.example.contactstest.provider/table1，写成vnd.android.dir/vnd.content://com.example.contactstest.provider/table1
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (mUriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.dir/vnd.content://com.example.contactstest.provider/table1";
            case TABLE1_ITEM:
                return "vnd.android.item/vnd.content://com.example.contactstest.provider/table1";
            case TABLE2_DIR:
                return "vnd.android.dir/vnd.content://com.example.contactstest.provider/table2";
            case TABLE2_ITEM:
                return "vnd.android.item/vnd.content://com.example.contactstest.provider/table2";
            default:
                break;
        }

        return null;
    }
}
