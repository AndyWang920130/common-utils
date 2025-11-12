package com.twsny.utils.string;

import com.twsny.constants.RegexConstants;

import java.util.Random;
import java.util.regex.Pattern;

public class StringUtil {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    public static String empty() {
        return "";
    }

    public static boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    /**
     * 获取指定小数位的字符串
     * @param rawData
     * @param decimalLength
     * @return
     */
    public static String parseDecimalString(Object rawData, int decimalLength) {
        String format = "%." + decimalLength + "f";  // %.6f
        return String.format(format, rawData);
    }

    public static String parseHexString(byte rawData) {
        return String.format("%02X",rawData);
    }
}
