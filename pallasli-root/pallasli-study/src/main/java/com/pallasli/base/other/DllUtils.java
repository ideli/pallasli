package com.pallasli.base.other;

//import org.xvolks.jnative.JNative;
//import org.xvolks.jnative.Type;
//import org.xvolks.jnative.exceptions.NativeException;
//import org.xvolks.jnative.pointers.Pointer;

public class DllUtils {

	// public static void matchFinger(String sFingerSrc, String sFingerDst,
	// Pointer a, Pointer b) {
	//
	// }
	//
	// public static int hexCharToInt(char c) {
	// if (c >= '0' && c <= '9')
	// return (c - '0');
	// if (c >= 'A' && c <= 'F')
	// return (c - 'A' + 10);
	// if (c >= 'a' && c <= 'f')
	// return (c - 'a' + 10);
	// throw new RuntimeException("invalid hex char '" + c + "'");
	// }
	//
	// public static byte[] hexStringToBytes(String s) {
	// byte[] ret;
	// if (s == null)
	// return null;
	// int sz = s.length();
	// ret = new byte[sz / 2];
	// for (int i = 0; i < sz; i += 2) {
	// ret[i / 2] = (byte) ((hexCharToInt(s.charAt(i)) << 4) | hexCharToInt(s
	// .charAt(i + 1)));
	// }
	// return ret;
	// }
	//
	// public static String bytesToHexString(byte[] bytes) {
	// if (bytes == null)
	// return null;
	// StringBuilder ret = new StringBuilder(2 * bytes.length);
	// for (int i = 0; i < bytes.length; i++) {
	// int b;
	// b = 0x0f & (bytes[i] >> 4);
	// ret.append("0123456789abcdef".charAt(b));
	// b = 0x0f & bytes[i];
	// ret.append("0123456789abcdef".charAt(b));
	// }
	// return ret.toString();
	// }
	//
	// public static int checkFinger(String regFingertpl, String verFingertpl)
	// throws NativeException, IllegalAccessException {
	//
	// byte[] regFingertplbytes = hexStringToBytes(regFingertpl);
	// byte[] verFingertplbytes = hexStringToBytes(verFingertpl);
	//
	// System.out.println("");
	// for (byte b : regFingertplbytes) {
	// System.out.print(b);
	// }
	// System.out.println("");
	// for (byte b : verFingertplbytes) {
	// System.out.print(b);
	// }
	//
	// System.out.println("");
	// int i = 0;
	// for (int l = 0; l < verFingertplbytes.length; l++) {
	// if (regFingertplbytes[l] == verFingertplbytes[l]) {
	// i++;
	// }
	// }
	//
	// System.out.println(i);
	//
	// JNative jna = null;
	// try {
	// if (jna == null) {
	// String path = DllUtilsTest.class.getResource("/").getPath();
	// path = path.substring(1, path.length());
	// System.load(path + "ARTH_DLL.dll");
	// jna = new JNative("ARTH_DLL.dll", "Match2Fp");
	// jna.setRetVal(Type.INT);
	// }
	//
	// jna.setParameter(0, Type.STRING, verFingertplbytes);//
	// 设置UserMatch方法中的第一个参数
	// jna.setParameter(1, Type.STRING, regFingertplbytes);//
	// 设置UserMatch方法中的第二个参数
	// jna.invoke();
	// int val = jna.getRetValAsInt();// 获取Process方法的返回值
	// System.out.println("");
	// System.out.println(val);
	// return val;
	// } finally {
	// if (jna != null) {
	// jna.dispose();
	// }
	// }
	// }
}
