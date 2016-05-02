package com.pallasli.study.jce;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.crypto.Cipher;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * 证书的制作,导入,删除
 * <ul>
 * <li>生成keyStroe文件</li>
 * <ul>
 * <li>keytool -genkey -validity 36000 -alias 127.0.0.1 -keyalg RSA -keystore
 * d:\zlex.keystore</li>
 * <ul>
 * 参数说明
 * <li>-genkey表示生成密钥</li>
 * <li>-validity指定证书有效期，这里是36000天</li>
 * <li>-alias指定别名，这里是192.168.20.230</li>
 * <li>-keyalg指定算法，这里是RSA</li>
 * <li>-keystore指定存储位置，这里是d:\zlex.keystore</li>
 * </ul>
 * </ul> <li>生成自签名证书</li>
 * <ul>
 * <li>keytool -export -keystore d:\zlex.keystore -alias 127.0.0.1 -file
 * d:\zlex.cer -rfc</li>
 * <ul>
 * 参数说明
 * <li>-export指定为导出操作</li>
 * <li>-keystore指定keystore文件</li>
 * <li>-alias指定导出keystore文件中的别名</li>
 * <li>-file指向导出路径</li>
 * <li>-rfc以文本格式输出，也就是以BASE64编码输出</li>
 * </ul>
 * </ul> <li>证书导入到密钥库</li>
 * <ul>
 * <li>keytool -import -alias 127.0.0.1 -file d:/zlex.cer -keystore
 * d:/zlex.keystore</li>
 * <ul>
 * 参数说明
 * <li>-import表示导入</li>
 * <li>-alias指定别名，这里是192.168.20.230</li>
 * <li>-file指定算法，这里是d:/zlex.cer</li>
 * <li>-keystore指定存储位置，这里是d:/zlex.keystore</li>
 * </ul>
 * </ul> <li>配置tomcat</li>
 * <ul>
 * <li>将zlex.keystore拷贝到tomcat的conf目录下</li>
 * <li>配置server.xml。将如下内容加入配置文件<br>
 * &lt;Connector SSLEnabled="true" URIEncoding="UTF-8" clientAuth="false"
 * keystoreFile="conf/zlex.keystore" keystorePass="654321" maxThreads="150"
 * port="443" protocol="HTTP/1.1" scheme="https" secure="true" sslProtocol="TLS"
 * /></li>
 * <li>clientAuth="false"测试阶段，置为false，正式使用时建议使用true</li>
 * <li>启动tomcat，访问https://192.168.20.230</li>
 * <li></li>
 * </ul>
 * <li>证书的删除</li>
 * <ul>
 * <li>命令行中输入 certmgr.msc</li>
 * <li>找到安装的证书,删除即可</li>
 * </ul>
 * </ul>
 * 
 * @author Ice_Liu
 * 
 */
public class Certificateutil {
	/**
	 * Java密钥库(Java Key Store，JKS)KEY_STORE
	 */
	public static final String KEY_STORE = "JKS";

	public static final String X509 = "X.509";
	public static final String SunX509 = "SunX509";
	public static final String SSL = "SSL";

