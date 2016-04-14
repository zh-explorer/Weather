package com.example.explorer.weather.model.wertherData;

/**
 * Created by explorer on 16-4-13.
 */
public class DailyForecast {
    public Astro astro;     //time of sunrise and sun set
    public DCond cond;
    public String date;
    public String hum;      //humidity
    public String pcpn;     //precipitation
    public String pop;      //precipitation probability
    public String pres;     //pressure
    public Tmp tmp;         //temperature
    public String vis;      //visibility
    public Wind wind;
}
