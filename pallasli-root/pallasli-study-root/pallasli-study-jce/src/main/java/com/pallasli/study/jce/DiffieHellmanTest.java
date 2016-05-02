package com.pallasli.study.jce;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

//公开密钥
public class DiffieHellmanTest {
	// 常用 API
	//
	// java.security.KeyPairGenerator 密钥生成器类
	// public static KeyPairGenerator getInstance(String algorithm)
	// throws NoSuchAlgorithmException
	// 以指定的算法返回一个 KeyPairGenerator 对象
	// 参数 : algorithm 算法名 . 如 : 原来是 DSA, 现在添加了 DiffieHellman(DH)
	//
	// public void initialize(int keysize)
	// 以指定的长度初始化 KeyPairGenerator 对象 , 如果没有初始化系统以 1024 长度默认设置
	// 参数 :keysize 算法位长 . 其范围必须在 512 到 1024 之间，且必须为 64 的倍数
	// 注意 : 如果用 1024 生长的时间很长 , 最好生成一次后就保存 , 下次就不用生成了
	//
	// public void initialize(AlgorithmParameterSpec params)
	// throws InvalidAlgorithmParameterException
	// 以指定参数初始化
	//
	// javax.crypto.interfaces.DHPublicKey
	// public DHParameterSpec getParams()
	// 返回
	// java.security.KeyFactory
	//
	// public static KeyFactory getInstance(String algorithm)
	// throws NoSuchAlgorithmException
	// 以指定的算法返回一个 KeyFactory
	// 参数 : algorithm 算法名 :DSH,DH
	//
	// public final PublicKey generatePublic(KeySpec keySpec)
	// throws InvalidKeySpecException
	// 根据指定的 key 说明 , 返回一个 PublicKey 对象
	//
	// java.security.spec.X509EncodedKeySpec
	// public X509EncodedKeySpec(byte[] encodedKey)
	// 根据指定的二进制编码的字串生成一个 key 的说明
	// 参数 :encodedKey 二进制编码的字串 ( 一般能过 PublicKey.getEncoded() 生成 )
	// javax.crypto.KeyAgreement 密码一至类
	//
	// public static final KeyAgreement getInstance(java.lang.String
	// algorithm)
	// throws java.security.NoSuchAlgorithmException
	// 返回一个指定算法的 KeyAgreement 对象
	// 参数 :algorithm 算法名 , 现在只能是 DiffieHellman(DH)
	//
	// public final void init(java.security.Key key)
	// throws java.security.InvalidKeyException
	// 用指定的私钥初始化
	// 参数 :key 一个私钥
	//
	// public final java.security.Key doPhase(java.security.Key key,
	// boolean lastPhase)
	// throws java.security.InvalidKeyException,
	// java.lang.IllegalStateException
	// 用指定的公钥进行定位 ,lastPhase 确定这是否是最后一个公钥 , 对于两个用户的
	// 情况下就可以多次定次 , 最后确定
	// 参数 :key 公钥
	// lastPhase 是否最后公钥
	//
	// public final SecretKey generateSecret(java.lang.String algorithm)
	// throws java.lang.IllegalStateException,
	// java.security.NoSuchAlgorithmException,
	// java.security.InvalidKeyException
	// 根据指定的算法生成密钥
	// 参数 :algorithm 加密算法 ( 可用 DES,DESede,Blowfish)
	public static void main(String[] args) throws Exception {
		// Security.addProvider(new com.sun.crypto.provider.SunJCE());
		// 公开密钥密码体制 奠基人 Diffie和 Hellman提出的 "指数密钥一致协议"(Exponential Key Agreement
		// Protocol), 该协议不要求别的安全性 先决条件 , 允许两名用户在公开媒体上交换信息以生成"一致"的 ,
		// 可以共享的密钥。在JCE 中的实现用户 alice 生成 DH 类型的密钥对 , 如果长度用 1024 生成的时间请 ,
		// 推荐第一次生成后保存DHParameterSpec, 以便下次使用直接初始化 . 使其速度加快
		//
		System.out.println("ALICE: 产生 DH 对 ...");
		KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
		aliceKpairGen.initialize(512);
		KeyPair aliceKpair = aliceKpairGen.generateKeyPair();
		// alice 生成公钥发送组 bob
		byte[] alicePubKeyEnc = aliceKpair.getPublic().getEncoded();
		// /bob 接收到 alice 的编码后的公钥 , 将其解码
		// bob 从 alice 发送来的公钥中读出 DH 密钥对的初始参数生成 bob 的 DH 密钥对
		// 注意这一步一定要做 , 要保证每个用户用相同的初始参数生成的
		PublicKey alicePubKey = aliceKpair.getPublic();
		DHParameterSpec dhParamSpec = ((DHPublicKey) alicePubKey).getParams();
		KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
		bobKpairGen.initialize(dhParamSpec);
		KeyPair bobKpair = bobKpairGen.generateKeyPair();
		// bob 根据 alice 的公钥生成本地的 DES 密钥

		KeyFactory bobKeyFac = KeyFactory.getInstance("DH");
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(alicePubKeyEnc);
		alicePubKey = bobKeyFac.generatePublic(x509KeySpec);
		System.out.println("alice 公钥 bob 解码成功");

		KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
		bobKeyAgree.init(bobKpair.getPrivate());
		bobKeyAgree.doPhase(alicePubKey, true);
		SecretKey bobDesKey = bobKeyAgree.generateSecret("DES");
		// bob 已经生成了他的 DES 密钥 , 他现把他的公钥发给 alice,
		byte[] bobPubKeyEnc = bobKpair.getPublic().getEncoded();
		// alice 根据 bob 的公钥生成本地的 DES 密钥
		//
		// ,,,,,, 解码
		PublicKey bobPubKey = bobKpair.getPublic();
		KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
		aliceKeyAgree.init(aliceKpair.getPrivate());
		aliceKeyAgree.doPhase(bobPubKey, true);
		SecretKey aliceDesKey = aliceKeyAgree.generateSecret("DES");
		// bob 和 alice 能过这个过程就生成了相同的 DES 密钥 , 在这种基础就可进行安全能信
		//

		// // 张三 (Alice) 生成公共密钥 alicePubKeyEnc 并发送给李四 (Bob) ,
		// // 比如用文件方式 ,socket.....
		// // bob 必须用相同的参数初始化的他的 DH KEY 对 , 所以要从 Alice 发给他的公开密钥 ,
		// // 中读出参数 , 再用这个参数初始化他的 DH key 对
		// // 从 alicePubKye 中取 alice 初始化时用的参数
		// DHParameterSpec dhParamSpec = ((DHPublicKey)alicePubKey).getParams();
		// KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
		// bobKpairGen.initialize(dhParamSpec);
		// KeyPair bobKpair = bobKpairGen.generateKeyPair();
		// System.out.println("BOB: 生成 DH key 对成功");
		// KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
		// bobKeyAgree.init(bobKpair.getPrivate());
		// System.out.println("BOB: 初始化本地 key 成功");
		// // 李四 (bob) 生成本地的密钥 bobDesKey
		// bobKeyAgree.doPhase(alicePubKey, true);
		// SecretKey bobDesKey = bobKeyAgree.generateSecret("DES");
		// System.out.println("BOB: 用 alice 的公钥定位本地 key, 生成本地 DES 密钥成功");
		// // Bob 生成公共密钥 bobPubKeyEnc 并发送给 Alice,
		// // 比如用文件方式 ,socket....., 使其生成本地密钥
		// byte[] bobPubKeyEnc = bobKpair.getPublic().getEncoded();
		// System.out.println("BOB 向 ALICE 发送公钥");
		// // alice 接收到 bobPubKeyEnc 后生成 bobPubKey
		// // 再进行定位 , 使 aliceKeyAgree 定位在 bobPubKey
		// KeyFactory aliceKeyFac = KeyFactory.getInstance("DH");
		// x509KeySpec = new X509EncodedKeySpec(bobPubKeyEnc);
		// PublicKey bobPubKey = aliceKeyFac.generatePublic(x509KeySpec);
		// System.out.println("ALICE 接收 BOB 公钥并解码成功");
		// ;
		// KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
		// aliceKeyAgree.init(aliceKpair.getPrivate());
		// System.out.println("ALICE: 初始化本地 key 成功");
		// aliceKeyAgree.doPhase(bobPubKey, true);
		// // 张三 (alice) 生成本地的密钥 aliceDesKey
		// SecretKey aliceDesKey = aliceKeyAgree.generateSecret("DES");
		// System.out.println("ALICE: 用 bob 的公钥定位本地 key, 并生成本地 DES 密钥");
		// if (aliceDesKey.equals(bobDesKey)) System.out.println("张三和李四的密钥相同");
		// // 现在张三和李四的本地的 deskey 是相同的所以 , 完全可以进行发送加密 , 接收后解密 , 达到
		// // 安全通道的的目的
		// /*
		// * bob 用 bobDesKey 密钥加密信息
		// */
		// Cipher bobCipher = Cipher.getInstance("DES");
		// bobCipher.init(Cipher.ENCRYPT_MODE, bobDesKey);
		// String bobinfo= "这是李四的机密信息";
		// System.out.println("李四加密前原文 :"+bobinfo);
		// byte[] cleartext =bobinfo.getBytes();
		// byte[] ciphertext = bobCipher.doFinal(cleartext);
		// /*
		// * alice 用 aliceDesKey 密钥解密
		// */
		// Cipher aliceCipher = Cipher.getInstance("DES");
		// aliceCipher.init(Cipher.DECRYPT_MODE, aliceDesKey);
		// byte[] recovered = aliceCipher.doFinal(ciphertext);
		// System.out.println("alice 解密 bob 的信息 :"+(new String(recovered)));
		// if (!java.util.Arrays.equals(cleartext, recovered))
		// throw new Exception("解密后与原文信息不同");
		// System.out.println("解密后相同");
		// }
		// }

	}
}
