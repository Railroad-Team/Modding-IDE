package io.github.railroad.platform;

import io.github.railroad.platform.versionparts.PreReleasePart;
import io.github.railroad.platform.versionparts.ReleasePart;
import io.github.railroad.platform.versionparts.TextPart;
import io.github.railroad.platform.versionparts.VersionPart;
import io.github.railroad.utility.functional.QuaFunction;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SemanticVersion implements Comparable<SemanticVersion> {
    public static final SemanticVersion ERROR_VERSION_PLACEHOLDER = new SemanticVersion(null, null);
    public static final Map<String, Integer> TEXT_PROPERTIES = Map.of(
            "snapshot", 0,
            "rc", 1,
            "pre", 1
    );
    private static final List<Character> SEPARATORS = List.of('-', '_');

    public final List<VersionPart> parts;
    private final String buildMetadata;
    private final String version = createVersionString();

    public SemanticVersion(List<VersionPart> parts) {
        this(parts, "");
    }

    public SemanticVersion(List<VersionPart> parts, String buildMetadata) {
        this.parts = parts;
        this.buildMetadata = buildMetadata;
    }

    public SemanticVersion take(int count) {
        return new SemanticVersion(parts.subList(0, count - 1));
    }

    public boolean startWith(SemanticVersion other) {
        if (other.parts.size() > parts.size())
            return false;

        for (int i = 0; i < other.parts.size(); i++)
            if (parts.get(i) != other.parts.get(i))
                return false;

        return true;
    }

    private String createVersionString() {
        String mainPart = parts != null
                ? parts.stream()
                .map(VersionPart::toString)
                .collect(Collectors.joining("."))
                : "";

        if (buildMetadata == null)
            return mainPart;
        else if (buildMetadata.isBlank())
            return mainPart;
        else
            return mainPart + "+" + buildMetadata;
    }

    @Override
    public int compareTo(@NotNull SemanticVersion o) {
        return compareListsLexicographically(parts, o.parts);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SemanticVersion) {
            SemanticVersion s = (SemanticVersion) o;
            return parts.size() == s.parts.size() && parts.equals(s.parts);
        } else return false;
    }

    @Override
    public int hashCode() {
        return parts.hashCode();
    }

    @Override
    public String toString() {
        return version;
    }

    public static SemanticVersion createRelease(int... parts) {
        return new SemanticVersion(Arrays.stream(parts)
                .mapToObj(i -> new ReleasePart(i, String.valueOf(i)))
                .collect(Collectors.toList()));
    }

    public static SemanticVersion parse(String value) {
        Function<String, Integer> parseVersionInteger = (part) -> {
            if (part.matches("^[0-9]+$")) return Integer.parseInt(part);
            else
                throw new IllegalArgumentException("Failed to parse version part as integer: " + part + "(version text: " + value + ")");
        };
        QuaFunction<String, String, Character, String, VersionPart> parsePreReleasePart = (versionPart, preReleasePart, separator, version) -> {
            int versionID = parseVersionInteger.apply(versionPart);
            BiFunction<String, Predicate<Character>, Pair<String, String>> spanFunction = (str, predicate) -> {
                String prefix = str.chars()
                        .takeWhile(i -> !predicate.test((char) i))
                        .mapToObj(i -> (char) i)
                        .map(Object::toString)
                        .reduce((acc, c) -> acc + c)
                        .orElse("");

                return new Pair<>(prefix, str.substring(0, prefix.length()));
            };

            if (!preReleasePart.contains(".")) {
                Pair<String, String> textAndNumber = spanFunction.apply(preReleasePart, Character::isDigit);
                String text = textAndNumber.getKey(), number = textAndNumber.getValue();
                List<VersionPart> subParts = text.isEmpty()
                        ? List.of(new ReleasePart(parseVersionInteger.apply(number), number))
                        : textAndNumber.getValue().isEmpty()
                        ? List.of(new TextPart(text))
                        : List.of(new TextPart(text), new ReleasePart(parseVersionInteger.apply(number), text));

                return new PreReleasePart(versionID, separator, subParts, version);
            } else {
                List<VersionPart> subParts = Arrays.stream(preReleasePart.split("\\.")).map(subPart -> {
                    try {
                        int number = Integer.parseInt(subPart);
                        return new ReleasePart(number, subPart);
                    } catch (Exception e) {
                        return new TextPart(subPart);
                    }
                }).collect(Collectors.toList());

                return new PreReleasePart(versionID, separator, subParts, version);
            }
        };

        String[] mainPartAndMetadata = value.split("\\+", 2);
        String mainPart = mainPartAndMetadata[0];
        String metadata = mainPartAndMetadata.length == 1 ? "" : mainPartAndMetadata[1];

        Optional<Character> separator = SEPARATORS.stream().filter(c -> mainPart.contains(c.toString())).findFirst();
        String beforeSeparator;

        if (separator.isPresent()) {
            String regex = String.format("\\%s", separator.get());
            beforeSeparator = mainPart.split(regex)[0];
        } else {
            beforeSeparator = mainPart;
        }

        int partCount = beforeSeparator.length() - beforeSeparator.replace(".", "").length() + 1;
        List<VersionPart> parts = Arrays.stream(mainPart.split("\\.", partCount)).map(part -> {
            if (separator.isPresent() && part.contains(separator.get().toString())) {
                String regex = String.format("\\%s", separator.get());
                String[] subParts = part.split(regex, 2);

                return parsePreReleasePart.apply(subParts[0], subParts[1], separator.get(), part);
            } else {
                String numberPart = part.startsWith("v") ? part.substring(1) : part;

                return new ReleasePart(parseVersionInteger.apply(numberPart), part);
            }
        }).collect(Collectors.toList());

        return new SemanticVersion(parts, metadata);
    }

    public static <T extends Comparable<T>> int compareListsLexicographically(List<T> o1, List<T> o2) {
        Iterator<T> i1 = o1.iterator();
        Iterator<T> i2 = o2.iterator();
        int result;

        do {
            if (!i1.hasNext())
                return !i2.hasNext() ? 0 : -1;

            if (!i2.hasNext())
                return 1;

            result = i1.next().compareTo(i2.next());
        } while (result == 0);

        return result;
    }
}
