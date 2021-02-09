package com.turtywurty.railroad.fetch.version.forge;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.turtywurty.railroad.fetch.version.SemVer;
import com.turtywurty.railroad.fetch.version.VersionMeta;

//Author: MMD. Taken and changed on 08/02/21
public class ForgeVersionHelper {

	private static final String VERSION_URL = "https://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json";
	private static final Pattern VERSION_REGEX = Pattern.compile("(.+?)-(.+)");

	private static final Gson gson = new Gson();

	public static String getLatestVersion(List<String> versions) {
		SemVer latest = new SemVer(versions.get(0));

		for (String version : versions) {
			SemVer ver = new SemVer(version);
			if (latest.compareTo(ver) < 0) {
				latest = ver;
			}
		}

		return latest.toString();
	}

	public static ForgeVersion getForgeVersionsForMcVersion(String mcVersion) throws Exception {
		return getForgeVersions().get(mcVersion);
	}

	public static MinecraftForgeVersion getLatestMcVersionForgeVersions() throws Exception {
		Map<String, ForgeVersion> versions = getForgeVersions();

		String latest = getLatestVersion(new ArrayList<>(versions.keySet()));

		return new MinecraftForgeVersion(latest, versions.get(latest));
	}

	private static InputStreamReader openUrl() throws Exception {
		URL urlObj = new URL(VERSION_URL);

		return new InputStreamReader(urlObj.openStream());
	}

	public static Map<String, ForgeVersion> getForgeVersions() throws Exception {
		InputStreamReader reader = openUrl();

		ForgePromoData data = gson.fromJson(reader, ForgePromoData.class);

		// Remove broken entries from the API
		data.promos.remove("1.7.10-latest-1.7.10");
		data.promos.remove("latest-1.7.10");

		// Collect version data
		Map<String, ForgeVersion> versions = new HashMap<>();

		for (Map.Entry<String, String> entry : data.promos.entrySet()) {
			String mc = entry.getKey();
			String forge = entry.getValue();

			VersionMeta meta = getMCVersion(mc);

			if (versions.containsKey(meta.version)) {
				ForgeVersion version = versions.get(meta.version);
				if (meta.state.equals("recommended")) {
					version.setRecommended(forge);
				} else {
					version.setLatest(forge);
				}
			} else {
				ForgeVersion version = new ForgeVersion();
				if (meta.state.equals("recommended")) {
					version.setRecommended(forge);
				} else {
					version.setLatest(forge);
				}
				versions.put(meta.version, version);
			}
		}

		return versions;
	}

	public static VersionMeta getMCVersion(String version) {
		Matcher m = VERSION_REGEX.matcher(version);

		if (m.find()) {
			return new VersionMeta(m.group(1), m.group(2));
		} else {
			return null;
		}
	}

}
