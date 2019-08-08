package com.example.messy_flatmates.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;

/**
 * @version 1.0
 * The InternalDBHandler uses the inbuilt SQLite database on android. Contains DBhandler class
 * @author Jordan Gardiner
 */
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

        ContentValues values = new ContentValues();
        values.putNull("user_id");
        values.putNull("token");
        long count = db.update("user",values, "KEY_ID = ?", new String[]{"1"});

        if (count >= 1) {
            System.out.println("logout query success");
            db.close();
            return true;
        } else {
            System.out.println("logout query failure");
            db.close();
            return false;
        }
    }

    /**
     * tries to update row 1 with user_id and token, If it does not exist then it will insert it.
     * @param user_id
     * @param token
     * @return true if successful, false if error.
     */
    public boolean addSession(String user_id, String token){

        SQLiteDatabase db = myhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("user_id", user_id);
        values.put("token", token);

        long count = db.update("user",values, "KEY_ID = ?", new String[]{"1"});

        if (count >= 1) {
            System.out.println("logout query success");
            db.close();
            return true;
        } else {
            long counter = db.insert("user", null, values);
            if (counter >= 1){
                System.out.println("Added token successfully");
                db.close();
                return true;
            } else {
                System.out.println("error adding token");
                db.close();
                return false;
            }
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
            System.out.println("Could not fetch token from internal db");
            return null;
        }
        String token = result.getString(result.getColumnIndex("token"));
        System.out.println(token);
        db.close();

        return token;

    }

    /**
     * Class to construct the database
     * @author Jordan Gardiner
     */
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
                    + TOKEN + " TEXT "
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
