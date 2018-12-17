package com.shiyou.arbitrage.cache;

import com.shiyou.arbitrage.common.ApplicationContext;
import com.shiyou.arbitrage.data.model.PlatformInfo;
import com.shiyou.arbitrage.service.contract.PlatformInfoService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: com.azoveh.arbitrage.cache
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/14 0014 16:33
 */
public class PlatformInfoCache {


    private static PlatformInfoService platformInfoService = ApplicationContext.getBean(PlatformInfoService.class);

    private static Map<String, Map<String, String>> map = new ConcurrentHashMap<>();

    public static String getInfo(String platform, String key) {
        Map<String, String> infoMap = map.get(platform);
        if (infoMap == null){
            infoMap = new HashMap<>(4);
            PlatformInfo platformInfo = platformInfoService.get(platform).getData();
            if (platformInfo != null){
                add(platformInfo);
            }
        }
        return infoMap.get(key);
    }


    public static void add(PlatformInfo platformInfo){
        Map<String, String> infoMap = new HashMap<>(4);
        infoMap.put("apiUrl", platformInfo.getApiUrl());
        infoMap.put("socketUrl", platformInfo.getSocketUrl());
        infoMap.put("apiKey", platformInfo.getApiKey());
        infoMap.put("apiSecrect", platformInfo.getApiSecrect());

        map.put(platformInfo.getPlatform(), infoMap);
    }
}
