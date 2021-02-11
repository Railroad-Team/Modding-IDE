package io.github.railroad.platform.forge;

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
 * @author ChAoS
 *
 * Class ForgeClass can be used as a list of forge version fetching result,
 * you can only get it by using method {@code downloadVersion()}.
 *
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

    public static ForgeVersion downloadVersions() throws IOException {
        InputStreamReader stream = null;

        try {
            URL url = new URL("https://files.minecraftforge.net/maven/net/minecraftforge/forge/maven-metadata.xml");
            ArrayList<String> result = new ArrayList<>();
            stream = new InputStreamReader(url.openStream());

            XMLInputFactory XMLFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = XMLFactory.createXMLEventReader(stream);

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (!event.isStartElement())
                    continue;

                StartElement start = event.asStartElement();
                String name = start.getName().getLocalPart();

                if (!name.equals("version"))
                    continue;

                XMLEvent versionEvent = reader.nextEvent();

                if (!versionEvent.isCharacters())
                    continue;

                String version = versionEvent.asCharacters().getData();
                int index = version.indexOf('-');

                if (index == -1)
                    continue;

                result.add(version);
            }

            return new ForgeVersion(result);
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }

        return null;
    }
}
