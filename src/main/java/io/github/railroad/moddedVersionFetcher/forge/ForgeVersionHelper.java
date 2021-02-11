package io.github.railroad.moddedVersionFetcher.forge;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.JSONTokener;

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
	public List<String> getAllForgeVersions() throws IOException {
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
	public List<String> getAllForgeVersionsForMinecraft(String mcVersion) throws IOException {
		return getAllForgeVersions(mcVersion + "-(.*?)");
	}

	private List<String> getAllForgeVersions(String regex) throws IOException {
		InputStreamReader reader = getStreamReader(FORGE_MAVEN);
		if (reader != null) {
			final int bufferSize = 102400;
			final char[] buffer = new char[bufferSize];
			final StringBuilder out = new StringBuilder();
			int charsRead;
			while ((charsRead = reader.read(buffer, 0, buffer.length)) > 0) {
				out.append(buffer, 0, charsRead);
			}
			Pattern pattern = Pattern.compile("<version>" + regex + "</version>"); // Yes regex, I use it too much now.
			Matcher matcher = pattern.matcher(out.toString());

			List<String> versions = new ArrayList<String>();
			while (matcher.find()) {
				versions.add(matcher.group(1));
			}
			return versions;
		}
		throw new IOException("Forge Maven not Found");
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
	public String getPromotionVersion(String version, boolean recommended) throws IOException {
		InputStreamReader reader = getStreamReader(FORGE_PROMOTIONS);
		if (reader != null) {
			JSONObject promos = new JSONObject(new JSONTokener(reader)).getJSONObject("promos");
			return promos.getString(version + "-" + (recommended ? "recommended" : "latest"));
		}
		throw new IOException("Forge Promotion not Found");
	}

	private static InputStreamReader getStreamReader(String urlStr) throws IOException {
		return getStream(urlStr) != null ? new InputStreamReader(getStream(urlStr)) : null;
	}

	private static InputStream getStream(String urlStr) throws IOException {
		return new URL(urlStr).openStream();

	}
}
