package io.github.railroad.mods.version;

import static java.nio.channels.Channels.newChannel;
import static java.nio.channels.Channels.newInputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;


/**
 * @author Cy4Shot
 */
public interface VersionUtils {
	
    /**
     * Gets a string from a {@link URL}.
     *
     * @param url the URL.
     * @return get string returned from the URL.
     * @author Cy4shot, TheOnlyTails
     */
    static String getStringFromUrl(URL url) {
        try (final ReadableByteChannel channel = newChannel(new URL(url.toString()).openStream())) {
            try (var stream = newInputStream(channel)) {
                return new BufferedReader(new InputStreamReader(stream, UTF_8))
                        .lines()
                        .collect(joining("\n"));
            }
        } catch (IOException reason) {
            throw new FailedToGetException("Failed to GET from a resource locator", reason);
        }
    }
}
