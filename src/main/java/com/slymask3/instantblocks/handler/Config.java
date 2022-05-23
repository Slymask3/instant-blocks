package com.slymask3.instantblocks.handler;

import com.slymask3.instantblocks.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Config {
    public static Configuration configuration;
    
    public static boolean msg;
	public static Property crafting;
	public static Property dungeonChests;
	public static Property bonusChest;
	public static Property villageChests;
	public static Property mineshaftChests;
	public static Property strongholdChests;
	public static Property templeChests;
	public static boolean effect;
	public static String sound;
	public static boolean tpGrinder;
	public static int max;
	public static int maxSuck;
	public static boolean simpleWL;
	public static boolean useWands;
	public static boolean packWood;
	public static boolean keepBlocks;
	public static int xp;
	public static int radiusHarvest;
	public static int radiusLight;

    public static void init(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

	public static String options = "Options";
	public static String woolColors = "Options";
	public static String other = "Options";
	public static String cheats = "Options";
    
    private static void loadConfiguration() {
        crafting = configuration.get(options, "[Option] Crafting Instant Blocks", true, "Whether to allow crafting of Instant Blocks or not.\n(Default = true)");
		dungeonChests = configuration.get(options, "[Option] Dungeon Chests", true, "Whether to generate Instant Blocks in Dungeon Chests or not.\n(Default = true)");
		bonusChest = configuration.get(options, "[Option] Bonus Chest", true, "Whether to generate the Instant Wooden House Block in the Bonus Chest or not.\n(Default = true)");
		villageChests = configuration.get(options, "[Option] Village Chests", true, "Whether to generate Instant Blocks in Village Chests or not.\n(Default = true)");
		mineshaftChests = configuration.get(options, "[Option] Mineshaft Chests", true, "Whether to generate Instant Blocks in Mineshaft Chests or not.\n(Default = true)");
		strongholdChests = configuration.get(options, "[Option] Stronghold Chests", true, "Whether to generate Instant Blocks in  Stronghold Chests or not.\n(Default = true)");
		templeChests = configuration.get(options, "[Option] Temple Chests", true, "Whether to generate Instant Blocks in Desert/Jungle Temple Chests or not.\n(Default = true)");
		tpGrinder = configuration.get(options, "[Option] Grinder Teleport", true, "Whether to teleport the player to the collection room of the Instant Grinder upon right-click.\n(Default = true)").getBoolean();
		max = configuration.get(options, "[Option] Maximum Water/Lava Blocks", 1000, "Maximum amount of water/lava blocks to create.\n(Default = 1000)").getInt();
		maxSuck = configuration.get(options, "[Option] Maximum Suction", 1000, "Maximum amount of water/lava blocks to suck in.\n(Default = 1000)").getInt();
		simpleWL = configuration.get(options, "[Option] Simple Water/Lava Blocks", false, "Only create water/lava source blocks on the block's layer, instead of the whole area.\n(Default = false)").getBoolean();
		useWands = configuration.get(options, "[Option] Use Wands", true, "Whether to use wands to create Instant Blocks.\n(Default = true)").getBoolean();
		packWood = configuration.get(options, "[Option] Pack Up House", true, "Whether to be able to pack up an Instant Wooden House.\n(Default = false)").getBoolean();

		keepBlocks = configuration.get(cheats, "[Cheat] Keep Instant Blocks", false, "Whether to keep Instant Blocks after right-clicking or not.\n(Default = false)").getBoolean();
		xp = configuration.get(cheats, "[Cheat] XP From Instant Blocks", 0, "How much experience from right-clicking Instant Blocks give you.\n(Default = 0)").getInt();

		msg = configuration.get(options, "[Option] Messages", true, "Whether to show InstantBlock messages or not.\n(Default = true)").getBoolean(true);
		effect = configuration.get(options, "[Option] Effect", true, "Whether to show red particle effects on right-click of Instant Blocks or not.\n(Default = true)").getBoolean(true);
		sound = configuration.get(options, "[Option] Sound", "random.levelup", "Which sound to play on right-click of Instant Blocks.\nThe directory is .minecarft\\resources\\sound3\\.\nFor example, the default sound is .minecarft\\resources\\sound3\\random\\levelup.ogg\n(Default = random.levelup)").getString();

		radiusHarvest = configuration.get(options, "[Option] Instant Harvester Radius.", 50, "Radius to Harvest blocks around Instant Harvester.\n(Default = 50)").getInt();
		radiusLight = configuration.get(options, "[Option] Instant Light Radius.", 50, "Radius to light up dark areas around Instant Light Block.\n(Default = 50)").getInt();

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }
}