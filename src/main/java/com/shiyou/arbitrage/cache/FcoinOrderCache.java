package com.shiyou.arbitrage.cache;

import com.shiyou.arbitrage.data.model.TradeDepth;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.azoveh.arbitrage.cache
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/7 0007 14:38
 */
public class FcoinOrderCache {

    private static Map<String , TradeDepth> fcoinDepthMap = new HashMap<>();

    public static TradeDepth getTradeDepth(String symbol){
        return fcoinDepthMap.get(symbol);
    }

    public static void update(String symbol,TradeDepth tradeDepth) {
        fcoinDepthMap.put(symbol, tradeDepth);
        //System.out.println("Fcoin 深度："+ ObjectMapper.toJson(fcoinDepthMap));
    }

}
