package com.djin.api.service;

import com.djin.api.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @auther dj
 * @date 2022/7/19
 * @note:查询统计数据
 */
@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataMapper dataMapper;
    @Override
    public BigDecimal getGMV(int date) {
        return dataMapper.getGMV(date);
    }

}
