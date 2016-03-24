package com.example.explorer.weather.model;

import java.util.List;

/**
 * Created by explorer on 16-3-16.
 *
 */
public class CityList {
    private List<City> city_info;
    private String status;

    public List<City> getCityList() {
        return city_info;
    }

    public boolean getStatus() {
        return status.equals("ok");
    }
}
