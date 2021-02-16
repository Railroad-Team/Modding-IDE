package io.github.railroad.syntax;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import io.github.railroad.Railroad;

public interface SyntaxConfig {
	@SuppressWarnings("serial")
	static final SyntaxObject EMPTY = new SyntaxObject("styles/syntax/none", "null", new HashMap<String, String>() {
		{
			put("(?s).*", "empty");
		}
	});

	// Be careful not to run this every time a file is opened.
	static List<SyntaxObject> initSyntax() {
		File[] allFiles = new File("src/main/resources/assets/syntax").listFiles();
		List<SyntaxObject> languages = new ArrayList<SyntaxObject>();
		// Create Syntax Objects for each language and add them to the list
		for (File currFile : allFiles) {
			if (currFile.getName().substring(currFile.getName().length() - 4).equals("json")) {
				JSONTokener tokenizer = new JSONTokener(
						Railroad.class.getResourceAsStream("/assets/syntax/" + currFile.getName()));

				JSONObject obj = new JSONObject(tokenizer);
				JSONArray rules = obj.getJSONArray("rules");

				Map<String, String> ruleMap = new HashMap<String, String>();
				for (int j = 0; j < rules.length(); j++) {
					JSONObject rule = rules.getJSONObject(j);
					ruleMap.put(rule.getString("regex"), rule.getString("type"));
				}

				languages.add(new SyntaxObject(obj.getString("css_path"), obj.getString("extension"), ruleMap));
			}
		}

		return languages;
	}

	static SyntaxObject getByExt(String ext, List<SyntaxObject> languages) {
		for (SyntaxObject o : languages) {
			if (o.getExt().equals(ext))
				return o;
		}
		return EMPTY;
	}
}
