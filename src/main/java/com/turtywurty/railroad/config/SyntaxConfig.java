package com.turtywurty.railroad.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.turtywurty.railroad.Railroad;

public class SyntaxConfig extends AbstractConfig {

	public Map<String, Map<String, String>> languages = new HashMap<String, Map<String, String>>();

	@Override
	public String getName() {
		return "syntax";
	}

	public SyntaxConfig() {
		File folder = new File("src/main/resources/assets/syntax");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (getExtensionByStringHandling(listOfFiles[i].getName()).get().equals("json")) {
				JSONTokener tokener = new JSONTokener(
						Railroad.class.getResourceAsStream("assets/syntax" + listOfFiles[i].getName()));

				JSONObject obj = new JSONObject(tokener);
				JSONArray rules = obj.getJSONArray("rules");

				Map<String, String> ruleMap = new HashMap<String, String>();
				for (int j = 0; j < rules.length(); j++) {
					JSONObject rule = rules.getJSONObject(j);
					ruleMap.put(rule.getString("regex"), rule.getString("type"));
				}

				languages.put(obj.getString("extension"), ruleMap);
			}
		}
	}

	public Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

}
