package com.example.messy_flatmates.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;

public class InternalDBHandler {

    DbHandler myhelper;

    public InternalDBHandler(Context context)
    {

        myhelper = new DbHandler(context);
    }

    /**
     * sets user_id and token to null from key_id 1.
     * @return true if successful, false if there is an error
     */
    public boolean removeSession()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String query = "UPDATE user SET user_id = null, token = null WHERE key_id = 1;";

        Cursor result = db.rawQuery(query, null);

        if (result != null)
            result.moveToFirst();
        else {
            return false;
        }

        db.close();

        return true;
    }

    public void addSession(String user_id, String token){

        long count =0;
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("user_id",user_id);
        values.put("token", token);
        count = db.insert("user", null, values);
         if (count >= 0){
             System.out.println("Added token successfully");
         } else {
             System.out.println("error adding token");
         }
    }

    /**
     * if token is not found return -1
     * @return token stored on internal db
     */
    public String getToken()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String querry = "SELECT * FROM user WHERE KEY_ID = 1" + ";";

        Cursor result = db.rawQuery(querry, null);

        if (result != null && result.getCount()>0) {
            result.moveToFirst();
        } else {
          return "-1";
        }
        String token = result.getString(result.getColumnIndex("token"));
        System.out.println(token);
        db.close();

        return token;

    }

    public class DbHandler extends SQLiteOpenHelper {
        private static final int DB_VERSION = 1;
        private static final String DB_NAME = "MessyFlatmates";
        private static final String TABLE_NAME = "user";
        private static final String KEY_ID = "key_id";
        private static final String USER_ID = "user_id";
        private static final String TOKEN = "token";
        public DbHandler(Context context){
            super(context,DB_NAME, null, DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + USER_ID + " INTEGER, "
                    + TOKEN + " TEXT"
                    +  ")";
            db.execSQL(CREATE_TABLE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            // Drop older table if exist
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            // Create tables again
            onCreate(db);
        }
    }
}
