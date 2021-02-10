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

public class SyntaxConfig implements AbstractConfig {
    public static final SyntaxObject EMPTY = new SyntaxObject("null", new HashMap<>() {
        {
            put("(?s).*", EnumSyntaxType.ELSE);
        }
    });
    public List<SyntaxObject> languages = new ArrayList<>();

    public SyntaxConfig() {
        // Get all languages
        File folder = new File("src/main/resources/assets/syntax");
        File[] listOfFiles = folder.listFiles();

        // Create Syntax Objects for each language and add them to the list
        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                Optional<String> optional = FileUtils.getExtension(listOfFile.getName());
                if (optional.isEmpty()) {
                    //TODO: Do something here
                    break;
                }
                if (optional.get().equals("json")) {
                    JSONTokener tokenizer = new JSONTokener(
                            Railroad.class.getResourceAsStream("/assets/syntax/" + listOfFile.getName()));

                    JSONObject obj = new JSONObject(tokenizer);
                    JSONArray rules = obj.getJSONArray("rules");

                    Map<String, EnumSyntaxType> ruleMap = new HashMap<>();
                    for (int j = 0; j < rules.length(); j++) {
                        JSONObject rule = rules.getJSONObject(j);
                        ruleMap.put(rule.getString("regex"), EnumSyntaxType.valueOf(rule.getString("type")));
                    }

                    languages.add(new SyntaxObject(obj.getString("extension"), ruleMap));
                }
            }
        }
    }

    @Override
    public String getName() {
        return "syntax";
    }

    public SyntaxObject getByExt(String ext) {
        for (SyntaxObject o : this.languages) {
            if (o.ext.equals(ext))
                return o;
        }

        return EMPTY;
    }
}
