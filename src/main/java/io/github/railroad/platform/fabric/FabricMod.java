package io.github.railroad.platform.fabric;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @author lifebearrrr
 * 
 *         Used to generate new Fabric Mod with all assets
 * 
 *         TODO: Replace my Fabric version getter (that doesn't get the right
 *         fabric api version) with Saksham4106's Fabric version getter when
 *         it's done
 */
public class FabricMod {

	private String pkg;
	private String name;
	private String modID;
	private static String workspacePath;

	public FabricMod(String name, String modId, String pkg, String version,
			String workspace/* Put file.getAbsolutePath() or string in this format "C:\\Users\\name\\" */) {
		this.pkg = pkg;
		this.name = name;
		this.modID = modId;
		generateMod(name, modId, pkg, version, workspace, Optional.empty());
	}

	public FabricMod(String name, String modId, String pkg, String version, String fapiVersion,
			String workspace/* Put file.getAbsolutePath() or string in this format "C:\\Users\\name\\" */) {
		this.pkg = pkg;
		this.name = name;
		this.modID = modId;
		generateMod(name, modId, pkg, version, workspace, Optional.of(fapiVersion));
	}

	public static void generateMod(String name, String modID, String pkg, String version, String workspaceString,
			Optional<String> fapiVersion) {
		workspacePath = workspaceString + "\\";

		File workspace = getOrCreateFolder(workspacePath);

		File proj = createDirs(workspace, name, modID);

		downloadFiles(name, modID);

		setUpFabric(workspace.getAbsolutePath(), name, modID, version, pkg);

		setUpGradle(name, modID, pkg, version, proj + "\\" + "gradle.properties", proj, fapiVersion);

	}

	public static File createDirs(File workspace, String name, String modID) {
		// Creating Folders / Files
		File proj = getOrCreateFolder(workspace.getAbsolutePath() + "\\" + name);
		getOrCreateFolder(proj + "\\" + "src\\main\\resources\\assets\\" + modID);
		getOrCreateFolder(proj + "\\" + "src\\main\\resources\\data\\" + modID);
		getOrCreateFile(proj + "\\" + "gradle.properties");
		return proj;
	}

	public static void downloadFiles(String name, String modID) {
		// Downloading gradlew stuff
		download("gradlew.bat", workspacePath + name + "\\",
				"https://github.com/FabricMC/fabric-example-mod/raw/master/gradlew.bat");
		download("build.gradle", workspacePath + name + "\\",
				"https://github.com/FabricMC/fabric-example-mod/raw/master/build.gradle");
		download("settings.gradle", workspacePath + name + "\\",
				"https://github.com/FabricMC/fabric-example-mod/raw/master/settings.gradle");
		download("gradlew", workspacePath + name + "\\",
				"https://github.com/FabricMC/fabric-example-mod/raw/master/gradlew");
		download("gradle-wrapper.properties", workspacePath + name + "\\gradle\\wrapper\\",
				"https://github.com/FabricMC/fabric-example-mod/raw/master/gradle/wrapper/gradle-wrapper.properties");
		download("gradle-wrapper.jar", workspacePath + name + "\\gradle\\wrapper\\",
				"https://github.com/FabricMC/fabric-example-mod/raw/master/gradle/wrapper/gradle-wrapper.jar");
		download("fabric.mod.json", workspacePath + name + "\\src\\main\\resources\\",
				"https://github.com/FabricMC/fabric-example-mod/raw/master/src/main/resources/fabric.mod.json");
		download(modID + ".mixins.json", workspacePath + name + "\\src\\main\\resources\\",
				"https://github.com/FabricMC/fabric-example-mod/raw/master/src/main/resources/modid.mixins.json");
		download("icon.png", workspacePath + name + "\\src\\main\\resources\\assets\\" + modID,
				"https://github.com/FabricMC/fabric-example-mod/raw/master/src/main/resources/assets/modid/icon.png");
	}

	public static void setUpFabric(String workspacestr, String name, String modID, String version,
			String packageString) {
		fabricModJson(name, modID, version, packageString);
		modIdMixinsJson(name, modID, packageString);
		setUpMainClass(name, packageString, workspacestr);
		setUpMixinClass(name, packageString, workspacestr);
	}

