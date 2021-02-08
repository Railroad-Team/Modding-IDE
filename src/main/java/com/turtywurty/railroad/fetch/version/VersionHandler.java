package com.turtywurty.railroad.fetch.version;

import com.turtywurty.railroad.fetch.version.forge.ForgeVersion;
import com.turtywurty.railroad.fetch.version.forge.ForgeVersionHelper;
import com.turtywurty.railroad.fetch.version.forge.MinecraftForgeVersion;

public class VersionHandler {

	public static MinecraftForgeVersion getForgeLatest() {
		MinecraftForgeVersion latest;
		try {
			latest = ForgeVersionHelper.getLatestMcVersionForgeVersions();
		} catch (Exception e) {
			System.out.println("Unable to get forge versions.");
			e.printStackTrace();
			return new MinecraftForgeVersion("Null", new ForgeVersion("Null", "Null"));
		}

		return latest;
	}


}
