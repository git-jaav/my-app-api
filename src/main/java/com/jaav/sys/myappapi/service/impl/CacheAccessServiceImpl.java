package com.jaav.sys.myappapi.service.impl;

import com.jaav.sys.myappapi.model.api.DataCacheEntity;
import com.jaav.sys.myappapi.model.api.ProcessEntity;
import com.jaav.sys.myappapi.service.CacheAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
public class CacheAccessServiceImpl implements CacheAccessService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String saveData(DataCacheEntity data) {
        String key = UUID.randomUUID().toString();
        ValueOperations<String, DataCacheEntity> ops = this.redisTemplate.opsForValue();
        if (!this.redisTemplate.hasKey(key)) {
            ops.set(key, data);
            // prueba de invocacion del guardado con otra entidad
            //saveData2(data);
        }
        return key;
    }

    @Override
    public Optional<DataCacheEntity> getData(String key) {
        ValueOperations<String, DataCacheEntity> ops = this.redisTemplate.opsForValue();
        if (this.redisTemplate.hasKey(key)) {
            return Optional.ofNullable(ops.get(key));
        }
        return Optional.empty();
    }

    public String saveData2(DataCacheEntity data) {
        String key = "process-aux01";
        ValueOperations<String, ProcessEntity> ops = this.redisTemplate.opsForValue();
        if (!this.redisTemplate.hasKey(key)) {
            ops.set(key, new ProcessEntity("PROCESS FOR: " + data.getName(), "ENABLED", 2, 0));
        }
        return key;
    }
}
