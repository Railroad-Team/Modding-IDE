package io.github.railroad.mods.version;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.railroad.mods.version.VersionUtils.getStringFromUrl;
import static javax.xml.xpath.XPathFactory.newInstance;

/**
 * @author Cy4Shot, Sak, TheOnlyTails
 */
public interface FabricVersionHelper {
    String MC_VERSIONS_URL = "https://meta.fabricmc.net/v1/versions/game";
    String FABRIC_LOADER_URL = "https://meta.fabricmc.net/v1/versions/loader/";
    String FABRIC_API_URL = "https://maven.fabricmc.net/net/fabricmc/fabric-api/fabric-api/maven-metadata.xml";

    static List<McVersion> getFabricMcVersions() {
        var array = new JSONArray(getStringFromUrl(MC_VERSIONS_URL));

        return IntStream.range(0, array.length())
                .mapToObj(i -> new McVersion(array.getJSONObject(i)))
                .collect(Collectors.toList());
    }

    static List<FabricBuildVersion> getFabricLoaderVersions(String mcVersion) {
        var array = new JSONArray(getStringFromUrl(FABRIC_LOADER_URL + mcVersion));

        return IntStream.range(0, array.length())
                .mapToObj(i -> new FabricBuildVersion(array.getJSONObject(i)))
                .collect(Collectors.toList());
    }

    static String getFabricAPIVersions() {
        try {
            var doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(FABRIC_API_URL);

            return newInstance()
                    .newXPath()
                    .compile("/metadata/versioning/versions")
                    .evaluate(doc);

        } catch (SAXException | XPathExpressionException | ParserConfigurationException | IOException e) {
            System.err.println("Failed to resolve Fabric API version" + e);
        }

        return null;
    }

    class McVersion {
        public String version;
        public boolean stable;

        public McVersion(JSONObject json) {
            this.version = json.getString("version");
            this.stable = json.getBoolean("stable");
        }

        @Override
        public String toString() {
            return version;
        }
    }

    class FabricBuildVersion {
        public final String loaderVersion;
        public final String mappingsVersion;

        public FabricBuildVersion(JSONObject json) {
            var loader = json.getJSONObject("loader");
            var mappings = json.getJSONObject("mappings");

            this.loaderVersion = loader.getString("version");
            this.mappingsVersion = mappings.getString("version");
        }
    }
}
