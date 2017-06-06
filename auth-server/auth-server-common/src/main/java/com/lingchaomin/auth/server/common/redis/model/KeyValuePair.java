package com.lingchaomin.auth.server.common.redis.model;

/**
 * key-value 对
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16/6/24 上午9:59
 */
public class KeyValuePair {

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "KeyValuePair{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
