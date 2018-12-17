package com.shiyou.arbitrage.data.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * FileName: UserContractAccount
 * Description:
 * Author: ZhangYX
 * Date:  2018/8/21
 */
public class UserContractAccount {

    private BigDecimal rights;//账户权益
    private BigDecimal balance;//账户余额
    private BigDecimal unrealizedProfitLoss;//未实现盈亏
    private BigDecimal available;//可用保证金
    private BigDecimal orderMargin;//委托保证金
    private BigDecimal positionMargin;//持仓保证金
    private Map<String, BigDecimal> coins;//资产数量

    public BigDecimal getRights() {
        return rights;
    }

    public void setRights(BigDecimal rights) {
        this.rights = rights;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getUnrealizedProfitLoss() {
        return unrealizedProfitLoss;
    }

    public void setUnrealizedProfitLoss(BigDecimal unrealizedProfitLoss) {
        this.unrealizedProfitLoss = unrealizedProfitLoss;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public BigDecimal getOrderMargin() {
        return orderMargin;
    }

    public void setOrderMargin(BigDecimal orderMargin) {
        this.orderMargin = orderMargin;
    }

    public BigDecimal getPositionMargin() {
        return positionMargin;
    }

    public void setPositionMargin(BigDecimal positionMargin) {
        this.positionMargin = positionMargin;
    }

    public Map<String, BigDecimal> getCoins() {
        return coins;
    }

    public void setCoins(Map<String, BigDecimal> coins) {
        this.coins = coins;
    }
}
