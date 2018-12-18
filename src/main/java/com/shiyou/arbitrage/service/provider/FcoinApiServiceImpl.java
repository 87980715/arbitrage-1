package com.shiyou.arbitrage.service.provider;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.shiyou.arbitrage.api.SignatureUtil;
import com.shiyou.arbitrage.cache.PlatformInfoCache;
import com.shiyou.arbitrage.data.model.Symbol;
import com.shiyou.arbitrage.service.contract.FcoinApiService;
import com.shiyou.arbitrage.service.contract.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.shiyou.arbitrage.common.HttpUtil.doGet;
import static com.shiyou.arbitrage.common.HttpUtil.doPost;
import static com.shiyou.arbitrage.data.model.Contant.FCOIN;

/**
 * @Package: com.azoveh.arbitrage.service.impl
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/12 0012 15:43
 */
@Service
public class FcoinApiServiceImpl implements FcoinApiService {

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_DELETE = "DELETE";

    private static final String SERVER_TIME = "/public/server-time";
    private static final String ACCOUNT_BALANCE = "/accounts/balance";
    private static final String SYMBOL = "/public/symbols";
    private static final String CURRENCY = "/public/currencies";
    private static final String ORDERS = "/orders";


    @Autowired
    private SymbolService symbolService;

    private String urlPre = null;
    private String apiKey = null;
    private String secrect = null;

    @Override
    public String serverTime() {

        String url = getUrlPre() + SERVER_TIME;
        String httpResponse = doGet(url);
        String serverTime = new JsonParser().parse(httpResponse).getAsJsonObject().get("data").getAsString();
        System.out.println(serverTime);
        return serverTime;
    }

    @Override
    public String accountInfo() {
        String url = getUrlPre() + ACCOUNT_BALANCE;
        String serverTime = serverTime();
        Map<String, String> params = new HashMap<>();
        String signature = SignatureUtil.signatureFcoin(METHOD_GET, url, serverTime, params, getSecrect());
        String httpResponse = doGet(url, params, getApiKey(), signature, serverTime);
        System.out.println(httpResponse);
        //symbols();
        //createOrder("btcusdt", "buy", "limit", "1000.0", "1", "main", null);
        return httpResponse;
    }

    @Override
    public String currencies() {

        String url = getUrlPre() + CURRENCY;
        String httpResponse = doGet(url);
        JsonArray jsonArray = new JsonParser().parse(httpResponse).getAsJsonObject().get("data").getAsJsonArray();

        return httpResponse;
    }

    @Override
    public String symbols() {
        String url = getUrlPre() + SYMBOL;
        String httpResponse = doGet(url);
//        JsonArray jsonArray = new JsonParser().parse(httpResponse).getAsJsonObject().get("data").getAsJsonArray();
//        for (JsonElement object : jsonArray) {
//            Symbol symbol = new Symbol();
//            symbol.setName(object.getAsJsonObject().get("name").getAsString());
//            symbol.setPlatform("Fcoin");
//            symbol.setPriceDecimal(object.getAsJsonObject().get("price_decimal").getAsInt());
//            symbol.setAmountDecimal(object.getAsJsonObject().get("amount_decimal").getAsInt());
//            symbol.setTradeable(object.getAsJsonObject().get("tradable").getAsBoolean());
//            symbol.setType("futures");
//            if (symbol.getName().contains("eth")){
//                symbol.setArea("eth");
//                symbol.setType("spot");
//            }
//            if (symbol.getName().contains("btc")){
//                symbol.setArea("btc");
//                symbol.setType("spot");
//            }
//            if (symbol.getName().contains("usdt")){
//                symbol.setArea("usdt");
//                symbol.setType("spot");
//            }
//            if (symbol.getName().contains("ft")){
//                symbol.setArea("ft");
//                symbol.setType("spot");
//            }
//            symbolService.save(symbol);
//        }
        return httpResponse;
    }

    @Override
    public String createOrder(String symbol, String side, String type, String price, String amount, String exchange, String accountType) {

        String url = getUrlPre() + ORDERS;
        String serverTime = serverTime();
        Map<String, String> params = new HashMap<>(7);
        params.put("amount", amount);
        params.put("exchange", exchange);
        params.put("price", price);
        params.put("side", side);
        params.put("symbol", symbol);
        params.put("type", type);
        if (accountType != null) {
            params.put("account_type", accountType);
        }
        String signature = SignatureUtil.signatureFcoin(METHOD_POST, url, serverTime, params, getSecrect());
        String s = doPost(url, params, getApiKey(), signature, serverTime);
        return s;
    }

    @Override
    public String orderList(String symbol, String status, String before, String after, String limit) {
        String url = getUrlPre() + ORDERS;
        String serverTime = serverTime();
        Map<String, String> params = new HashMap<>(7);
        params.put("after", after);
        params.put("before", before);
        params.put("limit", limit);
        params.put("status", status);//submitted:已提交, partial_filled:部分成交, partial_canceled:部分成交已撤销, filled:完全成交, canceled:已撤销, pending_cancel:撤销已提交
        params.put("symbol", symbol);
        String signature = SignatureUtil.signatureFcoin(METHOD_GET, url, serverTime, params, secrect);
        return doPost(url, params, getApiKey(), signature, serverTime);
    }

    @Override
    public String orderById(String orderId) {
        String url = urlPre + ORDERS + "/" + orderId;
        String serverTime = serverTime();
        Map<String, String> params = new HashMap<>(2);
        params.put("order_id", orderId);
        String signature = SignatureUtil.signatureFcoin(METHOD_GET, url, serverTime, params, getSecrect());
        return doPost(url, params, getApiKey(), signature, serverTime);
    }

    @Override
    public String cancelOrderById(String orderId) {
        String url = getUrlPre() + ORDERS + "/" + orderId + "/submit-cancel";
        String serverTime = serverTime();
        Map<String, String> params = new HashMap<>(1);
        params.put("order_id", orderId);
        String signature = SignatureUtil.signatureFcoin(METHOD_POST, url, serverTime, params, getSecrect());
        return doPost(url, params, getApiKey(), signature, serverTime);
    }

    @Override
    public String dealRecordById(String orderId) {
        String url = getUrlPre() + ORDERS + "/" + orderId + "/match-results";
        String serverTime = serverTime();
        Map<String, String> params = new HashMap<>();
        params.put("order_id", orderId);
        String signature = SignatureUtil.signatureFcoin(METHOD_GET, url, serverTime, params, getSecrect());
        return doPost(url, params, getApiKey(), signature, serverTime);
    }



    private String getUrlPre() {
        if (urlPre == null) {
            urlPre = PlatformInfoCache.getInfo(FCOIN, "apiUrl");
        }
        return urlPre;
    }

    private String getApiKey() {
        if (apiKey == null) {
            apiKey = PlatformInfoCache.getInfo(FCOIN, "apiKey");
        }
        return apiKey;
    }

    private String getSecrect() {
        if (secrect == null) {
            secrect = PlatformInfoCache.getInfo(FCOIN, "apiSecrect");
        }
        return secrect;
    }
}
