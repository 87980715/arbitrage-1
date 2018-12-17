package com.shiyou.arbitrage.task;

import com.shiyou.arbitrage.cache.BloexOrderCache;
import com.shiyou.arbitrage.common.ApplicationContext;
import com.shiyou.arbitrage.data.model.ApiResult;
import com.shiyou.arbitrage.data.model.TradeDepth;
import com.shiyou.arbitrage.service.contract.BloexApiService;

import java.util.TimerTask;

/**
 * @Package: com.azoveh.arbitrage.task
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/10 0010 16:32
 */
public class BloexDepthTask extends TimerTask {

    private String symbol;
    private static BloexApiService apiService = ApplicationContext.getBean(BloexApiService.class);
    public BloexDepthTask(String symbol){
        this.symbol = symbol;
    }

    @Override
    public void run() {
        ApiResult<TradeDepth> apiResult = apiService.getTradeDepth(symbol);
        TradeDepth tradeDepth = apiResult.getData();
        BloexOrderCache.update(symbol, tradeDepth);

    }
}
