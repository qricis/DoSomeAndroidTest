package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/9/4 13:20
 * @version 1.0.0
 */
public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More mMore;

    public static class More {

        @SerializedName("txt")
        public String info;
    }
}
