package com.myhttpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestHttpClient {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		HttpClient httpClient=new DefaultHttpClient();
		HttpGet httpGet=new HttpGet("http://www.baidu.com/s?wd=httpClient");
		HttpResponse response=httpClient.execute(httpGet);
		HttpEntity entity=response.getEntity();
		
		InputStream ins=entity.getContent();
		BufferedReader reader=new BufferedReader(new InputStreamReader(ins));
		FileWriter writer=new FileWriter(new File("d:/baidu.htm"));
		String strLine=reader.readLine();
		while(strLine!=null){
			writer.write(strLine);
			strLine=reader.readLine();
		}
		writer.close();
		ins.close();
		reader.close();
		httpClient.getConnectionManager().shutdown();
		System.out.println("页面生成完毕！");
	}

}
