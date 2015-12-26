package lyt.portal.config;

import java.util.List;

import com.pallasli.utils.XmlUtils;

public class Config {
	public static List<CompBean> load(String path) throws Exception {
		return XmlUtils.getObjFromXml(path, CompBean.class);
	}

}
