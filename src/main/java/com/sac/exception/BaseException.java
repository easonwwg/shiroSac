package com.sac.exception;

/**
 * Created by 99079 on 2017/11/7.
 */
public class BaseException extends  RuntimeException {
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
