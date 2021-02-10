package io.github.railroad.debugger.syntax;

import java.util.Map;
import java.util.regex.Pattern;

public class SyntaxObject {

	private String ext;
	private String path;
	private Map<String, String> regex;
	private Pattern compiled;

	public SyntaxObject(String path, String e, Map<String, String> r) {
		this.ext = e;
		this.regex = r;
		this.setPath(path);
		compile();
	}

	public void compile() {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> e : this.regex.entrySet()) {
			builder.append("|(?<").append(e.getValue()).append(">").append(e.getKey()).append(")");
		}
		this.compiled = Pattern.compile(builder.substring(1)); // Remove first "|"
	}

	public boolean hasRegexFor(String type) {
		return regex.containsValue(type);
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Map<String, String> getRegex() {
		return regex;
	}

	public void setRegex(Map<String, String> regex) {
		this.regex = regex;
	}

	public Pattern getCompiled() {
		return compiled;
	}

	public void setCompiled(Pattern compiled) {
		this.compiled = compiled;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
