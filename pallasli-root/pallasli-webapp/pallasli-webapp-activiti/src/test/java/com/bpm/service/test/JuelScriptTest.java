package com.bpm.service.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.activiti.engine.impl.scripting.JuelScriptEngineFactory;
import org.junit.Test;

public class JuelScriptTest {
	@Test
	public void test() {

		ScriptEngineManager manager = new ScriptEngineManager();
		JuelScriptEngineFactory factory = new JuelScriptEngineFactory();
		ScriptEngine engine = manager.getEngineByName(factory.getEngineName());

		engine.put("a", 4);
		try {
			Object ret = engine.eval("${a==1}");
			System.out.println(ret);
		} catch (ScriptException e) {
			e.printStackTrace();
		}

	}
}
