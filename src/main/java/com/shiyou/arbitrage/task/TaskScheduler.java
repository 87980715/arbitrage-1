package com.shiyou.arbitrage.task;

import com.shiyou.arbitrage.api.SocketClient;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {

    public static void start(SocketClient client, String market){
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("heart-check-%d").daemon(true).build());
        scheduledExecutorService.scheduleAtFixedRate(new HeartTask(client, market), 5000, 30000, TimeUnit.MILLISECONDS);
    }

    public static void startArbitrage(String symbol){
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("bloex-fcoin-arbitrage-%d").daemon(true).build());
        scheduledExecutorService.scheduleAtFixedRate(new ArbitrageTask(symbol), 10000, 500, TimeUnit.MILLISECONDS);
    }

    public static void startBloexDepth(String symbol){
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("bloex-depth-%d").daemon(true).build());
        scheduledExecutorService.scheduleAtFixedRate(new BloexDepthTask(symbol), 5000, 1000, TimeUnit.MILLISECONDS);
    }
}
