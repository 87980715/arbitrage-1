package com.shiyou.arbitrage.service.provider;

import com.shiyou.arbitrage.common.BasicServiceImpl;
import com.shiyou.arbitrage.common.Result;
import com.shiyou.arbitrage.data.model.Symbol;
import com.shiyou.arbitrage.service.contract.SymbolService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.azoveh.arbitrage.service.impl
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/14 0014 14:43
 */
@Service
public class SymbolServiceImpl extends BasicServiceImpl<Symbol> implements SymbolService {

    @Override
    public Result save(Symbol symbol) {
        return super.insert(symbol);
    }

    @Override
    public Result<Symbol> get(String platform, String name) {
        Symbol symbol = new Symbol();
        symbol.setPlatform(platform);
        symbol.setName(name);
        return super.selectOne(symbol);
    }

    @Override
    public Result<List<Symbol>> listByPlatform(String platform) {
        Symbol symbol = new Symbol();
        symbol.setPlatform(platform);
        return super.select(symbol);
    }
}
