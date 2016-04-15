package com.example.explorer.weather.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by explorer on 16-3-16.
 *
 */
public class CityList {
    private List<City> city_info;
    private String status;

    public static List<City> handleCitiesResponse(String response) {
        Gson gson = new Gson();
        CityList cityList = gson.fromJson(response, CityList.class);
        if (cityList.getStatus()) {
            return cityList.getCityList();
        } else {
            return null;    //raise a error will be better.Change it in later version
        }
    }

    public List<City> getCityList() {
        return city_info;
    }

    public boolean getStatus() {
        return status.equals("ok");
    }
}
