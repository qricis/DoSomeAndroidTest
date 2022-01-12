package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/9/4 11:30
 * @version 1.0.0
 */
public class AQI {

    @SerializedName("city")
    public AQICity mAQICity;

    public static class AQICity {

        public String aqi;

        public String pm25;

    }
}
