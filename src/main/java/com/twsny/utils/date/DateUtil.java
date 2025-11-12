package com.twsny.utils.date;

import com.twsny.utils.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static boolean checkFormat(String strDatetime, String timeFormat) {
        if (StringUtil.isBlank(strDatetime)) return false;
        SimpleDateFormat formatDate = new SimpleDateFormat(timeFormat);
        try {
            formatDate.parse(strDatetime);
        } catch (ParseException e) {
            log.error("parse error: {}", e.getMessage());
            return false;
        }
        return true;
    }
    public static Date parse(String strDatetime, String timeFormat)
    {
        if(strDatetime == null || strDatetime.length() != timeFormat.length())
        {
            log.error("source data length is not match format data length");
            return null;
        }

        Date datetime;
        try
        {
            SimpleDateFormat formatDate = new SimpleDateFormat(timeFormat);
            datetime = formatDate.parse(strDatetime);
        }
        catch(ParseException e)
        {
            log.error("parse error: {}", e.getMessage());
            return null;
        }
        return datetime;
    }

    public static String parse(Date datetime, String timeFormat)
    {
        SimpleDateFormat formatDate = new SimpleDateFormat(timeFormat);
        return formatDate.format(datetime);
    }

    public static long currentMillis() {
        return new Date().getTime();
    }


}
