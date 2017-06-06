package com.lingchaomin.auth.server.common.redis.model;

import java.util.Map;

/**
 * key-map 对
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16/6/24 上午9:57
 */
public class KeyMapPair {

    private String key;

    private Map<String, String> map;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "KeyMapPair{" +
                "key='" + key + '\'' +
                ", map=" + map +
                '}';
    }
}
