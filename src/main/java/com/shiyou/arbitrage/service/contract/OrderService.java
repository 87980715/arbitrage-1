package com.shiyou.arbitrage.service.contract;

import com.shiyou.arbitrage.common.Result;
import com.shiyou.arbitrage.data.model.Order;

import java.util.List;

/**
 * @Package: com.azoveh.arbitrage.service
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/14 0014 14:38
 */
public interface OrderService {

    Result save(Order order);

    Result update(Order order);

    Result<Order> getById(Long id);

    Result<List<Order>> listByPlatformAndSymbolAndStatus(String platform, String symbol, Integer status);


}
