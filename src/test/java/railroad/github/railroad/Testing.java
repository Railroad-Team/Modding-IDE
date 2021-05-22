package railroad.github.railroad;

import io.github.railroad.mods.version.FabricVersionHelper;
import io.github.railroad.mods.version.ForgeVersionHelper;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Testing {
    @Test
    void getAllForgeVersions() {
        System.out.println(ForgeVersionHelper.getAllForgeVersions());

        assertNotNull(ForgeVersionHelper.getAllForgeVersions());
    }

    @Test
    void allForgeForLatestMcStartsWith36() {
        System.out.println(ForgeVersionHelper.getAllForgeVersions("1.16.5"));

        assertTrue(ForgeVersionHelper.getAllForgeVersions("1.16.5").stream()
                .allMatch(version -> version.startsWith("36"))
        );
    }

    @Test
    void getFabricMcVersions() {
        System.out.println(FabricVersionHelper.getFabricMcVersions());

        assertNotNull(FabricVersionHelper.getFabricMcVersions());
    }

    @Test
    void getLatestFabricAPIVersions() {
        System.out.println(FabricVersionHelper.getFabricAPIVersions());

        assertNotNull(FabricVersionHelper.getFabricAPIVersions());
    }

    @Test
    void getAllFabricLoaderVersions() {
        System.out.println(FabricVersionHelper.getFabricLoaderVersions("1.16.5").stream()
                .map(buildVersion -> buildVersion.loaderVersion + "_" + buildVersion.mappingsVersion)
                .collect(Collectors.toList()));

        assertNotNull(FabricVersionHelper.getFabricLoaderVersions("1.16.5"));
    }
}
