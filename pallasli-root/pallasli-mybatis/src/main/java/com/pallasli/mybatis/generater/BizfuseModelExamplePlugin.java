package com.pallasli.mybatis.generater;


import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

public class BizfuseModelExamplePlugin extends PluginAdapter {
	private static String[] fieldsToRemove = { "orderByClause", "oredCriteria" };
	private static String[] methodsToRemove = { "setOrderByClause",
			"getOrderByClause", "getOredCriteria" };
	private static FullyQualifiedJavaType exampleBaseClass;

	protected FullyQualifiedJavaType getExampleBaseClass() {
		if ((exampleBaseClass == null)
				&& (StringUtility.stringHasValue(this.properties
						.getProperty("exampleBaseClass"))))
			exampleBaseClass = new FullyQualifiedJavaType(
					this.properties.getProperty("exampleBaseClass"));

		return exampleBaseClass;
	}

	protected Attribute findAttribute(List<Attribute> attributes, String name) {
		for (Attribute attribute : attributes) {
			if (!(attribute.getName().equalsIgnoreCase(name)))
				continue;
			return attribute;
		}
		return null;
	}

	protected boolean removeXmlAttribute(XmlElement element, String name) {
		List<Attribute> attributes = element.getAttributes();
		label51: for (Attribute attribute : attributes) {
			if (!(attribute.getName().equalsIgnoreCase(name)))
				break label51;
			attributes.remove(attribute);
			return true;
		}

		return false;
	}

	protected void setXmlAttribute(XmlElement element, String name, String value) {
		if (removeXmlAttribute(element, name))
			element.addAttribute(new Attribute(name, value));
	}

	@Override
	public boolean validate(List<String> warnings) {
		boolean valid = true;

		if (!(StringUtility.stringHasValue(this.properties
				.getProperty("exampleBaseClass")))) {
			warnings.add(Messages.getString("ValidationError.18",
					"BizfuseModelExamplePlugin", "exampleBaseClass"));
			valid = false;
		}

		return valid;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				this.context.getJavaModelGeneratorConfiguration()
						.getTargetPackage())
				.append(".example.")
				.append(introspectedTable.getFullyQualifiedTable()
						.getDomainObjectName()).append("Example");

		introspectedTable.setExampleType(sb.toString());
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType fqjt = getExampleBaseClass();
		topLevelClass.setSuperClass(fqjt);
		topLevelClass.addImportedType(fqjt);

		List fields = topLevelClass.getFields();
		int i = 0;
		while (i < fields.size()) {
			Field field = (Field) fields.get(i);
			if (ArrayUtils.contains(fieldsToRemove, field.getName())) {
				fields.remove(i);
			} else
				++i;
		}

		List methods = topLevelClass.getMethods();
		i = 0;
		while (i < methods.size()) {
			Method method = (Method) methods.get(i);
			if (ArrayUtils.contains(methodsToRemove, method.getName())) {
				methods.remove(method);
			} else if (method.isConstructor()) {
				methods.remove(method);
			} else
				++i;
		}

		return true;
	}

	@Override
	public boolean sqlMapCountByExampleElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		setXmlAttribute(element, "parameterClass", getExampleBaseClass()
				.getFullyQualifiedName());
		return true;
	}

	@Override
	public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		setXmlAttribute(element, "parameterClass", getExampleBaseClass()
				.getFullyQualifiedName());
		return true;
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		setXmlAttribute(element, "parameterClass", getExampleBaseClass()
				.getFullyQualifiedName());
		return true;
	}

	@Override
	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		setXmlAttribute(element, "parameterClass", getExampleBaseClass()
				.getFullyQualifiedName());
		return true;
	}

	@Override
	public boolean sqlMapUpdateByExampleSelectiveElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		setXmlAttribute(element, "parameterClass", getExampleBaseClass()
				.getFullyQualifiedName());
		return true;
	}

	@Override
	public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		setXmlAttribute(element, "parameterClass", getExampleBaseClass()
				.getFullyQualifiedName());
		return true;
	}

	@Override
	public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		setXmlAttribute(element, "parameterClass", getExampleBaseClass()
				.getFullyQualifiedName());
		return true;
	}
}