	// 取得私匙和公匙的方法：
	//
	// KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry)
	// ks.getEntry("client", new
	// KeyStore.PasswordProtection(password.toCharArray()));
	// privateKey = pkEntry.getPrivateKey();
	// publicKey = certificate.getPublicKey();
	//
	// 這種取法 私匙和公匙一定是成對的。
	/**
	 * 由 KeyStore获得私钥
	 * 
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static PrivateKey getPrivateKey(String keyStorePath, String alias,
			String password) throws Exception {
		KeyStore ks = getKeyStore(keyStorePath, password);
		PrivateKey key = (PrivateKey) ks.getKey(alias, password.toCharArray());
		return key;
	}

	/**
	 * 由 Certificate获得公钥
	 * 
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	private static PublicKey getPublicKey(String certificatePath)
			throws Exception {
		Certificate certificate = getCertificate(certificatePath);
		PublicKey key = certificate.getPublicKey();
		return key;
	}

	/**
	 * 获得Certificate
	 * 
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	private static Certificate getCertificate(String certificatePath)
			throws Exception {
		CertificateFactory certificateFactory = CertificateFactory
				.getInstance(X509);
		FileInputStream in = new FileInputStream(certificatePath);

		Certificate certificate = certificateFactory.generateCertificate(in);
		in.close();

		return certificate;
	}

	/**
	 * 获得Certificate
	 * 
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static Certificate getCertificate(String keyStorePath,
			String alias, String password) throws Exception {
		KeyStore ks = getKeyStore(keyStorePath, password);
		Certificate certificate = ks.getCertificate(alias);

		return certificate;
	}

	/**
	 * 获得KeyStore
	 * 
	 * @param keyStorePath
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static KeyStore getKeyStore(String keyStorePath, String password)
			throws Exception {
		FileInputStream is = new FileInputStream(keyStorePath);
		KeyStore ks = KeyStore.getInstance(KEY_STORE);
		ks.load(is, password.toCharArray());
		is.close();
		return ks;
	}

	/**
	 * 私钥加密
	 * 
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath,
			String alias, String password) throws Exception {
		// 取得私钥
		PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(data);

	}

	/**
	 * 私钥解密
	 * 
	 * @param data
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String keyStorePath,
			String alias, String password) throws Exception {
		// 取得私钥
		PrivateKey privateKey = getPrivateKey(keyStorePath, alias, password);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		return cipher.doFinal(data);

	}

	/**
	 * 公钥加密
	 * 
	 * @param data
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String certificatePath)
			throws Exception {

		// 取得公钥
		PublicKey publicKey = getPublicKey(certificatePath);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		return cipher.doFinal(data);

	}

	/**
	 * 公钥解密
	 * 
	 * @param data
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String certificatePath)
			throws Exception {
		// 取得公钥
		PublicKey publicKey = getPublicKey(certificatePath);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);

	}

	/**
	 * 验证Certificate
	 * 
	 * @param certificatePath
	 * @return
	 */
	public static boolean verifyCertificate(String certificatePath) {
		return verifyCertificate(new Date(), certificatePath);
	}

