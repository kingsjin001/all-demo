package com.djin.api.service;

import java.math.BigDecimal;

/**
 * @auther dj
 * @date 2022/7/19
 * @note:
 */
public interface DataService {
    //获取某一天的总交易额

    public BigDecimal getGMV(int date);
}
