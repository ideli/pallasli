package com.pallasli.log4jdbc;

/**
 * 
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * ClassName: TestServlet
 * <p>
 * <p>
 * Description:
 * <p>
 * <p>
 * Company:广州利迪网络科技有限公司
 * <p>
 * 
 * @author xudp
 * @version 1.0 V
 * @createTime 2014-11-5 下午01:49:49
 */
public class TestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "INSERT INTO LD_USER(ID,USERNAME,USERCODE,PASSWORD) VALUES(?,?,?,?)";
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, UUID.randomUUID().toString());
			pstmt.setString(2, "孤傲苍狼");
			pstmt.setString(3, "gacl");
			pstmt.setString(4, "xdp");
			int executeResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}