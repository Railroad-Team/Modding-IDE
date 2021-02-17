package io.github.railroad.mods.version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;

import static java.nio.channels.Channels.newChannel;
import static java.nio.channels.Channels.newInputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;


/**
 * @author Cy4Shot
 */
public interface VersionUtils {
    static String getStringFromUrl(String locator) {
        try (final ReadableByteChannel channel = newChannel(new URL(locator).openStream())) {
            try (InputStream stream = newInputStream(channel)) {
                return new BufferedReader(new InputStreamReader(stream, UTF_8))
                        .lines()
                        .collect(joining("\n"));

            }
        } catch (IOException reason) {
            throw new RuntimeException("Failed to GET from a resource locator", reason);
        }
    }
}
