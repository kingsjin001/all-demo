package com.djin.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

/**
 * @auther dj
 * @date 2022/7/19
 * @note:编写SQL查询数据表
 */
@Mapper
public interface DataMapper {
    //获取数据
    @Select("select sum(order_amount) order_amount " +
            "from product_stats_2021 where toYYYYMMDD(stt)=#{date}")
    public BigDecimal getGMV(int date);
}
