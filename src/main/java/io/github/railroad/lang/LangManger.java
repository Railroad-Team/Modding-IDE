package io.github.railroad.lang;

import io.github.railroad.Railroad;
import org.json.JSONObject;
import org.json.JSONTokener;

public interface LangManger {
    @SuppressWarnings("ConstantConditions")
    JSONObject ENGLISH = new JSONObject(new JSONTokener(Railroad.class.getResourceAsStream("/assets/lang/en_us.json")));

    static String getLocalization(String localizationString, JSONObject localization) {
        return localization.getString(localizationString);
    }
}
