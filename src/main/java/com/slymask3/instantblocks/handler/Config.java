package com.slymask3.instantblocks.handler;

import com.slymask3.instantblocks.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {
    public static Configuration configuration;

	public static String GENERAL = "general";
    public static boolean SHOW_MESSAGES;
	public static boolean ALLOW_CRAFTING;
	public static boolean GENERATE_IN_CHESTS_DUNGEON;
	public static boolean GENERATE_IN_CHESTS_BONUS;
	public static boolean GENERATE_IN_CHESTS_VILLAGE;
	public static boolean GENERATE_IN_CHESTS_MINESHAFT;
	public static boolean GENERATE_IN_CHESTS_STRONGHOLD;
	public static boolean GENERATE_IN_CHESTS_TEMPLE;
	public static boolean SHOW_EFFECTS;
	public static String SOUND;
	public static boolean TP_GRINDER;
	public static int MAX_LIQUID;
	public static int MAX_FILL;
	public static boolean SIMPLE_LIQUID;
	public static boolean USE_WANDS;
	public static boolean PACK_HOUSE;
	public static int RADIUS_HARVEST;
	public static int RADIUS_LIGHT;
	public static boolean KEEP_BLOCKS;
	public static int XP_AMOUNT;
	public static int RAILS_AMOUNT;
	public static String PARTICLE;
	public static int SKYDIVE_RADIUS;
	public static int SKYDIVE_MIN;
	public static int SKYDIVE_MAX;
	public static int SKYDIVE_WATER;
	public static int MINING_LADDER_LAYER;

	public static String BLOCKS = "blocks";
	public static boolean ADD_WOODEN_HOUSE;
	public static boolean ADD_MINING_LADDER;
	public static boolean ADD_GLASS_DOME;
	public static boolean ADD_FARM;
	public static boolean ADD_SKYDIVE;
	public static boolean ADD_GRINDER;
	public static boolean ADD_POOL;
	public static boolean ADD_ESCAPE_LADDER;
	public static boolean ADD_WATER;
	public static boolean ADD_LAVA;
	public static boolean ADD_SUCTION;
	public static boolean ADD_RAIL;
	public static boolean ADD_STATUE;
	public static boolean ADD_HARVEST;
	public static boolean ADD_LIGHT;
	public static boolean ADD_SCHEMATIC;
	public static boolean ADD_TREE;

    public static void init(File configFile) {
        if(configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
		ALLOW_CRAFTING = configuration.get(GENERAL, "ALLOW_CRAFTING", true, "Whether to allow crafting of Instant Blocks.\n(Default = true)").getBoolean();
		GENERATE_IN_CHESTS_DUNGEON = configuration.get(GENERAL, "GENERATE_IN_CHESTS_DUNGEON", true, "Whether to generate Instant Blocks in Dungeon Chests.\n(Default = true)").getBoolean();
		GENERATE_IN_CHESTS_BONUS = configuration.get(GENERAL, "GENERATE_IN_CHESTS_BONUS", true, "Whether to generate the Instant Wooden House Block in the Bonus Chest.\n(Default = true)").getBoolean();
		GENERATE_IN_CHESTS_VILLAGE = configuration.get(GENERAL, "GENERATE_IN_CHESTS_VILLAGE", true, "Whether to generate Instant Blocks in Village Chests.\n(Default = true)").getBoolean();
		GENERATE_IN_CHESTS_MINESHAFT = configuration.get(GENERAL, "GENERATE_IN_CHESTS_MINESHAFT", true, "Whether to generate Instant Blocks in Mineshaft Chests.\n(Default = true)").getBoolean();
		GENERATE_IN_CHESTS_STRONGHOLD = configuration.get(GENERAL, "GENERATE_IN_CHESTS_STRONGHOLD", true, "Whether to generate Instant Blocks in  Stronghold Chests.\n(Default = true)").getBoolean();
		GENERATE_IN_CHESTS_TEMPLE = configuration.get(GENERAL, "GENERATE_IN_CHESTS_TEMPLE", true, "Whether to generate Instant Blocks in Desert/Jungle Temple Chests.\n(Default = true)").getBoolean();
		TP_GRINDER = configuration.get(GENERAL, "TP_GRINDER", true, "Whether to teleport the player to the collection room of the Instant Grinder upon right-click.\n(Default = true)").getBoolean();
		MAX_LIQUID = configuration.get(GENERAL, "MAX_LIQUID", 1000, "Maximum amount of liquid blocks to create.\n(Default = 1000)").getInt();
		MAX_FILL = configuration.get(GENERAL, "MAX_FILL", 1000, "Maximum amount of liquid blocks to fill in.\n(Default = 1000)").getInt();
		SIMPLE_LIQUID = configuration.get(GENERAL, "SIMPLE_LIQUID", false, "Only create liquid source blocks on the block's layer, instead of the whole area.\n(Default = false)").getBoolean();
		USE_WANDS = configuration.get(GENERAL, "USE_WANDS", true, "Whether to use wands to create Instant Blocks.\n(Default = true)").getBoolean();
		PACK_HOUSE = configuration.get(GENERAL, "PACK_HOUSE", false, "Whether to be able to pack up an Instant Wooden House.\n(Default = false)").getBoolean();
		SHOW_MESSAGES = configuration.get(GENERAL, "SHOW_MESSAGES", true, "Whether to show mod messages.\n(Default = true)").getBoolean(true);
		SHOW_EFFECTS = configuration.get(GENERAL, "SHOW_EFFECTS", true, "Whether to show particle effects on activation.\n(Default = true)").getBoolean(true);
		SOUND = configuration.get(GENERAL, "SOUND", "random.levelup", "Which sound is played on activation.\nThe directory is .minecraft\\resources\\sound3\\.\nFor example, the default sound is .minecraft\\resources\\sound3\\random\\levelup.ogg\n(Default = random.levelup)").getString();
		RADIUS_HARVEST = configuration.get(GENERAL, "RADIUS_HARVEST", 50, "Radius to Harvest blocks around Instant Harvester.\n(Default = 50)").getInt();
		RADIUS_LIGHT = configuration.get(GENERAL, "RADIUS_LIGHT", 50, "Radius to light up dark areas around Instant Light Block.\n(Default = 50)").getInt();
		KEEP_BLOCKS = configuration.get(GENERAL, "KEEP_BLOCKS", false, "Whether to keep Instant Blocks after activation.\n(Default = false)").getBoolean();
		XP_AMOUNT = configuration.get(GENERAL, "XP_AMOUNT", 0, "How much experience activating Instant Blocks gives you.\n(Default = 0)").getInt();
		RAILS_AMOUNT = configuration.get(GENERAL, "RAILS_AMOUNT", 37, "Amount of rail blocks to create for Instant Rail Block.\n(Default = 37)").getInt();
		PARTICLE = configuration.get(GENERAL, "PARTICLE", "reddust", "Which particles are generated on activation.\n(Default = reddust)").getString();
		SKYDIVE_RADIUS = configuration.get(GENERAL, "SKYDIVE_RADIUS", 5, "Default radius for the Instant Rainbow Skydive.\n(Default = 5)").getInt();
		SKYDIVE_MIN = configuration.get(GENERAL, "SKYDIVE_MIN", 5, "Minimum height for the Instant Rainbow Skydive.\n(Default = 5)").getInt();
		SKYDIVE_MAX = configuration.get(GENERAL, "SKYDIVE_MAX", 255, "Maximum height for the Instant Rainbow Skydive.\n(Default = 255)").getInt();
		SKYDIVE_WATER = configuration.get(GENERAL, "SKYDIVE_WATER", 1, "Water height for the Instant Rainbow Skydive.\n(Default = 1)").getInt();
		MINING_LADDER_LAYER = configuration.get(GENERAL, "MINING_LADDER_LAYER", 12, "Mining layer for the Instant Mining Ladder.\n(Default = 12)").getInt();

		ADD_WOODEN_HOUSE = configuration.get(BLOCKS, "ADD_WOODEN_HOUSE", true, "Add Instant Wooden House\n(Default = true)").getBoolean();
		ADD_MINING_LADDER = configuration.get(BLOCKS, "ADD_MINING_LADDER", true, "Add Instant Mining Ladder\n(Default = true)").getBoolean();
		ADD_GLASS_DOME = configuration.get(BLOCKS, "ADD_GLASS_DOME", true, "Add Instant Glass Dome\n(Default = true)").getBoolean();
		ADD_FARM = configuration.get(BLOCKS, "ADD_FARM", true, "Add Instant Farm\n(Default = true)").getBoolean();
		ADD_SKYDIVE = configuration.get(BLOCKS, "ADD_SKYDIVE", true, "Add Instant Rainbow Skydive\n(Default = true)").getBoolean();
		ADD_GRINDER = configuration.get(BLOCKS, "ADD_GRINDER", true, "Add Instant Grinder\n(Default = true)").getBoolean();
		ADD_POOL = configuration.get(BLOCKS, "ADD_POOL", true, "Add Instant Pool\n(Default = true)").getBoolean();
		ADD_ESCAPE_LADDER = configuration.get(BLOCKS, "ADD_ESCAPE_LADDER", true, "Add Instant Escape Ladder\n(Default = true)").getBoolean();
		ADD_WATER = configuration.get(BLOCKS, "ADD_WATER", true, "Add Instant Water\n(Default = true)").getBoolean();
		ADD_LAVA = configuration.get(BLOCKS, "ADD_LAVA", true, "Add Instant Lava\n(Default = true)").getBoolean();
		ADD_SUCTION = configuration.get(BLOCKS, "ADD_SUCTION", true, "Add Instant Suction\n(Default = true)").getBoolean();
		ADD_RAIL = configuration.get(BLOCKS, "ADD_RAIL", true, "Add Instant Rail\n(Default = true)").getBoolean();
		ADD_STATUE = configuration.get(BLOCKS, "ADD_STATUE", true, "Add Instant Statue\n(Default = true)").getBoolean();
		ADD_HARVEST = configuration.get(BLOCKS, "ADD_HARVEST", true, "Add Instant Harvest\n(Default = true)").getBoolean();
		ADD_LIGHT = configuration.get(BLOCKS, "ADD_LIGHT", true, "Add Instant Light\n(Default = true)").getBoolean();
		ADD_SCHEMATIC = configuration.get(BLOCKS, "ADD_SCHEMATIC", true, "Add Instant Schematic\n(Default = true)").getBoolean();
		ADD_TREE = configuration.get(BLOCKS, "ADD_TREE", true, "Add Instant Tree\n(Default = true)").getBoolean();

        if(configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }
}