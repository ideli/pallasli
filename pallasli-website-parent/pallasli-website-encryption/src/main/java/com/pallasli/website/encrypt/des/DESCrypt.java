package com.pallasli.website.encrypt.des;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class DESCrypt {

	String secretKey = "";

	// DES 对称加密类：
	// @Override
	public byte[] encrypt(byte[] data) throws Exception {
		if (secretKey == null || "".equals(secretKey)) {
			throw new Exception("scretKey need to exists");
		}

		SecretKey md5Key = getKey(secretKey);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, md5Key);
		return cipher.doFinal(data);
	}

	public byte[] decrypt(byte[] data) throws Exception {
		if (secretKey == null || "".equals(secretKey)) {
			throw new Exception("scretKey need to exists");
		}

		SecretKey md5Key = getKey(secretKey);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, md5Key);
		return cipher.doFinal(data);
	}
}
