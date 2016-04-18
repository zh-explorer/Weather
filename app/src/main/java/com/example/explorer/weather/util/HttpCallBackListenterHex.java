package com.example.explorer.weather.util;

import java.io.InputStream;

/**
 * Created by explorer on 16-4-18.
 */
public interface HttpCallBackListenterHex {
    public void onFinish(InputStream response);

    public void onError(Exception e);
}
