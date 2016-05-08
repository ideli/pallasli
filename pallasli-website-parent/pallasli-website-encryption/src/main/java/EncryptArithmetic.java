

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptArithmetic {

	private static final String Algorithm = "DESede"; // 定义加密算法,可用DES,DESede,Blowfish

	/**
	 * keybyte为加密密钥，长度为24字节 <br>
	 * src为被加密的数据缓冲区（源）
	 * 
	 * @param keybyte
	 * @param src
	 * @return
	 */
	private byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, 0, 24, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * keybyte为加密密钥，长度为24字节 <br>
	 * src为加密后的缓冲区
	 * 
	 * @param keybyte
	 * @param src
	 * @return
	 */
	private byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, 0, 24, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (javax.crypto.BadPaddingException e3) {

		} catch (java.lang.Exception e4) {
			e4.printStackTrace();
		}
		return null;
	}

	// 转换成十六进制字符串
	private String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n < b.length - 1)
			// hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	/**
	 * 
	 * @param hex
	 * @return
	 * @throws IllegalArgumentException
	 */
	private byte[] hex2byte(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	/**
	 * 加密指定字符串
	 * 
	 * @param sourceCode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String encryptIt(String sourceCode, byte[] key)
			throws UnsupportedEncodingException {
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] encoded = encryptMode(key, sourceCode.getBytes());
		return byte2hex(encoded);
	}

	/**
	 * 还原加密字符串
	 * 
	 * @param encodedCode
	 * @return
	 */
	public String decryptIt(String encodedCode, byte[] key) {
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] encoded = decryptMode(key, hex2byte(encodedCode));
		if (encoded != null) {
			return new String(encoded);
		}
		return "";
	}

	public String md5It(String source) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: " + e);
		}
		md5.update(source.getBytes());

		byte[] array = md5.digest();
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < array.length; ++j) {
			int b = array[j] & 0xFF;
			if (b < 0x10)
				sb.append('0');
			sb.append(Integer.toHexString(b));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			if (args.length != 1) {
				System.out.println("please input password string.");
				return;
			}
			// java -jar garage.dbac.jar password
			System.out
					.println(args[0] + " --> " + EncryptArithmetic.e(args[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String e(String source) {
		String encode = "";
		try {
			EncryptArithmetic t = new EncryptArithmetic();
			String key = t.md5It(Algorithm);
			encode = "{DES}" + t.encryptIt(source, key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encode;
	}

	public static String d(String encode) {
		String source = "";
		try {
			EncryptArithmetic t = new EncryptArithmetic();
			String key = t.md5It(Algorithm);
			source = t.decryptIt(encode, key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return source;
	}

}
