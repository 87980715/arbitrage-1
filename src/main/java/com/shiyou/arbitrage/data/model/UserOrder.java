package com.shiyou.arbitrage.data.model;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * FileName: UserOrder
 * Description:
 * Author: ZhangYX
 * Date:  2018/3/5
 */
public class UserOrder implements Serializable {
    private Long id;
    private Long create_date;
    private String symbol;
    private BigDecimal total_amount;
    private BigDecimal deal_amount;
    private BigDecimal price;
    private BigDecimal avg_price;
    private BigDecimal fee;
    private String fee_asset;
    private Integer type;
    private Integer status;
    private String lever_rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Long create_date) {
        this.create_date = create_date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public BigDecimal getDeal_amount() {
        return deal_amount;
    }

    public void setDeal_amount(BigDecimal deal_amount) {
        this.deal_amount = deal_amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(BigDecimal avg_price) {
        this.avg_price = avg_price;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getFee_asset() {
        return fee_asset;
    }

    public void setFee_asset(String fee_asset) {
        this.fee_asset = fee_asset;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLever_rate() {
        return lever_rate;
    }

    public void setLever_rate(String lever_rate) {
        this.lever_rate = lever_rate;
    }
}
