package com.shiyou.arbitrage.cache;


import com.shiyou.arbitrage.data.model.UserContractAccount;
import com.shiyou.arbitrage.data.model.UserContractAccount;

import java.util.HashMap;
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

    private static Map<String, Map<String, UserContractAccount>> map = new ConcurrentHashMap<>();

    public static UserContractAccount getContractAccount(String platform, String symbol) {
        Map<String, UserContractAccount> accountMap = map.get(platform);
        return accountMap.get(symbol);
    }

    public static void update(String platform, String symbol, UserContractAccount contractAccount) {
        Map<String, UserContractAccount> accountMap = map.get(platform);
        if (accountMap == null){
            accountMap = new HashMap<>();
        }
        accountMap.put(symbol, contractAccount);
    }

}
