package com.cs407.hippie_christmas;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class PostDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PostDatabase";
    private static final int DATABASE_VERSION = 1;
    //private static final String COLUMN_ID = "id"; //TODO: necessary?
    private static final String TABLE_POSTS = "posts";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_IMAGE_PATH = "image_path";
    static SQLiteDatabase sqLiteDatabase;
    public PostDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_POST_TABLE = "CREATE TABLE " + TABLE_POSTS + "("
                //+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_IMAGE_PATH + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_POST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
        onCreate(db);
    }

    public void addPost(String username, String title, String location, String category, String image_path) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_IMAGE_PATH, image_path);
        db.insert(TABLE_POSTS, null, values);
        db.close();
    }

    public ArrayList<Items> readPosts() {

        if (sqLiteDatabase == null) {
            sqLiteDatabase = this.getReadableDatabase();
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_POSTS, null);

        int titleIndex = c.getColumnIndex(COLUMN_TITLE);
        int locationIndex = c.getColumnIndex(COLUMN_LOCATION);
        int categoryIndex = c.getColumnIndex(COLUMN_CATEGORY);

        ArrayList<Items> itemList = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                String title = c.getString(titleIndex);
                String location = c.getString(locationIndex);
                String category = c.getString(categoryIndex);

                Items item = new Items(category, title, location);
                itemList.add(item);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return itemList;
    }

    public ArrayList<Items> readPostsByCategory(String category) {

        if (sqLiteDatabase == null) {
            sqLiteDatabase = this.getReadableDatabase();
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_POSTS + " WHERE " + COLUMN_CATEGORY + " = ?", new String[]{category});

        int titleIndex = c.getColumnIndex(COLUMN_TITLE);
        int locationIndex = c.getColumnIndex(COLUMN_LOCATION);
        int categoryIndex = c.getColumnIndex(COLUMN_CATEGORY);

        ArrayList<Items> itemList = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                String title = c.getString(titleIndex);
                String location = c.getString(locationIndex);
                String category1 = c.getString(categoryIndex);

                Items item = new Items(category1, title, location);
                itemList.add(item);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return itemList;
    }
}
