package com.shiyou.arbitrage.api;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shiyou.arbitrage.cache.FcoinOrderCache;
import com.shiyou.arbitrage.common.ObjectMapper;
import com.shiyou.arbitrage.data.model.TradeDepth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.math.BigDecimal;
import java.util.LinkedList;

public class FcoinSocketClientHandler extends MyDataHandler {

    private static final String COMMAND_EVENT = "e";
    private static final String TICKER = "ticker";
    private static final String ALL_TRADE = "AllTrade";
    private static final String ALL_BID = "AllBid";
    private static final String ALL_ASK = "AllAsk";
    private static final String DEAL_RECORD = "t";
    private static final String PARAMS = "p";
    private static final String HREAT = "ping";
    private static final String COMMAND_HREAT = "c";
    private static final String ORDER = "o";

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Async
    @Override
    public void execute(String message, String symbol) {

        JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
        if (jsonObject.keySet().contains("type")) {
            String type = jsonObject.get("type").getAsString();
            if (isHeart(type)) {
                logger.info(symbol + "收到心跳回复：" + ObjectMapper.toJson(jsonObject));
            }else if (type.equals("hello")){
                logger.info(symbol + "连接成功：" + ObjectMapper.toJson(jsonObject));
            } else if (type.equals("topics")){
                logger.info(jsonObject.get("topics").getAsString() + "订阅成功：" + ObjectMapper.toJson(jsonObject));
            } else if (type.startsWith("ticker")) {
                logger.info(symbol + "收到ticker：" + ObjectMapper.toJson(jsonObject));
            } else if (type.startsWith("depth")) {
                logger.info(symbol + "收到depth：" + ObjectMapper.toJson(jsonObject));
                JsonArray bids = jsonObject.get("bids").getAsJsonArray();
                JsonArray asks = jsonObject.get("asks").getAsJsonArray();
                LinkedList<BigDecimal[]> bidsList = buildList(bids);
                LinkedList<BigDecimal[]> asksList = buildList(asks);
                TradeDepth tradeDepth = new TradeDepth();
                tradeDepth.setBids(bidsList);
                tradeDepth.setAsks(asksList);
                FcoinOrderCache.update(symbol, tradeDepth);
            } else {
                logger.info(symbol + "没有处理方法：" + ObjectMapper.toJson(jsonObject));
            }
        }else {

        }
    }

    private LinkedList<BigDecimal[]> buildList(JsonArray jsonArray) {
        LinkedList<BigDecimal[]> linkedList = new LinkedList<>();
        if (jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size() / 2; i++) {
                BigDecimal[] decimals = {new BigDecimal(String.valueOf(jsonArray.get(2 * i))), new BigDecimal(String.valueOf(jsonArray.get(2 * i + 1)))};
                linkedList.add(decimals);
            }
        }
        return linkedList;
    }


    private boolean isHeart(String type) {
        return type.equals(HREAT);
    }


}
