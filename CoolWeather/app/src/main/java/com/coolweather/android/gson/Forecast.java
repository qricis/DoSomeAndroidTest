package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/9/4 13:34
 * @version 1.0.0
 */
public class Forecast {

    public String date;

    @SerializedName("tmp")
    public Temperature mTemperature;

    @SerializedName("cond")
    public More mMore;

    public static class Temperature {

        public String max;

        public String min;

    }

    public static class More {

        @SerializedName("txt")
        public String info;
    }

}
