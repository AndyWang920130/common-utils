package com.twsny.utils.crypt.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


public class AESEncryption {
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/NoPadding";// 默认的加密算法

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param password 加密密码
     * @return
     */
    public static String encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
            int blockSize = cipher.getBlockSize();
            byte[] byteContent = content.getBytes();
            int plaintextLength = byteContent.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(byteContent, 0, plaintext, 0, byteContent.length);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));// 初始化为加密模式的密码器
            return Base64.encodeBase64String(cipher.doFinal(plaintext));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {
        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));// 初始化为加密模式的密码器
            // 执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));// 解密
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        int len = password.getBytes().length;
        if (len % 16 != 0) {
            len = len + (16 - (len % 16));
        }
        byte[] newpass = new byte[len];
        System.arraycopy(password.getBytes(), 0, newpass, 0, password.getBytes().length);
        return new SecretKeySpec(newpass, "AES");
    }

}
