package com.pallasli.study.ip;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

////node发布web服务
//var http = require('http');
//http.createServer(function (req, res) {
//res.writeHead(200, {'Content-Type': 'text/plain'});
//res.write('remoteAddress: ' + req.connection.remoteAddress + '\n');
//res.write('x-forwarded-for: ' + req.headers['x-forwarded-for'] + '\n');
//res.write('x-real-ip: ' + req.headers['x-real-ip'] + '\n');
//res.end();
//}).listen(9009, '0.0.0.0');
//
//
////nginx反向代理
//location / {
//proxy_set_header X-Real-IP $remote_addr;
//proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
//proxy_set_header Host $http_host;
//proxy_set_header X-NginX-Proxy true;
//proxy_pass http://127.0.0.1:9009/;
//proxy_redirect off;
//}
//
//
//测试直接访问 Node 服务：
//curl http://t1.imququ.com:9009/
//
//remoteAddress: 114.248.238.236
//x-forwarded-for: undefined
//x-real-ip: undefined
//
//再来访问 Nginx 转发过的服务：
//
//curl http://t1.imququ.com/
//
//remoteAddress: 127.0.0.1
//x-forwarded-for: 114.248.238.236
//x-real-ip: 114.248.238.236
//
//
//
//直接访问 Node 服务：
//
//curl http://t1.imququ.com:9009/ -H 'X-Forwarded-For: 1.1.1.1' -H 'X-Real-Ip: 2.2.2.2'
//
//remoteAddress: 114.248.238.236
//x-forwarded-for: 1.1.1.1
//x-real-ip: 2.2.2.2
//
//访问 Nginx 转发过的服务：
//
//curl http://t1.imququ.com/ -H 'X-Forwarded-For: 1.1.1.1' -H 'X-Real-Ip: 2.2.2.2'
//
//remoteAddress: 127.0.0.1
//x-forwarded-for: 1.1.1.1, 114.248.238.236
//x-real-ip: 114.248.238.236
public class IpServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ip = getRemoteAddress(request);
		System.out.println(ip);
		System.out.println(getMACAddress(ip));

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			// InputStreamReader ir = new InputStreamReader(p.getInputStream(),
			// "GB2312");
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;

						// } else if (str.indexOf("MAC 地址") > 1) {
						// macAddress = str.substring(str.indexOf("MAC 地址") + 8,
						// str.length());
						// break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}

}
