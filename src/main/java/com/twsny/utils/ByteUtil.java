package com.twsny.utils;

import com.twsny.utils.collection.ArrayUtil;
import com.twsny.utils.collection.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ByteUtil
{
    private static Logger log = LoggerFactory.getLogger(ByteUtil.class);


    public static boolean bytes2Boolean(byte byteValue)
    {
        return byteValue == 1;
    }


    /**
     * 将整型转成字节数组
     * @param i 整型
     * @param length 数组长度
     * @return
     */
    public static byte[] intToByteArray(int i, int length)
    {
        if( length < 1 ) return null;
        byte[] result = new byte[length];
        //由低位到高位
        for(int j = 0; j < length; j++)
        {
            result[j] = (byte)((i >> 8 * j) & 0xFF);
        }

        return result;
    }

    /**
     * 将字节数组转换成整型
     * @param bytes
     * @return
     */
    public static int bytes2int(byte[] bytes)
    {
        int result = 0;

        for(int i = 0; i < bytes.length; i++)
        {
            int a = (bytes[i] & 0xff) << i * 8;
            result = result | a;
        }

        return result;
    }

    public static Float bytes2Float(byte[] bytes)
    {
        int intValue =  (0xff & bytes[3]) << 24  |
            (0xff & bytes[2]) << 16  |
            (0xff & bytes[1]) << 8   |
            (0xff & bytes[0]);

        return Float.intBitsToFloat(intValue);
    }

    /**
     * 将两个Int类型的数合并
     * @return
     */
    public static int mergeInt(Integer high, Integer low)
    {
        return (high & 0xff) << 8 | (low & 0xff);
    }

    /**
     * 将字节数组转成16进制字符串显示
     * @param b 数组
     * @return
     */
    public static String bytes2HexString(byte[] b) {
        String[] stringArr = bytes2HexStringArr(b);
        return ArrayUtil.parseString(stringArr);
    }

    /**
     * 将字节数组转成16进制字符串显示
     * @param b 数组
     * @return
     */
    public static String[] bytes2HexStringArr(byte[] b) {
        String[] hexStrArr = new String[b.length];

        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexStrArr[i] = hex.toUpperCase();
        }

        return hexStrArr;
    }

    public static String[] byteList2HexStringArr(List<Byte> bList) {
        byte[] bArr = ArrayUtil.byteListToArray(bList);
        return bytes2HexStringArr(bArr);
    }

    /**
     *
     * @param bList
     * @param appendStr 连接符
     * @return
     */
    public static String byteListToHexString(List<Byte> bList, String appendStr){
        String[] stringArr = byteList2HexStringArr(bList);
        return ArrayUtil.parseString(stringArr, appendStr);
    }
}
