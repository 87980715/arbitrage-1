package com.shiyou.arbitrage.data.model;

public class DataResult {

    /**
     * 事件
     */
    private String e;

    /**
     * 交易对
     */
    private String s;

    /**
     * 数据
     */
    private Object d;

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Object getD() {
        return d;
    }

    public void setD(Object d) {
        this.d = d;
    }
}
