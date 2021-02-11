package io.github.railroad.moddedVersionFetcher.fabric;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/*
 * @author Saksham4106
 * 
 * Used to get the info for making a fabric mod all automatically
 * 
 *TODO: I still need to fix the fapi and builder thing 
 */
public class FabricVersionHelper {
	
	private static final String MC_VERSIONS_URL = "https://meta.fabricmc.net/v1/versions/game";
	private static final String FABRIC_BUILD_URL = "https://meta.fabricmc.net/v1/versions/loader/";
	private static final String FAPI_URL = "https://maven.fabricmc.net/net/fabricmc/fabric-api/fabric-api/maven-metadata.xml";
	
	private static ArrayList<String> versionList = new ArrayList<>();
	
	public static ArrayList<String> getFabricVersions(){
		InputStreamReader reader = getStreamReader(MC_VERSIONS_URL);
		if (reader != null) {
			TypeToken<List<FabricVersionsInfo>> token = new TypeToken<List<FabricVersionsInfo>>(){
				
			};
			List<FabricVersionsInfo> versionInfo = new Gson().fromJson(reader, token.getType());
			versionList.add(versionInfo.get(0).version);
			for(FabricVersionsInfo it: versionInfo) {
				if(it.stable) {
					versionList.add(it.version);
				}
			}
			return versionList;
		}
		return null;
	}
	
	//TODO: not done yet
	public static void getFabricBuilderInfo(String version) {
		InputStreamReader reader = getStreamReader(FABRIC_BUILD_URL + version);
		if (reader != null) {
			
		}
	}
	
	//TODO: now completed yet
	public static String getFAPIVersion() {
		InputStream stream = getStream(FAPI_URL);
		if(stream != null) {
			try {
				Document doc = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder()
						.parse(stream);
				
				XPathExpression expr = javax.xml.xpath.XPathFactory.newInstance()
						.newXPath()
						.compile("/metadata/versioning/latest/text()");
				return expr.evaluate(doc);
			}catch (SAXException | XPathExpressionException | ParserConfigurationException | IOException e) {
				System.out.println("Failed to resolve FAPI " + e);
			}
		}
		return null;
	}
	
	private static InputStreamReader getStreamReader(String urlStr) {
		if(getStream(urlStr) != null) {
			return new InputStreamReader(getStream(urlStr));
		}else {
			return null;
		}
	}
	private static InputStream getStream(String urlStr) {
		try {
			URL url = new URL(urlStr);
			return url.openStream();
		}catch (IOException e){
			System.out.println("Unable to get Minecraft Fabric VERSIONS ");
			return null;
		}
		
	}
	
	private static class FabricVersionsInfo {
		public String version;
		public boolean stable;
	}
	
	// Temporarily removed to not cause problems 
/*
	private static class FabricBuilderInfo{
		public int buildNumber;
		public String separator;
		public String maven;
		public String version;
		public boolean stable;
		
		private static class yeet{
			
		}
	}
*/
}
