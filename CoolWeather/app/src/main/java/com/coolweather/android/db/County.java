package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Description
 * <p>
 * id是每个实体类都应该有的字段
 * countyName记录县的名字
 * weatherId记录天气的id值
 * cityId记录当前县所属市的id
 * @author qricis on 2020/9/3 14:47
 * @version 1.0.0
 */
public class County extends DataSupport {

    private int id;

    private String countyName;

    private String weatherId;

    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
