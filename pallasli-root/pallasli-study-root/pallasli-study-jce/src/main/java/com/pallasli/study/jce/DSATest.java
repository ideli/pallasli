package com.pallasli.study.jce;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

//非对称算法

// 生成一对文件 myprikey.dat 和 mypubkey.dat--- 私钥和公钥 ,
// 公钥要用户发送 ( 文件 , 网络等方法 ) 给其它用户 , 私钥保存在本地
public class DSATest {
	// 常用 API
	// java.security.KeyPairGenerator 密钥生成器类
	// public static KeyPairGenerator getInstance(String algorithm) throws
	// NoSuchAlgorithmException
	// 以指定的算法返回一个 KeyPairGenerator 对象
	// 参数 : algorithm 算法名 . 如 :"DSA","RSA"
	//
	// public void initialize(int keysize)
	//
	// 以指定的长度初始化 KeyPairGenerator 对象 , 如果没有初始化系统以 1024 长度默认设置
	//
	// 参数 :keysize 算法位长 . 其范围必须在 512 到 1024 之间，且必须为 64 的倍数
	//
	// public void initialize(int keysize, SecureRandom random)
	// 以指定的长度初始化和随机发生器初始化 KeyPairGenerator 对象
	// 参数 :keysize 算法位长 . 其范围必须在 512 到 1024 之间，且必须为 64 的倍数
	// random 一个随机位的来源 ( 对于 initialize(int keysize) 使用了默认随机器
	//
	// public abstract KeyPair generateKeyPair()
	// 产生新密钥对
	//
	// java.security.KeyPair 密钥对类
	// public PrivateKey getPrivate()
	// 返回私钥
	//
	// public PublicKey getPublic()
	// 返回公钥
	//
	// java.security.Signature 签名类
	// public static Signature getInstance(String algorithm) throws
	// NoSuchAlgorithmException
	// 返回一个指定算法的 Signature 对象
	// 参数 algorithm 如 :"DSA"
	//
	// public final void initSign(PrivateKey privateKey)
	// throws InvalidKeyException
	// 用指定的私钥初始化
	// 参数 :privateKey 所进行签名时用的私钥
	//
	// public final void update(byte data)
	// throws SignatureException
	// public final void update(byte[] data)
	// throws SignatureException
	// public final void update(byte[] data, int off, int len)
	// throws SignatureException
	// 添加要签名的信息
	//
	// public final byte[] sign()
	// throws SignatureException
	// 返回签名的数组 , 前提是 initSign 和 update
	//
	// public final void initVerify(PublicKey publicKey)
	// throws InvalidKeyException
	// 用指定的公钥初始化
	// 参数 :publicKey 验证时用的公钥
	//
	// public final boolean verify(byte[] signature)
	// throws SignatureException
	// 验证签名是否有效 , 前提是已经 initVerify 初始化
	// 参数 : signature 签名数组
	public static void main(String[] args) throws Exception {
		// 对于一个用户来讲首先要生成他的密钥对 , 并且分别保存
		// 生成一个 KeyPairGenerator 实例
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA");
		// 如果设定随机产生器就用如下代码初始化
		SecureRandom secrand = new SecureRandom();
		secrand.setSeed("tttt".getBytes()); // 初始化随机产生器
		keygen.initialize(512, secrand); // 初始化密钥生成器
		// //否则
		// keygen.initialize(512);
		//
		// 生成密钥公钥 pubkey 和私钥 prikey
		KeyPair keys = keygen.generateKeyPair(); // 生成密钥组
		PublicKey pubkey = keys.getPublic();
		PrivateKey prikey = keys.getPrivate();
		System.out.println("公钥prikey:" + pubkey);
		System.out.println("私钥prikey：" + prikey);

		// 分别保存在 myprikey.dat 和 mypubkey.dat 中 , 以便下次不在生成
		// ( 生成密钥对的时间比较长
		java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(new java.io.FileOutputStream("myprikey.dat"));
		out.writeObject(prikey);
		out.close();
		out = new java.io.ObjectOutputStream(new java.io.FileOutputStream("mypubkey.dat"));
		out.writeObject(pubkey);
		out.close();
		// 用他私人密钥 (prikey) 对他所确认的信息 (info) 进行数字签名产生一个签名数组
		// 从文件中读入私人密钥 (prikey)
		java.io.ObjectInputStream in = new java.io.ObjectInputStream(new java.io.FileInputStream("myprikey.dat"));
		PrivateKey myprikey = (PrivateKey) in.readObject();
		in.close();
		System.out.println(myprikey.getAlgorithm());
		System.out.println(myprikey.getFormat());
		System.out.println(new String(myprikey.getEncoded(), "UTF-8"));
		// 初始一个 Signature 对象 , 并用私钥对信息签名
		java.security.Signature signet = java.security.Signature.getInstance("DSA");
		signet.initSign(myprikey);
		String myinfo = "加密的信息";
		signet.update(myinfo.getBytes());
		byte[] signed = signet.sign();
		// 把信息和签名保存在一个文件中 (myinfo.dat)
		out = new java.io.ObjectOutputStream(new java.io.FileOutputStream("myinfo.dat"));
		out.writeObject(myinfo);
		out.writeObject(signed);
		out.close();
		// 把他的公钥的信息及签名发给其它用户
		// 其他用户用他的公共密钥 (pubkey) 和签名 (signed) 和信息 (info) 进行验证是否由他签名的信息
		// 读入公钥
		in = new java.io.ObjectInputStream(new java.io.FileInputStream("mypubkey.dat"));
		pubkey = (PublicKey) in.readObject();
		in.close();
		//
		// 读入签名和信息
		in = new java.io.ObjectInputStream(new java.io.FileInputStream("myinfo.dat"));
		String info = (String) in.readObject();
		signed = (byte[]) in.readObject();
		in.close();
		System.out.println(info);
		//
		// 初始一个 Signature 对象 , 并用公钥和签名进行验证
		java.security.Signature signetcheck = java.security.Signature.getInstance("DSA");
		signetcheck.initVerify(pubkey);
		signetcheck.update(info.getBytes());
		if (signetcheck.verify(signed)) {
			System.out.println("签名正常");
		}
		// 对于密钥的保存本文是用对象流的方式保存和传送的 , 也可可以用编码的方式保存 . 注意要
		// public key 是用 X.509 编码的 , 例码如下 :
		byte[] bobEncodedPubKey = pubkey.getEncoded(); // 生成编码
		// 传送二进制编码
		// 以下代码转换编码为相应 key 对象
		X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(bobEncodedPubKey);
		KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		PublicKey bobPubKey = keyFactory.generatePublic(bobPubKeySpec);
		// 对于 Private key 是用 PKCS#8 编码 , 例码如下 :
		byte[] bPKCS = myprikey.getEncoded();
		// 传送二进制编码
		// 以下代码转换编码为相应 key 对象
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(bPKCS);
		KeyFactory keyf = KeyFactory.getInstance("DSA");
		PrivateKey otherprikey = keyf.generatePrivate(priPKCS8);

	}

}
