package io.github.railroad.config;

public class Configs {
	
	public static Configs INSTANCE;

	public LanguageConfig lang;
	public SyntaxConfig syntax;

	public Configs() {
		INSTANCE = this;
		this.lang = new LanguageConfig("/assets/lang/en_us.json");
		this.syntax = new SyntaxConfig();
	}
}
