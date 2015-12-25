package com.lyt.pallas.basic.poi.word;

import java.io.File;
import java.io.FileInputStream;

public class WordReader {

	public static String readDoc(String doc) throws Exception {
		FileInputStream in = new FileInputStream(new File(doc));

		// WordExtractor extractor = null;
		String text = null;
		// extractor = new WordExtractor();
		// text = extractor.extractText(in);
		return text;
	}

	public static void main(String[] args) {

		try {
			String text = WordReader.readDoc("D:/test.doc");
			System.out.println(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
