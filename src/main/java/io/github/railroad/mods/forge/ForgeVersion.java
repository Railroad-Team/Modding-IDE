package io.github.railroad.mods.forge;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChAoS
 * <p>
 * Class ForgeClass can be used as a list of forge version fetching result, you can only get it by using method {@code
 * downloadVersion()}.
 * <p>
 * TODO: Sorted version list still unavailable until {@code SemanticVersion.parse(String value)} is fully handled.
 */
public class ForgeVersion {
    public final List<String> versions;
    public final List<String> sortedVersion;
    public final List<String> unsortedMinecraftVersions;
    public final List<String> sortedMinecraftVersions;

    private ForgeVersion(List<String> versions) {
        this.versions = versions;
        unsortedMinecraftVersions = versions.stream()
                .map(str -> {
                    int index = str.indexOf('-');

                    if (index == -1)
                        return null;

                    return str.substring(0, index);
                })
                .distinct()
                .collect(Collectors.toList());

        Collections.reverse(new ArrayList<>(versions));
        this.sortedVersion = versions;
        Collections.reverse(new ArrayList<>(unsortedMinecraftVersions));
        sortedMinecraftVersions = unsortedMinecraftVersions;
    }

    public static ForgeVersion downloadVersions() {
        try (var stream = new InputStreamReader(
                new URL("https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml")
                        .openStream())) {
            var result = new ArrayList<String>();

            var xmlFactory = XMLInputFactory.newInstance();
            var reader = xmlFactory.createXMLEventReader(stream);

            while (reader.hasNext()) {
                var event = reader.nextEvent();

                if (!event.isStartElement()) continue;

                var start = event.asStartElement();
                var name = start.getName().getLocalPart();

                if (!name.equals("version")) continue;

                var versionEvent = reader.nextEvent();

                if (!versionEvent.isCharacters()) continue;

                var version = versionEvent.asCharacters().getData();

                if (version.indexOf('-') == -1) continue;

                result.add(version);
            }

            return new ForgeVersion(result);

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString() {
        return "sortedVersion=" + sortedVersion + "\n" +
               "sortedMinecraftVersions=" + sortedMinecraftVersions;
    }
}
