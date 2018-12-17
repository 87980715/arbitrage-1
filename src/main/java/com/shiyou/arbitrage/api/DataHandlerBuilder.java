package com.shiyou.arbitrage.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.azoveh.bloex.arbitrage.bloex
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/7 0007 10:36
 */
public class DataHandlerBuilder {

    private static Logger logger = LoggerFactory.getLogger(DataHandlerBuilder.class);

    private static Map<String, MyDataHandler> handlerMap = new HashMap<>();

    public static MyDataHandler newInstance(String market) {
        MyDataHandler myDataHandler = handlerMap.get(market);
        try {
            if (myDataHandler == null) {
                Class handlerClass = Class.forName("com.shiyou.arbitrage.api." + market + "SocketClientHandler");
                myDataHandler = (MyDataHandler) handlerClass.newInstance();
                myDataHandler.setMarket(market);
                handlerMap.put(market, myDataHandler);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            logger.error(e.getMessage(), e);
        }

        return myDataHandler;
    }
}
