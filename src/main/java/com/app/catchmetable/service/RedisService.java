package com.app.catchmetable.service;

import com.app.catchmetable.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Component
@RequiredArgsConstructor
@Service
public class RedisService {
    final private RedisTemplate<String,Object> redisTemplate;

    public void setValue(String key, Object data){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,data);
    }

    public void setValue(String key, Object data, Duration duration){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,data,duration);
    }

    public Object getValue(String key){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
    public String getStringValue(String key){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return StringUtils.nvl(valueOperations.get(key),"");
    }
    public void deleteValue(String key){
        redisTemplate.delete(key);
    }
    public void expiredValue(String key,int timeout){
        redisTemplate.expire(key,timeout, TimeUnit.MILLISECONDS);
    }
}
