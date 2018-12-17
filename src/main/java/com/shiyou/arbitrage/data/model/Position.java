package com.shiyou.arbitrage.data.model;


import java.math.BigDecimal;

/**
 * FileName: Position
 * Description:
 * Author: ZhangYX
 * Date:  2018/3/5
 */
public class Position {
    private String symbol;
    private Integer type;
    private BigDecimal margin;
    private BigDecimal amount;
    //开仓平均价
    private BigDecimal entry_price;
    //创建日期
    private Long create_date;
    //杠杆倍数
    private String lever_rate;
    //预计爆仓价
    private BigDecimal force_burst_price;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getEntry_price() {
        return entry_price;
    }

    public void setEntry_price(BigDecimal entry_price) {
        this.entry_price = entry_price;
    }

    public Long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Long create_date) {
        this.create_date = create_date;
    }

    public String getLever_rate() {
        return lever_rate;
    }

    public void setLever_rate(String lever_rate) {
        this.lever_rate = lever_rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getForce_burst_price() {
        return force_burst_price;
    }

    public void setForce_burst_price(BigDecimal force_burst_price) {
        this.force_burst_price = force_burst_price;
    }
}
