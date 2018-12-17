package com.shiyou.arbitrage.data.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * FileName: Ticker
 * Description:
 * Author: ZhangYX
 * Date:  2017/12/18
 */
public class Ticker implements Serializable{
    private BigDecimal a = BigDecimal.ZERO;
    private BigDecimal b = BigDecimal.ZERO;
    private BigDecimal l = BigDecimal.ZERO;
    private BigDecimal h = BigDecimal.ZERO;
    private BigDecimal o = BigDecimal.ZERO;
    private BigDecimal c = BigDecimal.ZERO;
    private BigDecimal v = BigDecimal.ZERO;
    private Long t = 0L;

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }

    public BigDecimal getL() {
        return l;
    }

    public void setL(BigDecimal l) {
        this.l = l;
    }

    public BigDecimal getH() {
        return h;
    }

    public void setH(BigDecimal h) {
        this.h = h;
    }

    public BigDecimal getO() {
        return o;
    }

    public void setO(BigDecimal o) {
        this.o = o;
    }

    public BigDecimal getC() {
        return c;
    }

    public void setC(BigDecimal c) {
        this.c = c;
    }

    public BigDecimal getV() {
        return v;
    }

    public void setV(BigDecimal v) {
        this.v = v;
    }

    public Long getT() {
        return t;
    }

    public void setT(Long t) {
        this.t = t;
    }
}
