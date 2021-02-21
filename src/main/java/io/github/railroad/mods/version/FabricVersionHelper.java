package io.github.railroad.mods.version;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.railroad.mods.version.VersionUtils.getStringFromUrl;
import static javax.xml.xpath.XPathFactory.newInstance;

/**
 * Utility methods to retrieve versions for fabric modding.
 *
 * @author Cy4Shot, Sak, TheOnlyTails
 */
public interface FabricVersionHelper {
    /**
     * The URL for a list of all fabric-supported MC versions.
     */
    String MC_VERSIONS_URL = "https://meta.fabricmc.net/v1/versions/game";
    /**
     * The URL for a list of all fabric-loader and yarn mappings versions.
     */
    String FABRIC_LOADER_URL = "https://meta.fabricmc.net/v1/versions/loader/";
    /**
     * The URL for a list of all fabric API versions.
     */
    String FABRIC_API_URL = "https://maven.fabricmc.net/net/fabricmc/fabric-api/fabric-api/maven-metadata.xml";

    /**
     * Retrieves a list of all fabric-supported MC versions.
     *
     * @return a list of all fabric-supported MC versions.
     * @author Cy4Shot, TheOnlyTails
     */
    static List<McVersion> getFabricMcVersions() {
        try {
            var array = new JSONArray(getStringFromUrl(new URL(MC_VERSIONS_URL)));

            return IntStream.range(0, array.length())
                    .mapToObj(i -> new McVersion(array.getJSONObject(i)))
                    .collect(Collectors.toList());
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL. Please replace the URL and try again.");
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves a list of all fabric-loader and yarn mappings versions for a certain MC version.
     *
     * @return a list of all fabric-supported MC versions.
     * @author Cy4Shot, TheOnlyTails
     */
    static List<FabricBuildVersion> getFabricLoaderVersions(String mcVersion) {
        try {
            var array = new JSONArray(getStringFromUrl(new URL(FABRIC_LOADER_URL + mcVersion)));

            return IntStream.range(0, array.length())
                    .mapToObj(i -> new FabricBuildVersion(array.getJSONObject(i)))
                    .collect(Collectors.toList());
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL. Please replace the URL and try again.");
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves a list of all fabric API versions.
     *
     * @return a list of all fabric API versions.
     * @author Cy4Shot, TheOnlyTails
     */
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

    /**
     * A data class that holds an MC version.
     *
     * @author Cy4Shot, TheOnlyTails
     */
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

    /**
     * A data class that holds a fabric loader and yarn mappings version.
     *
     * @author Cy4Shot, TheOnlyTails
     */
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
