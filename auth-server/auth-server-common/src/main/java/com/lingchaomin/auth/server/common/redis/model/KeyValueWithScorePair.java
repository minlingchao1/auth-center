package com.lingchaomin.auth.server.common.redis.model;

/**
 * key-value 对 带分数
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16/6/24 上午9:59
 */
public class KeyValueWithScorePair {

    private String key;

    private String value;

    private double score;

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "KeyValueWithScorePair{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", score=" + score +
                '}';
    }
}
