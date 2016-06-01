package com.mypdfbox.test;

import java.io.IOException;






public class TestRuntime {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
//		Runtime.getRuntime().exec("C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE");
//		Runtime.getRuntime().exec("C:\\Program Files\\Adobe\\Reader 9.0\\Reader\\AcroRd32.exe");
	    XpdfParams xparam=new XpdfParams();
	    xparam.setConvertor("C:\\tmp\\xpdf\\pdftotext");
	    xparam.setEncoding("-enc UTF-8");
	    xparam.setSource("d:\\Exploiting.pdf");
	    xparam.setTarget("d:\\Exploiting.txt");
	    String cmd=xparam.getCMD();
	    Runtime.getRuntime().exec(cmd);
	}

}
