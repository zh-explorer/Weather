package com.example.explorer.weather.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.explorer.weather.R;
import com.example.explorer.weather.model.City;
import com.example.explorer.weather.model.wertherData.WeatherData;
import com.example.explorer.weather.util.position.Position;


import static com.example.explorer.weather.util.position.Position.locationInit;
import static com.example.explorer.weather.util.position.PositionUtil.checkDBData;
import static com.example.explorer.weather.util.weather.Weather.getWeather;
import static com.example.explorer.weather.util.weather.Weather.getWeatherIcon;

public class MainActivity extends AppCompatActivity {

    private Button search;
    private TextView response;
    private TextView titleCity;
    private ImageView weatherIcon;
    private ProgressDialog progressDialog;
    private Position position;
    private City city;
    private WeatherData weatherData;

    public static final int GET_CITY_NAME = 0;
    public static final int GET_WEATHER = 1;
    public static final int GET_WEATHER_ICON = 2;

    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_CITY_NAME:
                    city = (City) msg.obj;
                    updateCity();
                    break;
                case GET_WEATHER:
                    weatherData = (WeatherData) msg.obj;
                    updateWeather();
                    break;
                case GET_WEATHER_ICON:
                    Bitmap bmp = (Bitmap) msg.obj;
                    showWeatherPic(bmp);
                default:
                    break;
            }
        }
    };

    private void updateCity() {
        if (city != null) {
            titleCity.setText(city.getCity());
            getWeather(city, this);
        } else {
            titleCity.setText("city not found");
        }
    }

    private void updateWeather() {
        if (weatherData != null) {
            response.setText("当前的空气状况为:" + weatherData.aqi.city.qlty);
            String weatherCode = weatherData.now.cond.code;
            getWeatherIcon(weatherCode, this);
        } else {
            response.setText("can't get city date");
        }
    }

    private void showWeatherPic(Bitmap bmp) {
        if (weatherIcon != null) {
            weatherIcon.setImageBitmap(bmp);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (Button) findViewById(R.id.search_button);
        response = (TextView) findViewById(R.id.response_text);
        titleCity = (TextView) findViewById(R.id.city_text);
        weatherIcon = (ImageView) findViewById(R.id.weather_icon);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseAreaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        checkDBData(this);
        locationInit(this);
        position = Position.getInstance();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getBundleExtra("return_city");
                    city = new City(bundle);
                    updateCity();
                    position.setCity(city);
                }
        }
    }

    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    public void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        position.removeLister(this);
    }
}
