package com.pallas.sys.dispatch.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ContextVariablesContainer {
	private static ContextVariablesContainer singleton;
	private final Map<String, IContextVariablesParser> parsers;
	private Map<String, String> variables;

	public ContextVariablesContainer() {
		this.parsers = new HashMap<String, IContextVariablesParser>();
	}

	public static ContextVariablesContainer instance() {
		if (singleton == null) {
			// singleton =
			// (ContextVariablesContainer)ContextHolder.instance().getBean("mixky-contextvarialbes-container");
			if (singleton == null) {
				singleton = new ContextVariablesContainer();
			}
		}
		return singleton;
	}

	public void setParsers(
			Map<String, List<IContextVariablesParser>> parsersList) {
		if (parsersList != null) {
			Iterator<String> keys = parsersList.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				this.parsers.put(key,
						(IContextVariablesParser) ((List<?>) parsersList
								.get(key)).get(0));
			}
		}
	}

	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	public String replaceContextVariables(String source) {
		String replacedString = "";
		if ((source != null) && (!(source.equals("")))) {
			Iterator<String> keys;
			replacedString = replaceSystemParameter(source);

			if ((this.variables != null) && (this.variables.size() > 0)) {
				keys = this.variables.keySet().iterator();
				while (keys.hasNext()) {
					String key = keys.next();
					if ((key != null) && (!(key.equals(""))))
						replacedString = replacedString.replaceAll("\\|" + key
								+ "\\|", this.variables.get(key));
				}
			}
		}

		return replacedString;
	}

	private String replaceSystemParameter(String source) {
		if (source == null)
			return null;

		source = replaceCommonParameters(source);
		return source;
	}

	private String replaceCommonParameters(String source) {
		return source;
	}
}