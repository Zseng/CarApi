package com.zseng.car.common;

import java.util.regex.Pattern;

public class Config {

    public static final Pattern[] PATH_GUEST_CAN_ACCESS_PATTERN = new Pattern[]{
            Pattern.compile("^/client/1/user/(login|register|password-reset)"),
            Pattern.compile("^/test/.*")
    };

    public static final String IMG_PATH = "/users/cc/Dev/web/Sites/static/img";

}
