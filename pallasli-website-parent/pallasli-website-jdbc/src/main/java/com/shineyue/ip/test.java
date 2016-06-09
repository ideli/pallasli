package com.shineyue.ip;

import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IPSeeker ips = IPSeeker.getInstance(".");
		String ip = "62.76.42.1";//"124.193.185.6";
		//System.out.println("ip=" + ip + "," + ips.getCountry(ip) + " " + ips.getArea(ip));
		//ips.getTxtFile();
		Map map = new TreeMap();
		map.put("2004", "2004S");
		map.put("2000", "2000S");
		map.put("2002", "2002S");
		map.put("2009", "2009S");
		Iterator it = map.keySet().iterator();
	    //Iterator it = col.iterator();
	    while(it.hasNext()) {
	    	Object i = it.next();
	        System.out.println(i + "," + map.get(i));
	    }
	}
}
