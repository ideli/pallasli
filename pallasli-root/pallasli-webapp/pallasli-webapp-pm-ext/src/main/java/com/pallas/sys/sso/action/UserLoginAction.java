package com.pallas.sys.sso.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class UserLoginAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		arg0.setCharacterEncoding("UTF-8");
		// String source = arg0.getParameter("source");
		// String redirectUrl = arg0.getParameter("redirectUrl");
		// String action = arg0.getParameter("action");

		String userName = arg0.getParameter("userName");
		String password = arg0.getParameter("password");
		String figerCode = arg0.getParameter("figerCode");
		boolean withPassWord = false;
		ModelAndView view = null;
		if (password != null && password.trim().length() > 0) {
			withPassWord = true;
		}
		User user = SsoServerUserCertification.instance().getUserInfo(arg0);
		if (userName.equals("pallas")) {
			if (withPassWord) {
				if (password.equals(user.getPassword())) {
					view = new ModelAndView("/home");
				} else {
					view = new ModelAndView("/login/login");
					view.addObject("msg", "密码错误");
				}
			} else {
				if (checkFinger(user.getFingerCode(), figerCode) > 50) {
					view = new ModelAndView("/home");
				} else {
					view = new ModelAndView("/login/login");
					view.addObject("msg", "指纹错误");
				}
			}
		} else {
			view = new ModelAndView("/login/login");
			view.addObject("msg", "用户名错误");
			System.out.println("用户名错误");
		}
		return view;
	}

	// @Test
	// public void checkFinger() throws NativeException, IllegalAccessException
	// {
	// int a = UserLoginAction.checkFinger("", "");
	// System.out.println(a);
	// }

	public interface TestDll1 extends Library {

		/**
		 * 
		 * 当前路径是在项目下，而不是bin输出目录下。
		 */

		TestDll1 INSTANCE = (TestDll1) Native.loadLibrary("ARTH_DLL",
				TestDll1.class);

		public int Match2Fp(byte[] value, byte[] value2);

	}

	public static int checkFinger(String regFingertpl, String verFingertpl) {

		byte[] regFingertplbytes = hexStringToBytes(regFingertpl);
		byte[] verFingertplbytes = hexStringToBytes(verFingertpl);
		int i = TestDll1.INSTANCE
				.Match2Fp(regFingertplbytes, verFingertplbytes);
		return i;
		/**
		 * JNative jna = null; try { if (jna == null) { String path =
		 * UserLoginAction.class.getResource("/").getPath(); path =
		 * path.substring(1, path.length()); System.load(path + "ARTH_DLL.dll");
		 * jna = new JNative("ARTH_DLL.dll", "Match2Fp");
		 * jna.setRetVal(Type.INT); }
		 * 
		 * jna.setParameter(0, Type.STRING, verFingertplbytes);//
		 * 设置UserMatch方法中的第一个参数 jna.setParameter(1, Type.STRING,
		 * regFingertplbytes);// 设置UserMatch方法中的第二个参数 jna.invoke(); int val =
		 * jna.getRetValAsInt();// 获取Process方法的返回值 return val; } finally { if
		 * (jna != null) { jna.dispose(); } }
		 **/
	}

	public static int hexCharToInt(char c) {
		if (c >= '0' && c <= '9')
			return (c - '0');
		if (c >= 'A' && c <= 'F')
			return (c - 'A' + 10);
		if (c >= 'a' && c <= 'f')
			return (c - 'a' + 10);
		throw new RuntimeException("invalid hex char '" + c + "'");
	}

	public static byte[] hexStringToBytes(String s) {
		byte[] ret;
		if (s == null)
			return null;
		int sz = s.length();
		ret = new byte[sz / 2];
		for (int i = 0; i < sz; i += 2) {
			ret[i / 2] = (byte) ((hexCharToInt(s.charAt(i)) << 4) | hexCharToInt(s
					.charAt(i + 1)));
		}
		return ret;
	}

	public static String bytesToHexString(byte[] bytes) {
		if (bytes == null)
			return null;
		StringBuilder ret = new StringBuilder(2 * bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			int b;
			b = 0x0f & (bytes[i] >> 4);
			ret.append("0123456789abcdef".charAt(b));
			b = 0x0f & bytes[i];
			ret.append("0123456789abcdef".charAt(b));
		}
		return ret.toString();
	}
}
