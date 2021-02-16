package io.github.railroad.mods.version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author Cy4Shot
 */
public interface VersionUtils {

	static String getStringFromUrl(String url, int c) {
		try {
			ReadableByteChannel channel = Channels.newChannel(new URL(url).openStream());
			InputStream in = Channels.newInputStream(channel);
			return new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)).lines()
					.collect(Collectors.joining("\n"));
		} catch (MalformedURLException e) {
			System.out.printf("Url: %s malformed", url);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

}
