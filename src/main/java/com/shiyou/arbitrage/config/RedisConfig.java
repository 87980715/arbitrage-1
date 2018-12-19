package com.shiyou.arbitrage.config;

import com.shiyou.arbitrage.common.RedisObjectSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * FileName: RedisConfig
 * Description:
 * Author: ZhangYX
 * Date:  2017/12/22
 */

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@ConditionalOnClass({JedisCluster.class})
public class RedisConfig {

    @Value("${db.redis.host:47.98.121.33}")
    private String host;
    @Value("${db.redis.password:redis-admin}")
    private String password;
    @Value("${db.redis.min-idle-connections:8}")
    private Integer minIdleConnections;
    @Value("${db.redis.max-idle-connections:16}")
    private Integer maxIdleConnections;
    @Value("${db.redis.max-connections:32}")
    private Integer maxConnections;
    @Value("${db.redis.max-wait-time:30000}")
    private Integer maxWaitTime;
    //出现异常最大重试次数
    @Value("${db.redis.max-attempts:5}")
    private Integer maxAttempts;

    @Bean
    public Jedis MyJedis() {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(16);
        // 最大空闲数
        poolConfig.setMaxIdle(2);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(5000);
        JedisPool pool = new JedisPool(poolConfig, "47.98.121.33", 6379, 0, "redis-admin");
        return pool.getResource();

    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }


}
