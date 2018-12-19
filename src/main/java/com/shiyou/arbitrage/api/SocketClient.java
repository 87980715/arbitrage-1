package com.shiyou.arbitrage.api;

import com.shiyou.arbitrage.task.HeartTask;
import com.shiyou.arbitrage.task.TaskScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

/**
 * @Package: com.azoveh.bloex.arbitrage.bloex
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/6 0006 16:02
 */
@ClientEndpoint
public class SocketClient {

    private Logger logger = LoggerFactory.getLogger(SocketClient.class);
    private Session session;
    private String symbol;
    private String market;
    private String url;


    public SocketClient(String market, String symbol, String url) {
        this.symbol = symbol;
        this.market = market;
        this.url = url;
    }


    public void start() {
        // Auto-generated method stub

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            container.connectToServer(this, URI.create(url));
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void open(Session session) {
        logger.info(market + "    Client WebSocket is opening...  " );
        this.session = session;
        TaskScheduler.start(this, market);
        MessageSubscribe.subscribe(this, market, symbol);
    }

    @OnMessage
    public void onMessage(String message) {
        //logger.info("Server send message: " + message);
        HeartTask.updateTime();
        MyDataHandler handler = DataHandlerBuilder.newInstance(market);
        handler.execute(message, symbol);
    }

    @OnClose
    public void onClose() {
        logger.info("Websocket closed, reconnect ... ...");
        start();
    }


    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
    }

    public synchronized void send(String message) {
        this.session.getAsyncRemote().sendText(message);
        logger.info("订阅信息："+message);
    }

    public void close() throws IOException {
        if (this.session.isOpen()) {
            this.session.close();
        }
        logger.info("webSocket closed !!! ");
    }

    public void reConnect() {

        start();
        logger.info("webSocket reconnect ... ... ");
    }
}
