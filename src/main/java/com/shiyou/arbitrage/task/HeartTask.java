package com.shiyou.arbitrage.task;


import com.shiyou.arbitrage.api.MessageSubscribe;
import com.shiyou.arbitrage.api.SocketClient;

import java.io.IOException;
import java.util.TimerTask;

public class HeartTask extends TimerTask {

    private SocketClient client;
    private String market;
    private static long startTime = System.currentTimeMillis();

    public static void updateTime() {
        startTime = System.currentTimeMillis();
    }

    public HeartTask(SocketClient client, String market) {
        this.client = client;
        this.market = market;
    }

    @Override
    public void run() {
        int checkTime = 30000;
        if (System.currentTimeMillis() - startTime > checkTime) {
            try {
                client.close();
                client.reConnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MessageSubscribe.subscribeHeart(client, market);
    }
}
