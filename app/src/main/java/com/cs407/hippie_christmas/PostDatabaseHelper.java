package com.cs407.hippie_christmas;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    /*public ArrayList<String[]> getUserPosts(String username) {
        if (DatabaseHelper.checkUserExists(username)) {
            return
        }
        return null;
    }*/
}
