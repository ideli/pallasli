package com.pallasli.study.jce;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * <ul>
 * <li>BASE64的加密解密是双向的，可以求反解。</li>
 * <li>MD5、SHA以及HMAC是单向加密，任何数据加密后只会产生唯一的一个加密串，通常用来校验数据在传输过程中是否被修改。</li>
 * <li>HMAC算法有一个密钥，增强了数据传输过程中的安全性，强化了算法外的不可控因素。</li>
 * <li>DES DES-Data Encryption Standard,即数据加密算法。 DES算法的入口参数有三个:Key、Data、Mode。
 * <ul>
 * <li>Key:8个字节共64位,是DES算法的工作密钥;</li>
 * <li>Data:8个字节64位,是要被加密或被解密的数据;</li>
 * <li>Mode:DES的工作方式,有两种:加密或解密。</li>
 * </ul>
 * </li>
 * <ul>
 * 
 * @author Ice_Liu
 * 
 */
public class CryptUtil {
	private static final String KEY_MD5 = "MD5";
	private static final String KEY_SHA = "SHA";

	/**
	 * DES 算法 <br>
	 * 可替换为以下任意一种算法，同时key值的size相应改变。
	 * 
	 * <pre>
	 * DES                  key size must be equal to 56 
	 * DESede(TripleDES)    key size must be equal to 112 or 168 
	 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
	 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
	 * RC2                  key size must be between 40 and 1024 bits 
	 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
	 * </pre>
	 */
	public static final String ALGORITHM = "DES";

	public static void main(String[] args) {
		try {
			String s = "阿伯才的覆盖";
			String b = CryptUtil.encryptBASE64(s.getBytes("UTF-8"));
			System.out.println("BASE64加密后:" + b);
			byte[] c = CryptUtil.decryptBASE64(b);
			System.out.println("BASE64解密后:" + new String(c, "UTF-8"));

			c = encryptMD5(s.getBytes());
			System.out.println("MD5   加密后:" + new BigInteger(c).toString(16));

			c = encryptSHA(s.getBytes());
			System.out.println("SHA   加密后:" + new BigInteger(c).toString(16));

			String key = initMacKey();
			System.out.println("HMAC密匙:" + key);
			c = encryptHMAC(s.getBytes(), key);
			System.out.println("HMAC  加密后:" + new BigInteger(c).toString(16));

			key = initKey();
			System.out.println(ALGORITHM + "密钥:\t" + key);
			c = encrypt(s.getBytes("UTF-8"), key);
			System.out.println(ALGORITHM + "   加密后:" + new BigInteger(c).toString(16));
			c = decrypt(c, key);
			System.out.println(ALGORITHM + "   解密后:" + new String(c, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}