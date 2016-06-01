package com.pallasli.study.cache.redis.cache;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/*
 * 监听器，用于项目启动的时候初始化信息
 */
@Service
public class StartAddCacheListener implements ApplicationListener<ContextRefreshedEvent> {
	// 日志
	private final Logger log = Logger.getLogger(StartAddCacheListener.class);

	@Autowired
	private RedisCacheUtil<Object> redisCache;

	// @Autowired
	// private BrandStoreService brandStoreService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// spring 启动的时候缓存城市和国家等信息
		if (event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
			System.out.println("\n\n\n_________\n\n缓存数据 \n\n ________\n\n\n\n");
			// List<City> cityList = brandStoreService.selectAllCityMessage();
			// List<Country> countryList =
			// brandStoreService.selectAllCountryMessage();
			//
			// Map<Integer, City> cityMap = new HashMap<Integer, City>();
			//
			// Map<Integer, Country> countryMap = new HashMap<Integer,
			// Country>();
			//
			// int cityListSize = cityList.size();
			// int countryListSize = countryList.size();
			//
			// for (int i = 0; i < cityListSize; i++) {
			// cityMap.put(cityList.get(i).getCity_id(), cityList.get(i));
			// }
			//
			// for (int i = 0; i < countryListSize; i++) {
			// countryMap.put(countryList.get(i).getCountry_id(),
			// countryList.get(i));
			// }
			//
			// redisCache.setCacheIntegerMap("cityMap", cityMap);
			// redisCache.setCacheIntegerMap("countryMap", countryMap);
		}
	}

}