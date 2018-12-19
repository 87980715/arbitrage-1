package com.shiyou.arbitrage.service.provider;

import com.github.pagehelper.PageInfo;
import com.google.gson.reflect.TypeToken;
import com.shiyou.arbitrage.api.BloexAPI;
import com.shiyou.arbitrage.api.SignatureUtil;
import com.shiyou.arbitrage.cache.PlatformInfoCache;
import com.shiyou.arbitrage.common.ObjectMapper;
import com.shiyou.arbitrage.data.model.*;
import com.shiyou.arbitrage.service.contract.BloexApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.lanjiejia.demo.service.provider
 * @Project: lanJieHouseKeeping
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/11/23 0023 15:43
 */
@Service
public class BloexApiServiceImpl implements BloexApiService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String TICKER = "/ticker/get?";
    public static final String DEPTH = "/trade/depth/get";
    public static final String EXCHANGE_RATE = "/currency/exchange/rate/get?";
    public static final String ORDER_LIST = "/order/get?";
    public static final String ORDER = "/order/get/by-id?";
    public static final String CREATE_ORDER = "/order/submit?";
    public static final String CANCEL_ORDER_ID = "/order/cancel?";
    public static final String CANCEL_ORDER_TXTYPE = "/order/cancel/by-symbol?";
    public static final String POSITION = "/contract/position/get?";
    public static final String CONTRACT_ACCOUNT = "/contract/account/get?";
    public static final String COIN_ACCOUNT = "/token/account/get?";
    public static final String CLOSE_POSITION = "/contract/position/close?";
    public static final String DEAL_ORDER = "/deal/record/get?";
    public static final String COIN_INDEX = "/coin/index/get?";
    public static final String CONTRACT_INDEX = "/contract/index/get?";

    private BloexAPI api = new BloexAPI();
    private String apiKey = null;
    private String secrect = null;


    @Override
    public String getBinanceServerTime() {
        return api.getBinanceServerTime();
    }

    @Override
    public ApiResult<Ticker> getTicker(String symbol) {
        Map<String, String> params = new HashMap<>(3);
        params.put("symbol", symbol);
        Type type = new TypeToken<ApiResult<Ticker>>() {
        }.getType();
        return getResult(type, TICKER, params, false);
//        params.put("time", api.getServerTime());
//        String response = api.getDataWithOutSignature(TICKER, params);
//        Type type = new TypeToken<ApiResult<Ticker>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);
    }

    @Override
    public ApiResult<TradeDepth> getTradeDepth(String symbol) {

        Map<String, String> params = new HashMap<>(3);
        params.put("symbol", symbol);
        Type type = new TypeToken<ApiResult<TradeDepth>>() {
        }.getType();
        return getResult(type, DEPTH, params, false);
//        params.put("time", api.getServerTime());
//        String response = api.getDataWithOutSignature(DEPTH, params);
//        Type type = new TypeToken<ApiResult<TradeDepth>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);
    }

    @Override
    public ApiResult<Double> getExchangeRate() {

        Map<String, String> params = new HashMap<>(1);
        params.put("time", api.getServerTime());
        Type type = new TypeToken<ApiResult<Double>>() {
        }.getType();
        return getResult(type, EXCHANGE_RATE, params, false);
//        String response = api.getDataWithOutSignature(EXCHANGE_RATE, params);
//        Type type = new TypeToken<ApiResult<Double>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);
    }

    @Override
    public ApiResult<PageInfo<UserOrder>> getOrderList(String symbol, Integer pageNum, Integer pageSize) {

        Map<String, String> params = new HashMap<>(6);
        params.put("symbol", symbol);
        params.put("pageNum", pageNum.toString());
        params.put("pageSize", pageSize.toString());
        Type type = new TypeToken<ApiResult<PageInfo<UserOrder>>>() {
        }.getType();
        return getResult(type, ORDER_LIST, params, true);
//        addTimeStamp(params);
//        String signature = SignatureUtil.buildSignature(params, API_KEY, API_SERECT);
//        String response = api.getData(ORDER_LIST, params, signature);
//        Type type = new TypeToken<ApiResult<PageInfo<UserOrder>>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);
    }

    @Override
    public ApiResult<UserOrder> getOrderById(Long orderId) {
        Map<String, String> params = new HashMap<>(4);
        params.put("orderId", orderId.toString());
        Type type = new TypeToken<ApiResult<UserOrder>>() {
        }.getType();
        return getResult(type, ORDER, params, true);
//        addTimeStamp(params);
//        String signature = SignatureUtil.buildSignature(params, API_KEY, API_SERECT);
//        String response = api.getData(ORDER, params, signature);
//        Type type = new TypeToken<ApiResult<UserOrder>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);
    }

    @Override
    public ApiResult<Map<String, String>> createOrder(String symbol, BigDecimal price, BigDecimal amount, Integer type, String lever) {
        Map<String, String> params = new HashMap<>(8);
        params.put("symbol", symbol);
        params.put("price", price.toString());
        params.put("amount", amount.toString());
        params.put("type", type.toString());
        if (lever != null) {
            params.put("lever", lever);
        }
        Type typeToken = new TypeToken<ApiResult<Long>>() {
        }.getType();
        return getResult(typeToken, CREATE_ORDER, params, true);
//        addTimeStamp(params);
//        String signature = SignatureUtil.buildSignature(params, API_KEY, API_SERECT);
//        String response = api.getData(CREATE_ORDER, params, signature);
//        Type typeToken = new TypeToken<ApiResult<Long>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, typeToken);
    }

    @Override
    public ApiResult<Map<String, String>> cancelOrderById(String symbol, Long orderId) {
        Map<String, String> params = new HashMap<>(5);
        params.put("symbol", symbol);
        params.put("orderId", orderId.toString());
        Type type = new TypeToken<ApiResult<Long>>() {
        }.getType();
        return getResult(type, CANCEL_ORDER_ID, params, true);
//        addTimeStamp(params);
//        String signature = SignatureUtil.buildSignature(params, API_KEY, API_SERECT);
//        String response = api.getData(CANCEL_ORDER_ID, params, signature);
//        Type type = new TypeToken<ApiResult<Long>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);
    }

    @Override
    public ApiResult<Map<String, Integer>> cancelOrderBySymbol(String symbol) {
        Map<String, String> params = new HashMap<>(4);
        params.put("symbol", symbol);
        Type type = new TypeToken<ApiResult<Integer>>() {
        }.getType();
        return getResult(type, CANCEL_ORDER_TXTYPE, params, true);
//        addTimeStamp(params);
//        String signature = SignatureUtil.buildSignature(params, API_KEY, API_SERECT);
//        String response = api.getData(CANCEL_ORDER_TXTYPE, params, signature);
//        Type type = new TypeToken<ApiResult<Integer>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);
    }

    @Override
    public ApiResult<Position> getPosition(String symbol) {
        Map<String, String> params = new HashMap<>(4);
        params.put("symbol", symbol);
        Type type = new TypeToken<ApiResult<Position>>() {
        }.getType();
        return getResult(type, POSITION, params, true);
//        addTimeStamp(params);
//        String signature = SignatureUtil.buildSignature(params, API_KEY, API_SERECT);
//        String response = api.getData(POSITION, params, signature);
//        Type type = new TypeToken<ApiResult<Position>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);
    }

    @Override
    public ApiResult<Map<String, UserContractAccount>> getContractAccount() {
        Map<String, String> params = new HashMap<>(3);
        Type type = new TypeToken<ApiResult<Map<String, UserContractAccount>>>() {
        }.getType();
        return getResult(type, CONTRACT_ACCOUNT, params, true);
//        addTimeStamp(params);
//        String signature = SignatureUtil.buildSignature(params, API_KEY, API_SERECT);
//        String response = api.getData(CONTRACT_ACCOUNT, params, signature);
//        Type type = new TypeToken<ApiResult<Map<String, UserContractAccount>>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);
    }

    @Override
    public ApiResult<Map<String, UserCoinAccount>> getCoinAccount() {
        Map<String, String> params = new HashMap<>(3);
        Type type = new TypeToken<ApiResult<Map<String, UserCoinAccount>>>() {
        }.getType();
        return getResult(type, COIN_ACCOUNT, params, true);
//        addTimeStamp(params);
//        String signature = SignatureUtil.buildSignature(params, API_KEY, API_SERECT);
//        String response = api.getData(COIN_ACCOUNT, params, signature);
//        Type type = new TypeToken<ApiResult<Map<String, UserCoinAccount>>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);
    }

    @Override
    public ApiResult<Long> closePosition(String symbol, BigDecimal price) {
        Map<String, String> params = new HashMap<>(5);
        params.put("symbol", symbol);
        if (price != null && price.compareTo(BigDecimal.ZERO) > 0) {
            params.put("price", price.toString());
        }
        Type type = new TypeToken<ApiResult<Long>>() {
        }.getType();
        return getResult(type, CLOSE_POSITION, params, true);
//        addTimeStamp(params);
//        String signature = SignatureUtil.buildSignature(params, API_KEY, API_SERECT);
//        String response = api.getData(CLOSE_POSITION, params, signature);
//        Type type = new TypeToken<ApiResult<Long>>() {
//        }.getType();
//        return ObjectMapper.fromJson(response, type);

    }

    @Override
    public ApiResult<BigDecimal> getCoinIndex(String symbol) {
        Map<String, String> params = new HashMap<>(5);
        params.put("symbol", symbol);
        Type type = new TypeToken<ApiResult<BigDecimal>>() {
        }.getType();
        return getResult(type, COIN_INDEX, params, true);
    }

    @Override
    public ApiResult<BigDecimal> getContractIndex(String symbol) {
        Map<String, String> params = new HashMap<>(5);
        params.put("symbol", symbol);
        Type type = new TypeToken<ApiResult<BigDecimal>>() {
        }.getType();
        return getResult(type, CONTRACT_INDEX, params, true);
    }

    private void addTimeStamp(Map<String, String> params) {
        params.put("time", api.getServerTime());
        params.put("timeError", "20000");
    }

    private <T> T getResult(Type type, String url, Map<String, String> params, boolean isSignature) {
        String response = null;
        try {
            addTimeStamp(params);
            if (isSignature) {
                String signature = SignatureUtil.buildSignature(params, getApiKey(), getSecrect());
                response = api.getData(url, params, signature);
            } else {
                response = api.getDataWithOutSignature(url, params);
            }
        }catch (Exception e){
            logger.error("BloexApi获取数据异常："+e.getMessage(), e);
        }
        return ObjectMapper.fromJson(response, type);
    }

    private String getApiKey() {
        if (apiKey == null) {
            apiKey = PlatformInfoCache.getInfo(Contant.BLOEX, "apiKey");
        }
        return apiKey;
    }

    private String getSecrect() {
        if (secrect == null) {
            secrect = PlatformInfoCache.getInfo(Contant.BLOEX, "apiSecrect");
        }
        return secrect;
    }
}
