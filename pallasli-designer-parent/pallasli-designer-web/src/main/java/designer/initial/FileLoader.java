package designer.initial;

import com.google.gson.Gson;
import com.pallasli.utils.FileUtils;

import designer.bean.Page;
import designer.bean.PageComp;

public class FileLoader {

	static String loadFile(String filepath) {
		return FileUtils.readFileToString(filepath);
	}

	public static void main(String[] a) {
		String pageString = FileLoader
				.loadFile("J:\\pallasli@icloud.com\\designer\\WebContent\\WEB-INF\\design\\testPage.json");
		System.out.println(pageString);
		Page p = new Gson().fromJson(pageString, Page.class);
		System.out.println(p.getF_children().size());
		System.out.println(p.getF_key());
		for (PageComp c : p.getF_children()) {
			System.out.println(c.getF_key());
		}
	}
}
