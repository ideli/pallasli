package com.bpm.service.test;

import java.util.List;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class JavaScriptTest {

	public interface JSLib {
		public int max_num(int a, int b);
	}

	@Test
	public void test() {
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> factories = manager.getEngineFactories();
		for (ScriptEngineFactory f : factories) {
			System.out.println("egine name:" + f.getEngineName()
					+ ",engine version:" + f.getEngineVersion()
					+ ",language name:" + f.getLanguageName()
					+ ",language version:" + f.getLanguageVersion() + ",names:"
					+ f.getNames() + ",mime:" + f.getMimeTypes()
					+ ",extension:" + f.getExtensions());
		}

		ScriptEngine engine = manager.getEngineByName("js");
		String script = "println ('hello script')";
		try {
			engine.eval(script);
		} catch (ScriptException e) {
			e.printStackTrace();
		}

		// 静态执行脚本
		engine.put("a", 4);
		engine.put("b", 6);
		try {
			Object maxNum = engine
					.eval("function max_num(a,b){return (a>b)?a:b;}  max_num(a,b);  ");
			System.out.println("max_num:" + maxNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 动态调用脚本方法
		try {
			engine.eval("function max_num(a,b){return (a>b)?a:b;}");
			Invocable invoke = (Invocable) engine;
			Object maxNum = invoke.invokeFunction("max_num", 4, 6);
			System.out.println(maxNum);
			maxNum = invoke.invokeFunction("max_num", 7, 6);
			System.out.println(maxNum);
		} catch (Exception e) {

		}

		// 定义脚本接口，动态脚本实现接口
		try {
			engine.eval("function max_num(a,b){return (a>b)?a:b;}");
			Invocable invoke = (Invocable) engine;
			JSLib jslib = invoke.getInterface(JSLib.class);
			int maxNum = jslib.max_num(4, 6);
			System.out.println(maxNum);
		} catch (Exception e) { // TODO: handle exception

		}

		// 脚本中使用java对象
		try {
			script = "var list = java.util.ArrayList();list.add(\"kafka0102\");println(list.get(0));";
			engine.eval(script);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 编译执行；脚本引擎默许是诠释履行的，若是需要频频执行脚本，可使用它的可选接口Compilable来编译执行脚本，以取得更好的机能
		try {
			Compilable compEngine = (Compilable) engine;
			CompiledScript script2 = compEngine
					.compile("function max_num(a,b){return (a>b)?a:b;}");
			script2.eval();
			Invocable invoke = (Invocable) engine;
			Object maxNum = invoke.invokeFunction("max_num", 4, 6);
			System.out.println(maxNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
