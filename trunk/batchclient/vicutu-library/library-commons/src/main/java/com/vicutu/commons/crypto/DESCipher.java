package com.vicutu.commons.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public final class DESCipher {
	private static final String ALGORITHM = "DES";

	private static final byte[] KEY = { 0x11, 0x22, 0x4F, 0x58, 0x40, 0x38, 0x28, 0x25 };

	private DESCipher() {
	}

	private static byte[] encodeBytes(byte[] password) throws Exception {
		SecretKey key = new SecretKeySpec(KEY, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(password);
	}

	private static byte[] decodeBytes(byte[] password) throws Exception {
		SecretKey key = new SecretKeySpec(KEY, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(password);
	}

	public static String encodeBase64(String password) {
		try {
			return new String(Base64.encodeBase64(encodeBytes(password.getBytes())));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String decodeBase64(String password) {
		try {
			return new String(decodeBytes(Base64.decodeBase64(password.getBytes())));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String encodeHex(String password) {
		try {
			return new String(Hex.encodeHex(encodeBytes(password.getBytes())));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String decodeHex(String password) {
		try {
			return new String(decodeBytes(Hex.decodeHex(password.toCharArray())));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}