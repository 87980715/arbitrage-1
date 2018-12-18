package com.shiyou.arbitrage;

import com.shiyou.arbitrage.api.Init;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Package: PACKAGE_NAME
 * @Project: arbitrage
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/12/17 0017 14:46
 */


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = {"com.shiyou.arbitrage.data.mapper"})

public class ArbitrageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArbitrageApplication.class, args);
        Init.start("BTCUSDT", null);
    }
}
