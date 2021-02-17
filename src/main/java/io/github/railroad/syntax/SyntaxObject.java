package io.github.railroad.syntax;

import java.util.Map;
import java.util.regex.Pattern;

public class SyntaxObject {
    public final String extension;
    public final String path;
    public final Map<String, String> regex;
    public Pattern compiled;

    public SyntaxObject(String path, String extension, Map<String, String> regex) {
        this.extension = extension;
        this.regex = regex;
        this.path = path;
        compile();
    }

    public void compile() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> e : this.regex.entrySet()) {
            builder.append("|(?<").append(e.getValue()).append(">").append(e.getKey()).append(")");
        }
        this.compiled = Pattern.compile(builder.substring(1)); // Remove first "|"
    }
}
