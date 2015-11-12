package com.bpm.service.test;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.IOException;

import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.groovy.control.CompilationFailedException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class GroovyRuleTest {
	Log log = LogFactory.getLog(getClass());
	GroovyClassLoader loader = new GroovyClassLoader(getClass()
			.getClassLoader());

	@Test
	public void testFindGroovyRules() {

		MatcherAssert.assertThat(2, CoreMatchers.not(3));
		MatcherAssert.assertThat(2, CoreMatchers.is(2));
		MatcherAssert.assertThat(2, CoreMatchers.is(CoreMatchers.not(3)));
		// "create table act_re_rule(id varchar(16),rule_type varchar(2),caption varchar(50),scripts text )";

		// 可以在Service中把创建表的sql拼接好，
		// DAO中这样写：
		// public int executeCreateTableSql(String sql){
		// Map<String , Object> map=new HashMap<String , Object>();
		// map.put("sql",sql);
		// return this.update("XX.createTableSql",map);
		// }
		// mapper中这样写:
		// <update id="createTableSql" parameterType="map">
		// ${sql}
		// </update>
		File f = new File(
				"J:/bpm-test/src/test/resources/groovyRules/上级领导.groovy");

		if (!f.exists()) {
			log.error("not found groovy " + f.getPath());
			return;
		} else {
			// #debug
			log.info("run groovy " + f.getPath());
		}
		Class groovyClass;
		try {
			groovyClass = loader.parseClass(new GroovyCodeSource(f));
			GroovyObject object = (GroovyObject) groovyClass.newInstance();
			object.setProperty("xxx", "abc");// 设置的自定义参数
			object.setProperty("yyy", 322);// 设置的自定义参数
			System.out.println(object.invokeMethod("run", null));

			Binding binding = new Binding();
			binding.setVariable("foo", new Integer(2));
			GroovyShell shell = new GroovyShell(binding);
			String script = "import org.apache.commons.lang.StringUtils; \t\r\n"
					+ "def a = 12; println 'C# md5:' + StringUtils.indexOf(\"dds\", 'd'); "
					+ "return foo * a ";
			Object value = shell.evaluate(script);
			System.out.println(value.toString());

			UserEntity to = new UserEntity();
			to.setId("11111");
			to.setFirstName("xxxxxx");
			binding = new Binding();
			binding.setVariable("to", to);
			shell = new GroovyShell(binding);
			script = "import org.activiti.engine.impl.persistence.entity.UserEntity; "
					+ "def getTitle(UserEntity to) { return to.getFirstName()}; "
					+ "getTitle(to)";
			value = shell.evaluate(script);
			System.out.println(value.toString());
		} catch (CompilationFailedException | InstantiationException
				| IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
	}
}
