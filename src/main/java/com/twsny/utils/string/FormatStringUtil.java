package com.twsny.utils.string;

import com.twsny.constants.RegexConstants;

import java.util.regex.Pattern;

public class FormatStringUtil {
    public static boolean checkPhoneNumber(String phoneNumber)
    {
        if(StringUtil.isBlank(phoneNumber)) return false;
        Pattern phonePattern = Pattern.compile(RegexConstants.PHONE_FORMAT);
        return phonePattern.matcher(phoneNumber).find();
    }

    public static boolean checkEmailNumber(String email)
    {
        if(StringUtil.isBlank(email)) return false;
        Pattern emailPattern = Pattern.compile(RegexConstants.EMAIL_FORMAT);
        return emailPattern.matcher(email).find();
    }

    public static boolean checkUserName(String userName)
    {
        if(StringUtil.isBlank(userName)) return false;
        Pattern userNamePattern = Pattern.compile(RegexConstants.USER_NAME_FORMAT);
        return userNamePattern.matcher(userName).find();
    }

    /**
     * (?=.{8,20}$) —— 总长度必须是 8 到 12。
     * (?=.*[A-Za-z]) —— 至少包含一个字母（大小写任意）。
     * (?=.*\d) —— 至少包含一个数字。
     * [A-Za-z0-9_]+ —— 只允许字母、数字、下划线、等号、@号（即禁止其它特殊字符）。
     * ^...$ —— 整个字符串必须符合。
     * @param password
     * @return
     */
    public static boolean checkPassword(String password)
    {
        if(StringUtil.isBlank(password)) return false;
        Pattern passwordPattern = Pattern.compile(RegexConstants.PASSWORD_FORMAT);
        return  passwordPattern.matcher(password).find();
    }

    public static boolean checkDate(String date)
    {
        if(StringUtil.isBlank(date)) return false;
        Pattern datePattern = Pattern.compile(RegexConstants.DATE_FORMAT);
        return datePattern.matcher(date).find();
    }


    /**
     * 时分格式校验
     * @param time
     * @return
     */
    public static boolean checkHMTime(String time)
    {
        if(StringUtil.isBlank(time)) return false;
        Pattern timePattern = Pattern.compile(RegexConstants.TIME_HM_FORMAT);
        return timePattern.matcher(time).find();
    }

    /**
     * 时分秒格式校验
     * @param time
     * @return
     */
    public static boolean checkHMSTime(String time)
    {
        if(StringUtil.isBlank(time)) return false;
        Pattern timePattern = Pattern.compile(RegexConstants.TIME_HMS_FORMAT);
        return timePattern.matcher(time).find();
    }

    /**
     * 日期时间校验，例: yyyyMMdd hh:mm
     * @param dateTime
     * @return
     */
    public static boolean checkDateTime(String dateTime)
    {
        if(StringUtil.isBlank(dateTime)) return false;
        Pattern dateTimePattern = Pattern.compile(RegexConstants.DATE_TIME_FORMAT);
        return dateTimePattern.matcher(dateTime).find();
    }

    /**
     * 是否为时间范围,例: 09:00-18:00
     * @param timeRange
     * @return
     */
    public static boolean checkTimeRange(String timeRange)
    {
        if(StringUtil.isBlank(timeRange)) return false;
        Pattern timeRangePattern = Pattern.compile(RegexConstants.TIME_RANGE_FORMAT);
        return timeRangePattern.matcher(timeRange).find();
    }

    /**
     * @return 包含字母 返回true
     * @brief 检测密码中是否包含字母（不区分大小写）
     * @param[in] password            密码字符串
     */
    public static boolean checkContainLetter(String str) {
        if(StringUtil.isBlank(str)) return false;
        char[] chPass = str.toCharArray();

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLetter(chPass[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return 包含小写字母 返回true
     * @brief 检测密码中是否包含小写字母
     * @param[in] password            密码字符串
     */
    public static boolean checkContainLowerCase(String str) {
        if(StringUtil.isBlank(str)) return false;
        char[] chPass = str.toCharArray();

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLowerCase(chPass[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return 包含大写字母 返回true
     * @brief 检测密码中是否包含大写字母
     * @param[in] password            密码字符串
     */
    public static boolean checkContainUpperCase(String str) {
        if(StringUtil.isBlank(str)) return false;
        char[] chPass = str.toCharArray();
        for (int i = 0; i < chPass.length; i++) {
            if (Character.isUpperCase(chPass[i])) {
                return true;
            }
        }

        return false;
    }


    /**
     * @return 包含数字 返回true
     * @brief 检测密码中是否包含数字
     * @param[in] password            密码字符串
     */
    public static boolean checkContainDigit(String str) {
        char[] chPass = str.toCharArray();
        for (int i = 0; i < chPass.length; i++) {
            if (Character.isDigit(chPass[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否包含特殊字符
     * @param str
     * @return
     */
    public static boolean checkContainSpecialChar(String str) {
        if(StringUtil.isBlank(str)) return false;
        return RegexConstants.SPECIAL_CHAR.indexOf(str) > -1;
    }

    public static void main(String[] args) {
        System.out.println(FormatStringUtil.checkPhoneNumber("15651125953"));
        System.out.println(FormatStringUtil.checkEmailNumber("wanghf@uuu.com"));
        System.out.println(FormatStringUtil.checkUserName("daa12_"));
        System.out.println(FormatStringUtil.checkPassword("daa12_122123312"));
        System.out.println(FormatStringUtil.checkDate("19930301"));
        System.out.println(FormatStringUtil.checkHMTime("01:12"));
        System.out.println(FormatStringUtil.checkHMSTime("12:12:01"));
        System.out.println(FormatStringUtil.checkDateTime("19930102 12:12"));
        System.out.println(FormatStringUtil.checkContainSpecialChar("&*()"));
    }
}
