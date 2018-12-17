package com.shiyou.arbitrage.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LastDealPriceCache {
    private static Logger logger = LoggerFactory.getLogger(LastDealPriceCache.class);

    private static Map<String, BigDecimal> map = new ConcurrentHashMap<>();

    public static BigDecimal getLastDealPrice(String symbol) {
        return map.get(symbol);
    }

    public static void update(String symbol, BigDecimal lastDealPrice) {
        map.put(symbol, lastDealPrice);
       //logger.info(">>>>>>>>>>>>>>>>>.修改缓存最新成交价："+ ObjectMapper.toJson(map));
    }
}
