package com.twsny.utils.encrypt;

import com.twsny.utils.signature.ShaUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * 对称加密
 */
public class AESUtil
{
    private static final Logger log = LoggerFactory.getLogger(AESUtil.class);

    private static final String ALGORITHM_KEY = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS7Padding";
    private static final String iv = "abcdef1234567890";  // 16 字节
    /*
     * Encrypt data with specified secret keys
     *
     * @param sources, ready for encrypting data.
     *
     * @param secretKeys, encrypt data with this secret keys.
     */
    public static byte[] encrypt(byte[] secretKeys, byte[] sources)
    {
        Security.addProvider(new BouncyCastleProvider());
        try
        {
            SecretKeySpec keySpec = new SecretKeySpec(secretKeys, ALGORITHM_KEY);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encryptedData = cipher.doFinal(sources);
            return encryptedData;
        }
        catch (Exception e)
        {
            log.error("encrypt err: {}", e.getMessage());
            return null;
        }
    }

    /*
     * Decrypt data with specified secret keys
     *
     * @param sources, ready for decrypting data.
     *
     * @param secretKeys, decrypt data with this secret keys.
     */
    public static byte[] decrypt(byte[] secretKeys, byte[] sources)
    {
        Security.addProvider(new BouncyCastleProvider());
        try
        {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeys, ALGORITHM_KEY);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
            byte[] decryptedData = cipher.doFinal(sources);
            return decryptedData;
        }
        catch (Exception e)
        {
            log.error("decrypt error: {}", e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        String key128 = "1234567890abcdef";
        String value = "5678";
        byte[] encryptValue = AESUtil.encrypt(key128.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
        byte[] decryptValue = AESUtil.decrypt(key128.getBytes(StandardCharsets.UTF_8), encryptValue);
        System.out.println("result:" + new String(decryptValue));
    }
}
