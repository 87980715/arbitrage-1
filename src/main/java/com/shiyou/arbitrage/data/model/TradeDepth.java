package com.shiyou.arbitrage.data.model;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * FileName: TradeDepth
 * Description:
 * Author: ZhangYX
 * Date:  2018/3/5
 */
public class TradeDepth {
    private LinkedList<BigDecimal[]> bids = new LinkedList<>();
    private LinkedList<BigDecimal[]> asks = new LinkedList<>();

    public LinkedList<BigDecimal[]> getBids() {
        return bids;
    }

    public void setBids(LinkedList<BigDecimal[]> bids) {
        this.bids = bids;
    }

    public LinkedList<BigDecimal[]> getAsks() {
        return asks;
    }

    public void setAsks(LinkedList<BigDecimal[]> asks) {
        this.asks = asks;
    }
}
