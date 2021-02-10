package io.github.railroad.utility;

import io.github.railroad.platform.SemanticVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortUtils {
    private static final SemanticVersion MINIMAL_VERSION = SemanticVersion.createRelease(1, 12);

    public static <T> List<SemanticVersion> sortVersions(List<T> versions) {
        return sortVersions(versions, T::toString);
    }

    public static <T> List<SemanticVersion> sortVersions(List<T> versions, Function<T, String> convertor) {
        if (versions.isEmpty())
            return new ArrayList<>();

        return versions.stream()
                .map(convertor)
                .map(SemanticVersion::parse)
                .sorted()
                .filter(sv -> sv.compareTo(MINIMAL_VERSION) > 0)
                .collect(Collectors.toList());
    }
}
