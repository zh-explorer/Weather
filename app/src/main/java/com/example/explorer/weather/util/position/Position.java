package com.example.explorer.weather.util.position;

import com.example.explorer.weather.db.WeatherDB;
import com.example.explorer.weather.model.City;
import com.example.explorer.weather.model.CityList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by explorer on 16-3-22.
 *
 */
public class Position {
    public static List<City> handleCitiesResponse(String response) {
        Gson gson = new Gson();
        CityList cityList = gson.fromJson(response, CityList.class);
        if (cityList.getStatus()) {
            return cityList.getCityList();
        }
        else {
            return null;
        }
    }

    public static City findCityByName(String cityName) {
        WeatherDB weatherDB = WeatherDB.getInstance(null);      //maybe cause some problem
        List<City> cityList =  weatherDB.loadCitys();
        for(City city : cityList) {
            if(city.getCity().equals(cityName)) {
                return city;
            }
        }
        return null;        //raise a error will be better.Change it in later version
    }

    /**
     * get the similar city of cityName
     */
    public static List<City> findSimilarCity(String cityName){
        WeatherDB weatherDB = WeatherDB.getInstance(null);
        List<City> cityList = weatherDB.loadCitys();

        Pattern p = Pattern.compile("^"+cityName);
        List<City> similarCity = new ArrayList<>();
        for(City city : cityList) {
            if(p.matcher(city.getCity()).matches()) {
                similarCity.add(city);
            }
        }
        p = Pattern.compile("."+cityName);
        for(City city : cityList) {
            if(p.matcher(city.getCity()).matches()) {
                similarCity.add(city);
            }
        }
        return similarCity;
    }
}
