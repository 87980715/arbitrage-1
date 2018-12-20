package com.shiyou.arbitrage.service.provider;

import com.shiyou.arbitrage.common.BasicServiceImpl;
import com.shiyou.arbitrage.common.Result;
import com.shiyou.arbitrage.common.StringUtil;
import com.shiyou.arbitrage.data.model.Order;
import com.shiyou.arbitrage.service.contract.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.shiyou.arbitrage.service.provider
 * @Project: arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/20 0020 15:33
 */
@Service
public class OrderServiceImpl extends BasicServiceImpl<Order> implements OrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Result save(Order order) {
        return super.insert(order);
    }

    @Override
    public Result update(Order order) {
        return super.update(order);
    }

    @Override
    public Result<Order> getById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public Result<List<Order>> listByPlatformAndSymbolAndStatus(String platform, String symbol, Integer status) {
        Order order = new Order();
        if (StringUtil.isNotEmpty(platform)) {
            order.setPlatform(platform);
        }
        if (StringUtil.isNotEmpty(symbol)) {
            order.setSymbol(symbol);
        }
        if (status == null) {
            order.setStatus(status);
        }
        return super.select(order);
    }
}
