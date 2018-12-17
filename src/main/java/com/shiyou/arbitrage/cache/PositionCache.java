package com.shiyou.arbitrage.cache;


import com.shiyou.arbitrage.data.model.Position;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: com.lanjiejia.demo.cache
 * @Project: lanJieHouseKeeping
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/11/27 0027 10:22
 */
public class PositionCache {


    private static Map<String, Position> map = new ConcurrentHashMap<>();

    public static Position getPosition(String symbol) {
        return map.get(symbol);
    }

    public static void update(String symbol, Position position) {
        map.put(symbol, position);
    }
}
