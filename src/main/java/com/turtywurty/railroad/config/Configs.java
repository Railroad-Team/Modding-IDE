package com.turtywurty.railroad.config;

public class Configs {

	public LanguageConfig lang;

	public Configs() {
		this.lang = new LanguageConfig("/assets/lang/en_us.json");
	}
	
	public LanguageConfig getLang() {
		return this.lang;
	}
	
	public void setLang(LanguageConfig lang) {
		this.lang = lang;
	}
}
