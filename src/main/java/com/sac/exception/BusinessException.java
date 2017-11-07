package com.sac.exception;

import com.sun.xml.internal.rngom.parse.host.Base;

/**
 * @author:eason
 * @Description
 * @Date: 21:52,2017/11/7
 * @ModifiedBy
 */
public class BusinessException extends BaseException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
