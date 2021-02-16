package io.github.railroad.mods.version;

import static io.github.railroad.mods.version.VersionUtils.getStringFromUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.JSONTokener;

public interface ForgeVersionHelper {

	static final String FORGE_MAVEN = "https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml";
	static final String FORGE_PROMOTIONS = "https://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json";

	static List<String> getAllForgeVersions() {
		return getAllForgeVersions(".*?-(.*?)");
	}

	static List<String> getAllForgeVersionsForMinecraft(String mcVersion) {
		return getAllForgeVersions(mcVersion + "-(.*?)");
	}

	static List<String> getAllForgeVersions(String regex) {
		Pattern pattern = Pattern.compile("<version>" + regex + "</version>");
		Matcher matcher = pattern.matcher(getStringFromUrl(FORGE_MAVEN, 524288));

		List<String> versions = new ArrayList<String>();
		while (matcher.find()) {
			versions.add(matcher.group(1));
		}
		return versions;
	}

	static String getPromotionVersion(String version, boolean recommended) {
		JSONObject promos = new JSONObject(new JSONTokener(getStringFromUrl(FORGE_PROMOTIONS, 4096)))
				.getJSONObject("promos");
		return promos.getString(version + "-" + (recommended ? "recommended" : "latest"));
	}
}
