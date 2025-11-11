package com.twsny.utils.crypt.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptor
{
	private static final String ALGORITHM_KEY = "AES";
	private static final String TRANSFORMATION = "AES/ECB/NoPadding";

	/*
	 * Encrypt data with specified secret keys
	 *
	 * @param sources, ready for encrypting data.
	 *
	 * @param secretKeys, encrypt data with this secret keys.
	 */
	public static byte[] encrypt(byte[] secretKeys, byte[] sources)
	{
		try
		{
			SecretKeySpec keySpec = new SecretKeySpec(secretKeys, ALGORITHM_KEY);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			byte[] encryptedData = cipher.doFinal(sources);
			return encryptedData;
		}
		catch (Exception e)
		{
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
		try
		{
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeys, ALGORITHM_KEY);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] decryptedData = cipher.doFinal(sources);
			return decryptedData;
		}
		catch (Exception e)
		{
			return null;
		}
	}

//	public static void main(String[] args)
//	{
//		byte[] forEncrypt = {
//				0x06, 0x01, 0x01, 0x01,
//				0x2D, 0x1A, 0x68, 0x3D, 0x48, 0x27, 0x1A, 0x18, 0x31, 0x6E, 0x47, 0x1A
//			};
//
//		System.out.println(ByteUtil.bytes2HexStringForLogPrint(encrypt(S_BlueToothLock.SECRET_KEYS, forEncrypt)));
//
//		byte[] fordecrypt = {
//				(byte)0xA5, 0x6C, 0x7D, 0x75, 0x48, (byte)0xDE, (byte)0xFF, (byte)0xEF,
//				(byte)0xE7, (byte)0xAC, 0x1E, (byte)0xA9, (byte)0xBC, (byte)0xCE, 0x66, (byte)0xE6,
//			};
//
//		byte[] tokenFromLock = ByteUtil.Hex2Bytes("45198fbe6fa02d8df4227190fb951480");
//		String secretkey = "47:12:36:1C:2B:17:62:2F:35:4B:54:50:19:43:19:33";
//		secretkey = secretkey.replace(":", "");
//		byte[] secretkeyBytes = ByteUtil.Hex2Bytes(secretkey);
//
//		System.out.println(ByteUtil.bytes2HexStringForLogPrint(decrypt(secretkeyBytes, tokenFromLock)));
//	}
}
