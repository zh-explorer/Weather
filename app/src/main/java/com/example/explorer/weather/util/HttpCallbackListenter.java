package com.example.explorer.weather.util;

/**
 * Created by explorer on 15-11-24.
 *
 */
public interface HttpCallbackListenter {

    public void onFinish(String response);

    public void onError(Exception e);
}
