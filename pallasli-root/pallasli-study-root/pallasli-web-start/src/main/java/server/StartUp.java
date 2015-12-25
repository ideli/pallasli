package server;

import com.pallasli.core.constant.PropertiesConstants;
import com.pallasli.core.properties.PropertiesFactory;
import com.pallasli.core.properties.PropertiesHelper;
import com.pallasli.core.server.JettyServer;

public class StartUp {
	public static void main(String[] args) {
		PropertiesHelper pHelper = PropertiesFactory
				.getPropertiesHelper(PropertiesConstants.JETTYSERVER);
		JettyServer server = new JettyServer(
				pHelper.getValue("server.WebContext"), new Integer(
						pHelper.getValue("server.WebPort")));
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
