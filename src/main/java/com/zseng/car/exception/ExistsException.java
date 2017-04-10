package com.zseng.car.exception;

/**
 * Created by cc on 16/5/29.
 */
public class ExistsException extends BaseException {

    public ExistsException() {
        super(ERROR_EXISTS, "exists");
    }

    public ExistsException(String message) {
        super(ERROR_EXISTS, message);
    }
}