	/**
	 * 验证Certificate是否过期或无效
	 * 
	 * @param date
	 * @param certificatePath
	 * @return
	 */
	public static boolean verifyCertificate(Date date, String certificatePath) {
		boolean status = true;
		try {
			// 取得证书
			Certificate certificate = getCertificate(certificatePath);
			// 验证证书是否过期或无效
			status = verifyCertificate(date, certificate);
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	/**
	 * 验证证书是否过期或无效
	 * 
	 * @param date
	 * @param certificate
	 * @return
	 */
	private static boolean verifyCertificate(Date date, Certificate certificate) {
		boolean status = true;
		try {
			X509Certificate x509Certificate = (X509Certificate) certificate;
			x509Certificate.checkValidity(date);
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	/**
	 * 签名
	 * 
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] sign, String keyStorePath, String alias,
			String password) throws Exception {
		// 获得证书
		X509Certificate x509Certificate = (X509Certificate) getCertificate(
				keyStorePath, alias, password);
		// 获取私钥
		KeyStore ks = getKeyStore(keyStorePath, password);
		// 取得私钥
		PrivateKey privateKey = (PrivateKey) ks.getKey(alias,
				password.toCharArray());

		// 构建签名
		Signature signature = Signature.getInstance(x509Certificate
				.getSigAlgName());
		signature.initSign(privateKey);
		signature.update(sign);
		return CryptUtil.encryptBASE64(signature.sign());
	}

	/**
	 * 验证签名
	 * 
	 * @param data
	 * @param sign
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String sign,
			String certificatePath) throws Exception {
		// 获得证书
		X509Certificate x509Certificate = (X509Certificate) getCertificate(certificatePath);
		// 获得公钥
		PublicKey publicKey = x509Certificate.getPublicKey();
		// 构建签名
		Signature signature = Signature.getInstance(x509Certificate
				.getSigAlgName());
		signature.initVerify(publicKey);
		signature.update(data);

		return signature.verify(CryptUtil.decryptBASE64(sign));

	}

	/**
	 * 验证Certificate
	 * 
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 */
	public static boolean verifyCertificate(Date date, String keyStorePath,
			String alias, String password) {
		boolean status = true;
		try {
			Certificate certificate = getCertificate(keyStorePath, alias,
					password);
			status = verifyCertificate(date, certificate);
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	/**
	 * 验证Certificate
	 * 
	 * @param keyStorePath
	 * @param alias
	 * @param password
	 * @return
	 */
	public static boolean verifyCertificate(String keyStorePath, String alias,
			String password) {
		return verifyCertificate(new Date(), keyStorePath, alias, password);
	}

	/**
	 * 获得SSLSocektFactory
	 * 
	 * @param password
	 *            密码
	 * @param keyStorePath
	 *            密钥库路径
	 * 
	 * @param trustKeyStorePath
	 *            信任库路径
	 * @return
	 * @throws Exception
	 */
	private static SSLSocketFactory getSSLSocketFactory(String password,
			String keyStorePath, String trustKeyStorePath) throws Exception {
		// 初始化密钥库
		KeyManagerFactory keyManagerFactory = KeyManagerFactory
				.getInstance(SunX509);
		KeyStore keyStore = getKeyStore(keyStorePath, password);
		keyManagerFactory.init(keyStore, password.toCharArray());

		// 初始化信任库
		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(SunX509);
		KeyStore trustkeyStore = getKeyStore(trustKeyStorePath, password);
		trustManagerFactory.init(trustkeyStore);

		// 初始化SSL上下文
		SSLContext ctx = SSLContext.getInstance(SSL);
		ctx.init(keyManagerFactory.getKeyManagers(),
				trustManagerFactory.getTrustManagers(), null);
		SSLSocketFactory sf = ctx.getSocketFactory();

		return sf;
	}

	/**
	 * 为 HttpsURLConnection配置SSLSocketFactory
	 * 
	 * @param conn
	 *            HttpsURLConnection
	 * @param password
	 *            密码
	 * @param keyStorePath
	 *            密钥库路径
	 * 
	 * @param trustKeyStorePath
	 *            信任库路径
	 * @throws Exception
	 */
	public static void configSSLSocketFactory(HttpsURLConnection conn,
			String password, String keyStorePath, String trustKeyStorePath)
			throws Exception {
		conn.setSSLSocketFactory(getSSLSocketFactory(password, keyStorePath,
				trustKeyStorePath));
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String password = "860830";
		String alias = "127.0.0.1";
		String certificatePath = "d:/zlex.cer";
		String keyStorePath = "d:/zlex.keystore";
		String clientKeyStorePath = "d:/zlex-client.keystore";// keyStorePath;//
																// "d:/zlex-client.keystore";
		String clientPassword = "860830";

		System.err.println("公钥加密——私钥解密");
		String inputStr = "Ceritifcate";
		byte[] data = inputStr.getBytes();

		byte[] encrypt = Certificateutil.encryptByPublicKey(data,
				certificatePath);

		byte[] decrypt = Certificateutil.decryptByPrivateKey(encrypt,
				keyStorePath, alias, password);
		String outputStr = new String(decrypt);

		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		// 验证证书有效
		System.out.println("验证证书有效:"
				+ Certificateutil.verifyCertificate(certificatePath));

		System.out.println("**********************************************");
		System.err.println("私钥加密——公钥解密");

		inputStr = "sign";
		data = inputStr.getBytes();

		byte[] encodedData = Certificateutil.encryptByPrivateKey(data,
				keyStorePath, alias, password);

		byte[] decodedData = Certificateutil.decryptByPublicKey(encodedData,
				certificatePath);

		outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);

		System.err.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = Certificateutil.sign(encodedData, keyStorePath, alias,
				password);
		System.err.println("签名:\r" + sign);

		// 验证签名
		boolean status = Certificateutil.verify(encodedData, sign,
				certificatePath);
		System.err.println("状态:\r" + status);

		System.out
				.println("*****************************************************");
		URL url = new URL("https://" + alias + ":18433/webos");
		System.out.println(alias);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		conn.setDoInput(true);
		conn.setDoOutput(true);

		Certificateutil.configSSLSocketFactory(conn, clientPassword,
				clientKeyStorePath, clientKeyStorePath);

		InputStream is = conn.getInputStream();

		int length = conn.getContentLength();

		DataInputStream dis = new DataInputStream(is);
		data = new byte[length];
		dis.readFully(data);

		dis.close();
		System.err.println(new String(data));
		conn.disconnect();

	}
}
