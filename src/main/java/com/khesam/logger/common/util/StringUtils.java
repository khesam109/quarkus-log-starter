package com.khesam.logger.common.util;

public class StringUtils {

    private StringUtils() {}

    public static boolean isEmpty(String input) {
        return input == null || input.length() == 0;
    }

    public static boolean notEmpty(String input) {
        return !isEmpty(input);
    }
}
