package com.turtywurty.railroad.config;

public class Configs {
	public LanguageConfig lang;
	
	public Configs() {
		// TODO select custom language file, using en_us.json for now.
		this.lang = new LanguageConfig("/assets/lang/en_us.json"); // Yes, this is the same way minecraft does it. Why not?
		
		// TODO theme config, java config, and all of the other settings
	}
}
