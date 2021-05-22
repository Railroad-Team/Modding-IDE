package io.github.railroad.mods.version;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static io.github.railroad.mods.version.VersionUtils.getStringFromUrl;

/**
 * Utility methods to retrieve versions for forge modding.
 *
 * @author Cy4Shot, TheOnlyTails
 */
public interface ForgeVersionHelper {
	
    /**
     * The URL for a list of all forge versions.
     */
    String FORGE_VERSIONS = "https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml";
    
    /**
     * The URL for a list of all significant (recommended or latest) forge versions for each MC version.
     */
    String FORGE_PROMOTIONS = "https://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json";

    /**
     * Retrieves a list of all forge versions from
     * <a href=https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml>here</a>.
     *
     * @return a list of all forge versions.
     * @author Cy4Shot, TheOnlyTails
     */
    static List<String> getAllForgeVersions() {
        return getAllForgeVersionsWithRegex(".*?-(.*?)");
    }

    /**
     * Retrieves a list of all forge versions for a certain MC version from
     * <a href=https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml>here</a>.
     *
     * @return a list of all forge versions for a certain MC version.
     * @author Cy4Shot, TheOnlyTails
     */
    static List<String> getAllForgeVersions(String mcVersion) {
        return getAllForgeVersionsWithRegex(mcVersion + "-(.*?)");
    }

    /**
     * Retrieves a list of all forge versions that match a regex from
     * <a href=https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml>here</a>.
     *
     * @param regex the regex to match the versions for.
     * @return a list of all forge versions that match a regex.
     * @author Cy4Shot, TheOnlyTails
     */
    static List<String> getAllForgeVersionsWithRegex(String regex) {
        try {
            var pattern = Pattern.compile("<version>" + regex + "</version>");
            var matcher = pattern.matcher(getStringFromUrl(new URL(FORGE_VERSIONS)));

            var versions = new ArrayList<String>();

            while (matcher.find()) versions.add(matcher.group(1));

            return versions;
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL. Please replace the URL and try again.");
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves a list of all significant (recommended or latest) forge versions for an MC version.
     *
     * @param recommended do you want the recommended version or the latest version.
     * @return a list of all significant (recommended or latest) forge versions for an MC version.
     * @author Cy4Shot, TheOnlyTails
     */
    static String getPromotionVersion(String version, boolean recommended) {
        try {
            var promos = new JSONObject(new JSONTokener(getStringFromUrl(new URL(FORGE_PROMOTIONS))))
                    .getJSONObject("promos");

            return promos.getString(version + "-" + (recommended ? "recommended" : "latest"));
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL. Please replace the URL and try again.");
            return "";
        }
    }
}
