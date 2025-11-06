package com.twsny.constants;

public class RegexConstants {

    //字符串去除特殊符号
    public static final String REG_EX = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";

    //电话格式
    public static final String PHONE_FORMAT = "^[1][0-9]{10}$";

    //时间yyyyMMdd
    public static final String DATE_FORMAT_NUMBER = "^[0-9]{8}$";

    //时间字符串格式：10:45
    public static final String TIME_FORMAT = "^(([0-1]([0-9]))|(2([0-3]))):[0-5][0-9]";


    //发放时间字符串格式：10:45-22:55
    public static final String PUBLIC_TIME_FORMAT = "^((([0-1]([0-9]))|(2([0-3]))):[0-5][0-9])-(([0-1]([0-9]))|(2([0-3]))):[0-5][0-9]";

    //mq队列格式
    public static final String QUEUE_FORMAT = "^real_time_data_[a-zA-Z|0-9]+";

    //日期字符串： yyyy-MM-dd
    public static final String DATE_FORMAT = "^((?:19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";

    //日期时间字符串： yyyy-MM-dd HH:mm:ss
    public static final String DATE_TIME_FORMAT = "^([1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9])\\s(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";

}
