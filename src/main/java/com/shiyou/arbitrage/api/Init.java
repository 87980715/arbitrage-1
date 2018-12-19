package com.shiyou.arbitrage.api;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shiyou.arbitrage.cache.CoinAccountCache;
import com.shiyou.arbitrage.cache.ContractAccountCache;
import com.shiyou.arbitrage.cache.PlatformInfoCache;
import com.shiyou.arbitrage.common.ApplicationContext;
import com.shiyou.arbitrage.common.Result;
import com.shiyou.arbitrage.data.model.PlatformInfo;
import com.shiyou.arbitrage.data.model.UserCoinAccount;
import com.shiyou.arbitrage.data.model.UserContractAccount;
import com.shiyou.arbitrage.service.contract.BloexApiService;
import com.shiyou.arbitrage.service.contract.FcoinApiService;
import com.shiyou.arbitrage.service.contract.PlatformInfoService;
import com.shiyou.arbitrage.task.TaskScheduler;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static com.shiyou.arbitrage.data.model.Contant.BLOEX;
import static com.shiyou.arbitrage.data.model.Contant.FCOIN;

/**
 * @Package: com.azoveh.bloex.arbitrage.api.bloex
 * @Project: lanJieHouseKeeping
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/11/27 0027 11:11
 */
public class Init {

    private static BloexApiService apiService = ApplicationContext.getBean(BloexApiService.class);
    private static FcoinApiService fcoinApiService = ApplicationContext.getBean(FcoinApiService.class);
    private static PlatformInfoService platformInfoService = ApplicationContext.getBean(PlatformInfoService.class);

    public static void start(String symbolCoin, String symbolContract) {
//        String[] contracts = symbolContract.split(",");
        String[] coins = symbolCoin.split(",");

        loadPlatformInfo();
        loadBloexCoinAccount();
        loadFcoinCoinAccount();
        loadBloexContractAccount();

        for (String coin : coins) {

            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("demo-pool-%d").build();
            ExecutorService singleThreadPool = new ThreadPoolExecutor(2, 5,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

            singleThreadPool.execute(()-> new SocketClient(BLOEX, coin, PlatformInfoCache.getInfo(BLOEX, "socketUrl")).start());
            singleThreadPool.execute(()-> new SocketClient(FCOIN, coin.toLowerCase(), PlatformInfoCache.getInfo(FCOIN, "socketUrl")).start());


//            new SocketClient(BLOEX, coin, PlatformInfoCache.getInfo(BLOEX, "socketUrl")).start();
//            new SocketClient(FCOIN, coin.toLowerCase(), PlatformInfoCache.getInfo(FCOIN, "socketUrl")).start();
            //new SocketClient(Contant.COIN58, coin.toLowerCase(), PlatformInfoCache.getInfo(Contant.COIN58, "socketUrl")).start();
            TaskScheduler.startBloexDepth(coin);
            TaskScheduler.startArbitrage(coin);
        }


    }


    private static void loadBloexCoinAccount() {
        Map<String, UserCoinAccount> coinAccountResult = apiService.getCoinAccount().getData();
        Iterator<Map.Entry<String, UserCoinAccount>> iterator = coinAccountResult.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, UserCoinAccount> next = iterator.next();
            String symbol = next.getKey();
            UserCoinAccount userCoinAccount = next.getValue();
            CoinAccountCache.update(BLOEX, symbol, userCoinAccount);
        }

    }

    private static void loadFcoinCoinAccount() {
        String returnStr = fcoinApiService.accountInfo();
        JsonArray jsonArray = new JsonParser().parse(returnStr).getAsJsonObject().get("data").getAsJsonArray();
        for (JsonElement element : jsonArray) {
            UserCoinAccount coinAccount = new UserCoinAccount();
            JsonObject object = element.getAsJsonObject();
            String symbol = object.get("currency").getAsString();
            coinAccount.setAvailable(object.get("available").getAsBigDecimal().setScale(8, BigDecimal.ROUND_DOWN));
            coinAccount.setFrozen(object.get("frozen").getAsBigDecimal().setScale(8, BigDecimal.ROUND_DOWN));
            CoinAccountCache.update(BLOEX, symbol, coinAccount);
        }
    }

    private static void loadBloexContractAccount() {
        Map<String, UserContractAccount> contractAccountResult = apiService.getContractAccount().getData();
        Iterator<Map.Entry<String, UserContractAccount>> iterator = contractAccountResult.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, UserContractAccount> next = iterator.next();
            String symbol = next.getKey();
            UserContractAccount userContractAccount = next.getValue();
            ContractAccountCache.update(BLOEX, symbol, userContractAccount);
        }
    }

    private static void loadPlatformInfo() {
        Result<List<PlatformInfo>> result = platformInfoService.getAll();
        if (result.isSuccess()) {
            List<PlatformInfo> list = result.getData();
            if (list.size() > 0) {
                for (PlatformInfo info : list) {
                    PlatformInfoCache.add(info);
                }
            }
        }
    }
}
