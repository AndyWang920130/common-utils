package com.twsny.utils.string;

import com.twsny.constants.RegexConstants;

import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }
}
