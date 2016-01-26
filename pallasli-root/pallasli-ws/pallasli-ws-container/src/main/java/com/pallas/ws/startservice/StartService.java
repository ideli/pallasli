package com.pallas.ws.startservice;

import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pallas.utils.ClassUtil;

public class StartService extends HttpServlet {

	private static final Logger log = LoggerFactory
			.getLogger(StartService.class);
	private static final String server = "0.0.0.0";
	private static final long serialVersionUID = 1L;
	private static Endpoint endpoint;

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		start();
	}

	public static void start() {
		// Package pack;
		try {
			// pack = Package.getPackage("com.pallas.ws");
			Set<Class<?>> classes = ClassUtil.getClasses("com.pallas.ws");
			log.info("[classes.size]:" + classes.size());
			for (Class<?> clas : classes) {
				String serviceName = ClassUtil.getServicename(clas);
				log.info("[serviceName]:" + serviceName);
				if (serviceName != null) {
					create_endpoint(clas);
					configure_endpoint(clas.getName());
					publish(serviceName);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("扫描Package下的class失败：" + e.getMessage());
		}
	}

	private static void create_endpoint(Class<?> clas) {
		try {
			endpoint = Endpoint.create(clas.newInstance());
		} catch (Exception e) {
			log.error("启动WEB数据服务失败：" + e.getMessage());
		}
	}

	private static void configure_endpoint(String classname) {
	}

	private static void publish(String serviceName) {
		String url;
		try {
			url = "http://" + server + ":" + 8081;
			endpoint.publish(url + "/" + serviceName);
			log.info("服务发布成功：" + url + "/" + serviceName);
		} catch (Exception e) {
			log.error("WEB数据服务启动失败：" + e.getMessage());
		}
	}
}
