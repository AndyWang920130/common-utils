package com.twsny.constants;

public class RegexConstants {

    //字符串去除特殊符号
    public static final String SPECIAL_CHAR = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";

    //电话格式
    public static final String PHONE_FORMAT = "^1(3|4|5|6|7|8|9)\\d{9}$";

    //邮件格式
    public static final String EMAIL_FORMAT = "^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,5}$";

    //用户名
    public static final String USER_NAME_FORMAT = "^[a-zA-Z0-9_]{4,20}$";

    //密码
    public static final String PASSWORD_FORMAT = "^(?=.{8,20}$)(?=.*[A-Za-z])(?=.*\\d)[A-Za-z0-9_=@.+-]+$";

    //时间yyyyMMdd 1900-2099之间
    public static final String DATE_FORMAT = "^((?:(?:19|20)[0-9]{2})(?:(?:0[13578]|1[02])(?:0[1-9]|[12][0-9]|3[01])|(?:0[469]|11)(?:0[1-9]|[12][0-9]|30)|02(?:0[1-9]|1[0-9]|2[0-8]))|(?:((?:19|20)(?:04|08|[2468][048]|[13579][26]))0229))$";

    //时间字符串格式：10:45
    public static final String TIME_HM_FORMAT = "^(([0-1]([0-9]))|(2([0-3]))):[0-5][0-9]$";

    //时间字符串格式：10:45:01
    public static final String TIME_HMS_FORMAT = "^(([0-1]([0-9]))|(2([0-3]))):[0-5][0-9]:[0-5][0-9]$";

    //发放时间字符串格式：10:45-22:55
    public static final String TIME_RANGE_FORMAT = "^((([0-1]([0-9]))|(2([0-3]))):[0-5][0-9])-((([0-1]([0-9]))|(2([0-3]))):[0-5][0-9])$";

    //日期时间字符串： yyyyMMdd HH:mm
    public static final String DATE_TIME_FORMAT =
            "^((?:(?:19|20)[0-9]{2})" +
                    "(?:(?:0[13578]|1[02])(?:0[1-9]|[12][0-9]|3[01])" +
                    "|(?:0[469]|11)(?:0[1-9]|[12][0-9]|30)" +
                    "|02(?:0[1-9]|1[0-9]|2[0-8]))" +
                    "|(?:(?:19|20)(?:04|08|[2468][048]|[13579][26])0229))" +
                    "\\s" +                              // 日期和时间之间一个空格
                    "((?:[01][0-9])|(?:2[0-3])):[0-5][0-9]$";

}
