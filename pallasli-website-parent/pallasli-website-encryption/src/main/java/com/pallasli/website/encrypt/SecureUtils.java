package com.pallasli.website.encrypt;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class SecureUtils {
	// MD5 单向加密：
	/**
	 * 返回MD5单向加密后的十六进制字符串
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String getEncryptForHex(byte[] data) throws Exception {
		byte[] digestData = encrypt(data);
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < digestData.length; i++) {
			int h = (digestData[i]) & 0XFF;
			if (h < 16) {
				hex.append("0");
			}
			hex.append(Integer.toHexString(h));
		}
		return hex.toString();
	}

	/**
	 * 使用私钥 对数据进行签名
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String sign(byte[] data) throws Exception {
		PrivateKey rsaPrivateKey = getRSAPrivateKey();
		Signature signature = Signature.getInstance(SIGN_ALGORITHM);
		signature.initSign(rsaPrivateKey);
		signature.update(data);
		return encoder(signature.sign());
	}

	// RSA 非对称加密。公钥加密 & 公钥解密 & 公钥校验签名
	@Override
	public byte[] encrypt(byte[] data) throws Exception {
		if (publicKey == null || "".equals(publicKey)) {
			throw new Exception("publicKey is need exists");
		}

		PublicKey rsaPublicKey = getRSAPublicKey(publicKey);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
		return cipher.doFinal(data);
	}

	// @Override
	public byte[] decrypt(byte[] data) throws Exception {
		if (publicKey == null || "".equals(publicKey)) {
			throw new Exception("publicKey is need exists");
		}

		PublicKey rsaPublicKey = getRSAPublicKey(publicKey);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 使用公钥校验签名
	 * 
	 * @param data
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public boolean verifySign(byte[] data, String sign) throws Exception {
		if (publicKey == null || "".equals(publicKey)) {
			throw new Exception("publicKey is need exists");
		}

		PublicKey rsaPublicKey = getRSAPublicKey(publicKey);
		Signature signature = Signature.getInstance(SIGN_ALGORITHM);
		signature.initVerify(rsaPublicKey);
		signature.update(data);
		return signature.verify(decoder(sign));
	}
}
