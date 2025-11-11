package com.twsny.utils.crypt;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.UserDataHandler;

import javax.sound.midi.Soundbank;
import java.security.MessageDigest;

/**
 * 哈希加密
 * sha1 签名工具
 * 将签名后的值进行比较，防篡改
 */
public class ShaUtil {
    private static final Logger log = LoggerFactory.getLogger(ShaUtil.class);

    private final static String algorithm = "SHA-1";
    public static String sign(String source) {
        try {
            MessageDigest digestInstance = MessageDigest.getInstance(algorithm);
            digestInstance.update(source.getBytes("UTF-8"));
            byte[] hashValue = digestInstance.digest();
            byte[] encodedMessage = Base64.encodeBase64(hashValue);
            return new String(encodedMessage);
        } catch (Exception e) {
            log.error("Error occurred when sign method was invoked.ex:", e);
        }
        return null;
    }


    public static boolean check(String source, String shaData) {
        return  shaData.equals(sign(source));
    }


    public static void main(String[] args) {
        String source = "1234";
        String signedValue = ShaUtil.sign(source);
        System.out.println(signedValue);
        System.out.println(ShaUtil.check(source, signedValue));
        System.out.println(ShaUtil.check("12345", signedValue));
    }
}
