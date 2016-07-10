package com.pallasli.study.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

public class JAXBXmlUtil {
	public static String toXML(Object obj) {
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXML(String xml, Class<T> valueType) {
		try {
			JAXBContext context = JAXBContext.newInstance(valueType);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 这里要注意以下几点
	 * 
	 * 1.要序列化的类加上 @XmlRootElement注解，否则会报错(错误提示很清晰，这里就不贴出来了)
	 * 
	 * 2.JAXB序列化XML时 默认序列化getter和setter，且getter和setter必须成对出现才会被序列化
	 * 
	 * 3.属性名称，默认序列化出来的类和属性名称默认是首字母转换为小写，
	 * 若需要控制属性名称需要在getter或setter上使用 @XmlElement( name="ClassAId")
	 * 指定名称，这里要注意的是@XmlElement放置在getter或setter上都行，但只能放一个，
	 * 也就是说不能同时在getter和setter上使用@XmlElement注解
	 * 
	 * 4.如何控制根节点名称？ 使用@XmlRootElement指定name属性即可，如@XmlRootElement(name="ClassA")
	 * 
	 * 5.怎么添加命名空间 使用@XmlRootElement(namespace="cn.lzrabbit") 指定namespace属性
	 * 
	 * 6.怎么精确控制每个属性名称 JAXB自动转化为首字母小写会导致不可预料的属性名称出现，
	 * 不嫌麻烦的话为每个属性设置@XmlElement(name="")，想省事的话使用Field
	 * 
	 * 7.怎么样实现序列化时使用Field字段而不是使用setter和getter
	 * 在要使用的类上面加上@XmlAccessorType(XmlAccessType.FIELD)注解，并指定为XmlAccessType.FIELD
	 * ，这里强烈推荐使用@XmlAccessorType(XmlAccessType.FIELD)注解，因为这样你可以精确的控制每个元素的名称，
	 * 而不需要为每个属性去设置@XmlElement(name="")注解，当然也可以在Field上使用@XmlElement注解
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		ClassB classB = new ClassB();
		classB.setClassBId(22);
		classB.setClassBName("B2");

		ClassA classA = new ClassA();
		classA.setClassAId(11);
		classA.setClassAName("A1");
		classA.setClassB(classB);

		System.out.println(JAXBXmlUtil.toXML(classA));
	}

}

@XmlRootElement(namespace = "cn.lzrabbit")
@XmlAccessorType(XmlAccessType.FIELD)
class ClassA {
	private int classAId;
	private String classAName;

	private ClassB classB;

	public int getClassAId() {
		return classAId;
	}

	public void setClassAId(int classAId) {
		this.classAId = classAId;
	}

	public String getClassAName() {
		return classAName;
	}

	public void setClassAName(String classAName) {
		this.classAName = classAName;
	}

	public ClassB getClassB() {
		return classB;
	}

	public void setClassB(ClassB classB) {
		this.classB = classB;
	}
}

@XmlRootElement(namespace = "cn.lzrabbit")
@XmlAccessorType(XmlAccessType.FIELD)
class ClassB {
	private int classBId;
	private String classBName;

	public int getClassBId() {
		return classBId;
	}

	public void setClassBId(int classBId) {
		this.classBId = classBId;
	}

	public String getClassBName() {
		return classBName;
	}

	public void setClassBName(String classBName) {
		this.classBName = classBName;
	}
}
