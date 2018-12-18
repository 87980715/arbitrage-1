package com.shiyou.arbitrage.controller;


import com.shiyou.arbitrage.common.ExcelUtil;
import com.shiyou.arbitrage.common.Result;
import com.shiyou.arbitrage.data.model.Symbol;
import com.shiyou.arbitrage.service.contract.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: com.azoveh.arbitrage.controller
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/17 0017 14:26
 */
@RestController
@RequestMapping("/order")
public class SymbolController {

    @Autowired
    private SymbolService symbolService;

    @RequestMapping("/get")
    public Result<Symbol> get(String platform, String symbol){
        return symbolService.get(platform, symbol);
    }


    @RequestMapping("/list")
    public Result<List<Symbol>> list(String platform){
        Result<List<Symbol>> result =  symbolService.listByPlatform(platform);
        List<Symbol> list = result.getData();
        ExcelUtil.buildExcel(list);
        return new Result<>();
    }
}
