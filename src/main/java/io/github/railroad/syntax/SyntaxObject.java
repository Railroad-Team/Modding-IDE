package io.github.railroad.syntax;

import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.joining;

final class SyntaxObject {
    public final String extension;
    public final String path;
    public final Map<String, String> expressions;
    public final Pattern compiled;

    private SyntaxObject(String extension, String path, Map<String, String> expressions, Pattern compiled) {
        this.extension = extension;
        this.path = path;
        this.expressions = expressions;
        this.compiled = compiled;
    }

    public static SyntaxObject load(String extension, String path, Map<String, String> expressions) {
        return new SyntaxObject(extension, path, expressions,
                compile(expressions.entrySet()
                        .stream()
                        .map(entry -> format("|(?<%s>%s)", entry.getKey(), entry.getValue()))
                        .collect(joining())
                        .substring(1))
        );
    }
}

