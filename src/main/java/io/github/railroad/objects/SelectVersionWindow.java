package io.github.railroad.objects;

import io.github.railroad.platform.PlatformType;
import io.github.railroad.platform.forge.ForgeVersion;

import java.util.List;

public class SelectVersionWindow {

    private final PlatformType type;

    public SelectVersionWindow(PlatformType type) {
        this.type = type;
    }

    public void displayWindow() {
        try {
            switch (type) {
                case FORGE -> {
                    List<String> versionList = ForgeVersion.downloadVersions().versions;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
