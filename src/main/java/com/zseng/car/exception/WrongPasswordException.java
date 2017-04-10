package com.zseng.car.exception;

/**
 * Created by cc on 16/5/29.
 */
public class WrongPasswordException extends BaseException {

    public WrongPasswordException() {
        super(WRONG_PASSWORD, "wrong password");
    }
}
