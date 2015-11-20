package com.pallasli.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mixky.engine.certification.MixkyUserCertification;
import com.mixky.engine.organization.dao.User;

public class ImageUploadBean {

	private Dictionary fields; // 保存普通表单域的name和value

	private boolean writeToFile; // 是写成文件还是直接存入byte[]数组,默认值false为存入byte数组

	private byte[] data;

	/**
	 * 文件保存成本地文件时使用的属性
	 */
	private String orfilename = ""; // 原始文件名

	private String filepath = ""; // 保存文件的路径

	private String exfilename = ""; // 要保存的文件名的额外值

	private String filename = ""; // 保存到服务器的文件名

	private long filesize = 0; // 文件大小（单位byte）

	private String urlString[];// 下载全部图片的url地址集合 电子档案系统中使用

	private String filenames[];// 下载全部图片的分类名称集合 电子档案系统中使用

	private String filecodes[];// 下载全部图片的分类编码集合 电子档案系统中使用

	private String waterMark; // 保存生成的水印 电子档案系统中使用
	
	private int count;// 判断数量常用字段

	private String bm[];//编码

	private String mc[];//名称
	
	private List<byte[]> list=new ArrayList<byte[]>();

	/**
	 * 文件上传 2010年9月15日 add by HD
	 * 此方法是基于commons-fileupload组件1.0,jdk1.4下的实现,部分核心代码已经过期
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void doUpload(HttpServletRequest request) throws Exception {

		doUpload(request, "gb2312");

		// request.setCharacterEncoding("GB2312");
		//
		// // 检查表单格式是否正确,是否包含上传文件组件
		// if
		// (org.apache.commons.fileupload.FileUpload.isMultipartContent(request))
		// {
		//
		// // 文件上传
		// org.apache.commons.fileupload.DiskFileUpload upload = new
		// org.apache.commons.fileupload.DiskFileUpload();
		//
		// // 获取表单字段集合
		// List items = upload.parseRequest(request);
		//
		// // 初始化一个集合,存放普通表单域的名称和值
		// this.fields = new Hashtable();
		//
		// // 遍历获取所有的表单字段
		// Iterator iter = items.iterator();
		// while (iter.hasNext()) {
		// FileItem item = (FileItem) iter.next();
		//
		// // 判断表单字段的类型,如果是普通表单字段,把名称和值存入fields集合,如果是文件域则解析成byte数组或保存成文件
		// if (item.isFormField()) {
		// processFormField(item);
		// } else {
		// processUploadedFile(item);
		// }
		// }
		// }
	}

	/**
	 * 文件上传 2010年9月16日 add by HD
	 * 
	 * @param request
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void doUpload(HttpServletRequest request, String encoding)
			throws Exception {

		request.setCharacterEncoding(encoding);

		// 为文件对象产生工厂对象。
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 1); // 设置缓冲区的大小，此处为1M

		// 产生servlet上传对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(-1); // 不限制上传文件的大小

		List<FileItem> list = upload.parseRequest(request); // 取得所有的上传文件信息
		Iterator<FileItem> it = list.iterator();

		// 初始化一个集合,存放普通表单域的名称和值
		this.fields = new Hashtable();

		while (it.hasNext()) {
			FileItem item = it.next();
			if (item.isFormField()) {
				processFormField(item);
			} else {
				processUploadedFile(item);
			}
		}
	}

	/**
	 * 把普通字段的名称和值放入Hashtable
	 * 
	 * @param item
	 */
	@SuppressWarnings("unchecked")
	public void processFormField(FileItem item) {
		// Process a regular form field
		if (item.isFormField()) {
			String name = item.getFieldName();
			String value = item.getString();
			this.fields.put(name, value);
		}
	}

	/**
	 * 把文件流存入byte[]数组或保存成文件
	 * 
	 * @param item
	 * @throws IOException
	 * @throws Exception
	 */
	public void processUploadedFile(FileItem item) throws Exception {
		if (!item.isFormField()) {
			//String orfileName = item.getName(); // 上传的文件名
			//orfileName = orfileName.substring(orfileName.lastIndexOf("\\") + 1); // 只获得文件名
			//this.setOrfilename(orfileName);
			//int lastdot = orfileName.lastIndexOf("."); // 最后一个“.”的位置
//			String fileName = orfileName.substring(0, lastdot)
//					+ this.getExfilename() + orfileName.substring(lastdot); // 重新命名保存在服务器上的文件名
//			System.out.println("上传的文件名上传的文件名上传的文件名上传的文件名"+fileName);
//			this.setFilename(fileName);
//			long sizeInBytes = item.getSize();
//			this.setFilesize(sizeInBytes);

			// Process a file upload
			if (this.isWriteToFile()) {
				File uploadedFile = new File(this.getFilepath() + "\\"
						+ this.getFilename());
				item.write(uploadedFile);
			} else {
				InputStream uploadedStream = item.getInputStream();
				// Process a file upload in memory
				this.setData(item.get());
				uploadedStream.close();
			}
		}
	}

