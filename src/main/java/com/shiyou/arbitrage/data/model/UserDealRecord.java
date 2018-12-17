package com.shiyou.arbitrage.data.model;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * FileName: UserDealRecord
 * Description:
 * Author: ZhangYX
 * Date:  2018/7/24
 */
public class UserDealRecord implements Serializable {
    private Long id;
    private Long create_date;
    private String symbol;
    private BigDecimal deal_amount;
    private BigDecimal price;
    private BigDecimal fee;
    private String fee_asset;
    private Integer type;
    private Long order_id;

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

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }
}
