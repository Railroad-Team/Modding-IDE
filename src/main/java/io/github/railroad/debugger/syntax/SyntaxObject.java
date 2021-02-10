package io.github.railroad.debugger.syntax;

import java.util.Map;
import java.util.regex.Pattern;

public class SyntaxObject {

	private String ext;
	private Map<String, EnumSyntaxType> regex;
	private Pattern compiled;

	public SyntaxObject(String e, Map<String, EnumSyntaxType> r) {
		this.ext = e;
		this.regex = r;
		compile();
	}

	public void compile() {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, EnumSyntaxType> e : this.regex.entrySet()) {
			builder.append("|(?<").append(e.getValue().name()).append(">").append(e.getKey()).append(")"); // If you're going to use append, you gotta commit to it ;)
		}
		this.compiled = Pattern.compile(builder.substring(1)); // Remove first "|"
	}

	public boolean hasRegexFor(EnumSyntaxType type) {
		return regex.containsValue(type);
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Map<String, EnumSyntaxType> getRegex() {
		return regex;
	}

	public void setRegex(Map<String, EnumSyntaxType> regex) {
		this.regex = regex;
	}

	public Pattern getCompiled() {
		return compiled;
	}

	public void setCompiled(Pattern compiled) {
		this.compiled = compiled;
	}

}
