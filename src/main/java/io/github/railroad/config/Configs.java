package io.github.railroad.config;

public class Configs {
    public LanguageConfig lang;
    public SyntaxConfig syntax;

    public Configs() {
        lang = new LanguageConfig("/assets/lang/en_us.json");
        syntax = new SyntaxConfig();
    }
}
