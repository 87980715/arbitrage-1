package com.shiyou.arbitrage.data.model;


import java.math.BigDecimal;

/**
 * FileName: UserInfo
 * Description:
 * Author: ZhangYX
 * Date:  2018/3/5
 */
public class UserCoinAccount {
    private BigDecimal available;
    private BigDecimal frozen;

    public UserCoinAccount() {
        this.available = BigDecimal.ZERO;
        this.frozen = BigDecimal.ZERO;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public BigDecimal getFrozen() {
        return frozen;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }
}
