package com.shiyou.arbitrage.task;

import com.shiyou.arbitrage.cache.BloexOrderCache;
import com.shiyou.arbitrage.cache.FcoinOrderCache;
import com.shiyou.arbitrage.data.model.TradeDepth;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.TimerTask;

/**
 * @Package: com.azoveh.arbitrage.task
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/7 0007 16:51
 */
public class ArbitrageTask extends TimerTask {

    private String symbol;

    public ArbitrageTask(String symbol) {
        this.symbol = symbol;
    }


    @Override
    public void run() {
        try {
            TradeDepth bloexDepth = BloexOrderCache.getTradeDepth(symbol);
            TradeDepth fcoinDepth = FcoinOrderCache.getTradeDepth(symbol.toLowerCase());

            LinkedList<BigDecimal[]> bloexBids = bloexDepth.getBids();
            LinkedList<BigDecimal[]> bloexasks = bloexDepth.getAsks();
            LinkedList<BigDecimal[]> fcoinBids = fcoinDepth.getBids();
            LinkedList<BigDecimal[]> fcoinAsks = fcoinDepth.getAsks();

            BigDecimal bloexBuyOnePrice = bloexBids.get(0)[0]; //买一价
            BigDecimal bloexBuyOnevolume = bloexBids.get(0)[1]; //买一量
            BigDecimal fcoinBuyOnePrice = fcoinBids.get(0)[0];
            BigDecimal fcoinBuyOnevolume = fcoinBids.get(0)[1];

            BigDecimal bloexSellOnePrice = bloexasks.get(0)[0];//卖一价
            BigDecimal bloexSellOnevolume = bloexasks.get(0)[1];//卖一量
            BigDecimal fcoinSellOnePrice = fcoinAsks.get(0)[0];
            BigDecimal fcoinSellOnevolume = fcoinAsks.get(0)[1];
            BigDecimal bloHigh = bloexBuyOnePrice.subtract(fcoinSellOnePrice);
            BigDecimal fcoinHigh = fcoinBuyOnePrice.subtract(bloexSellOnePrice);

            if (bloHigh.compareTo(new BigDecimal(5)) > 0) {
                System.out.println("blo买一价" + bloexBuyOnePrice + "---fcoin卖一价" + fcoinSellOnePrice + "---可以套利：" + bloHigh);

            }

            if (fcoinHigh.compareTo(new BigDecimal(5)) > 0) {
                System.out.println("fcoin买一价" + fcoinBuyOnePrice + "---blo卖一价" + bloexSellOnePrice + "---可以套利：" + fcoinHigh);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
