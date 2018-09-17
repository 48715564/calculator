package com.cn.service;

import com.cn.common.exception.ResponseException;
import com.cn.common.utils.Provider;
import com.cn.common.utils.StringUtils;
import com.fasterxml.jackson.databind.JavaType;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 缓存服务
 * Created by bozhou on 2017/12/7.
 */
@Service
public class CacheService {
    @Autowired
    private HazelcastInstance hazelcastInstance;
    @Autowired
    private Provider provider;

    @Value("${project.cache.disableCache:false}")
    private Boolean disableCache;

    /**
     * 删除hashMap的缓存
     *
     * @param mapName
     * @param key
     */
    public void removeHashMapCache(String mapName, String key) {
        if (!disableCache) {
            hazelcastInstance.getMap(mapName).delete(key);
        }
    }

    /**
     * 删除hashMap的缓存
     *
     * @param mapName
     * @param key
     */
    public void removeHashMapCache(String mapName, String... key) {
        if (!disableCache) {
            for(String k:key) {
                hazelcastInstance.getMap(mapName).delete(k);
            }
        }
    }

    public Object getListHashMapByKey(String mapName, String key, Class className) {
        if (!disableCache) {
            String json = String.valueOf(hazelcastInstance.getMap(mapName).get(key));
            if (StringUtils.isNotBlank(json)) {
                try {
                    JavaType javaType = provider.getObjectMapper().getTypeFactory().constructParametricType(List.class, className);
                    return provider.getObjectMapper().readValue(json, javaType);
                } catch (IOException e) {
                    throw new ResponseException("缓存异常", HttpStatus.valueOf(500));
                }
            }
        }
        return null;
    }

    public Object getMapHashMapByKey(String mapName, String key, Class className) {
        if (!disableCache) {
            String json = String.valueOf(hazelcastInstance.getMap(mapName).get(key));
            if (StringUtils.isNotBlank(json)) {
                try {
                    JavaType javaType = provider.getObjectMapper().getTypeFactory().constructParametricType(HashMap.class, String.class, className);
                    ;
                    return provider.getObjectMapper().readValue(json, javaType);
                } catch (IOException e) {
                    throw new ResponseException("缓存异常", HttpStatus.valueOf(500));
                }
            }
        }
        return null;
    }

    public Object getHashMapByKeyString(String mapName, String key) {
        if (!disableCache) {
            return hazelcastInstance.getMap(mapName).get(key);
        }
        return null;
    }

    public Object getHashMapByKey(String mapName, String key, Class className) {
        if (!disableCache) {
            String json = String.valueOf(hazelcastInstance.getMap(mapName).get(key));
            if (StringUtils.isNotBlank(json)) {
                try {
                    return provider.getObjectMapper().readValue(json, className);
                } catch (IOException e) {
                    throw new ResponseException("缓存异常", HttpStatus.valueOf(500));
                }
            }
        }
        return null;
    }

    /**
     * 保存hashMap的缓存
     *
     * @param mapName
     * @param key
     */
    public int saveHashMapCache(String mapName, String key, Object value) {
        if (!disableCache) {
            try {
                hazelcastInstance.getMap(mapName).put(key, provider.getObjectMapper().writeValueAsString(value));
                return 1;
            } catch (IOException e) {
                throw new ResponseException("缓存异常", HttpStatus.valueOf(500));
            }
        }
        return 0;
    }

    /**
     * 保存hashMap的缓存
     *
     * @param mapName
     * @param key
     */
    public int saveHashMapCache(String mapName, String key, String value) {
        if (!disableCache) {
            hazelcastInstance.getMap(mapName).put(key, value);
            return 1;

        }
        return 0;
    }

}
