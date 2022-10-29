package com.djin.apikylin.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @auther dj
 * @date 2022/8/5
 * @note:
 */

public class CarNum {
    @JSONField(ordinal = 1)
    private String country;
    @JSONField(ordinal = 2)
    private String regionname;
    @JSONField(ordinal = 3)
    private String province;
    @JSONField(ordinal = 4)
    private String city;
    @JSONField(ordinal = 5)
    private String cars;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCars() {
        return cars;
    }

    public void setCars(String cars) {
        this.cars = cars;
    }
}
