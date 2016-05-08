package pallasli.jasper.compile;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JRCompiler;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Jxrml2JasperManager {
	public static void run() {
		String path = System.getProperty("user.dir");
		try {
			String sourceFileName = path
					+ "/WebContent/reports/WebappReport.jrxml";
			JasperDesign jasperDesign = JRXmlLoader.load(sourceFileName);
			// String tempDirStr =
			// JRPropertiesUtil.getInstance(jasperReportsContext).getProperty(JRCompiler.COMPILER_TEMP_DIR);
			System.out.println(JRCompiler.COMPILER_TEMP_DIR);
			JasperCompileManager.compileReportToFile(sourceFileName, path
					+ "/WebappReport.jasper");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Jxrml2JasperManager.run();
	}
}
