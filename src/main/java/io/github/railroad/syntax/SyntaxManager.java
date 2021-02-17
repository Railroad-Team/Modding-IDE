package io.github.railroad.syntax;

import io.github.railroad.Railroad;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface SyntaxManager {
    SyntaxObject EMPTY = new SyntaxObject("styles/syntax/none", "null", new HashMap<>() {
        {
            put("(?s).*", "empty");
        }
    });

    // Be careful not to run this every time a file is opened.
    static List<SyntaxObject> initSyntax() {
        File[] allFiles = new File("src/main/resources/assets/syntax").listFiles();
        List<SyntaxObject> languages = new ArrayList<>();

        // Create Syntax Objects for each language and add them to the list
        if (allFiles != null) {
            Arrays.stream(allFiles).forEach(currentFile -> {
                if (currentFile.getName().endsWith("json")) {
                    var syntaxResourceStream = Railroad.class.getResourceAsStream("/assets/syntax/" + currentFile.getName());

                    JSONTokener tokenizer = new JSONTokener(Objects.requireNonNull(syntaxResourceStream));

                    var obj = new JSONObject(tokenizer);
                    var rules = obj.getJSONArray("rules");

                    Map<String, String> ruleMap = IntStream.range(0, rules.length())
                            .mapToObj(rules::getJSONObject)
                            .collect(Collectors.toMap(rule -> rule.getString("regex"),
                                    rule -> rule.getString("type"), (a, b) -> b));

                    languages.add(new SyntaxObject(obj.getString("css_path"), obj.getString("extension"), ruleMap));
                }
            });
        }

        return languages;
    }

    static SyntaxObject getLanguageFromExtension(String extension, List<SyntaxObject> languages) {
        return languages.stream()
                .filter(other -> other.extension.equals(extension))
                .findFirst()
                .orElse(EMPTY);
    }
}
