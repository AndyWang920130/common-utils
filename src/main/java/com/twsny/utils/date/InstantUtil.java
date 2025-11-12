package com.twsny.utils.date;

import com.twsny.utils.string.StringUtil;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class InstantUtil {
    public static String toLocalString(Instant instantDate, String formatterPatter) {
        if (instantDate == null) return StringUtil.empty();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterPatter);
        return formatter.format(ZonedDateTime.ofInstant(instantDate, ZoneId.systemDefault()));
    }

}
