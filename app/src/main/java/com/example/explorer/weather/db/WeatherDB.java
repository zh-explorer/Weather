package com.example.explorer.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.explorer.weather.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by explorer on 16-3-22.
 */
public class WeatherDB {
    /**
     * Database name
     */
    public static final String DB_NAME = "weather";

    /**
     * Database version
     */
    public static final int VERSION = 1;

    private List<City> cityList;

    private static WeatherDB weatherDB;

    private SQLiteDatabase db;

    private WeatherDB(Context context) {
        WeatherOpenHelper dbHelper = new WeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
        cityList = null;
    }

    public synchronized static WeatherDB getInstance(Context context) {
        if (weatherDB == null) {
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    /**
     * Get the list of all citys
     **/
    public List<City> loadCities() {
        if (cityList == null) {
            cityList = new ArrayList<>();
            Cursor cursor = db.query("City", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    City city = new City();
                    city.setCity(cursor.getString(cursor.getColumnIndex("city")));
                    city.setCnty(cursor.getString(cursor.getColumnIndex("cnty")));
                    city.setId(cursor.getString(cursor.getColumnIndex("id")));
                    city.setLat(cursor.getString(cursor.getColumnIndex("lat")));
                    city.setLon(cursor.getString(cursor.getColumnIndex("lon")));
                    city.setProv(cursor.getString(cursor.getColumnIndex("prov")));
                    cityList.add(city);
                } while (cursor.moveToNext());

            } else {
                return null;       //Maybe raise a error is better.Will be change in later version
            }
            cursor.close();
        }
        return cityList;
    }

    /**
     * Get the list of citys whit prov
     **/
    public List<City> loadCities(String prov) {
        List<City>allCityList =  loadCities();
        List<City>cityList = new ArrayList<>();
        for(City city: allCityList) {
            if(Pattern.matches("^"+city.getProv()+".*?",prov)) {
                cityList.add(city);
            }
        }
        return cityList;
    }

    public void saveCities(List<City> cityList) {
        if (cityList == null) {
            return;
        }
        for (City city : cityList) {
            ContentValues values = new ContentValues();
            values.put("city", city.getCity());
            values.put("cnty", city.getCity());
            values.put("id", city.getId());
            values.put("lat", city.getLat());
            values.put("lon", city.getLon());
            values.put("prov", city.getProv());
            db.insert("City", null, values);
        }
    }
}
