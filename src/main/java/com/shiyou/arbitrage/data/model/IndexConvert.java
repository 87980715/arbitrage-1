package com.shiyou.arbitrage.data.model;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName: IndexConvert
 * Description:
 * Author: ZhangYX
 * Date:  2018/3/7
 */
public class IndexConvert {
    private static Map<String, String> INDEX_MAP = new HashMap<String, String>();

    /*static {
        INDEX_MAP.put("BTCUSDT_C", "bitcoin");
        INDEX_MAP.put("ETHUSDT_C", "ethereum");
        INDEX_MAP.put("BCHUSDT_C", "bitcoin-cash");
        INDEX_MAP.put("LTCUSDT_C", "litecoin");
        INDEX_MAP.put("ETCUSDT_C", "ethereum-classic");
        INDEX_MAP.put("NULSUSDT_C", "nuls");
    }*/

    public static String get(String symbol) {
        return INDEX_MAP.get(symbol);
    }

    public static void add(String symbol, String indexName) {
        INDEX_MAP.put(symbol, indexName);
    }
}
