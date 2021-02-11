package io.github.railroad.utility;

import javafx.util.Pair;

import java.nio.file.Path;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.String.format;

/**
 * @author Temedy
 */
public interface Templates {

    /**
     * @param <Type> the type of the template
     * @author Temedy
     */
    interface JavaTemplate<Type> extends Function<Type, String> {
        BiFunction<String, Pair<Path, String>, String> GENERATE = (type, pair) -> {
            final Path path = pair.getKey();
            final String label = pair.getValue();
            if (path != null) return format("%s %s \n\n %s %s %s %s", "package", path, "public", type, label, "{}");
            return format("%s %s %s %s", "public", type, label, "{}");
        };

        String CLASS_NAME = "class",
                ENUM_NAME = "enum",
                RECORD_NAME = "record",
                INTERFACE_NAME = "interface",
                ANNOTATION_NAME = "@interface";

        JavaTemplate<Pair<Path, String>> CLASS = pair -> GENERATE.apply(CLASS_NAME, pair);
        JavaTemplate<Pair<Path, String>> INTERFACE = pair -> GENERATE.apply(INTERFACE_NAME, pair);
        JavaTemplate<Pair<Path, String>> ENUM = pair -> GENERATE.apply(ENUM_NAME, pair);
        JavaTemplate<Pair<Path, String>> RECORD = pair -> GENERATE.apply(RECORD_NAME, pair);
        JavaTemplate<Pair<Path, String>> ANNOTATION = pair -> GENERATE.apply(ANNOTATION_NAME, pair);

        interface JsonTemplate<Type> extends Function<Type, String> {
            JsonTemplate<Object> EMPTY = $ -> "{}";
        }
    }
}
