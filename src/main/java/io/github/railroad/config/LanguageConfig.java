package io.github.railroad.config;

import io.github.railroad.Railroad;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

public class LanguageConfig implements AbstractConfig {

    public Map<String, String> languageTranslator;

    public LanguageConfig(String file) {
        languageTranslator = new HashMap<>();
        final JSONTokener tokenizer = new JSONTokener(Railroad.class.getResourceAsStream(file));
        final JSONObject obj = new JSONObject(tokenizer);
        for (final String key : obj.keySet()) {
            languageTranslator.put(key, obj.getString(key));
        }
    }

    public String get(String key) {
        return languageTranslator.getOrDefault(key, key);
    }

    @Override
    public String name() {
        return "lang";
    }
}
