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

        StringBuilder builder = new StringBuilder();

        this.regex.forEach((key, value) ->
                builder.append("|(?<").append(value).append(">").append(key).append(")")
        );

        this.compiled = Pattern.compile(builder.substring(1)); // Remove first "|"
    }
}
