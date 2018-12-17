package com.shiyou.arbitrage.service.provider;

import com.shiyou.arbitrage.common.BasicServiceImpl;
import com.shiyou.arbitrage.common.Result;
import com.shiyou.arbitrage.data.model.PlatformInfo;
import com.shiyou.arbitrage.service.contract.PlatformInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.azoveh.arbitrage.service.impl
 * @Project: bloex-arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/14 0014 16:46
 */
@Service
public class PlatformInfoServiceImpl extends BasicServiceImpl<PlatformInfo> implements PlatformInfoService {

    @Override
    public Result<PlatformInfo> get(String platform) {

        PlatformInfo platformInfo = new PlatformInfo();
        platformInfo.setPlatform(platform);
        return super.selectOne(platformInfo);

    }

    @Override
    public Result<List<PlatformInfo>> getAll() {
        PlatformInfo platformInfo = new PlatformInfo();
        return super.select(platformInfo);
    }
}
