package io.github.railroad.utility;

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
    // TODO: add back the package
    interface JavaTemplate<Type> extends Function<Type, String> {
        BiFunction<String, String, String> GENERATE = (type, label) -> {
            // final Path path = pair.getKey();
            // if (path != null) return format("%s %s; \n\n %s %s %s %s", "package", path, "public", type, label, "{}");
            return format("%s %s %s {\n\t\n}", "public", type, label);
        };

        String CLASS_NAME = "class",
                ENUM_NAME = "enum",
                RECORD_NAME = "record",
                INTERFACE_NAME = "interface",
                ANNOTATION_NAME = "@interface";

        JavaTemplate<String>
                CLASS = label -> GENERATE.apply(CLASS_NAME, label),
                INTERFACE = label -> GENERATE.apply(INTERFACE_NAME, label),
                ENUM = label -> GENERATE.apply(ENUM_NAME, label),
                RECORD = label -> GENERATE.apply(RECORD_NAME, label),
                ANNOTATION = label -> GENERATE.apply(ANNOTATION_NAME, label);
    }

    /**
     * @author Temedy
     */
    interface JsonTemplate<Type> extends Function<Type, String> {
        JsonTemplate<Object> EMPTY = $ -> "{}";
    }
}
