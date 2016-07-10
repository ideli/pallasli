package com.pallasli.website.encrypt.rsa;

import java.security.PrivateKey;

import javax.crypto.Cipher;

public class DESCrypt {
	// RSA 非对称加密。私钥加密 & 私钥解密 & 私钥签名
	// @Override
	public byte[] encrypt(byte[] data) throws Exception {
		PrivateKey rsaPrivateKey = getRSAPrivateKey();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
		return cipher.doFinal(data);
	}

	public byte[] decrypt(byte[] data) throws Exception {
		PrivateKey rsaPrivateKey = getRSAPrivateKey();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
		return cipher.update(data);
	}
}
