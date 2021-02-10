package io.github.railroad.drp;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

/**
 * [Does Discord Rich Presence for the IDE](https://cdn.discordapp.com/attachments/808027153606115349/808744581071503370/unknown.png)
 * Uses factory-style construction to build a presence. This class is meant to be non-static for flexibility
 * However, it wouldn't be a bad idea to de objectify it later but then you lose options /shrug
 *
 * @author jojo2357
 */
public class DiscordRichPresenceManager {
    /**
     * The ID of the discord application. Here is mine by default
     */
    public static final String applicationID = "808571949227049000";

    private String lastDetails = "";
    private BigImageKeys lastBigImage = BigImageKeys.NONE;
    private String lastBigImageDetails = "";
    private String lastStats = "";
    private long lastStartTime = System.currentTimeMillis();

    public DiscordRichPresenceManager() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Closing Discord hook.");
            DiscordRPC.discordShutdown();
        }));

        initDiscord();
    }

    private void initDiscord() {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(user -> {
            System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
            DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder("Score: ");
            presence.setDetails("Running Test");
            DiscordRPC.discordUpdatePresence(presence.build());
        }).build();
        DiscordRPC.discordInitialize(applicationID, handlers, false);
        DiscordRPC.discordRegister(applicationID, "");
    }

    public DiscordRichPresenceManager setBigImage(BigImageKeys imageKey) {
        return setBigImage(imageKey, "");
    }

    public DiscordRichPresenceManager setBigImage(BigImageKeys imageKey, String tooltip) {
        lastBigImage = imageKey;
        lastBigImageDetails = tooltip;
        return this;
    }

    public DiscordRichPresenceManager setDetails(String details) {
        lastDetails = details;
        return this;
    }

    public DiscordRichPresenceManager setStats(String stats) {
        lastStats = stats;
        return this;
    }

    public DiscordRichPresenceManager setStartTime(long time) {
        lastStartTime = time;
        return this;
    }

	/**
	 * factory-style build. Chain can be started from just an instance of this class
	 */
	public void build() {
        DiscordRPC.discordRunCallbacks();
        DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder(lastStats);
        presence.setDetails(lastDetails);
        presence.setStartTimestamps(lastStartTime);
        presence.setBigImage(lastBigImage.KEY, lastBigImageDetails);
        DiscordRPC.discordUpdatePresence(presence.build());
    }

	/**
	 * The image key is for the big picture in the presence.
	 * {@link DiscordRichPresenceManager.BigImageKeys#KEY The key} is the name of the asset as it appears in the application
	 * While similar to {@link SmallImageKeys small keys} in pulling from the same assets,
	 * there may be some that dont show up right in one or the other so build accordingly
	 */
	public enum BigImageKeys {
        NONE(""), JOJO_PFP("mariopfp");

        public final String KEY;

        BigImageKeys(String key) {
            KEY = key;
        }
    }

	/**
	 * The image key is for the small picture in the presence.
	 * {@link DiscordRichPresenceManager.SmallImageKeys#KEY The key} is the name of the asset as it appears in the application
	 * While similar to {@link BigImageKeys small keys} in pulling from the same assets,
	 * there may be some that dont show up right in one or the other so build accordingly
	 * */
	public enum SmallImageKeys {
		NONE(""), JOJO_PFP("mariopfp");

		public final String KEY;

		SmallImageKeys(String key) {
			KEY = key;
		}
	}
}
