package io.github.railroad.objects;

import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.String.format;
import static java.lang.reflect.Modifier.PUBLIC;

/**
 * @author TheOnlyTails
 */
interface ClassType extends Function<Path, String>, Supplier<String> {
    ClassType CLASS = create("class");
    ClassType INTERFACE = create("interface");
    ClassType ENUM = create("enum");
    ClassType RECORD = create("record");

    static ClassType create(String string) {
        return new ClassType() {
            @Override
            public String apply(Path path) {
                return format("%s %s %s {\n \t\n}", Modifier.toString(PUBLIC), string, path.getFileName());
            }

            @Override
            public String get() {
                return string;
            }
        };
    }
}