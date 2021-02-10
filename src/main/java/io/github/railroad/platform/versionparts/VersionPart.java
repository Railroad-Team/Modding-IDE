package io.github.railroad.platform.versionparts;

public abstract class VersionPart implements Comparable<VersionPart> {
    public final String version;

    protected VersionPart(String version) {
        this.version = version;
    }
}
