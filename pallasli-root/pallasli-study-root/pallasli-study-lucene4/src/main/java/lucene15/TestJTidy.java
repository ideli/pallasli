package com.beifeng.testjtidy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

public class TestJTidy {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		FileInputStream fis=new FileInputStream(new File("d:/baidu.htm"));
		InputStreamReader isr=new InputStreamReader(fis,"GBK");
		
        FileOutputStream fos=new FileOutputStream(new File("d:/baidu1.htm"));
        Tidy tidy=new Tidy();
        tidy.setXmlTags(true);
        tidy.setMakeClean(true);
        Document doc=tidy.parseDOM(isr, null);
        tidy.pprint(doc, fos);
        fos.close();
        fis.close();
        isr.close();
	}

}
