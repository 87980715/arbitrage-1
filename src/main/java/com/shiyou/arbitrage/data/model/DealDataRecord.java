package com.shiyou.arbitrage.data.model;


import javax.annotation.Nonnull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * FileName: DealDataRecord
 * Description:
 * Author: ZhangYX
 * Date:  2017/12/27
 */
public class DealDataRecord implements Comparable<DealDataRecord>,Serializable {

    //时间
    private Date t;
    //价格
    private BigDecimal p;
    //数量
    private BigDecimal v;
    //成交类型：1：买，2：卖
    private Integer s;

    private int i;

    public Date getT() {
        return t;
    }

    public void setT(Date t) {
        this.t = t;
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

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public int compareTo(@Nonnull DealDataRecord record) {
        return t.compareTo(record.getT());
    }
}
