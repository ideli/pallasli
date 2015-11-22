/**
 * 
 */
package com.pallasli.upload;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.JsonObject;

/**
 * @author singing
 *
 */
@SuppressWarnings("serial")
public class FileUpload2FolderServlet extends HttpServlet {

	private static Log logger = LogFactory
			.getLog(FileUpload2FolderServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		MultipartRequest multi;
		try {
			String fileUploadPath = "";
			multi = new MultipartRequest(request, fileUploadPath,
					20 * 1024 * 1024, "UTF-8");

			String targetFolderName = multi.getParameter("targetFolder");
			String newName = multi.getParameter("newName");

			String targetFolderPath = ContextHolder.instance().getRealPath(
					targetFolderName);
			File targetFolder = new File(targetFolderPath);
			if (!targetFolder.exists()) {
				targetFolder.mkdir();
			}

			JsonObject json = new JsonObject();
			json.addProperty("success", false);

			Enumeration<String> em = multi.getFileNames();
			while (em.hasMoreElements()) {
				String fileFiledName = em.nextElement();
				java.io.File file = multi.getFile(fileFiledName);

				if (newName == null) {
					FileTool.copyFile(file.getAbsolutePath(), targetFolderPath
							+ "/" + file.getName());
				} else {
					FileTool.copyFile(file.getAbsolutePath(), targetFolderPath
							+ "/" + newName);
				}

				json.addProperty("message", "上传完毕. " + file.length() + " 字节.");
				logger.debug("上传完毕. " + file.length() + " 字节.");

				file.delete();
				json.addProperty("success", true);
			}

			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			logger.error("上传文件到文件夹中失败！", e);
		}
	}
}