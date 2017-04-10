package com.zseng.car.common;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by cc on 16/5/29.
 */
public class PasswordHashEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            return PasswordHash.createHash((String) rawPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            return PasswordHash.validatePassword((String) rawPassword, encodedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return false;
    }
}
