package com.mypdfbox.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

public class TestPdfBox {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		PDDocument doc=PDDocument.load("d:/Exploiting.pdf");
		
		int pagenumber=doc.getNumberOfPages();
		System.out.println("pages---"+pagenumber);
		FileOutputStream fos=new FileOutputStream("d:/Exploiting.txt");
		Writer writer=new OutputStreamWriter(fos,"UTF-8");
		PDFTextStripper stripper=new PDFTextStripper();
		
		stripper.setSortByPosition(false);
//		stripper.setWordSeparator("");
		stripper.setStartPage(1);
		stripper.setEndPage(4);
		stripper.writeText(doc, writer);
		writer.close();
		doc.close();
	}

}
