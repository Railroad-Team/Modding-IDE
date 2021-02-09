package io.github.railroad.config;

public class Configs {

    public LanguageConfig lang;
    public SyntaxConfig syntax;

    public Configs() {
        this.lang = new LanguageConfig("/assets/lang/en_us.json");
        this.syntax = new SyntaxConfig();
    }
}
