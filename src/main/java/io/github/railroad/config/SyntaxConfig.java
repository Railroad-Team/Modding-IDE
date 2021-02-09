package io.github.railroad.config;

import io.github.railroad.Railroad;
import io.github.railroad.debugger.syntax.EnumSyntaxType;
import io.github.railroad.debugger.syntax.SyntaxObject;
import io.github.railroad.utility.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyntaxConfig extends AbstractConfig {

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
        for (File file : listOfFiles) {
            if (FileUtils.getExtention(file.getName()).get().equals("json")) {
                JSONTokener tokener = new JSONTokener(
                        Railroad.class.getResourceAsStream("/assets/syntax/" + file.getName()));

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

    @Override
    public String getName() {
        return "syntax";
    }

    public SyntaxObject getByExt(String ext) {
        for (SyntaxObject o : this.languages) {
            if (o.getExt().equals(ext))
                return o;
        }
        return EMPTY;
    }
}
