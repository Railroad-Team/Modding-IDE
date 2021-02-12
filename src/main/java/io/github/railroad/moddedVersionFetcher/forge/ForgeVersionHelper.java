package io.github.railroad.moddedVersionFetcher.forge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.JSONTokener;

import io.github.railroad.utility.FileUtils;

/**
 * @author Cy4Shot
 * 
 *         This class crawls the forge website in order to get version ids.
 */
public class ForgeVersionHelper {

	private static final String FORGE_MAVEN = "https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml";
	private static final String FORGE_PROMOTIONS = "https://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json";

	/**
	 * This method is used to get all of the forge versions.
	 * 
	 * @return A list of the forge versions
	 * @throws IOException meaning it couldn't find the forge maven.
	 */
	public List<String> getAllForgeVersions() {
		return getAllForgeVersions(".*?-(.*?)");
	}

	/**
	 * This method is used to get all of the forge versions for a specifix MC
	 * Version
	 * 
	 * @param mcVersion The version of minecraft. e.g. "1.16.5"
	 * @return A list of the forge versions
	 * @throws IOException meaning it couldn't find the forge maven.
	 */
	public List<String> getAllForgeVersionsForMinecraft(String mcVersion) {
		return getAllForgeVersions(mcVersion + "-(.*?)");
	}

	private List<String> getAllForgeVersions(String regex) {
		Pattern pattern = Pattern.compile("<version>" + regex + "</version>"); // Yes regex, I use it too much now.
		Matcher matcher = pattern.matcher(FileUtils.getFile(FORGE_MAVEN, 524288));

		List<String> versions = new ArrayList<String>();
		while (matcher.find()) {
			versions.add(matcher.group(1));
		}
		return versions;
	}

	/**
	 * This method is used to get all of the forge versions for a specifix MC
	 * Version
	 * 
	 * @param version     The version of minecraft. e.g. "1.16.5"
	 * @param recommended Whether the version is recommended or latest.
	 * @return A string containing the version number
	 * @throws IOException meaning it couldn't find the forge promotion.
	 */
	public String getPromotionVersion(String version, boolean recommended) {
		JSONObject promos = new JSONObject(new JSONTokener(FileUtils.getFile(FORGE_PROMOTIONS, 4096)))
				.getJSONObject("promos");
		return promos.getString(version + "-" + (recommended ? "recommended" : "latest"));
	}
}
