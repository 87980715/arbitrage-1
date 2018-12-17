package com.shiyou.arbitrage.api;

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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        //String[] contracts = symbolContract.split(",");
        String[] coins = symbolCoin.split(",");

        loadPlatformInfo();
        fcoinApiService.accountInfo();

        /*loadBloCoinAccount();
        loadFcoinCoinAccount();
        loadContractAccount();*/

//        for (String contract : contracts) {
//
//            new BloSocketClient(contract).start();
//            ApiResult<Position> positionResult = apiService.getPosition(contract);
//            if (positionResult == null || positionResult.getData() == null) {
//                continue;
//            }
//            Position position = positionResult.getData();
//            PositionCache.update(position.getSymbol(), position);
//        }

        for (String coin : coins) {


            //new SocketClient(coin, Contant.MARKET_BLOEX, Contant.BLOEX_SOCKET_URL).start();
            new SocketClient(coin.toLowerCase(),FCOIN, "wss://api.fcoin.com/v2/ws").start();
           // new SocketClient(coin.toLowerCase(), Contant.MARKET_COIN58, Contant.COIN58_SOCKET_URL).start();


        }
//        TaskScheduler.startBloexDepth("BTCUSDT");
//        TaskScheduler.startArbitrage("BTCUSDT");

    }


    private static void loadBloCoinAccount() {


        Map<String, UserCoinAccount> coinAccountResult = apiService.getCoinAccount().getData();
        Iterator<Map.Entry<String, UserCoinAccount>> iterator = coinAccountResult.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, UserCoinAccount> next = iterator.next();
            String symbol = next.getKey();
            UserCoinAccount userCoinAccount = next.getValue();
            CoinAccountCache.update(symbol, userCoinAccount);
        }

    }

    private static void loadFcoinCoinAccount() {

    }

    private static void loadContractAccount() {


        Map<String, UserContractAccount> contractAccountResult = apiService.getContractAccount().getData();
        Iterator<Map.Entry<String, UserContractAccount>> iterator = contractAccountResult.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, UserContractAccount> next = iterator.next();
            String symbol = next.getKey();
            UserContractAccount userContractAccount = next.getValue();
            ContractAccountCache.update(symbol, userContractAccount);
        }


    }

    private static void loadPlatformInfo() {
        Result<List<PlatformInfo>> result = platformInfoService.getAll();
        if (result.isSuccess()){
            List<PlatformInfo> list = result.getData();
            if (list.size() > 0){
                for (PlatformInfo info :list){
                    PlatformInfoCache.add(info);
                }
            }
        }
    }

}
