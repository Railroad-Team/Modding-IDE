package io.github.railroad.moddedVersionFetcher.game;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import io.github.railroad.utility.FileUtils;

public class MinecraftVersionHelper {
	private static final String MC_VERSIONS_URL = "https://launchermeta.mojang.com/mc/game/version_manifest_v2.json";

	public static List<String> getVersions(boolean onlyStable) {
		JSONArray versions = new JSONObject(FileUtils.getFile(MC_VERSIONS_URL, 262144)).getJSONArray("versions");

		List<String> ret = new ArrayList<>();
		for (int i = 0; i < versions.length(); i++) {
			JSONObject obj = versions.getJSONObject(i);
			if (onlyStable && obj.getString("type").equals("snapshot") || obj.getString("type").contains("old"))
				continue;
			ret.add(obj.getString("id"));
		}

		return ret;
	}
}
