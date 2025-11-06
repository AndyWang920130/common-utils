package com.twsny.utils.signature;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
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

    public static void main(String[] args) {
        String source = "1234";
        String signedValue = ShaUtil.sign(source);
        System.out.println(signedValue);
    }
}
