package com.example.explorer.weather.model;

import android.os.Bundle;

/**
 * Created by explorer on 16-3-22.
 *
 */
public class City {
    private String city;
    private String cnty;
    private String id;
    private String lat;
    private String lon;
    private String prov;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getProv() {
        return prov;
    }

    public void  setProv(String prov) {
        this.prov = prov;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("city", city);
        bundle.putString("cnty", cnty);
        bundle.putString("id", id);
        bundle.putString("lat", lat);
        bundle.putString("lon", lon);
        bundle.putString("prov", prov);
        return bundle;
    }
}