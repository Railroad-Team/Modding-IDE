package io.github.railroad.platform.versionparts;

import org.jetbrains.annotations.NotNull;

public class ReleasePart extends VersionPart {
    public final int versionID;

    public ReleasePart(int versionID, String version) {
        super(version);
        this.versionID = versionID;
    }

    @Override
    public int compareTo(@NotNull VersionPart o) {
        if (o instanceof ReleasePart) {
            ReleasePart r = (ReleasePart) o;
            return versionID - r.versionID;
        } else if (o instanceof TextPart) return 1;
        else return -1;
    }
}
