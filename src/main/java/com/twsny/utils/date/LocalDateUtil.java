package com.twsny.utils.date;

import com.twsny.utils.string.StringUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {
    public static String dateToString(LocalDate localDate, String pattern) {
        if (localDate == null) return StringUtil.empty();
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(df);
    }

}
