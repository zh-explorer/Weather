package com.example.explorer.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by explorer on 16-3-22.
 *
 */
public class WeatherOpenHelper extends SQLiteOpenHelper{

    /**
     * String to  create city table
     */

    public static final String CREATE_CITY = "Create table City (" +
            "id text primary key," +
            "city text," +
            "cnty text," +   //Country
            "lat text," +    //latitude
            "lon text," +    //longitude
            "prov text)";    //province

    public static final String CREATE_COND = "Create table Cond (" +
            "id text primary key," +
            "code text," +
            "txt text," +
            "txt_en text," +
            "icon text)";
    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