	/**
	 * 客户端根据表单字段名取值
	 * 
	 * @param fieldName
	 * @return
	 */
	public String getFieldValue(String fieldName) {

		if (this.fields == null || fieldName == null) {
			return null;
		}

		return null==fields.get(fieldName)?"":fields.get(fieldName).toString().trim();

	}

	/**
	 * 生成临时文件夹保存从数据库下载的图片 2010年9月29日 ADD BY HD
	 * 
	 * @param request
	 * @return url地址
	 */
	public String getDownloadFileUrl(HttpServletRequest request) {
		String url = "";
		String path = "";
		File tempDir = new File(request.getSession().getServletContext()
				.getRealPath("/")
				+ "tempImages");
		if (!tempDir.exists()) {
			tempDir.mkdir();
			System.out.println("tempDir make sucess");
		}
		// 删除之前生成的临时文件
		if (tempDir.list().length > 0) {
			cleanTempImages(tempDir);
		}

		// 生成新的临时文件
		File tempfile = null;
		try {
			tempfile = File.createTempFile(
					Math.random() + request.getParameter("ygdh")
					+ request.getParameter("id"), "", tempDir);
			FileOutputStream fis = null;
			fis = new FileOutputStream(tempfile);
			fis.write(this.data);
			
			fis.close();

			// 得到文件的url地址
			path = request.getContextPath();
			url = request.getScheme() + "://" + request.getServerName() + ":"
					+ request.getServerPort() + path + "/" + "tempImages" + "/"
					+ tempfile.getName();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			url = "";
		} finally {
			if (tempfile != null) {
				tempfile.deleteOnExit();
			}
		}

		return url;
	}

	/**
	 * 此方法用于删除临时文件夹下的临时文件 2010年9月29日 ADD BY HD
	 * 
	 * @param tempDir
	 */
	private void cleanTempImages(File tempDir) {

		if (tempDir.exists()) {
			if (tempDir.isDirectory()) {
				File[] fileList = tempDir.listFiles();
				for (int i = 0; i < fileList.length; i++) {
					File tempImage = fileList[i];
					if (tempImage.isDirectory()) {
						while (tempImage.list().length > 0) {
							cleanTempImages(tempImage);
						}
						tempImage.delete();
					} else {
						tempImage.delete();
					}
				}
			} else {
				tempDir.delete();
			}
		}

	}

	/**
	 * 生成水印的方法2010年9月29日 2010年9月29日 ADD BY HD
	 * 
	 * @param request
	 * @return
	 */
	public static String getWaterMark(HttpServletRequest request) {

		String curdate = "";
		String userName = "";
		String loginName = "";

		// 获取当前的用户名和日期
		// 设置当前时区
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
		TimeZone.setDefault(tz);
		// 得到当前日期
		curdate = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(Calendar
				.getInstance().getTime());
		// 得到当前用户
		User curUser = MixkyUserCertification.instance().getUserInfo(request);
		// 得到当前用户名
		userName = curUser.getF_caption();
		// 得到当前用户登录名
		loginName = curUser.getF_name();

		return userName + " " + loginName + " " + curdate;
	}

	public byte[][] getData() {
		byte[][] data = new byte[list.size()][];
		for(int i=0;i<list.size();i++)
		{
			data[i]=list.get(i);
		}
		return data;
	}

	public void setData(byte[] data) {
		System.out.println("长度=============="+data.length);
		list.add(data);
	}

	public String getExfilename() {
		return exfilename;
	}

	public void setExfilename(String exfilename) {
		this.exfilename = exfilename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public String getOrfilename() {
		return orfilename;
	}

	public void setOrfilename(String orfilename) {
		this.orfilename = orfilename;
	}

	public boolean isWriteToFile() {
		return writeToFile;
	}

	public void setWriteToFile(boolean writeToFile) {
		this.writeToFile = writeToFile;
	}

	public String[] getUrlString() {
		return urlString;
	}

	public void setUrlString(String[] urlString) {
		this.urlString = urlString;
	}

	public String[] getFilenames() {
		return filenames;
	}

	public void setFilenames(String[] filenames) {
		this.filenames = filenames;
	}

	public String[] getFilecodes() {
		return filecodes;
	}

	public void setFilecodes(String[] filecodes) {
		this.filecodes = filecodes;
	}

	public String getWaterMark() {
		return waterMark;
	}

	public void setWaterMark(String waterMark) {
		this.waterMark = waterMark;
	}

	public Dictionary getFields() {
		return fields;
	}

	public void setFields(Dictionary fields) {
		this.fields = fields;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String[] getBm() {
		return bm;
	}

	public void setBm(String[] bm) {
		this.bm = bm;
	}

	public String[] getMc() {
		return mc;
	}

	public void setMc(String[] mc) {
		this.mc = mc;
	}

}
