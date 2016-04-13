package com.example.explorer.weather.util.position;

import android.content.Context;
import android.widget.Toast;

import com.example.explorer.weather.R;
import com.example.explorer.weather.activity.MainActivity;
import com.example.explorer.weather.db.WeatherDB;
import com.example.explorer.weather.model.City;
import com.example.explorer.weather.model.CityList;
import com.example.explorer.weather.util.HttpCallbackListenter;
import com.example.explorer.weather.util.HttpUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by explorer on 16-3-22.
 */
public class PositionUtil {
    public static List<City> handleCitiesResponse(String response) {
        Gson gson = new Gson();
        CityList cityList = gson.fromJson(response, CityList.class);
        if (cityList.getStatus()) {
            return cityList.getCityList();
        } else {
            return null;    //raise a error will be better.Change it in later version
        }
    }

    public static City findCityByName(String cityName, Context context) {
        WeatherDB weatherDB = WeatherDB.getInstance(context);      //maybe cause some problem
        List<City> cityList = weatherDB.loadCities();
        for (City city : cityList) {
            if (city.getCity().equals(cityName)) {
                return city;
            }
        }
        return null;        //raise a error will be better.Change it in later version
    }

    /**
     * get the similar city of cityName
     */
    public static List<City> findSimilarCity(String cityName, Context context) {
        WeatherDB weatherDB = WeatherDB.getInstance(context);
        List<City> cityList = weatherDB.loadCities();

        Pattern p = Pattern.compile("^" + cityName + ".*?");
        List<City> similarCity = new ArrayList<>();
        for (City city : cityList) {
            if (p.matcher(city.getCity()).matches()) {
                similarCity.add(city);
            }
        }
        p = Pattern.compile("." + cityName);
        for (City city : cityList) {
            if (p.matcher(city.getCity()).matches()) {
                similarCity.add(city);
            }
        }
        return similarCity;
    }

    /**
     * Check if there are city list in database.If dou't find,query fron server;
     */
    public static void checkDBData(final Context context) {
        final WeatherDB weatherDB = WeatherDB.getInstance(context);
        List<City> cityList = weatherDB.loadCities();
        final MainActivity activity = (MainActivity) context;
        if (cityList == null) {
            activity.showProgressDialog();
            String url = context.getString(R.string.get_cities);
            HttpUtil.sendHttpRequest(url, new HttpCallbackListenter() {
                @Override
                public void onFinish(String response) {
                    List<City> getCityList = handleCitiesResponse(response);
                    weatherDB.saveCities(getCityList);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.closeProgressDialog();
                        }
                    });
                }

                @Override
                public void onError(Exception e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.closeProgressDialog();
                            Toast.makeText(activity, R.string.load_cites_fail, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    public static City findCityName(String response, Context context) {

        WeatherDB weatherDB = WeatherDB.getInstance(context);
        //List<City> cityList = weatherDB.loadCities();
        try {
            JSONObject position = new JSONObject(response);
            JSONArray array = position.getJSONArray("results");
            if (array.length() > 0) {
                JSONArray addrArray = array.getJSONObject(0).getJSONArray("address_components");
                //to improve validity of city find,find prov first
                String prov = getProvName(addrArray);
                List<City> cityList = weatherDB.loadCities(prov);
                for (int i = 0; i < addrArray.length(); i++) {
                    String addrString = addrArray.getJSONObject(i).getString("long_name");
                    for (City city : cityList) {
                        if (Pattern.matches("^" + city.getCity() + ".*?", addrString)) {
                            return city;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getProvName(JSONArray addrArray) {
        try {
            for (int i = 0; i < addrArray.length(); i++) {
                JSONArray types = addrArray.getJSONObject(i).getJSONArray("types");
                for (int j = 0; j < types.length(); j++) {
                    if (types.getString(j).equals("administrative_area_level_1")) {
                        return addrArray.getJSONObject(i).getString("long_name");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
