package com.shiyou.arbitrage.data.model;


import java.math.BigDecimal;

/**
 * FileName: Trade
 * Description:
 * Author: ZhangYX
 * Date:  2018/3/5
 */
public class Trade {
    private BigDecimal amount;
    private BigDecimal price;
    private Integer type;
    private Long date;

    public Trade(DealDataRecord dealDataRecord) {
        this.setAmount(dealDataRecord.getV());
        this.setPrice(dealDataRecord.getP());
        this.setType(dealDataRecord.getS());
        this.setDate(dealDataRecord.getT().getTime());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
