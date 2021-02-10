package io.github.railroad.config;

import io.github.railroad.Railroad;
import io.github.railroad.debugger.syntax.EnumSyntaxType;
import io.github.railroad.debugger.syntax.SyntaxObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import static java.nio.file.Files.newDirectoryStream;
import static java.nio.file.Paths.get;
import static java.util.Collections.newSetFromMap;

//TODO: what is even this for?
//TODO: get rid of this
public final class Configuration {

    //TODO: work out what LanguageConfiguration is
    public final LanguageConfiguration language = new LanguageConfiguration("assets/lang/en_us.json");
    public final Set<SyntaxObject> languages = newSetFromMap(new IdentityHashMap<>());

    {
        final Path folder = get("src/main/resources/assets/syntax");
        try (final DirectoryStream<Path> stream = newDirectoryStream(folder, path -> path.getFileName().toString().endsWith(".json"))) {
            for (final Path path : stream) {
                final String name = path.getFileName().toString();
                final JSONTokener tokenizer = new JSONTokener(Railroad.class.getResourceAsStream("/assets/syntax/" + name));
                final JSONObject obj = new JSONObject(tokenizer);
                final JSONArray rules = obj.getJSONArray("rules");

                final Map<String, EnumSyntaxType> ruleMap = new HashMap<>();
                for (int j = 0; j < rules.length(); j++) {
                    final JSONObject rule = rules.getJSONObject(j);
                    ruleMap.put(rule.getString("regex"), EnumSyntaxType.valueOf(rule.getString("type")));
                }

                languages.add(new SyntaxObject(obj.getString("extension"), ruleMap));
            }
        } catch (IOException reason) {
            throw new RuntimeException(reason);
        }
    }
}
