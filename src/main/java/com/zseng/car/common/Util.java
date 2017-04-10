package com.zseng.car.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {

    public static Long time() {
        return (System.currentTimeMillis() / 1000);
    }

    public static boolean isLatestDays(Long time, int day) {
        Long diff = Math.abs(time() - time);

        return 3600 * 24 * day > diff;
    }

    public static String getTimeString(DateFormat dateFormat, long timeStamp) {
        return dateFormat.format(new Date(timeStamp * 1000));
    }

    public static String getTimeString(long timeStamp) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(timeStamp * 1000));
    }

    public static int parseInt(Object obj) {
        try {
            return (int) Double.parseDouble("" + obj);
        } catch (Exception e) {

        }

        return 0;
    }

    public static long parseLong(Object obj) {
        try {
            return (long) Double.parseDouble("" + obj);
        } catch (Exception e) {

        }
        return 0;
    }

    public static String generateRandomString(int length) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        Random r = new Random(System.currentTimeMillis());
        char[] id = new char[length];
        for (int i = 0; i < length; i++) {
            id[i] = chars[r.nextInt(chars.length)];
        }
        return new String(id);
    }
}
