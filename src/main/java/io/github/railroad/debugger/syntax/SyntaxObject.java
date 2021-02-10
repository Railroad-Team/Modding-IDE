package io.github.railroad.debugger.syntax;

import java.util.Map;
import java.util.regex.Pattern;

public class SyntaxObject {
    public String ext;
    public Map<String, EnumSyntaxType> regex;
    public Pattern compiled;

    public SyntaxObject(String e, Map<String, EnumSyntaxType> r) {
        this.ext = e;
        this.regex = r;
        compile();
    }

    public void compile() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, EnumSyntaxType> e : this.regex.entrySet()) {
            builder.append("|(?<").append(e.getValue().name()).append(">").append(e.getKey()).append(")");
        }
        this.compiled = Pattern.compile(builder.substring(1)); // Remove first "|"
    }

    public boolean hasRegexFor(EnumSyntaxType type) {
        return regex.containsValue(type);
    }
}
