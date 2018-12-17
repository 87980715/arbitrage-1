package com.shiyou.arbitrage.cache;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContractIndexCache {

    private static Map<String, BigDecimal> map = new ConcurrentHashMap<>();

    public static BigDecimal getContractIndex(String symbol) {
        return map.get(symbol);
    }

    public static void update(String symbol, BigDecimal index) {
        map.put(symbol, index);
    }
}
