package com.example.explorer.weather.util.position;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.example.explorer.weather.R;
import com.example.explorer.weather.activity.MainActivity;
import com.example.explorer.weather.model.City;
import com.example.explorer.weather.util.HttpCallbackListenter;
import com.example.explorer.weather.util.HttpUtil;

import java.util.List;

import static com.example.explorer.weather.util.position.PositionUtil.findCityName;

/**
 * Created by explorer on 16-3-22.
 */
public class Position implements LocationListener {

    private String latitude;

    private String longitude;

    private LocationManager locationManager;

    private String provider;

    private static Position position = null;

    private Context context;

    private City city;

    private Position(final Context context) {
        this.context = context;
        new Thread(new Runnable() {
            @Override
            public void run() {
                locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

                List<String> providerList = locationManager.getProviders(true);
                if (providerList.contains(LocationManager.GPS_PROVIDER)) {
                    provider = LocationManager.GPS_PROVIDER;
                } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
                    provider = LocationManager.NETWORK_PROVIDER;
                } else {
                    Toast.makeText(context, R.string.no_location, Toast.LENGTH_LONG).show();
                    return;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                }
                Location location = locationManager.getLastKnownLocation(provider);

                if (location != null) {
                    longitude = Double.toString(location.getLongitude());
                    latitude = Double.toString(location.getLatitude());
                } else {
                    longitude = null;
                    latitude = null;
                }

                ((MainActivity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                    && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for Activity#requestPermissions for more details.
                                return;
                            }
                        }
                        locationManager.requestLocationUpdates(provider, 5000, 1, Position.this);
                    }
                });
                getLocationCity(context);
            }
        }).start();
    }

    private void getLocationCity(final Context context) {
        if(longitude == null&&latitude == null) {
            return;
        }
        StringBuilder url = new StringBuilder();

        url.append("http://maps.google.cn/maps/api/geocode/json?");
        url.append("latlng=");
        url.append(latitude);
        url.append(",");
        url.append(longitude);
        url.append("&sensor=false");
        HttpUtil.sendHttpRequest(url.toString(), new HttpCallbackListenter() {
            @Override
            public void onFinish(String response) {
                city = findCityName(response, context);
                Message message = new Message();
                message.what = MainActivity.GET_CITY_NAME;
                message.obj = city;
                ((MainActivity) context).handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });

    }

    public static void locationInit(Context context) {
        position = new Position(context);
    }

    public static Position getInstance() {
        return position;
    }

    public void removeLister(Context context) {
        if (locationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            locationManager.removeUpdates(this);
            locationManager = null;
        }
    }

    /**
     * Get Data from position instance
     */
    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
        removeLister(context);
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = Double.toString(location.getLongitude());
        latitude = Double.toString(location.getLatitude());
        getLocationCity(context);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
