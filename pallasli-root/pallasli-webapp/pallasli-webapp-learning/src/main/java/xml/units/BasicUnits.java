package xml.units;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import physics.app.model.BasicUnit;

import com.pallasli.utils.StringUtils;

public class BasicUnits {
	public List<BasicUnit> getBasicUnitsFromXML() {
		List<BasicUnit> rtnList = new ArrayList<BasicUnit>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath()
					+ "basicUnits.xml";
			path = path.substring(1).replace("%20", " ");
			document = builder.parse(new File(path));
			NodeList nodeList = document.getElementsByTagName("basicUnit");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element selElement = (Element) nodeList.item(i);
				NodeList childList = selElement.getChildNodes();
				BasicUnit bean = new BasicUnit();
				// bean.setId(selElement.getAttribute("id"));
				for (int j = 0; j < childList.getLength(); j++) {
					Node childElement = childList.item(j);
					if (childElement.getNodeType() == Node.ELEMENT_NODE) {
						String beanVarName = childElement.getNodeName();
						String methodname = StringUtils
								.parseToSetField(beanVarName);
						Method method = null;
						method = bean.getClass().getDeclaredMethod(methodname,
								new Class<?>[] { new String().getClass() });
						method.invoke(bean,
								new Object[] { childElement.getTextContent() });

					}

				}
				rtnList.add(bean);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return rtnList;
	}

	public Boolean save(BasicUnit bu) {
		boolean flag = false;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;

		try {
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath()
					+ "basicUnits.xml";
			path = path.substring(1).replace("%20", " ");
			document = builder.parse(new File(path));
			NodeList nodeList = document.getElementsByTagName("basicUnit");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Element selElement = (Element) nodeList.item(i);
				String id = selElement.getAttribute("id");

				if (id.equals(bu.getId())) {
					NodeList childList = selElement.getChildNodes();
					for (int j = 0; j < childList.getLength(); j++) {
						Node childElement = childList.item(j);

						if (childElement.getNodeType() == Node.ELEMENT_NODE) {
							String beanVarName = childElement.getNodeName();
							String methodname = StringUtils
									.parseToGetField(beanVarName);
							Method method = null;
							method = bu.getClass().getDeclaredMethod(
									methodname, new Class<?>[] {});

							childElement.setTextContent((String) method.invoke(
									bu, new Object[] {}));
						}
					}
				}
			}
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			tf.transform(new DOMSource(document),
					new StreamResult(new FileOutputStream(new File(this
							.getClass().getResource("/").getPath()
							+ "basicUnit.xml"))));
			flag = true;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return flag;

	}
}
