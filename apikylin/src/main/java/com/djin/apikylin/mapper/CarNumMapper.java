package com.djin.apikylin.mapper;

import com.djin.apikylin.entity.CarNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther dj
 * @date 2022/8/5
 * @note:
 */
@Mapper
public interface CarNumMapper {
    @Select("select  \n" +
            "tm.country, \n" +
            "tm.regionname, \n" +
            "tm.province, \n" +
            "tm.city,  \n" +
            "count(distinct tf.car_oneid) as cars \n" +
            "from kylintestdb.tf_vhcorder as tf inner join kylintestdb.td_vhcorder as td  on td.dimid = tf.dimid  \n" +
            "inner join  kylintestdb.tm_dlr_address_info as tm on  tf.dlr_oneid = tm.dealer_code   \n" +
            "left join  kylintestdb.tg_car_service_this_year as tg on tf.car_oneid = tg.car_id    \n" +
            "where  tf.dtime  between  20200101 and 20200131    \n" +
            "group by tm.COUNTRY,tm.REGIONNAME,tm.PROVINCE,tm.CITY")
    public List<CarNum> getCarNumbers();
}
