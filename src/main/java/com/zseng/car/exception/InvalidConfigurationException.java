package com.zseng.car.exception;

/**
 * Created by cc on 2017/4/12.
 */
public class InvalidConfigurationException extends BaseException {
    public InvalidConfigurationException(String message) {
        super(ERROR_INVALID_CONFIGURATION, message);
    }

    public InvalidConfigurationException() {
        super(ERROR_INVALID_CONFIGURATION, "invalid configuration");
    }
}