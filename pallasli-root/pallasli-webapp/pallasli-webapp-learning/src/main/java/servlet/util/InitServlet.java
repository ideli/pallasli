package servlet.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static {
		try {

			System.out
					.println("***********************************************************************");
			System.out
					.println("**                       初始化静态块成功                                                                              **");
			System.out
					.println("*****************************************************************\n\n\n");

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法用于测试资源文件是否有效 访问地址为/bizfuse/zqlei/zqlei.zqlei
	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.out.println("res ----> ");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
