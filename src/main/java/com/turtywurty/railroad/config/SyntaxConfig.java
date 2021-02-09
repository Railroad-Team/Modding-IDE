package com.turtywurty.railroad.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.turtywurty.railroad.Railroad;
import com.turtywurty.railroad.debugger.syntax.EnumSyntaxType;
import com.turtywurty.railroad.debugger.syntax.SyntaxObject;
import com.turtywurty.railroad.util.FileUtils;

public class SyntaxConfig extends AbstractConfig {

	public List<SyntaxObject> languages = new ArrayList<SyntaxObject>();

	@SuppressWarnings("serial")
	public static final SyntaxObject EMPTY = new SyntaxObject("null", new HashMap<String, EnumSyntaxType>() {
		{
			put("(?s).*", EnumSyntaxType.ELSE);
		}
	});

	@Override
	public String getName() {
		return "syntax";
	}

	public SyntaxConfig() {
		// Get all languages
		File folder = new File("src/main/resources/assets/syntax");
		File[] listOfFiles = folder.listFiles();

		// Create Syntax Objects for each language and add them to the list
		for (int i = 0; i < listOfFiles.length; i++) {
			if (FileUtils.getExtention(listOfFiles[i].getName()).get().equals("json")) {
				JSONTokener tokener = new JSONTokener(
						Railroad.class.getResourceAsStream("/assets/syntax/" + listOfFiles[i].getName()));

				JSONObject obj = new JSONObject(tokener);
				JSONArray rules = obj.getJSONArray("rules");

				Map<String, EnumSyntaxType> ruleMap = new HashMap<String, EnumSyntaxType>();
				for (int j = 0; j < rules.length(); j++) {
					JSONObject rule = rules.getJSONObject(j);
					ruleMap.put(rule.getString("regex"), EnumSyntaxType.valueOf(rule.getString("type")));
				}

				languages.add(new SyntaxObject(obj.getString("extension"), ruleMap));
			}
		}
	}

	public SyntaxObject getByExt(String ext) {
		for (SyntaxObject o : this.languages) {
			if (o.getExt().equals(ext))
				return o;
		}
		return EMPTY;
	}
}
