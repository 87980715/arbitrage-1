package com.shiyou.arbitrage.api;

/**
 * @Package: com.azoveh.bloex.arbitrage.bloex
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/7 0007 10:30
 */
public abstract class MyDataHandler {

    private String market;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public abstract void execute(String message, String symbol);
}
