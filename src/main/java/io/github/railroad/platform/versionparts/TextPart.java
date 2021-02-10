package io.github.railroad.platform.versionparts;

import io.github.railroad.platform.SemanticVersion;
import org.jetbrains.annotations.NotNull;

public class TextPart extends VersionPart {
    private final int priority = SemanticVersion.TEXT_PROPERTIES.getOrDefault(version, -1);

    public TextPart(String version) {
        super(version);
    }

    @Override
    public int compareTo(@NotNull VersionPart o) {
        if (o instanceof ReleasePart) return -1;
        else if (o instanceof PreReleasePart) return -1;
        else if (o instanceof TextPart) {
            TextPart t = (TextPart) o;
            return priority != t.priority ? priority - t.priority : version.compareTo(t.version);
        } else return -1;
    }
}
