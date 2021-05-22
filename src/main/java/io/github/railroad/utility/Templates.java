package io.github.railroad.utility;

import static java.lang.String.format;

import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author Temedy
 */
public interface Templates {

    /**
     * Holds templates for Java object types, such as {@link JavaTemplate#CLASS}, {@link JavaTemplate#INTERFACE}, {@link
     * JavaTemplate#ENUM} or an {@link JavaTemplate#ANNOTATION}
     *
     * @param <Type> the type of the template
     * @author Temedy
     */
    // TODO: add back the package
    interface JavaTemplate<Type> extends Function<Type, String> {
        /**
         * A function that takes the type of Java object and the name of the file, and returns a finished template.
         */
        BinaryOperator<String> GENERATE = (type, label) ->
                format("%s %s %s {%n\t%n}", "public", type, label);

        /**
         * the {@code class} keyword
         */
        String CLASS_NAME = "class";
        /**
         * the {@code enum} keyword
         */
        String ENUM_NAME = "enum";
        /**
         * the {@code record} keyword
         */
        String RECORD_NAME = "record";
        /**
         * the {@code interface} keyword
         */
        String INTERFACE_NAME = "interface";
        /**
         * the {@code @interface} keyword for an annotation
         */
        String ANNOTATION_NAME = "@interface";

        /**
         * The template for the {@code class} Java object type.
         */
        JavaTemplate<String> CLASS = label -> GENERATE.apply(CLASS_NAME, label);
        /**
         * The template for the {@code interface} Java object type.
         */
        JavaTemplate<String> INTERFACE = label -> GENERATE.apply(INTERFACE_NAME, label);
        /**
         * The template for the {@code enum} Java object type.
         */
        JavaTemplate<String> ENUM = label -> GENERATE.apply(ENUM_NAME, label);
        /**
         * The template for the {@code record} Java object type.
         */
        JavaTemplate<String> RECORD = label -> GENERATE.apply(RECORD_NAME, label);
        /**
         * The template for the {@code annotation} Java object type.
         */
        JavaTemplate<String> ANNOTATION = label -> GENERATE.apply(ANNOTATION_NAME, label);
    }

    /**
     * Holds templates for JSON files, such as {@link JsonTemplate#EMPTY}
     *
     * @param <Type> the type of the template.
     * @author Temedy
     */
    interface JsonTemplate<Type> extends Function<Type, String> {
        /**
         * The template for an empty JSON file.
         */
        JsonTemplate<Object> EMPTY = label -> "{\n\t\n}";
    }
}
