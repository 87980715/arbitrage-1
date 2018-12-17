package com.shiyou.arbitrage.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MysqlConfig {

    @Value("${db.mysql.url}")
    private String url;
    @Value("${db.mysql.user-name}")
    private String userName;
    @Value("${db.mysql.password}")
    private String password;
    @Value("${db.mysql.initial-connections:8}")
    private Integer initialConnections;
    @Value("${db.mysql.min-idle-connections:16}")
    private Integer minIdleConnections;
    @Value("${db.mysql.max-connections:32}")
    private Integer maxConnections;
    @Value("${db.mysql.max-wait-time:30000}")
    private Integer maxWaitTime;

    @Bean
    @Primary
    public DataSource createDataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(url);
        datasource.setUsername(userName);
        datasource.setPassword(password);
        datasource.setInitialSize(initialConnections);
        datasource.setMinIdle(minIdleConnections);
        datasource.setMaxActive(maxConnections);
        datasource.setMaxWait(maxWaitTime);

        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setValidationQuery("SELECT 1 FROM DUAL");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setMinEvictableIdleTimeMillis(1800000);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        //datasource.setUseLocalSessionState(false);

        return datasource;
    }

}
