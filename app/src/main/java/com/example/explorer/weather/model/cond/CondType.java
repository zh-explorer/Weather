package com.example.explorer.weather.model.cond;

/**
 * Created by explorer on 16-4-15.
 */
public class CondType {
    private String code;
    private String txt;
    private String txt_en;
    private String icon;

    public void setCode(String code) {
        this.code = code;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void setTxt_en(String txt_en) {
        this.txt_en = txt_en;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCode() {
        return code;
    }

    public String getTxt() {
        return txt;
    }

    public String getTxt_en() {
        return txt_en;
    }

    public String getIcon() {
        return icon;
    }
}
