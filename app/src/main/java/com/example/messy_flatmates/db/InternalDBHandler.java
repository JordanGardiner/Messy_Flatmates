package com.example.messy_flatmates.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class InternalDBHandler {

    InternalDB myhelper;

    public InternalDBHandler(Context context)
    {
        myhelper = new InternalDB(context);
    }


    public long updateToken(String user_id , String newToken)
    {
        long count =0;

        SQLiteDatabase db = myhelper.getWritableDatabase();
        System.out.println("got writable database");
        String[] whereArgs = {user_id};
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(myhelper.TOKEN, newToken);
            count = db.update(myhelper.TABLE_NAME, contentValues, myhelper.USER_ID + " = ?", whereArgs);
        } catch (SQLiteException e){
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(myhelper.USER_ID, user_id);
                contentValues.put(myhelper.TOKEN, newToken);
                count = db.insert(myhelper.TABLE_NAME, null, contentValues);
            } catch (SQLiteException x){
                db.needUpgrade(1);
                ContentValues contentValues = new ContentValues();
                contentValues.put(myhelper.USER_ID, user_id);
                contentValues.put(myhelper.TOKEN, newToken);
                count = db.insert(myhelper.TABLE_NAME, null, contentValues);

            }
        }

        return count;
    }

    public String getToken()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myhelper.USER_ID,myhelper.TOKEN};
        Cursor cursor =db.query(myhelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid = cursor.getInt(cursor.getColumnIndex(myhelper.USER_ID));
            String token =cursor.getString(cursor.getColumnIndex(myhelper.TOKEN));
            buffer.append(cid+ "   " + token + " \n");
        }
        return buffer.toString();
    }





    public class InternalDB extends SQLiteOpenHelper {


        public static final String DATABASE_NAME = "MessyFlatmates";    // Database Name
        public static final String TABLE_NAME = "flat_user";   // Table Name
        public static final int DATABASE_Version = 1;    // Database Version
        public static final String USER_ID ="_id";     // Column I (Primary Key)
        public static final String TOKEN = "token";    // Column II

        private Context context;

        public InternalDB(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        //creates database on first iter of lifecycle
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            System.out.println("attempting create");
            try {
                String create_User_table = "CREATE TABLE flat_user " +
                        "( " + USER_ID  + " INT(11) PRIMARY KEY, " + TOKEN + " VARCHAR(32)" +
                        ");";
                System.out.println(create_User_table);
                sqLiteDatabase.execSQL(create_User_table);
                System.out.println("created Database");
            } catch(SQLException e){
                System.out.println(e.getMessage());
                System.out.println("SQL ERROR");

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            this.onCreate(sqLiteDatabase);
        }


    }

}
