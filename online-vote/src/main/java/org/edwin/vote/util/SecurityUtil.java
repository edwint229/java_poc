package org.edwin.vote.util;

import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtil {
	public static final String KEY_ALGORITHM = "AES";
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final String DEFAULT_KEY = "4UKvr01xOxZhBaJUk88dxA==";

	public static String getRandomString(int length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_@#$%&";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * Generate key
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String generateKey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(128);
		SecretKey secretKey = kg.generateKey();
		return Base64.encodeBase64String(secretKey.getEncoded());
	}

	/**
	 * Convert bytes key to Key Object
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Key toKey(byte[] key) throws Exception {
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}

	public static String encrypt(String data) throws Exception {
		return encrypt(data, DEFAULT_KEY);
	}

	/**
	 * Encrypt Data
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws Exception {
		Key k = toKey(Base64.decodeBase64(key));
		// If we using PKCS7Padding, we need to using BouncyCastle component
		// like below
		// Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
	}

	public static String decrypt(String data) throws Exception {
		return decrypt(data, DEFAULT_KEY);
	}

	/**
	 * Decrypt data
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws Exception {
		Key k = toKey(Base64.decodeBase64(key));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return new String(cipher.doFinal(Base64.decodeBase64(data)));
	}

	public static void main(String[] args) throws Exception {
		String source = "Pass1234";
		System.out.println("Orignal: " + source);

		String key = SecurityUtil.generateKey();
		System.out.println("Key: " + key);

		String encryptData = SecurityUtil.encrypt(source);
		System.out.println("Encrypted: " + encryptData);

		String decryptData = SecurityUtil.decrypt(encryptData);
		System.out.println("Decrypted: " + decryptData);
	}

}
