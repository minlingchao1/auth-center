package com.lingchaomin.auth.server.common.redis.exception;

/**
 * TODO
 *
 * @author : lizhong.chen
 * @version : 1.0
 * @since : 16/6/24 下午12:21
 */
public class InvalidParameterException extends IllegalArgumentException {
    /**
     * Constructs an InvalidParameterException with no detail message.
     * A detail message is a String that describes this particular
     * exception.
     */
    public InvalidParameterException() {
        super();
    }

    /**
     * Constructs an InvalidParameterException with the specified
     * detail message.  A detail message is a String that describes
     * this particular exception.
     *
     * @param msg the detail message.
     */
    public InvalidParameterException(String msg) {
        super(msg);
    }
}
