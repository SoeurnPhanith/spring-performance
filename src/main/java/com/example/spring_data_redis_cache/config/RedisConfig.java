package com.example.spring_data_redis_cache.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching // បើកការប្រើ Cache នៅ project (អាចប្រើ @Cacheable, @CacheEvict, @CachePut)
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        // ObjectMapper សម្រាប់ serialize/deserialize object និង list
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(
                BasicPolymorphicTypeValidator.builder()
                        .allowIfSubType(Object.class) // អនុញ្ញាត type មួយណាក៏បាន
                        .build(),
                ObjectMapper.DefaultTyping.EVERYTHING,
                JsonTypeInfo.As.PROPERTY // រក្សា type info នៅក្នុង JSON
        );

        // Serializer ដែលប្រើ Jackson JSON + type info
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);

        // កំណត់ configuration សម្រាប់ Redis cache
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) // cache ត្រូវ expire បន្ទាប់ 10 នាទី
                .disableCachingNullValues() // មិន cache null values
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer)); // ប្រើ serializer JSON

        // បង្កើត RedisCacheManager ដែល Spring នឹងប្រើសម្រាប់ caching
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfig)
                .build();
    }
}