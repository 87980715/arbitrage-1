package com.shiyou.arbitrage.config;

import com.shiyou.arbitrage.common.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileName: CommonConfig
 * Description: 通用配置
 * Author:
 * Date:
 */
@Configuration
public class ObjectIdGeneratorConfig {

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(@Value("${snowflake.workerId:0}") long workerId, @Value("${snowflake.datacenterId:0}") long datacenterId) {
        return new SnowflakeIdWorker(workerId, datacenterId);
    }
}
