package io.github.railroad.mods.version;

import static io.github.railroad.mods.version.VersionUtils.getStringFromUrl;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * @author Cy4Shot, Sak
 */
public interface FabricVersionHelper {

	static final String MC_VERSIONS_URL = "https://meta.fabricmc.net/v1/versions/game";
	static final String FABRIC_BUILD_URL = "https://meta.fabricmc.net/v1/versions/loader/";
	static final String FAPI_URL = "https://maven.fabricmc.net/net/fabricmc/fabric-api/fabric-api/maven-metadata.xml";

	static List<FabricGameVersionInfo> getAllFabricVersions() {
		JSONArray array = new JSONArray(getStringFromUrl(MC_VERSIONS_URL, 16384));
		List<FabricGameVersionInfo> versions = new ArrayList<FabricGameVersionInfo>();
		for (int i = 0, size = array.length(); i < size; i++) {
			versions.add(new FabricGameVersionInfo(array.getJSONObject(i)));
		}

		return versions;
	}

	static List<FabricBuilderVersionInfo> getFabricBuilderInfo(String version) {
		try {
			FileWriter myWriter = new FileWriter("filename.txt");
			myWriter.write(getStringFromUrl(FABRIC_BUILD_URL + version, 131072));
			myWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray array = new JSONArray(getStringFromUrl(FABRIC_BUILD_URL + version, 131072));
		List<FabricBuilderVersionInfo> versions = new ArrayList<FabricBuilderVersionInfo>();
		for (int i = 0, size = array.length(); i < size; i++) {
			versions.add(new FabricBuilderVersionInfo(array.getJSONObject(i)));
		}

		return versions;
	}

	// TODO get fabric API version
//	public static String getFAPIVersion() {
//		InputStream stream = getStream(FAPI_URL);
//		if (stream != null) {
//			try {
//				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
//
//				XPathExpression expr = javax.xml.xpath.XPathFactory.newInstance().newXPath()
//						.compile("/metadata/versioning/latest/text()");
//				return expr.evaluate(doc);
//			} catch (SAXException | XPathExpressionException | ParserConfigurationException | IOException e) {
//				System.out.println("Failed to resolve FAPI " + e);
//			}
//		}
//		return null;
//	}

	static class FabricGameVersionInfo {
		public String version;
		public boolean stable;

		public FabricGameVersionInfo(JSONObject json) {
			this.version = json.getString("version");
			this.stable = json.getBoolean("stable");
		}
	}

	static class FabricBuilderVersionInfo {
		public FabricBuilderPartInfo loader;
		public FabricBuilderPartInfo mappings;

		public FabricBuilderVersionInfo(JSONObject json) {
			this.loader = new FabricBuilderPartInfo(json.getJSONObject("loader"));
			this.mappings = new FabricBuilderPartInfo(json.getJSONObject("mappings"));
		}
	}

	static class FabricBuilderPartInfo {
		public String separator;
		public String maven;
		public String version;
		public int build;
		public boolean stable;

		public FabricBuilderPartInfo(JSONObject json) {
			this.version = json.getString("version");
			this.maven = json.getString("maven");
			this.separator = json.getString("separator");
			this.build = json.getInt("build");
			this.stable = json.getBoolean("stable");
		}
	}
}
