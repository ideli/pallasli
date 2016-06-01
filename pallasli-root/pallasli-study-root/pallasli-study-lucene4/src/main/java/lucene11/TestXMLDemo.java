package com.beifeng.testxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


public class TestXMLDemo {

	
	public void myXMLReader() throws Exception{
		File file=new File("book.xml");
		SAXReader xreader=new SAXReader();
		Document doc=xreader.read(file);
		
		Element root=doc.getRootElement();
//		Element cbook=root.element("cbook");
////		Attribute cid=cbook.attribute("cid");
////		System.out.println(cid.getText());
//		System.out.println(cbook.attributeValue("cid"));
//        Element newBook=cbook.element("newBook");
////        System.out.println(newBook.getText());
//        Element newBook_cbook=newBook.element("cbook");
//        Element note=newBook_cbook.element("note");
////        System.out.println(note.getText());
		List<Element> list=root.elements("cbook");
		Iterator<Element> it=list.iterator();
		while(it.hasNext()){
			Element tempElement=it.next();
			Element price=tempElement.element("price");
			System.out.println(tempElement.getText()+ " --"+price.getText());
		}
	}
	
	public void myXMLWriter() throws IOException{
		Document doc=DocumentHelper.createDocument();
		Element booklist=doc.addElement("booklist");
		booklist.addComment("写注释操作");
		Element cbook=booklist.addElement("cbook");
		cbook.addText("第一个cbook");
		cbook.addComment("cbook的注释");
		cbook.addAttribute("cid", "0001");
		OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		
		File file=new File("mybook.xml");
		XMLWriter xwriter=new XMLWriter(new FileOutputStream(file),format);
	    xwriter.write(doc);
	    xwriter.close();
	}
	
	public void myXMLXpath() throws DocumentException{
		
		File file=new File("book.xml");
		SAXReader xreader=new SAXReader();
		Document doc=xreader.read(file);
		
		Element root=doc.getRootElement();
		
		List list=root.selectNodes("/booklist/cbook");
		for(int i=0;i<list.size();i++){
			Element node=(Element)list.get(i);
			System.out.println(node.getText());
		}
			
	}
	
	public static void main(String[] args) throws Exception {
		TestXMLDemo txmldemo=new TestXMLDemo();
//		txmldemo.myXMLReader();
//		txmldemo.myXMLWriter();
		txmldemo.myXMLXpath();
	}

}
