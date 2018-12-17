package com.shiyou.arbitrage.api;

public class MessageSubscribe {




    public static void subscribe(SocketClient client, String market, String symbol) {
        String text = null;
        switch (market) {
            case "Bloex":
                text = "{c:'s',p:[{t:'trade',l:'" + symbol + "'}]}";
                client.send(text);
                break;
            case "Fcoin":
                text = "{\"cmd\":\"sub\",\"args\":[\"depth.L20." + symbol + "\"]}";
                break;
            default:
                break;

        }
        client.send(text);
    }

    public static void subscribeTicker(SocketClient client, String market, String symbol) {
        String ticker = null;
        switch (market) {
            case "Bloex":
                ticker = "{c:'s',p:[{t:'ticker',l:'" + symbol + "'}]}";
                break;
            case "Fcoin":
                ticker = "{\"cmd\":\"sub\",\"args\":[\"ticker." + symbol + "\"]}";
                break;
            default:
                break;

        }
        client.send(ticker);
    }



    public static void subscribeTrade(SocketClient client, String market, String symbol) {
        String trade = null;
        switch (market) {
            case "Bloex":
                trade = "{c:'s',p:[{t:'trade',l:'" + symbol + "'}]}";
                break;
            case "Fcoin":
                //trade = "{\"cmd\":\"sub\",\"args\":[\"ticker." + symbol + "\"]}";
                break;
            default:
                break;

        }
        client.send(trade);
    }


    public static void subscribeHeart(SocketClient client, String market) {
        String heart = null;
        switch (market) {
            case "Bloex":
                heart = "{c:'h'}";
                break;
            case "Fcoin":
                Long time = System.currentTimeMillis();
                String timeStr = time.toString();
                heart = "{\"cmd\":\"ping\",\"args\":[" + timeStr + "],\"id\":\"sample.client.id\"}";
                break;
            case "Coin58":
                heart = "";
                break;
            default:
                break;

        }
        client.send(heart);
    }

//    public static void subscribeTicker(BloSocketClient client, String symbol) {
//
//        String ticker = "{c:'s',p:[{t:'trade',l:'" + symbol + "'}]}";
//        client.send(ticker);
//    }
//
//    public static void subscribeTrade(BloSocketClient client, String symbol) {
//        String trade = "{c:'s',p:[{t:'trade',l:'" + symbol + "'}]}";
//        client.send(trade);
//    }
//
//    public static void subscribeTradeAndTicker(BloSocketClient client, String symbol) {
//        String ticker = "{c:'s',p:[{t:'trade',l:'" + symbol + "'},{t:'trade',l:'" + symbol + "'}]}";
//        client.send(ticker);
//    }
//
//
//    public static void subscribeTicker(FcoinSocketClient client, String symbol) {
//        String ticker = "{\"cmd\":\"sub\",\"args\":[\"ticker." + symbol + "\"]}";
//        client.send(ticker);
//    }
//
//    public static void subscribeTicker(SocketClient client, String symbol) {
//        String ticker = "{\n" +
//                "  \"sub\": \"market.btcusdt.kline.1min\",\n" +
//                "  \"id\": \"id1\"\n" +
//                "}";
//        client.send(ticker);
//    }


}
