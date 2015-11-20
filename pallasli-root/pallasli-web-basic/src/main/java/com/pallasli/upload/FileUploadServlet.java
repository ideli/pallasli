package com.pallasli.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.garage.xdatabase.BinaryAccess;
import com.garage.xdatabase.binary.Attachment;
import com.mixky.common.database.MixkyDataAccess;
import com.mixky.engine.certification.MixkyUserCertification;
import com.mixky.engine.organization.dao.User;

/**
 * 
 * <p>
 * Title: UploadServlet,供文件上载调用
 * </p>
 * <p>
 * Description: 供控件上传二进制流调用
 * </p>
 * <p>
 * Copyright: Mixky co., ltd. Copyright (c) 2008
 * </p>
 * <p>
 * Company:Mixky co., ltd.
 * </p>
 * 
 * @author
 * @version 1.0
 */
@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {
	private static Log logger;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void init() throws ServletException {
		logger = LogFactory.getLog(this.getClass());
		logger.info("UploadServlet loaded");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("***UploadServlet Invoked!");
		response.setContentType(CONTENT_TYPE);
		
		Attachment attach = null;
		// 从Stream读取信息
		// 得到附件参数
		String querystring = "";
		// 已经知道需要更新的记录id
		// 指定二进制所在表 所在列及id即可
		String tablename = request.getParameter("t");
		if (tablename == null || "".equals(tablename)) {
			tablename = "T_MK_SYS_FILES";
		} else {
			tablename = tablename.toUpperCase();
		}
		String fieldname = request.getParameter("f");
		if (fieldname == null || "".equals(fieldname)) {
			fieldname = "F_CONTENT";
		} else {
			fieldname = fieldname.toUpperCase();
		}
		String attachid = request.getParameter("i");
		
		// 并不知道需要更新记录id
		// 传入二进制对应文档特点
		String sourceTableName = request.getParameter("st");		// source tablename
		if (sourceTableName == null || "".equals(sourceTableName)) {
			sourceTableName = "";
		}
		sourceTableName = sourceTableName.toUpperCase();
		String sourceDataId = request.getParameter("si");			// source dataid		
		if (sourceDataId == null || "".equals(sourceDataId)) {
			sourceDataId = "0";
		}

		// 二进制本身的属性 插入附件需要写入数据库
		// 文件名 附件名 附件类型 用户名
		String sourceFieldName = request.getParameter("sf");	// source field name
		if (sourceFieldName == null || "".equals(sourceFieldName)) {
			sourceFieldName = "NULL";
		}
		
		String filename = request.getParameter("fn");			// file name
		if (filename == null || "".equals(filename)) {
			filename = "NULL";
		}
		
		String attachname = request.getParameter("an");			// attachment name
		String attachType = request.getParameter("ft");			// file type
		if (attachType == null || "".equals(attachType)) {
			attachType = String.valueOf(Attachment.SYS_FILE_WORDNORMAL);
		}

		String authorName = request.getParameter("u");			// user name
		User user = MixkyUserCertification.instance().getUserInfo(request);
		if (user != null) {
			authorName = user.getF_caption();
		}
		if (authorName == null || "".equals(authorName)) {
			authorName = "NULL";
		}
		
		logger.debug(" **attachid=" + attachid);
		logger.debug(" **tablename=" + tablename);
		logger.debug(" **fieldname=" + fieldname);
		
		logger.debug(" **srctablename=" + sourceTableName.toUpperCase());
		logger.debug(" **documentid=" + sourceDataId);
		logger.debug(" **sourcefield=" + sourceFieldName);
		
		logger.debug(" **filename=" + filename);
		logger.debug(" **filetype=" + attachType);
		logger.debug(" **username=" + authorName);
		logger.debug(" **attachname=" + attachname);

		try {
			if (attachid != null && (!"".equals(attachid))) {
				querystring = "ID=" + attachid;
			} else if ((sourceTableName != null && (!"".equals(sourceTableName))) && (sourceDataId != null && (!"".equals(sourceDataId)))
					&& (attachType != null && ((!"".equals(attachType))))) {
				querystring = "F_SOURCE_TABLENAME='" + sourceTableName.toUpperCase() + "' AND F_SOURCE_DATAID=" + sourceDataId + " AND F_ATTACH_TYPE=" + attachType;
			} else {
				querystring = "id=-1";
			}

			logger.debug(" **querystring=" + querystring);
			attach = getDataAccess().get("SELECT ID FROM " + tablename + " WHERE " + querystring, Attachment.class);
//			attach = (Attachment) Access.getRsToObj("SELECT ID FROM " + tablename + " WHERE " + querystring, new Attachment());
			if (attach.getId() > 0) {
				attach.setF_source_tablename(sourceTableName);
				attach.setF_source_fieldname(sourceFieldName);
				attach.setF_source_dataid(Long.valueOf(sourceDataId));
				attach.setF_attach_type(Integer.valueOf(attachType));
				attach.setF_author_name(authorName);
				attach.setF_filename(filename);
			} else {
				attach = new Attachment();
				attach.setF_source_tablename(sourceTableName);
				attach.setF_source_fieldname(sourceFieldName);
				attach.setF_source_dataid(Long.valueOf(sourceDataId));
				attach.setF_attach_type(Integer.valueOf(attachType));
				attach.setF_author_name(authorName);
				attach.setF_filename(filename);
			}
//			attach.setF_blob(LongfileToDB.getAvailableStream(request.getInputStream()));
			attach.setF_blob(request.getInputStream());
			BinaryAccess.instance().uploadBlob(null, attach);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MixkyDataAccess getDataAccess() {
		return MixkyDataAccess.instance();
	}
	
	public void destroy() {
		logger.info("***UploadServlet Destroy！");
	}
}