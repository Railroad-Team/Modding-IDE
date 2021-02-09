package io.github.railroad.drp;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordRichPresenceManager {
	public static final String applicationID = "808571949227049000";

	private boolean ready = false;

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

	public void build() {
		DiscordRPC.discordRunCallbacks();
		DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder(lastStats);
		presence.setDetails(lastDetails);
		presence.setStartTimestamps(lastStartTime);
		presence.setBigImage(lastBigImage.KEY, lastBigImageDetails);
		DiscordRPC.discordUpdatePresence(presence.build());
	}

	private void initDiscord() {
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
			ready = true;
			System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
			DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder("Score: ");
			presence.setDetails("Running Test");
			DiscordRPC.discordUpdatePresence(presence.build());
		}).build();
		DiscordRPC.discordInitialize(applicationID, handlers, false);
		DiscordRPC.discordRegister(applicationID, "");
	}

	public enum BigImageKeys {
		NONE(""), JOJO_PFP("mariopfp");

		public final String KEY;

		BigImageKeys(String key) {
			KEY = key;
		}
	}
}
