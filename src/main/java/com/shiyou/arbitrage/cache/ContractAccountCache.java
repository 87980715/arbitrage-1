package com.shiyou.arbitrage.cache;


import com.shiyou.arbitrage.data.model.UserContractAccount;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: com.lanjiejia.demo.cache
 * @Project: lanJieHouseKeeping
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/11/27 0027 10:41
 */
public class ContractAccountCache {

    private static Map<String, UserContractAccount> map = new ConcurrentHashMap<>();

    public static UserContractAccount getContractAccount(String symbol) {
        return map.get(symbol);
    }

    public static void update(String symbol, UserContractAccount userContractAccount) {
        map.put(symbol, userContractAccount);
    }
}
