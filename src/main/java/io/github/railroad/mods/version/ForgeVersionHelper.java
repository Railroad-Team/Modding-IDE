package io.github.railroad.mods.version;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static io.github.railroad.mods.version.VersionUtils.getStringFromUrl;

/**
 * @author Cy4Shot, TheOnlyTails
 */
public interface ForgeVersionHelper {

	String FORGE_MAVEN = "https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml";
	String FORGE_PROMOTIONS = "https://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json";

	static List<String> getAllForgeVersions() {
		return getAllForgeVersionsWithRegex(".*?-(.*?)");
	}

	static List<String> getAllForgeVersions(String mcVersion) {
		return getAllForgeVersionsWithRegex(mcVersion + "-(.*?)");
	}

	static List<String> getAllForgeVersionsWithRegex(String regex) {
		var pattern = Pattern.compile("<version>" + regex + "</version>");
		var matcher = pattern.matcher(getStringFromUrl(FORGE_MAVEN));

		var versions = new ArrayList<String>();

		while (matcher.find()) versions.add(matcher.group(1));

		return versions;
	}

	static String getPromotionVersion(String version, boolean recommended) {
		var promos = new JSONObject(new JSONTokener(getStringFromUrl(FORGE_PROMOTIONS)))
				.getJSONObject("promos");

		return promos.getString(version + "-" + (recommended ? "recommended" : "latest"));
	}
}
