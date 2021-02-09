package com.turtywurty.railroad.config;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.turtywurty.railroad.Railroad;

public class LanguageConfig extends AbstractConfig {

	private Map<String, String> languageTranslator;

	public LanguageConfig(String file) {
		languageTranslator = new HashMap<String, String>();
		JSONTokener tokener = new JSONTokener(Railroad.class.getResourceAsStream(file));
		JSONObject obj = new JSONObject(tokener);
		for (String key : obj.keySet()) {
			languageTranslator.put(key, obj.getString(key));
		}
	}

	public Map<String, String> getLanguageTranslator() {
		return languageTranslator;
	}

	public void setLanguageTranslator(Map<String, String> languageTranslator) {
		this.languageTranslator = languageTranslator;
	}

	public String get(String key) {
		return languageTranslator.getOrDefault(key, key);
	}

	@Override
	public String getName() {
		return "lang";
	}
}
