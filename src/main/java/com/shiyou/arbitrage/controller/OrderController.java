package com.shiyou.arbitrage.controller;

import com.shiyou.arbitrage.common.Result;
import com.shiyou.arbitrage.data.model.Order;
import com.shiyou.arbitrage.service.contract.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Package: com.shiyou.arbitrage.controller
 * @Project: arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/20 0020 15:27
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;

    public Result<List<Order>> listByPlatformAndSymbolAndStatus(String platform, String symbol, Integer status) {
        return orderService.listByPlatformAndSymbolAndStatus(platform, symbol, status);
    }
}
