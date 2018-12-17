package com.shiyou.arbitrage.data.model;


import javax.annotation.Nonnull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * FileName: OrderDataRecord
 * Description:
 * Author: ZhangYX
 * Date:  2017/12/27
 */
public class OrderDataRecord implements Comparable<OrderDataRecord>,Serializable {

    //订单类型：1：买，2：卖
    private Integer s;
    //价格
    private BigDecimal p;
    //数量
    private BigDecimal v;

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public BigDecimal getP() {
        return p;
    }

    public void setP(BigDecimal p) {
        this.p = p;
    }

    public BigDecimal getV() {
        return v;
    }

    public void setV(BigDecimal v) {
        this.v = v;
    }

    public void addRemainingAmount(BigDecimal value) {
        v = v.add(value);
    }

    public void subtractRemainingAmount(BigDecimal value) {
        v = v.subtract(value);
    }

    @Override
    public int compareTo(@Nonnull OrderDataRecord record) {
        return p.compareTo(record.getP());
    }
}
