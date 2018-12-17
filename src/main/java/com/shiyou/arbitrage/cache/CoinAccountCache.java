package com.shiyou.arbitrage.cache;


import com.shiyou.arbitrage.data.model.UserCoinAccount;

import java.util.HashMap;
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

    private static Map<String, Map<String, UserCoinAccount>> map = new ConcurrentHashMap<>();

    public static UserCoinAccount getCoinAccount(String platform, String symbol) {
        Map<String, UserCoinAccount> accountMap = map.get(platform);
        return accountMap.get(symbol);
    }

    public static void update(String platform, String symbol, UserCoinAccount coinAccount) {
        Map<String, UserCoinAccount> accountMap = map.get(platform);
        if (accountMap == null){
            accountMap = new HashMap<>();
        }
        accountMap.put(symbol, coinAccount);
    }
}
