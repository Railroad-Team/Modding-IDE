package io.github.railroad.debugger.syntax;

import java.util.Map;
import java.util.regex.Pattern;

//TODO: get rid of this
public class SyntaxObject {
    public String ext;
    public Map<String, EnumSyntaxType> regex;
    public Pattern compiled;

    public SyntaxObject(String e, Map<String, EnumSyntaxType> r) {
        ext = e;
        regex = r;
        compile();
    }

    public void compile() {
        final StringBuilder builder = new StringBuilder();
        for (final Map.Entry<String, EnumSyntaxType> e : regex.entrySet()) {
            builder.append("|(?<").append(e.getValue().name()).append(">").append(e.getKey()).append(")");
        }
        compiled = Pattern.compile(builder.substring(1)); // Remove first "|"
    }

    public boolean hasRegexFor(EnumSyntaxType type) {
        return regex.containsValue(type);
    }
}
