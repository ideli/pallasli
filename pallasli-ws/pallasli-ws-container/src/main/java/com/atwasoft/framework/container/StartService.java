package com.atwasoft.framework.container;

import java.util.Map;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atwasoft.framework.core.AppContextCore;
import com.atwasoft.framework.core.annotation.SystemComponent;

public class StartService extends HttpServlet{

	private static final Logger log=LoggerFactory.getLogger(StartService.class);

	private static final String server = "0.0.0.0";
	private static final long serialVersionUID = 1L;

	public StartService() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {
		start();
	}

	public static void start() {
	
		try{

			int port=ContainerEnv.getInstance().getWebServicePort();

			Map<String,Object> beansWithAnno=AppContextCore.getContext().getBeansWithAnnotation(WebService.class);
			
			if(beansWithAnno.size()<=0){
				return;
			}
			
			log.info("系统开始发布WEB服务");


			for(String key:beansWithAnno.keySet()){
				Object webserviceObject=beansWithAnno.get(key);
				WebService webserviceAnno=webserviceObject.getClass().getAnnotation(WebService.class);
				String publishServiceName=webserviceAnno.serviceName();
				Endpoint endpoint;
				try {
					endpoint = Endpoint.create(webserviceObject);

					endpoint.setExecutor(new WsThreadPool(getPoolSize(webserviceObject,publishServiceName)));

					String url = "http://" + server + ":" + port;
					url=url + "/" + publishServiceName;
					endpoint.publish(url);

					log.info("{}发布成功，发布地址：{}",publishServiceName,url);

				} catch (Exception e) {
					log.error("启动WEB数据服务失败：" + e.getMessage());
				}
			}

			log.info("WEB数据服务启动成功，监听端口：[" + port + "]");
		}
		catch (Exception e) {

			log.error("系统发布WEB服务时出错：" + e.getMessage());

		}
	}

	private static int getPoolSize(Object webserviceObject,String publishServiceName){

		int poolSize=0;

		SystemComponent systemComp=webserviceObject.getClass().getAnnotation(SystemComponent.class);
		if(systemComp==null){
			poolSize=ContainerEnv.getInstance().getAppPoolSize();
			
			log.info("分配应用服务{},池大小：{}",publishServiceName,poolSize);
		}else{
			poolSize=ContainerEnv.getInstance().getSystemPoolSize();

			log.info("分配系统服务{},池大小：{}",publishServiceName,poolSize);
		}

		return poolSize;

	}
}
