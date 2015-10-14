package com.pallas.sys.sso.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SsoServerUserCertification {
	private static SsoServerUserCertification singleton;

	public SsoServerUserCertification() {
	}

	public static SsoServerUserCertification instance() {
		if (singleton == null) {
			// singleton = (SsoServerUserCertification) ContextHolder.instance()
			// .getBean("mixky-ssouser-certification");
			singleton = new SsoServerUserCertification();
		}
		return singleton;
	}

	public String certify(String username, String password, String zjhm,
			HttpServletRequest request, HttpServletResponse response,
			boolean keepLoginName, boolean keepStatus, boolean forceLogin,
			StringBuffer err) {

		return null;
	}

	public String getAddr(HttpServletRequest request) {
		String ip = getRemoteIpAddr(request);
		return getAddr(ip);
	}

	public String getAddr(String ip) {
		try {
			if (ip.startsWith("192.168")) {
				return "内网";
			}
			String[] ips = ip.split("[.]");
			long lip = Long.parseLong(ips[0]) * 255 * 255 * 255
					+ Long.parseLong(ips[1]) * 255 * 255
					+ Long.parseLong(ips[2]) * 255 + Integer.parseInt(ips[3]);
			String sql = "select address from t_mk_sys_ipaddress where startip<="
					+ lip + " and endip>=" + lip + " order by (endip-startip)";
			List<List<String>> addr = null;
			return addr.get(0).get(0);

		} catch (Exception e) {
			System.err.println("get address from ip error: " + e.getMessage());
			return "";
		}
	}

	public void clearUserInfo(String userName, HttpServletRequest request,
			HttpServletResponse response) {

	}

	public User getUserInfo(HttpServletRequest request) {
		String finger = "03014d250000fffefffeff00fe00fc00fc00f800f800f000f000f000e000e000e000c000c000c000000000000000000000000000000000007013599e6a9b069e5ca286de32270f5e6928891e32bb0b1e77be4e9e3ea5cf7f242d8f1747b0cb7f73b1e01f3b3889ff508f2bfa4398d1ba34ad4dba54b38ada5f3e125a66404f7a4f8d139b4ea1abbb4da58f9b532bcd3b3cb061125835e15853a40f7958a94ab93c2dcc13243de339551d12d653284d77583d25144fba61b555ba62d54d3f20324b9553305419952c4d966c4f000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000301471b0000fffeff06fc02f800f000f000f000e000e000e000e000c000c000c000c000c000c00200000000000000000000000000000000669319fe29a68f1e6028091e1f36641e2c3b0afe703dcdbe76915e375e9ac63f35a58f1f3438c9df4919537c451dec3c2cad0d7c3db0cb7c4e1d94dd5abd513d331d911a4c330b1a5135e17b32ae0c7837aee4f8613f0eb94c37e1d6381de8574f290a714bb9e3514f3d650e0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		String passWord = "123456";
		User u = new User();
		u.setPassword(passWord);
		u.setFingerCode(finger);
		return u;
	}

	public String getRemoteIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void main(String[] args) {
		String ip = "117.136.0.101";
		String[] ips = ip.split("[.]");
		long lip = Long.parseLong(ips[0]) * 255 * 255 * 255
				+ Long.parseLong(ips[1]) * 255 * 255 + Long.parseLong(ips[2])
				* 255 + Integer.parseInt(ips[3]);
		System.out.println(lip);
	}
}
