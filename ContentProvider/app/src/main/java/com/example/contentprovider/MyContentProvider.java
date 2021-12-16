package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.contentprovider.data.MyDatabaseHelper;

public class MyContentProvider extends ContentProvider {

    public static final int BOOK_DIR = 0;

    public static final int BOOK_ITEM = 1;

    public static final int CATEGORY_DIR = 2;

    public static final int CATEGORY_ITEM = 3;

    public static final String ACTHORITY = "com.example.contentprovider.provider";

    private static UriMatcher sUriMatcher;

    private MyDatabaseHelper mMyDatabaseHelper;

    //对UriMatcher进行了初始化操作
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(ACTHORITY,"book",BOOK_DIR);
        sUriMatcher.addURI(ACTHORITY,"book/#",BOOK_ITEM);
        sUriMatcher.addURI(ACTHORITY,"category",CATEGORY_DIR);
        sUriMatcher.addURI(ACTHORITY,"catetory/#",CATEGORY_ITEM);
    }

    public MyContentProvider() {
    }

    //创建了一个MyDatabaseHelper实例，返回true表示内容提供器初始化成功
    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        mMyDatabaseHelper = new MyDatabaseHelper(getContext(),"BookStore.db",null,2);
        return true;
    }

    //通过获取SQLiteDatabase实例，根据uri参数判断用户想要访问哪张表，调用其query方法进行查询，并将cursor对象返回
    //当访问单条数据时，调用了uri.getPathSegments()方法，它会将内容URI权限之后部分以/进行切割
    //并把分割后的结果放入到一个字符串列表里，这个列表的第0个位置存放的是路径，第1个位置存放的是id
    //得到id之后，再根据selection,selectionArgs进行约束，就实现了查找单条数据的功能
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        //查询数据
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (sUriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book",projection,"id=?",new String[]{bookId},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category",projection,"id=?",new String[]{categoryId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    //同样先获得了SQLiteDatabase实例，然后根据传入的参数判断出用户想要往哪张表里添加数据，在调用insert()
    //需要注意的是insert()要求返回一个uri，所以我们需要调用Uri.parse()方法来讲一个内容URI解析成Uri对象
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        //添加数据
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        Uri uriReturn = null;

        switch (sUriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book",null,values);
                uriReturn = Uri.parse("content://" + ACTHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Category",null,values);
                uriReturn = Uri.parse("content://" + ACTHORITY + "/category/" + newCategoryId);
                break;
            default:
                break;
        }

        return uriReturn;
    }

    //先获取实例，然后根据用户想要更新哪张表的数据，调用update方法进行更新
    //受影响的行数将作为返回值返回
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        //更新数据
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        int updatedRows = 0;

        switch (sUriMatcher.match(uri)) {
            case BOOK_DIR:
                updatedRows = db.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update("Book",values,"id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updatedRows = db.update("Category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRows = db.update("Category",values,"id=?",new String[]{categoryId});
                break;
            default:
                break;
        }

        return updatedRows;
    }

    //首先获取到SQLiteDatabase实例，然后根据传入的uri参数判断出用户想要删除哪张表的数据
    //被删除的行数将作为返回值返回
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (sUriMatcher.match(uri)) {
            case BOOK_DIR:
                deleteRows= db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = db.delete("Book","id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows = db.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = db.delete("Category","id=?",new String[]{categoryId});
                break;
            default:
                break;

        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (sUriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.contentprovider.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.contentprovider.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.contentprovider.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.contentprovider.provider.category";
        }
        return null;
    }

}
