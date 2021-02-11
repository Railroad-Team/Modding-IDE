package io.github.railroad.utility;

import javafx.util.Pair;

import java.nio.file.Path;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.String.format;

/**
 * @author Temedy
 */
public interface Templates {

    /**
     * @param <Type> the type of the template
     * @author Temedy
     */
    interface JavaTemplate<Type> extends Function<Type, String>, Supplier<String> {
        String CLASS_NAME = "class",
                ENUM_NAME = "enum",
                RECORD_NAME = "record",
                INTERFACE_NAME = "interface",
                ANNOTATION_NAME = "@interface";

        BiFunction<String, Pair<Path, String>, String> GENERATED = (type, pair) -> {
            final Path path = pair.getKey();
            final String label = pair.getValue();
            if (path != null)
                return format("%s %s \n\n %s %s %s %s", "package", path, "public", type, label, "{}");
            return format("%s %s %s %s", "public", type, label, "{}");
        };

        JavaTemplate<Pair<Path, String>> CLASS = createTemplate(CLASS_NAME, pair -> GENERATED.apply(CLASS_NAME, pair));
        JavaTemplate<Pair<Path, String>> INTERFACE = createTemplate(INTERFACE_NAME, pair -> GENERATED.apply(INTERFACE_NAME, pair));
        JavaTemplate<Pair<Path, String>> ENUM = createTemplate(ENUM_NAME, pair -> GENERATED.apply(ENUM_NAME, pair));
        JavaTemplate<Pair<Path, String>> RECORD = createTemplate(RECORD_NAME, pair -> GENERATED.apply(RECORD_NAME, pair));
        JavaTemplate<Pair<Path, String>> ANNOTATION = createTemplate(ANNOTATION_NAME, pair -> GENERATED.apply(ANNOTATION_NAME, pair));

        static <Type> JavaTemplate<Type> createTemplate(String string, Function<Type, String> function) {
            return new JavaTemplate<>() {
                @Override
                public String get() {
                    return string;
                }

                @Override
                public String apply(Type type) {
                    return function.apply(type);
                }
            };
        }
    }

    interface JsonTemplate<Type> extends Function<Type, String> {
        JsonTemplate<Void> EMPTY = type -> "{}";
    }
}
