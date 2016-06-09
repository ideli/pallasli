package com.shineyue.ip;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import com.shineyue.db.DBUtil;
import com.shineyue.sql.Row;

/**
 * 工具类，提供一些方便的方法，有些主要是用于调试用途，有些不是
 */
public class Util {

    // string buffer
    private static StringBuilder sb = new StringBuilder();

    /** Valid character mask. */
    public static final int MASK_VALID = 0x01;
    
    /**
     * @param ip ip的字节数组形式
     * @return 字符串形式的ip
     */
    public static String getIpStringFromBytes(byte[] ip) {
	    sb.delete(0, sb.length());
    	sb.append(ip[0] & 0xFF);
    	sb.append('.');
    	sb.append(ip[1] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[2] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[3] & 0xFF);
    	return sb.toString();
    }
    
    /**
     * 从ip的字符串形式得到字节数组形式
     * @param ip 字符串形式的ip
     * @return 字节数组形式的ip
     */
    public static byte[] getIpByteArrayFromString(String ip) {
        byte[] ret = new byte[4];
        StringTokenizer st = new StringTokenizer(ip, ".");
        try {
            ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
        } catch (Exception e) {
        	 System.out.println(e.getMessage());
        }
        return ret;
    }   
    
    /**
     * 根据某种编码方式将字节数组转换成字符串
     * @param b 字节数组
     * @param offset 要转换的起始位置
     * @param len 要转换的长度
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b, offset, len);
        }
    }
    
    private static long getIntFromIp(String ipString){
    	String [] ip = ipString.split("[.]");
    	return 	Long.parseLong(ip[0]) * 255 * 255 * 255 + 
    			Long.parseLong(ip[1]) * 255 * 255 +
    			Long.parseLong(ip[2]) * 255 + Integer.parseInt(ip[3]);
    }

    public static String getAddress(String ip){
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try{
    		//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    		/*Class.forName("oracle.jdbc.driver.OracleDriver");
    		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.8:1521:orcl","wasys350","wasoft2014");
			String sql = "select address from t_mk_sys_ipaddress where startip<=? and endip>=? order by (endip-startip)";
    		pstmt = conn.prepareStatement(sql);
    		long ip_num = getIntFromIp(ip);
    		pstmt.setLong(1,ip_num);
    		pstmt.setLong(2,ip_num);
    		rs = pstmt.executeQuery();*/
    		long ip_num = getIntFromIp(ip);
    		String sql = "select address from t_mk_sys_ipaddress where startip<="+ip_num+" and endip>="+ip_num+" order by (endip-startip)";
    		List<Row> list = DBUtil.getInstance().query(sql);
    		String dizhi = "未知";
    		/*while (rs.next()){			
    			dizhi = rs.getString("address").replace("CZ88.NET","");
    		}*/
    		for(Row row:list){
    			dizhi = row.getTrimString("address").replace("CZ88.NET","");
    		}
    		return dizhi;
    	}
    	catch(Exception e){
    		System.out.println("error:" + e.getMessage());
    		return "出错";
    	}
    	finally{
    		if (rs != null){
    			try {rs.close();} catch (SQLException e){e.printStackTrace();}
    		}
    		if (pstmt != null){
    			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
    		}
    		if (conn != null){
    			try {conn.close();} catch (SQLException e) {e.printStackTrace();}
    		}
    	}
    }
    
    public static void saveIplog(String ip,String referer,String userAgent){
    	String ipaddress = "";
    	if(ip.startsWith("192.168.")){
    		ipaddress="-";
    	}else{
    		ipaddress=getAddress(ip);
    	}
    	String sql = "insert into t_mk_sys_iplog(fwsj,fwip,ipadress,referer,useragent) values(sysdate,'"+ip+"','"+ipaddress+"','"+referer+"','"+userAgent+"')";
    	try {
			DBUtil.getInstance().execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("=============>>write log err:" + e.getMessage());
			e.printStackTrace();
		}
    }
    public static void main(String[] args){
    	String ip = "71.189.164.218";
    	ip="192.168.52.248";
    	System.out.println(getAddress(ip));
    	System.out.println(getIntFromIp(ip));
    }
}
