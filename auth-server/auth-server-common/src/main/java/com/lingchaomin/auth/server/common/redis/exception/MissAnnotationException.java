package com.lingchaomin.auth.server.common.redis.exception;

/**
 * 缺少注解异常
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16/6/24 上午10:11
 */
public class MissAnnotationException extends Exception {

    /**
     * 信息模版
     */
    private static final String MSG_TEMP = "class:[%s] miss annotation:[%s]";

    /**
     * 类名称
     */
    private String clazzName;

    /**
     * 注解名称
     */
    private String annoName;


    public MissAnnotationException(String messge) {
        super(messge);
    }

    public MissAnnotationException(String clazzName, String annoName) {
        super(String.format(MSG_TEMP, clazzName, annoName));
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getAnnoName() {
        return annoName;
    }

    public void setAnnoName(String annoName) {
        this.annoName = annoName;
    }
}
