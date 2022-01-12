package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Description
 * <p>
 * 根据返回的json数据basic创建的实体类Basic
 * @author qricis on 2020/9/4 11:26
 * @version 1.0.0
 */
public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    @SerializedName("update")
    public Update mUpdate;

    public static class Update {

        @SerializedName("loc")
        public String updateTime;
    }

}
