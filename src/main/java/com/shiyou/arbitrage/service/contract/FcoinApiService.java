package com.shiyou.arbitrage.service.contract;

import java.io.IOException;

/**
 * @Package: com.azoveh.arbitrage.service
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/12 0012 14:59
 */
public interface FcoinApiService {

    String serverTime() throws IOException;

    String accountInfo();

    String currencies();

    String symbols();

    String createOrder(String symbol, String side, String type, String price, String amount, String exchange, String accountType);

    String orderList(String symbol, String status, String before, String after, String limit);

    String orderById(String orderId);

    String cancelOrderById(String orderId);

    String dealRecordById(String orderId);


}
