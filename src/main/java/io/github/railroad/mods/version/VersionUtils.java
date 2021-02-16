package io.github.railroad.mods.version;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public interface VersionUtils {

	static String getStringFromUrl(String url, int c) {
		try {
			ReadableByteChannel channel = Channels.newChannel(new URL(url).openStream());
			InputStream in = Channels.newInputStream(channel);
			return IOUtils.toString(in, StandardCharsets.UTF_8.name());
		} catch (MalformedURLException e) {
			System.out.printf("Url: %s malformed", url);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

}
