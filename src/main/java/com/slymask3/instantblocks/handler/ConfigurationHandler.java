package com.slymask3.instantblocks.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;
    
    //public static boolean testValue = false;
    
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
	public static boolean tpFall;
	//public static Property animated;
	public static boolean tpGrinder;
	public static int[] wool = new int[11];
	//public static String checkURL;
	public static int max;
	public static int maxSuck;
	public static boolean simpleWL;
	public static boolean useWands;
	public static boolean packWood;
	public static boolean keepBlocks;
	public static int xp;
	//public static String colorBlock;
	//public static boolean rgbMode;
	public static int radiusHarvest;

    public static void init(File configFile)
    {
        // Create the configuration object from the given configuration file
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    //public static String ib = "Instant Block IDs";
	//public static String ibI = "Instant Block Item IDs";
	//public static String ach = "Achievement IDs";
	public static String options = "Options";
	public static String woolColors = "Options";
	public static String other = "Options";
	public static String cheats = "Options";
    
    private static void loadConfiguration()
    {
        //testValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, false, "This is an example configuration value");
        
        crafting = configuration.get(options, "[Option] Crafting Instant Blocks", true, "Whether to allow crafting of Instant Blocks or not.\n(Default = true)");
		dungeonChests = configuration.get(options, "[Option] Dungeon Chests", true, "Whether to generate Instant Blocks in Dungeon Chests or not.\n(Default = true)");
		bonusChest = configuration.get(options, "[Option] Bonus Chest", true, "Whether to generate the Instant Wooden House Block in the Bonus Chest or not.\n(Default = true)");
		villageChests = configuration.get(options, "[Option] Village Chests", true, "Whether to generate Instant Blocks in Village Chests or not.\n(Default = true)");
		mineshaftChests = configuration.get(options, "[Option] Mineshaft Chests", true, "Whether to generate Instant Blocks in Mineshaft Chests or not.\n(Default = true)");
		strongholdChests = configuration.get(options, "[Option] Stronghold Chests", true, "Whether to generate Instant Blocks in  Stronghold Chests or not.\n(Default = true)");
		templeChests = configuration.get(options, "[Option] Temple Chests", true, "Whether to generate Instant Blocks in Desert/Jungle Temple Chests or not.\n(Default = true)");
		tpFall = configuration.get(options, "[Option] Rainbow Skydive Teleport", true, "Whether to teleport the player to the top of the Instant Rainbow Skydive upon right-click.\n(Default = true)").getBoolean();
		tpGrinder = configuration.get(options, "[Option] Grinder Teleport", true, "Whether to teleport the player to the collection room of the Instant Grinder upon right-click.\n(Default = true)").getBoolean();
		max = configuration.get(options, "[Option] Maximum Water/Lava Blocks", 1000, "Maximum amount of water/lava blocks to create.\n(Default = 1000)").getInt();
		maxSuck = configuration.get(options, "[Option] Maximum Suction", 1000, "Maximum amount of water/lava blocks to suck in.\n(Default = 1000)").getInt();
		simpleWL = configuration.get(options, "[Option] Simple Water/Lava Blocks", false, "Only create water/lava source blocks on the block's layer, instead of the whole area.\n(Default = false)").getBoolean();
		useWands = configuration.get(options, "[Option] Use Wands", true, "Whether to use wands to create Instant Blocks.\n(Default = true)").getBoolean();
		packWood = configuration.get(options, "[Option] Pack Up House", true, "Whether to be able to pack up an Instant Wooden House.\n(Default = false)").getBoolean();
		//colorBlock = configuration.get(options, "[Option] Color Block Texture", "wool", "The texture of the block that is being recolored in InstantStatue.").getString();
		//rgbMode = configuration.get(options, "[Option] RGB Color Mode", true, "Whether to use rgb colors for statues. If false, it will use vanilla wool colors.\n(Default = true)").getBoolean();
		
		keepBlocks = configuration.get(cheats, "[Cheat] Keep Instant Blocks", false, "Whether to keep Instant Blocks after right-clicking or not.\n(Default = false)").getBoolean();
		xp = configuration.get(cheats, "[Cheat] XP From Instant Blocks", 0, "How much experience from right-clicking Instant Blocks give you.\n(Default = 0)").getInt();
		
		wool[0] = configuration.get(woolColors, "[Rainbow Skydive] Color #01", 14, "Metadata value of the first color in the Rainbow Skydive pattern.\n(Default = 14)").getInt();
		wool[1] = configuration.get(woolColors, "[Rainbow Skydive] Color #02", 1, "Metadata value of the second color in the Rainbow Skydive pattern.\n(Default = 1)").getInt();
		wool[2] = configuration.get(woolColors, "[Rainbow Skydive] Color #03", 4, "Metadata value of the third color in the Rainbow Skydive pattern.\n(Default = 4)").getInt();
		wool[3] = configuration.get(woolColors, "[Rainbow Skydive] Color #04", 5, "Metadata value of the fourth color in the Rainbow Skydive pattern.\n(Default = 5)").getInt();
		wool[4] = configuration.get(woolColors, "[Rainbow Skydive] Color #05", 13, "Metadata value of the fifth color in the Rainbow Skydive pattern.\n(Default = 13)").getInt();
		wool[5] = configuration.get(woolColors, "[Rainbow Skydive] Color #06", 9, "Metadata value of the sixth color in the Rainbow Skydive pattern.\n(Default = 9)").getInt();
		wool[6] = configuration.get(woolColors, "[Rainbow Skydive] Color #07", 3, "Metadata value of the seventh color in the Rainbow Skydive pattern.\n(Default = 3)").getInt();
		wool[7] = configuration.get(woolColors, "[Rainbow Skydive] Color #08", 11, "Metadata value of the eighth color in the Rainbow Skydive pattern.\n(Default = 11)").getInt();
		wool[8] = configuration.get(woolColors, "[Rainbow Skydive] Color #09", 10, "Metadata value of the ninth color in the Rainbow Skydive pattern.\n(Default = 10)").getInt();
		wool[9] = configuration.get(woolColors, "[Rainbow Skydive] Color #10", 2, "Metadata value of the tenth color in the Rainbow Skydive pattern.\n(Default = 2)").getInt();
		wool[10] = configuration.get(woolColors, "[Rainbow Skydive] Color #11", 6, "Metadata value of the eleventh color in the Rainbow Skydive pattern.\n(Default = 6)").getInt();

		//checkURL = configuration.get(other, "[Other] Update Checker URL", "https://dl.dropboxusercontent.com/u/23446861/Mods/InstantBlocks/check.txt", "The text document that InstantBlocks will check updates in.\nDO NOT CHANGE THIS UNLESS YOU HAVE MY CONSENT.").getString();

		msg = configuration.get(options, "[Option] Messages", true, "Whether to show InstantBlock messages or not.\n(Default = true)").getBoolean(true);
		effect = configuration.get(options, "[Option] Effect", true, "Whether to show red particle effects on right-click of Instant Blocks or not.\n(Default = true)").getBoolean(true);
		sound = configuration.get(options, "[Option] Sound", "random.levelup", "Which sound to play on right-click of Instant Blocks.\nThe directory is .minecarft\\resources\\sound3\\.\nFor example, the default sound is .minecarft\\resources\\sound3\\random\\levelup.ogg\n(Default = random.levelup)").getString();
		//animated = configuration.get(options, "[Option] Animated Textures", true, "Whether to use animated textures or not.\n(Default = true)");
		
		radiusHarvest = configuration.get(options, "[Option] Instant Harvester Radius.", 50, "Radius to Harvest blocks around Instant Harvester.\n(Default = 50)").getInt();
		
		
        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase("instantblocks"))
        {
            loadConfiguration();
        }
    }
}