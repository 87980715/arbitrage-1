package com.shiyou.arbitrage.service.contract;

import com.shiyou.arbitrage.common.Result;
import com.shiyou.arbitrage.data.model.PlatformInfo;

import java.util.List;

/**
 * @Package: com.azoveh.arbitrage.service
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/14 0014 16:42
 */
public interface PlatformInfoService {

    Result<PlatformInfo> get(String platform);

    Result<List<PlatformInfo>> getAll();


}
