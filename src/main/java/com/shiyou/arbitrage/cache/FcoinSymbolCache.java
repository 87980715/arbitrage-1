package com.shiyou.arbitrage.cache;

import com.shiyou.arbitrage.data.model.Symbol;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.azoveh.arbitrage.cache
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/13 0013 11:36
 */
public class FcoinSymbolCache {

    private static Map<String, Symbol> fcoinSymbolMap = new HashMap<>();

    public static Symbol getFcoinSymbol(String symbol) {
        return fcoinSymbolMap.get(symbol);
    }

    public static void update(String symbol, Symbol fcoinSymbol) {
        fcoinSymbolMap.put(symbol, fcoinSymbol);
    }
}
