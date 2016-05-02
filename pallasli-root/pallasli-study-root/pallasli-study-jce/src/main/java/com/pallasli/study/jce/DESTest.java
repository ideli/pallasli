package com.pallasli.study.jce;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//对称算法
public class DESTest {
	// 相关 API
	// KeyGenerator 在 DSA 中已经说明 , 在添加 JCE 后在 instance 进可以如下参数
	// DES,DESede,Blowfish,HmacMD5,HmacSHA1
	//
	// javax.crypto.Cipher 加 / 解密器
	//
	// public static final Cipher getInstance(java.lang.String
	// transformation)
	// throws java.security.NoSuchAlgorithmException,
	// NoSuchPaddingException
	// 返回一个指定方法的 Cipher 对象
	//
	// 参数 :transformation 方法名 ( 可用 DES,DESede,Blowfish)
	//
	// public final void init(int opmode, java.security.Key key)
	// throws java.security.InvalidKeyException
	//
	// 用指定的密钥和模式初始化 Cipher 对象
	//
	// 参数 :opmode 方式 (ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
	//
	// key 密钥
	//
	// public final byte[] doFinal(byte[] input)
	// throws java.lang.IllegalStateException,
	// IllegalBlockSizeException,
	// BadPaddingException
	// 对 input 内的串 , 进行编码处理 , 返回处理后二进制串 , 是返回解密文还是加解文由 init 时的 opmode 决定
	//
	// 注意 : 本方法的执行前如果有 update, 是对 updat 和本次 input 全部处理 , 否则是本 inout 的内容
	//
	public static void main(String[] args) throws Exception {
		// // 添加新安全算法 , 如果用 JCE 就要把它添加进去
		// Security.addProvider(new com.sun.crypto.provider.SunJCE());
		// 首先生成密钥 ,定义 加密算法 , 可用 DES,DESede,Blowfish,HmacMD5,HmacSHA1 并保存 (
		// 这里并没的保存的代码 , 可参考 DSA
		// 中的方法 )
		KeyGenerator keygen = KeyGenerator.getInstance("DES");
		SecretKey deskey = keygen.generateKey();

		// 用密钥加密明文 (myinfo), 生成密文 (cipherByte)
		String myinfo = "密文";
		System.out.println("加密前的二进串 :" + byte2hex(myinfo.getBytes()));
		System.out.println("加密前的信息 :" + myinfo);
		Cipher c1 = Cipher.getInstance("DES");
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] cipherByte = c1.doFinal(myinfo.getBytes());
		System.out.println("加密后的二进串 :" + byte2hex(cipherByte));
		// 传送密文和密钥 , 本文没有相应代码可参考 DSA
		// 用密钥解密密文
		c1 = Cipher.getInstance("DES");
		c1.init(Cipher.DECRYPT_MODE, deskey);
		byte[] clearByte = c1.doFinal(cipherByte);
		System.out.println("解密后的二进串 :" + byte2hex(clearByte));
		System.out.println("解密后的信息 :" + (new String(clearByte)));
		// 相对来说对称密钥的使用是很简单的 , 对于 JCE 来讲支技 DES,DESede,Blowfish 三种加密术
		// 对于密钥的保存各传送可使用对象流或者用二进制编码 , 相关参考代码如下
		deskey = keygen.generateKey();
		byte[] desEncode = deskey.getEncoded();
		javax.crypto.spec.SecretKeySpec destmp = new javax.crypto.spec.SecretKeySpec(
				desEncode, "DES");
		SecretKey mydeskey = destmp;
		System.out.println(mydeskey.getEncoded());

	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}
}
