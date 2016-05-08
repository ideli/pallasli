package com.pallasli.study.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class DLLTEST {

	// 1.实现PegRoute.dll 文件接口

	public interface PegRoute extends Library {

		// 2.PegRoute.dll 中 HCTInitEx方法
		public int HCTInitEx(int Version, String pStrCurrentDirectory);
	}

	public static void main(String[] args) {
		// 3.加载DLL文件，执行dll方法
		PegRoute epen = (PegRoute) Native.loadLibrary("PegRoute",
				PegRoute.class);
		if (epen != null) {
			System.out.println("DLL加载成功！");
			int success = epen.HCTInitEx(0, "");
			System.out.println("1.设备初始化信息！" + success);
		}
	}
}