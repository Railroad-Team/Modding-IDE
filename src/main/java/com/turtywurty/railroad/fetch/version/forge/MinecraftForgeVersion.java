package com.turtywurty.railroad.fetch.version.forge;

public class MinecraftForgeVersion {
	ForgeVersion forgeVersion;
	String mcVersion;

	public MinecraftForgeVersion(String mcVersion, ForgeVersion forgeVersion) {
		this.mcVersion = mcVersion;
		this.forgeVersion = forgeVersion;
	}

	public ForgeVersion getForgeVersion() {
		return forgeVersion;
	}

	public String getMcVersion() {
		return mcVersion;
	}

	/**
	If recommended is true, will show the recommended MDK. Otherwise, latest MDK. 
	*/
	public String getDownloadURL(boolean reccomended) {
		return String.format(
				"https://files.minecraftforge.net/maven/net/minecraftforge/forge/%1$s-%2$s/forge-%1$s-%2$s-mdk.zip",
				this.getMcVersion(), this.getForgeVersion().getLatest());
	}
}
