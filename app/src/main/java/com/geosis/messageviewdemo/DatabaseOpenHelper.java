package com.geosis.messageviewdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_EARTHQUAKE_TABLE="create table Earthquake("
            +"id integer primary key autoincrement, "
            +"uuid String, "
            +"time String, "
            +"adminregion text, "
            +"latitude real, "
            +"longitude real, "
            +"rank integer, "
            +"desciption integer, "
            +"infosource integer, "
            +"remark text)";

    private Context mContext;

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EARTHQUAKE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Earthquake");
        onCreate(db);
    }
}
