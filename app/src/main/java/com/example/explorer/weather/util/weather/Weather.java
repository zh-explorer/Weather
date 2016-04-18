package com.example.explorer.weather.util.weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;

import com.example.explorer.weather.activity.MainActivity;
import com.example.explorer.weather.db.WeatherDB;
import com.example.explorer.weather.model.City;
import com.example.explorer.weather.model.cond.CondType;
import com.example.explorer.weather.model.wertherData.WeatherData;
import com.example.explorer.weather.util.HttpCallBackListenterHex;
import com.example.explorer.weather.util.HttpCallbackListenter;
import com.example.explorer.weather.util.HttpUtil;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import static com.example.explorer.weather.util.HttpUtil.sendHttpRequest;

/**
 * Created by explorer on 16-4-14.
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
                WeatherData weatherData = WeatherData.handleWeatherResponse(response);
                if (weatherData == null || !weatherData.status.equals("ok")) {
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

    public static void getWeatherIcon(final String code, final Context context) {

        try {
            FileInputStream ins = context.openFileInput(code + ".png");
            Bitmap returnBmp = BitmapFactory.decodeStream(ins);
            Message message = new Message();
            message.what = MainActivity.GET_WEATHER_ICON;
            message.obj = returnBmp;
            ((MainActivity) context).handler.sendMessage(message);
            return;

        } catch (FileNotFoundException ignored) {

        }


        WeatherDB weatherDB = WeatherDB.getInstance(context);
        List<CondType> condLists = weatherDB.loadConds();
        String iconUrl = null;
        for (CondType cond : condLists) {
            if (cond.getCode().equals(code)) {
                iconUrl = cond.getIcon();
                break;
            }
        }
        HttpUtil.sendHttpRequest(iconUrl, new HttpCallBackListenterHex() {
            @Override
            public void onFinish(InputStream response) {
                Bitmap returnBmp = BitmapFactory.decodeStream(response);
                Message message = new Message();
                message.what = MainActivity.GET_WEATHER_ICON;
                message.obj = returnBmp;
                ((MainActivity) context).handler.sendMessage(message);

                try {
                    FileOutputStream out = context.openFileOutput(code + ".png", Context.MODE_PRIVATE);
                    if(returnBmp.compress(Bitmap.CompressFormat.PNG,90,out)) {
                        out.close();
                        out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

}
