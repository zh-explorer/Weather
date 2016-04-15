package com.example.explorer.weather.model.cond;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by explorer on 16-4-15.
 */
public class CondList {
    String status;
    List<CondType> cond_info;

    public static List<CondType> handleCondResponse(String json) {
        Gson gson = new Gson();
        CondList condList = gson.fromJson(json, CondList.class);
        if (condList.getStatus()) {
            return condList.cond_info;
        } else {
            return null;
        }
    }

    private boolean getStatus() {
        return status.equals("ok");
    }
}
