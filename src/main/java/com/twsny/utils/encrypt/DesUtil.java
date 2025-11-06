package com.twsny.utils.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;


/**
 * 对称加密
 * be replaced by AESUtil
 * @see com.twsny.utils.encrypt.AESUtil
 */
@Deprecated
public class DesUtil
{
    public static final String ENCRYPT_KEY= "FKRISJWK";

	//加密
	public static String encryptData(String input) throws Exception {
	    SecureRandom sr = new SecureRandom();
	    byte rawKeyData[] = ENCRYPT_KEY.getBytes();
	    DESKeySpec dks = new DESKeySpec(rawKeyData);

	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	    SecretKey key = keyFactory.generateSecret(dks);

	    Cipher c = Cipher.getInstance("DES");
	    c.init(Cipher.ENCRYPT_MODE, key, sr);
	    byte[] cipherByte = c.doFinal(input.getBytes());
		String dec = Base64.getEncoder().encodeToString(cipherByte);
	    return dec;
	}

	//解密
	public static String decryptData(String input) throws Exception {
		String s = input.replaceAll(" +","+");
		s = s.replaceAll("\r\n","");
	  	byte[] dec = Base64.getDecoder().decode(s);
	    SecureRandom sr = new SecureRandom();
	    byte rawKeyData[] = ENCRYPT_KEY.getBytes();

	    DESKeySpec dks = new DESKeySpec(rawKeyData);

	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

	    SecretKey key = keyFactory.generateSecret(dks);

	    Cipher c = Cipher.getInstance("DES");
	    c.init(Cipher.DECRYPT_MODE, key, sr);
	    byte[] clearByte = c.doFinal(dec);

	    return new String(clearByte);
	}
}
