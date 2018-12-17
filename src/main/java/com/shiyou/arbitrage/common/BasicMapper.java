package com.shiyou.arbitrage.common;

import tk.mybatis.mapper.common.Mapper;

public interface BasicMapper<T> extends Mapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
