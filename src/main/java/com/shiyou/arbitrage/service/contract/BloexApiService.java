package com.shiyou.arbitrage.service.contract;

import com.github.pagehelper.PageInfo;
import com.shiyou.arbitrage.data.model.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Package: com.lanjiejia.demo.service.contract
 * @Project: lanJieHouseKeeping
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/11/23 0023 15:28
 */
public interface BloexApiService {

    String getBinanceServerTime();

    ApiResult<Ticker> getTicker(String symbol);

    ApiResult<TradeDepth> getTradeDepth(String symbol);

    ApiResult<Double> getExchangeRate();

    ApiResult<PageInfo<UserOrder>> getOrderList(String symbol, Integer pageNum, Integer pageSize);

    ApiResult<UserOrder> getOrderById(Long orderId);

    ApiResult<Map<String, String>> createOrder(String symbol, BigDecimal price, BigDecimal amount, Integer type, String lever);

    ApiResult<Map<String, String>> cancelOrderById(String symbol, Long orderId);

    ApiResult<Map<String, Integer>> cancelOrderBySymbol(String symbol);

     ApiResult<Position> getPosition(String symbol);

     ApiResult<Map<String, UserContractAccount>> getContractAccount();

    ApiResult<Map<String, UserCoinAccount>> getCoinAccount();

    ApiResult<Long> closePosition(String symbol, BigDecimal price);

    ApiResult<BigDecimal> getCoinIndex(String symbol);

    ApiResult<BigDecimal> getContractIndex(String symbol);


}
