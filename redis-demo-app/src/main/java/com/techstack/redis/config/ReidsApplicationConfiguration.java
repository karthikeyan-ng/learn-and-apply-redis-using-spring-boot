package com.techstack.redis.config;

import com.techstack.redis.model.Programmer;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ReidsApplicationConfiguration {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.port}")
    private int port;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxTotal;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    /**
     * Inorder to configure Redis Template you have to do 3 steps.
     * 1. Configure Jedis Client configuration by configuring the connection pool.
     * It's okay if you don't use pooling. But, in a real production application you will
     * use a pooling.
     *
     * 2. Create a Jedis Connection Factory with the Jedis Client Configuration in the Step1
     *
     * 3. Create a Redis Template and assign a Connection Factory from the Step2
     *
     */

    /** Step1 **/
    @Bean
    public JedisClientConfiguration getJedisClientConfiguration() {
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder builder =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)
                        JedisClientConfiguration.builder();

        GenericObjectPoolConfig genericeObjectPoolConfig = new GenericObjectPoolConfig();
        genericeObjectPoolConfig.setMaxTotal(maxTotal);
        genericeObjectPoolConfig.setMaxIdle(maxIdle);
        genericeObjectPoolConfig.setMinIdle(minIdle);
        return builder.poolConfig(genericeObjectPoolConfig).build();
    }

    /** Step2 **/
    @Bean
    public JedisConnectionFactory getJedisConnectionFactory(JedisClientConfiguration clientConfiguration) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        if(!password.isBlank()) {
            redisStandaloneConfiguration.setPassword(password);
        }
        redisStandaloneConfiguration.setPort(port);
        return new JedisConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
    }

    /** Step3 **/
    @Bean @Primary
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /** This is optional. If you are dealing with only String values, consider to use
     *  {@code StringRedisTemplate}
     * **/
    /*@Bean
    public RedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        return  stringRedisTemplate;
    }*/

    @Bean
    @Qualifier("listOperations")
    public ListOperations<String, Programmer> listOperations(RedisTemplate<String, Programmer> redisTemplate) {
        return redisTemplate.opsForList();
    }
}
