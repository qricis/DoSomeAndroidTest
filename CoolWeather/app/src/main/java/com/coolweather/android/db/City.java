package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Description
 * <p>
 * id是每个实体类都应该有的字段
 * cityName记录市的名字
 * cityCode记录市的代码
 * provinceId记录当前市所属省的id值
 * @author qricis on 2020/9/3 14:47
 * @version 1.0.0
 */
public class City extends DataSupport {

    private int id;

    private String cityName;

    private int cityCode;

    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
