package com.wbl.dao.impl;

import com.wbl.dao.RedisClient;
import com.wbl.dao.RedisPoolManager;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by wbl on 16/10/8.
 */
@Repository
public class RedisClientImpl implements RedisClient {

    private Jedis jedis = RedisPoolManager.createInstance();

    @Override
    public void set(String key, String value) {
        jedis.set(key,value);
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public void lpush(String key, byte[] value) {
        jedis.lpush(key.getBytes(),value);
    }

    @Override
    public void lpush(String key, String value) {
        jedis.lpush(key,value);
    }

    @Override
    public List<String> getListValues(String key, int start, int end) {
        return jedis.lrange(key,start,end);
    }

    @Override
    public List<String> getListAllValues(String key) {
        return getListValues(key,0,-1);
    }

    @Override
    public long del(String key) {
        return jedis.del(key);
    }

    @Override
    public long del(Set<String> keys) {
        return jedis.del(keys.toArray(new String[keys.size()]));
    }

    @Override
    public Set<String> keys(String pattern) {
        return jedis.keys(pattern);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println(jedis.get("dd_09-2016_operateTimes"));
        System.out.println(jedis.keys("*_operateTimes"));
        jedis.del("dd_09-2016_operateTimes");
        System.out.println(jedis.keys("*_operateTimes"));
    }
}
