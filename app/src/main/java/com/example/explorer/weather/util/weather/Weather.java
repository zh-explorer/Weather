package com.example.explorer.weather.util.weather;

import android.content.Context;
import android.os.Message;

import com.example.explorer.weather.activity.MainActivity;
import com.example.explorer.weather.model.City;
import com.example.explorer.weather.model.wertherData.WeatherData;
import com.example.explorer.weather.util.HttpCallbackListenter;

import static com.example.explorer.weather.util.HttpUtil.sendHttpRequest;

/**
 * Created by explorer on 16-4-14.
 *
 */
public class Weather {
    public static void getWeather(City city, final Context context) {
        StringBuilder url = new StringBuilder();
        url.append("https://api.heweather.com/x3/weather");
        url.append("?cityid=");
        url.append(city.getId());
        url.append("&key=");
        url.append("9bcab69f01614c91a5a5c652b7999ee0");
        sendHttpRequest(url.toString(), new HttpCallbackListenter() {

            @Override
            public void onFinish(String response) {
                WeatherData weatherData =  WeatherData.handleWeatherResponse(response);
                if(weatherData==null || !weatherData.status.equals("ok")){
                    return;
                }
                Message msg = new Message();
                msg.what = MainActivity.GET_WEATHER;
                msg.obj = weatherData;
                ((MainActivity) context).handler.sendMessage(msg);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }


}
