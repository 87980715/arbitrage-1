package com.shiyou.arbitrage.api;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shiyou.arbitrage.cache.LastDealPriceCache;
import com.shiyou.arbitrage.common.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.math.BigDecimal;

public class BloexSocketClientHandler extends MyDataHandler {

    private static final String COMMAND_EVENT = "e";
    private static final String TICKER = "ticker";
    private static final String ALL_TRADE = "AllTrade";
    private static final String ALL_BID = "AllBid";
    private static final String ALL_ASK = "AllAsk";
    private static final String DEAL_RECORD = "t";
    private static final String PARAMS = "p";
    private static final String HREAT = "r";
    private static final String COMMAND_HREAT = "c";
    private static final String ORDER = "o";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    @Override
    public void execute(String message, String symbol) {
        JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();

        if (isHeart(jsonObject)) {
            JsonElement element = jsonObject.get(HREAT);
            logger.info(symbol + "收到心跳回复：" + ObjectMapper.toJson(element));
        }
        if (isEvent(jsonObject)) {
            String event = jsonObject.get(COMMAND_EVENT).getAsString();
            JsonArray valueList = jsonObject.get("d").getAsJsonArray();
            if (TICKER.equals(event)) {

               // logger.info(symbol + "最新ticker:" + ObjectMapper.toJson(valueList));
            } else if (ALL_TRADE.equals(event)) {

                //logger.info(symbol + "最近成交记录列表:" + ObjectMapper.toJson(valueList));
            } else if (DEAL_RECORD.equals(event)) {
                BigDecimal lastDealPrice = valueList.get(0).getAsJsonObject().get("p").getAsBigDecimal();
                LastDealPriceCache.update(symbol, lastDealPrice);
                //logger.info(symbol + "最新成交记录:" + ObjectMapper.toJson(valueList));
            } else if (ALL_ASK.equals(event)) {
                //最近订单   AllBid=买单，AllAsk=卖单   "s":"类型", //1=买单，2=卖单
                //updateOrderCache(valueList, symbol, "asks");
            } else if (ALL_BID.equals(event)) {
                //updateOrderCache(valueList, symbol, "bids");
            } else if (ORDER.equals(event)) {

                //logger.info(symbol + "最新委托单:" + ObjectMapper.toJson(valueList));
            } else {
                logger.error(symbol + "缺少相应事件处理方法！" + ObjectMapper.toJson(message));
            }
        }
    }

    private boolean isHeart(JsonObject jsonObject) {
        return jsonObject.keySet().contains(COMMAND_HREAT);
    }

    private boolean isEvent(JsonObject jsonObject) {
        return jsonObject.keySet().contains(COMMAND_EVENT);
    }

//    private void updateOrderCache(JsonArray valueList, String symbol, String type) {
//        LinkedList<BigDecimal[]> linkedList = new LinkedList<>();
//        for (int i = 0; i < 10; i++) {
//            JsonObject order = valueList.get(i).getAsJsonObject();
//            BigDecimal[] bigDecimals = {order.get("p").getAsBigDecimal(), order.get("v").getAsBigDecimal()};
//            linkedList.add(bigDecimals);
//        }
//        logger.info(symbol + "最近订单列表--:" + type + ObjectMapper.toJson(linkedList));
//    }


}
