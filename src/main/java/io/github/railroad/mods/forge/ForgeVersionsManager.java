package io.github.railroad.mods.forge;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the forge versions.
 *
 * @author ChAoS
 */
public class ForgeVersionsManager {
    public final List<String> versions;
    public final List<String> sortedVersion;
    public final List<String> unsortedMinecraftVersions;
    public final List<String> sortedMinecraftVersions;

    private ForgeVersionsManager(List<String> versions) {
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

    /**
     * Gets a list of all forge versions from
     * <a href=https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml>here</a>.
     *
     * @return a {@link ForgeVersionsManager} object with all of the versions retrieved.
     */
    public static ForgeVersionsManager downloadVersions() {
        try (var stream = new InputStreamReader(
                new URL("https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml")
                        .openStream())) {

            var result = new ArrayList<String>();

            XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlFactory.createXMLEventReader(stream);

            while (reader.hasNext()) {
                var event = reader.nextEvent();

                if (!event.isStartElement()) continue;

                StartElement start = event.asStartElement();
                String name = start.getName().getLocalPart();

                if (!name.equals("version")) continue;

                XMLEvent versionEvent = reader.nextEvent();

                if (!versionEvent.isCharacters()) continue;

                String version = versionEvent.asCharacters().getData();

                if (version.indexOf('-') == -1) continue;

                result.add(version);
            }

            return new ForgeVersionsManager(result);

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
