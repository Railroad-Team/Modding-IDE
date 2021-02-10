package io.github.railroad.platform.versionparts;

import io.github.railroad.platform.SemanticVersion;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PreReleasePart extends VersionPart {
    public final int versionID;
    public final char separator;
    public final List<VersionPart> subParts;

    public PreReleasePart(int versionID, char separator, List<VersionPart> subParts, String version) {
        super(version);
        this.versionID = versionID;
        this.separator = separator;
        this.subParts = subParts;
    }

    @Override
    public int compareTo(@NotNull VersionPart o) {
        if (o instanceof ReleasePart) {
            ReleasePart r = (ReleasePart) o;
            return versionID != r.versionID ? versionID - r.versionID : -1;
        } else if (o instanceof TextPart) return 1;
        else if (o instanceof PreReleasePart) {
            PreReleasePart p = (PreReleasePart) o;
            return versionID != p.versionID ? versionID - p.versionID : SemanticVersion.compareListsLexicographically(subParts, p.subParts);
        } else return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PreReleasePart) {
            PreReleasePart p = (PreReleasePart) o;
            return p.versionID == versionID && p.subParts == subParts;
        } else return false;
    }

    @Override
    public int hashCode() {
        return versionID + 31 * subParts.hashCode();
    }
}