	public static void setUpGradle(String name, String modID, String packageString, String version, String file,
			File proj, Optional<String> fapiVersion) {
		try {
			setUpGradleProperties(name, modID, packageString, version, proj + "\\" + "gradle.properties", fapiVersion);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void fabricModJson(String name, String modID, String version, String packageString) {
		File fabricModFile = new File(workspacePath + name + "\\src\\main\\resources\\fabric.mod.json");
		try {
			InputStream is = new FileInputStream(fabricModFile);
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject fabricMod = new JSONObject(jsonText);
			fabricMod.put("id", modID);
			fabricMod.put("name", name);
			fabricMod.put("icon", "assets/" + modID + "/icon.png");
			fabricMod.getJSONObject("entrypoints").getJSONArray("main").put(0, packageString + "." + name);
			fabricMod.getJSONObject("depends").put("minecraft", version);
			fabricMod.getJSONArray("mixins").put(0, modID + ".mixins.json");
			FileWriter writer = new FileWriter(fabricModFile);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(fabricMod.toString());
			String prettyJsonString = gson.toJson(je).replace("\\u003e\\u003d", ">=");
			writer.write(prettyJsonString);
			writer.close();
			rd.close();
			is.close();
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void modIdMixinsJson(String name, String modID, String packageString) {
		File fabricModFile = new File(workspacePath + name + "\\src\\main\\resources\\" + modID + ".mixins.json");
		try {
			InputStream is = new FileInputStream(fabricModFile);
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject fabricMod = new JSONObject(jsonText);
			fabricMod.put("package", packageString + ".mixin");
			fabricMod.getJSONArray("client").put(0, name.replace(" ", "") + "Mixin");
			FileWriter writer = new FileWriter(fabricModFile);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(fabricMod.toString());
			String prettyJsonString = gson.toJson(je);
			writer.write(prettyJsonString);
			writer.close();
			rd.close();
			is.close();
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void setUpMainClass(String name, String packageString, String workspacestr) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new URL(
					"https://raw.githubusercontent.com/FabricMC/fabric-example-mod/master/src/main/java/net/fabricmc/example/ExampleMod.java")
							.openStream()));
			String inputLine;
			File workspace = getOrCreateFolder(workspacestr);
			File proj = getOrCreateFolder(workspace.getAbsolutePath() + "\\" + name);
			File packageDir = getOrCreateFolder(proj + "\\" + "src\\main\\java\\" + packageString.replace(".", "\\"));
			File mainClass = getOrCreateFile(packageDir + "\\" + name + ".java");
			FileWriter writer = new FileWriter(mainClass);
			while ((inputLine = in.readLine()) != null) {
				writer.write(inputLine.replace("net.fabricmc.example", packageString).replace("ExampleMod", name));
			}
			writer.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setUpMixinClass(String name, String packageString, String workspacestr) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new URL(
					"https://raw.githubusercontent.com/FabricMC/fabric-example-mod/master/src/main/java/net/fabricmc/example/mixin/ExampleMixin.java")
							.openStream()));
			String inputLine;
			File workspace = getOrCreateFolder(workspacestr);
			File proj = getOrCreateFolder(workspace.getAbsolutePath() + "\\" + name);
			File packageDir = getOrCreateFolder(proj + "\\" + "src\\main\\java\\" + packageString.replace(".", "\\"));
			File mainClass = getOrCreateFile(packageDir + "\\mixin\\" + name.replace(" ", "") + "Mixin" + ".java");
			FileWriter writer = new FileWriter(mainClass);
			while ((inputLine = in.readLine()) != null) {
				writer.write(inputLine.replace("net.fabricmc.example", packageString).replace("ExampleMod", name));
			}
			writer.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setUpGradleProperties(String name, String modID, String packageString, String version,
			String file, Optional<String> fapiVersion) throws FileNotFoundException {

		Properties prop = new Properties();

		InputStream inputStream = new FileInputStream(new File(file));

		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Getting the version info for gradle.properties
		JSONObject properties = getFabricVersionInfo(version);
		String fabricAPI = getFabricApiVersion();

		// Setting properties in gradle.properties
		prop.setProperty("org.gradle.jvmargs", "-Xmx2G");
		prop.setProperty("minecraft_version", version);
		prop.setProperty("yarn_mappings", properties.getJSONObject("mappings").getString("version"));
		prop.setProperty("loader_version", properties.getJSONObject("loader").getString("version"));
		prop.setProperty("mod_version", "1.0.0");
		prop.setProperty("maven_group", packageString);
		prop.setProperty("archives_base_name", modID);
		if (fapiVersion.isEmpty()) {
			prop.setProperty("fabric_version", fabricAPI);
		} else {
			prop.setProperty("fabric_version", fapiVersion.get());
		}

		try {
			prop.store(new FileWriter(new File(file)), "Auto-Generated By Railroad");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static JSONObject getFabricVersionInfo(String version) {
		JSONArray json = readJsonArrayFromUrl("https://meta.fabricmc.net/v1/versions/loader/" + version);
		JSONObject properties = json.getJSONObject(0);

		return properties;
	}

	public static String getFabricApiVersion() {
		String fabricVersion = "0.30.2+1.17";

		URL url;
		try {
			url = new URL("https://maven.fabricmc.net/net/fabricmc/fabric-api/fabric-api/maven-metadata.xml");
			BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while ((inputLine = read.readLine()) != null)
				if (inputLine.contains("release")) {
					fabricVersion = inputLine.replace(" ", "").replace("<release>", "").replace("</release>", "");
					read.close();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fabricVersion;
	}

	public static JSONArray readJsonArrayFromUrl(String url) {
		try {
			InputStream is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONArray json = new JSONArray(jsonText);
			is.close();
			return json;
		} catch (IOException e) {
			return null;
		}
	}

	public static JSONObject readJsonObjectFromUrl(String url) {
		try {
			InputStream is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			is.close();
			return json;
		} catch (IOException e) {
			return null;
		}
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static void download(String name, String destFile, String url) {
		try {
			File proj = new File(destFile);
			if (!proj.exists()) {
				proj.mkdirs();
			}
			URL download = new URL(url);
			BufferedInputStream in = new BufferedInputStream(download.openStream());
			FileOutputStream fileOutputStream = new FileOutputStream(new File(proj.getAbsolutePath() + "\\" + name));
			System.out.println("Downloading " + name);
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getOrCreateFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public static File getOrCreateFolder(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}

	// Functions
	public ProcessBuilder runGradleTask(String task) {
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "gradlew", task);
		pb.directory(new File(workspacePath + name));
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pb;
	}

	public String getName() {
		return name;
	}

	public String getPackage() {
		return pkg;
	}

	public String getModID() {
		return modID;
	}

}
