package io.github.railroad.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import io.github.railroad.Railroad;
import io.github.railroad.debugger.syntax.SyntaxObject;
import io.github.railroad.utility.FileUtils;

public class SyntaxConfig extends AbstractConfig {

	public List<SyntaxObject> languages = new ArrayList<SyntaxObject>();

	@SuppressWarnings("serial")
	public static final SyntaxObject EMPTY = new SyntaxObject("styles/syntax/none", "null",
			new HashMap<String, String>() {
				{
					put("(?s).*", "empty");
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
		for (File listOfFile : listOfFiles) {
			Optional<String> optional = FileUtils.getExtension(listOfFile.getName());
			if (!optional.isPresent()) {
				// TODO: Do something here
				break;
			}
			if (optional.get().equals("json")) {
				JSONTokener tokenizer = new JSONTokener(
						Railroad.class.getResourceAsStream("/assets/syntax/" + listOfFile.getName()));

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
	}

	public SyntaxObject getByExt(String ext) {
		for (SyntaxObject o : this.languages) {
			if (o.getExt().equals(ext))
				return o;
		}
		return EMPTY;
	}
}
