package com.mixky.system;

import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mixky.app.ApplicationParameters;
import com.mixky.engine.authority.dao.ModuleRoleAuth;
import com.mixky.engine.design.DesignObject;
import com.mixky.engine.design.DesignObjectLoader;
import com.mixky.engine.design.application.Application;
import com.mixky.engine.design.workflow.Flow;
import com.mixky.toolkit.JsonObjectTool;
import com.mixky.ws.SyncData;
import com.mixky.ws.service.IPortalDataSyncService;
import com.wa.datas.access.ws.PortalDataSyncServiceAccess;

public class ApplicationDataLoader {
	private static ApplicationDataLoader singleton;
	private Log logger;
	private boolean applicationLoaded;

	protected ApplicationDataLoader() {
		logger = LogFactory.getLog(this.getClass());
		applicationLoaded = false;
	}

	public static ApplicationDataLoader instance() {
		if (singleton == null) {
			singleton = new ApplicationDataLoader();
		}
		return singleton;
	}

	public void load() throws FileNotFoundException {
		if (!applicationLoaded) {
			// 装载设计元素
			DesignObjectLoader.instance().load();
			loadApplicationDataFromServer();

			// 根据运行模式，生成相应文件
			// DesignerJsFileBuilder.instance().buildJsDesignerClass();
			// DesignerJsFileBuilder.instance().buildJsDesignerLib();
			// DesignerJsFileBuilder.instance().buildJsDesignerFramework();
			// 生成类文件
			// DesignerCssFileBuilder.instance().buildIconCss();

			applicationLoaded = true;
		}
	}

	public void loadApplicationDataFromServer() {
		// 从中心服务器装载模块角色权限
		loadModuleRoleAuthFromServer();
		// 从中心服务器装载流程定义
		loadWorkFlowDataFromServer();
		// 从中心服务器装载共用数据字典
		loadDictionaryFromServer();
	}

	public void loadModuleRoleAuthFromServer() {
		IPortalDataSyncService service = PortalDataSyncServiceAccess.instance()
				.getPortalDataSyncService();
		if (service != null) {
			// 从本地缓存当中清除相关数据
			com.wa.xdatabase.cache.DataCacheFactory waDataCache = com.wa.xdatabase.cache.DataCacheFactory
					.instance();
			waDataCache.removeCachedTable("t_mk_sys_module_role");

			// 从中心服务器下载角色权限定义
			String appKey = ApplicationParameters.instance()
					.getApplicationCode();
			SyncData syncData = service.getSyncData(appKey,
					SyncData.SYNC_TYPE_ROLEAUTH, null);
			if (syncData != null) {
				String jsonString = syncData.getF_jsonData();
				if (jsonString != null && !"".equals(jsonString)) {
					JsonArray jsonFlowArray = JsonObjectTool
							.string2JsonArray(jsonString);
					if (!jsonFlowArray.isJsonNull()) {
						List<ModuleRoleAuth> auths = JsonObjectTool
								.jsonArray2ObjectListT(jsonFlowArray,
										ModuleRoleAuth.class);
						for (int i = 0; i < auths.size(); i++) {
							addModuleRoleAuthToCache(auths.get(i));
						}
					}
				}
			}
		}
	}

	public void loadWorkFlowDataFromServer() {
		IPortalDataSyncService service = PortalDataSyncServiceAccess.instance()
				.getPortalDataSyncService();
		if (service != null) {
			String appKey = ApplicationParameters.instance()
					.getApplicationCode();
			SyncData syncData = service.getSyncData(appKey,
					SyncData.SYNC_TYPE_WORKFLOW, null);
			if (syncData != null) {
				String jsonString = syncData.getF_jsonData();
				if (jsonString != null && !"".equals(jsonString)) {
					JsonObject jsonObject = JsonObjectTool
							.string2JsonObject(jsonString);
					JsonObject applicationJsonObject = jsonObject.get(
							"application").getAsJsonObject();
					JsonArray flowsJsonArray = jsonObject.get("flows")
							.getAsJsonArray();
					Application application = DesignObject
							.loadDesignObject(applicationJsonObject
									.get("f_key").getAsString());
					if (application == null) {
						application = new Application();
					}
					application.update(applicationJsonObject);
					for (int i = 0; i < flowsJsonArray.size(); i++) {
						JsonObject flowJsonObject = flowsJsonArray.get(i)
								.getAsJsonObject();
						Flow flow = DesignObject.loadDesignObject(application
								.getF_key()
								+ "."
								+ flowJsonObject.get("f_key").getAsString());
						if (flow == null) {
							flow = new Flow();
							flow.setF_key(flowJsonObject.get("f_key")
									.getAsString());
							application.append(flow);
						}
						flow.update(flowJsonObject);
					}
				} else {
					logger.warn("服务器返回流程列表为空");
				}
			}
		} else {
			logger.warn("未找到WorkFlowDataService服务");
		}
	}

	private void addModuleRoleAuthToCache(ModuleRoleAuth auth) {
		com.wa.xdatabase.cache.DataCacheFactory waDataCache = com.wa.xdatabase.cache.DataCacheFactory
				.instance();
		// 添加角色权限到缓存当中
		waDataCache.updateDataRow(auth.getTablename(), auth.getId(), auth);
	}

	private void loadDictionaryFromServer() {
		IPortalDataSyncService service = PortalDataSyncServiceAccess.instance()
				.getPortalDataSyncService();
		if (service != null) {
			String appKey = ApplicationParameters.instance()
					.getApplicationCode();
			SyncData syncData = service.getSyncData(appKey,
					SyncData.SYNC_TYPE_WORKFLOW, null);
			if (syncData != null) {
				String jsonString = syncData.getF_jsonData();
				if (jsonString != null && !"".equals(jsonString)) {
					// JsonArray jsonFlowArray =
					// JsonObjectTool.string2JsonArray(jsonString);

					// TODO: 添加字典数据到本地数据缓存当中
				}
			}
		}
	}

	public void setApplicationLoaded(boolean applicationLoaded) {
		this.applicationLoaded = applicationLoaded;
	}

	public boolean isApplicationLoaded() {
		return applicationLoaded;
	}

}
