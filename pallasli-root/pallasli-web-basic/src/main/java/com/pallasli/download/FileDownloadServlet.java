package com.pallasli.download;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.garage.xdatabase.Access;
import com.garage.xdatabase.BinaryAccess;
import com.garage.xdatabase.binary.Attachment;

/**
 * <p>
 * 返回二进制数据流到页面
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Mixky co., ltd. (c) 2008
 * </p>
 * <p>
 * Company: Mixky co., ltd.
 * </p>
 * 
 * @author
 * @version 1.0
 */

@SuppressWarnings("serial")
public class FileDownloadServlet extends HttpServlet {
	private Log logger = LogFactory.getLog(this.getClass());

	public void init() throws ServletException {
		logger.info("DownloadServlet Loaded!");
		logger = LogFactory.getLog(this.getClass());
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String TABLENAME = "T_MK_SYS_FILES";
		String FIELDNAME = "F_CONTENT";

		// 得到传入的参数
		String tablename = request.getParameter("tablename");
		String fieldname = request.getParameter("fieldname");
		String ids = request.getParameter("id");
		String filename = request.getParameter("filename");
		if (tablename == null || "".equals(tablename)) {
			tablename = TABLENAME;
		}
		if (fieldname == null || "".equals(fieldname)) {
			fieldname = FIELDNAME;
		}
		if (filename == null || "".equals(filename)) {
			if (tablename.toUpperCase().equals(TABLENAME)) {
				/**
				 * 默认处理系统附件表
				 */
				Attachment attach = (Attachment) Access.getRsToObj( "SELECT F_FILENAME FROM " + TABLENAME + " WHERE ID=" + ids, new Attachment());
				filename = attach.getF_filename();
			} else {
				// TODO:
			}
		}
		if (filename == null) {
			filename = "无标题";
		}
		response.reset();
		response.setContentType("application/octet-stream;name=\"" + new String(filename.getBytes(), "ISO-8859-1") + "\"");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(filename.getBytes(), "ISO-8859-1") + "\"");

		response.setHeader("Accept-ranges", "bytes");
		OutputStream os = response.getOutputStream();
		BinaryAccess.instance().loadBlob(null, tablename, fieldname, "ID=" + ids, os);
		os.flush();
		os.close();
	}

	public void destroy() {
		logger.info("DownloadServlet Destroied！");
	}
}