package com.wbl.dao;

import java.util.List;
import java.util.Set;

/**
 * Created by wbl on 16/10/8.
 */
public interface RedisClient {
    void set(String key,String value);
    String get(String key);

    void lpush(String key,byte[] value);
    void lpush(String key,String value);
    List<String> getListValues(String key, int start, int end);
    List<String> getListAllValues(String key);

    long del(String key);
    long del(Set<String> keys);

    Set<String> keys(String pattern);
}
