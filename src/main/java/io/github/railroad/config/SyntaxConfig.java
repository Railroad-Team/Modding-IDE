package io.github.railroad.config;

import io.github.railroad.Railroad;
import io.github.railroad.debugger.syntax.EnumSyntaxType;
import io.github.railroad.debugger.syntax.SyntaxObject;
import io.github.railroad.utility.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.util.*;

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
