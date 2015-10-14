package com.pallasli.utils;

import java.io.IOException;

import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import com.maxmind.geoip.timeZone;

public class IpUtils {
	private static LookupService cl_city = null;
	private static LookupService cl_country = null;
	private static String basePath = IpUtils.class.getResource("/").getPath();

	static {
		try {
			cl_country = new LookupService(basePath + "GeoIP.dat",
					LookupService.GEOIP_MEMORY_CACHE);
			cl_city = new LookupService(basePath + "GeoLiteCity.dat",
					LookupService.GEOIP_MEMORY_CACHE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getCountry(String ip) {

		String name = cl_country.getCountry(ip).getName();
		cl_city.close();
		return name;
	}

	public static String getCountryCode(String ip) {

		String name = cl_country.getCountry(ip).getCode();
		cl_city.close();
		return name;
	}

	public static String getCity(String ip) {

		String name = cl_city.getLocation(ip).city;

		cl_city.close();
		return name;

	}

	public static String getRegion(String ip) {

		String countryCode = cl_city.getLocation(ip).countryCode;
		String regionCode = cl_city.getLocation(ip).region;
		String name = regionName.regionNameByCode(countryCode, regionCode);
		cl_city.close();
		return name;

	}

	public static double getDistance(String ip1, String ip2) {

		double distance = cl_city.getLocation(ip1).distance(
				cl_city.getLocation(ip2));

		cl_city.close();
		return distance;

	}

	public static String timeZoneByCountryAndRegion(String ip) {

		String countryCode = cl_city.getLocation(ip).countryCode;
		String regionCode = cl_city.getLocation(ip).region;
		String time = timeZone.timeZoneByCountryAndRegion(countryCode,
				regionCode);
		cl_city.close();
		return time;

	}

	public static String getPostalCode(String ip) {

		String name = cl_city.getLocation(ip).postalCode;

		cl_city.close();
		return name;

	}

	public static int getMetro_code(String ip) {

		int metro_code = cl_city.getLocation(ip).metro_code;

		cl_city.close();
		return metro_code;

	}

	public static float getLatitude(String ip) {

		float latitude = cl_city.getLocation(ip).latitude;

		cl_city.close();
		return latitude;

	}

	public static float getLongitude(String ip) {

		float longitude = cl_city.getLocation(ip).longitude;

		cl_city.close();
		return longitude;

	}

	public static int getArea(String ip) {
		int area_code = cl_city.getLocation(ip).area_code;

		cl_city.close();
		return area_code;

	}

}
