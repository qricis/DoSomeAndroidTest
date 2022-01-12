package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/9/4 13:23
 * @version 1.0.0
 */
public class Suggestion {

    @SerializedName("comf")
    public Comfort mComfort;

    @SerializedName("cw")
    public CarWash mCarWash;

    @SerializedName("sport")
    public Sport mSport;

    public static class Comfort {

        @SerializedName("txt")
        public String info;

    }

    public static class CarWash {

        @SerializedName("txt")
        public String info;

    }

    public static class Sport {

        @SerializedName("txt")
        public String info;

    }

}
