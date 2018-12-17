package com.shiyou.arbitrage.service.contract;

import com.shiyou.arbitrage.common.Result;
import com.shiyou.arbitrage.data.model.Symbol;

import java.util.List;

/**
 * @Package: com.azoveh.arbitrage.service
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/14 0014 14:37
 */
public interface SymbolService {

    Result save(Symbol symbol);

    Result<Symbol> get(String platform, String name);

    Result<List<Symbol>> listByPlatform(String platform);


}
