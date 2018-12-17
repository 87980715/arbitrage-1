package com.shiyou.arbitrage.cache;


import com.shiyou.arbitrage.data.model.UserCoinAccount;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: com.lanjiejia.demo.cache
 * @Project: lanJieHouseKeeping
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/11/27 0027 10:47
 */
public class CoinAccountCache {

    private static Map<String, UserCoinAccount> map = new ConcurrentHashMap<>();

    public static UserCoinAccount getCoinAccount(String symbol) {
        return map.get(symbol);
    }

    public static void update(String symbol, UserCoinAccount coin) {
        map.put(symbol, coin);
    }
}
