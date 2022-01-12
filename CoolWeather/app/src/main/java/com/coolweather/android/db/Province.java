package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Description
 * <p>
 * id是每个实体该有的字段
 * provinceName记录省的名字
 * provinceCode记录省的代号
 * @author qricis on 2020/9/3 14:47
 * @version 1.0.0
 */
public class Province extends DataSupport {

    private int id;

    private String provinceName;

    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
