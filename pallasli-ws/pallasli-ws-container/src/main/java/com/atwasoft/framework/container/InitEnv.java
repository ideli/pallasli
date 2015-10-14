package com.atwasoft.framework.container;

import java.util.Map;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.atwasoft.framework.core.AppContextCore;

public class InitEnv 
{	
	private static final Logger log=LoggerFactory.getLogger(InitEnv.class);

	private static String userid = "admin";
	private static String password = "admin";

	public static boolean initServer()
	{
		return initApp();
	}

	/**
	 * 初始化应用环境
	 * @return	成功返回true
	 */
	private static boolean initApp()
	{				
		try
		{	       

			log.info("系统初始化应用环境");

			ApplicationContext context=AppContextCore.getContext();

			log.info("系统初始化应用环境完成");

			log.info("WEB容器配置文件地址:{}",ContainerEnv.getInstance().getContainerConfigFile());

			log.info("应用池大小:{}",ContainerEnv.getInstance().getAppPoolSize());
			log.info("系统池大小:{}",ContainerEnv.getInstance().getSystemPoolSize());
			log.info("应用服务端口:{}",ContainerEnv.getInstance().getWebServicePort());
			log.info("是否打开DB日志:{}",ContainerEnv.getInstance().isDbTrace());
			log.info("数据库类型:{}",ContainerEnv.getInstance().getDbType());
			log.info("是否启用监控程序:{}",ContainerEnv.getInstance().isMonitor());
			log.info("日志最大计数器:{}",ContainerEnv.getInstance().getLogCounter());

			log.info("共加载组件数:{}",context.getBeanDefinitionCount());

			if(log.isDebugEnabled()){

				String[] beanNames=context.getBeanDefinitionNames();

				for(String beanName:beanNames){
					log.debug("load bean name:{}",beanName);		        
				}
			}

			Map<String,Object> beansWithAnno=context.getBeansWithAnnotation(WebService.class);

			log.info("共加载WEB服务组件数：{}",beansWithAnno==null || beansWithAnno.isEmpty() ?0:beansWithAnno.keySet().size());

			if(log.isDebugEnabled()){
				
				for(String key:beansWithAnno.keySet()){
					log.debug("load webservice:{},{}",key,beansWithAnno.get(key));
				}
				
			}



			String[] repoBeans=context.getBeanNamesForAnnotation(Repository.class);
			log.info("共加载数据库访问组件数：{}",repoBeans==null?0:repoBeans.length);

			if(log.isDebugEnabled()){

				for(String repo:repoBeans){
					log.debug("load repostiory:{}",repo);		        
				}	        

			}


			String[] serviceBeans=context.getBeanNamesForAnnotation(Service.class);

			log.info("共加载业务逻辑组件数：{}",serviceBeans==null?0:serviceBeans.length);

			if(log.isDebugEnabled()){

				for(String service:serviceBeans){
					log.debug("load buz service:{}",service);		        
				}	           

			}


			return true;
		}
		catch (Exception e) {
			log.error("初始化应用环境出错: " + e.getMessage());
			return false;
		}	      
	}	

	public static String getUserid() {
		return userid;
	}
	public static String getPassword() {
		return password;
	}

}
