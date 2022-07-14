package com.slymask3.instantblocks.handler;

import com.slymask3.instantblocks.core.Config;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class ForgeConfig {
	public static class Common {
		public static BooleanValue USE_WANDS;
		public static BooleanValue TP_GRINDER;
		public static BooleanValue KEEP_BLOCKS;
		public static BooleanValue ALLOW_WATER_IN_NETHER;
		public static IntValue RADIUS_HARVEST;
		public static IntValue RADIUS_LIGHT;
		public static IntValue RAILS_AMOUNT;
		public static IntValue MINING_LADDER_LAYER;
		public static IntValue XP_AMOUNT;
		public static IntValue TREE_SIZE;
		public static IntValue RADIUS_DOME;

		public static IntValue MAX_LIQUID;
		public static IntValue MAX_FILL;
		public static BooleanValue SIMPLE_LIQUID;

		public static IntValue SKYDIVE_MIN;
		public static IntValue SKYDIVE_MAX;
		public static IntValue SKYDIVE_WATER;
		public static IntValue SKYDIVE_RADIUS;

		public static IntValue WEIGHT_WHEAT;
		public static IntValue WEIGHT_POTATOES;
		public static IntValue WEIGHT_CARROTS;
		public static IntValue WEIGHT_BEETROOTS;

		public static ConfigValue<String> HOUSE_PLANKS_ONE;
		public static ConfigValue<String> HOUSE_PLANKS_TWO;
		public static ConfigValue<String> HOUSE_LOG;
		public static ConfigValue<String> HOUSE_DOOR;

		public static BooleanValue GENERATE_IN_CHESTS;
		public static BooleanValue GENERATE_IN_CHESTS_BONUS;

		public static BooleanValue DISABLE_WOODEN_HOUSE;
		public static BooleanValue DISABLE_MINING_LADDER;
		public static BooleanValue DISABLE_GLASS_DOME;
		public static BooleanValue DISABLE_FARM;
		public static BooleanValue DISABLE_SKYDIVE;
		public static BooleanValue DISABLE_GRINDER;
		public static BooleanValue DISABLE_POOL;
		public static BooleanValue DISABLE_ESCAPE_LADDER;
		public static BooleanValue DISABLE_WATER;
		public static BooleanValue DISABLE_LAVA;
		public static BooleanValue DISABLE_SUCTION;
		public static BooleanValue DISABLE_RAIL;
		public static BooleanValue DISABLE_STATUE;
		public static BooleanValue DISABLE_HARVEST;
		public static BooleanValue DISABLE_LIGHT;
		public static BooleanValue DISABLE_SCHEMATIC;
		public static BooleanValue DISABLE_TREE;

		public static IntValue DAMAGE_WOODEN_HOUSE;
		public static IntValue DAMAGE_MINING_LADDER;
		public static IntValue DAMAGE_GLASS_DOME;
		public static IntValue DAMAGE_FARM;
		public static IntValue DAMAGE_SKYDIVE;
		public static IntValue DAMAGE_GRINDER;
		public static IntValue DAMAGE_POOL;
		public static IntValue DAMAGE_ESCAPE_LADDER;
		public static IntValue DAMAGE_WATER;
		public static IntValue DAMAGE_LAVA;
		public static IntValue DAMAGE_SUCTION;
		public static IntValue DAMAGE_RAIL;
		public static IntValue DAMAGE_STATUE;
		public static IntValue DAMAGE_HARVEST;
		public static IntValue DAMAGE_LIGHT;
		public static IntValue DAMAGE_SCHEMATIC;
		public static IntValue DAMAGE_TREE;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General settings").push("general");

			USE_WANDS = builder
					.comment("Use wands to activate instant blocks.\nDefault: true")
					.define("USE_WANDS", true);

			TP_GRINDER = builder
					.comment("Teleport the player to the collection room of the Instant Grinder.\nDefault: true")
					.define("TP_GRINDER", true);

			KEEP_BLOCKS = builder
					.comment("Keep instant blocks after activation.\nDefault: false")
					.define("KEEP_BLOCKS", false);

			ALLOW_WATER_IN_NETHER = builder
					.comment("Allow generating water in the nether.\nDefault: false")
					.define("ALLOW_WATER_IN_NETHER", false);

			RADIUS_HARVEST = builder
					.comment("Radius to harvest blocks around Instant Harvest.\nDefault: 25")
					.defineInRange("RADIUS_HARVEST", 25,1,1000);

			RADIUS_LIGHT = builder
					.comment("Radius to light up dark areas around Instant Light.\nDefault: 25")
					.defineInRange("RADIUS_LIGHT", 25,1,1000);

			RAILS_AMOUNT = builder
					.comment("Amount of rail blocks to create for Instant Rail.\nDefault: 37")
					.defineInRange("RAILS_AMOUNT", 37,1,10000);

			MINING_LADDER_LAYER = builder
					.comment("Mining layer for the Instant Mining Ladder.\nDefault: -59")
					.defineInRange("MINING_LADDER_LAYER", -59,-59,320);

			XP_AMOUNT = builder
					.comment("How much experience activating instant blocks gives you.\nDefault: 0")
					.defineInRange("XP_AMOUNT", 0,0,10000);

			TREE_SIZE = builder
					.comment("Block size for the huge tree.\nDefault: 4")
					.defineInRange("TREE_SIZE", 4,1,24);

			RADIUS_DOME = builder
					.comment("Glass dome radius.\nDefault: 4")
					.defineInRange("RADIUS_DOME", 4,1,200);

			builder.pop();

			builder.comment("Toggling instant blocks").push("toggle");

			DISABLE_WOODEN_HOUSE = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_WOODEN_HOUSE", false);

			DISABLE_MINING_LADDER = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_MINING_LADDER", false);

			DISABLE_GLASS_DOME = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_GLASS_DOME", false);

			DISABLE_FARM = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_FARM", false);

			DISABLE_SKYDIVE = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_SKYDIVE", false);

			DISABLE_GRINDER = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_GRINDER", false);

			DISABLE_POOL = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_POOL", false);

			DISABLE_ESCAPE_LADDER = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_ESCAPE_LADDER", false);

			DISABLE_WATER = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_WATER", false);

			DISABLE_LAVA = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_LAVA", false);

			DISABLE_SUCTION = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_SUCTION", false);

			DISABLE_RAIL = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_RAIL", false);

			DISABLE_STATUE = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_STATUE", false);

			DISABLE_HARVEST = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_HARVEST", false);

			DISABLE_LIGHT = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_LIGHT", false);

			DISABLE_SCHEMATIC = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_SCHEMATIC", false);

			DISABLE_TREE = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_TREE", false);

			builder.pop();

			builder.comment("Generating items in structure chests").push("structures");

			GENERATE_IN_CHESTS = builder
					.comment("Add instant blocks in loot chests.\nDefault: true")
					.worldRestart()
					.define("GENERATE_IN_CHESTS", true);

			GENERATE_IN_CHESTS_BONUS = builder
					.comment("Add an Instant Wooden House and wand in the bonus chest.\nDefault: true")
					.worldRestart()
					.define("GENERATE_IN_CHESTS_BONUS", true);

			builder.pop();

			builder.comment("Instant liquid blocks settings").push("liquid");

			MAX_LIQUID = builder
					.comment("Maximum amount of liquid blocks to generate.\nDefault: 1000")
					.defineInRange("MAX_LIQUID", 1000,1,100000);

			MAX_FILL = builder
					.comment("Maximum amount of liquid blocks to fill in.\nDefault: 1000")
					.defineInRange("MAX_FILL", 1000,1,100000);

			SIMPLE_LIQUID = builder
					.comment("Only generate liquid blocks on the block's layer, not below it.\nDefault: false")
					.define("SIMPLE_LIQUID", false);

			builder.pop();

			builder.comment("Instant skydive settings").push("skydive");

			SKYDIVE_MIN = builder
					.comment("Minimum height for the Instant Rainbow Skydive.\nDefault: -59")
					.defineInRange("SKYDIVE_MIN", -59,-64,320);

			SKYDIVE_MAX = builder
					.comment("Maximum height for the Instant Rainbow Skydive.\nDefault: 320")
					.defineInRange("SKYDIVE_MAX", 320,-64,320);

			SKYDIVE_WATER = builder
					.comment("Water height for the Instant Rainbow Skydive.\nDefault: 1")
					.defineInRange("SKYDIVE_WATER", 1,1,300);

			SKYDIVE_RADIUS = builder
					.comment("Default radius for the Instant Rainbow Skydive GUI.\nDefault: 5")
					.defineInRange("SKYDIVE_RADIUS", 5,1,9999);

			builder.pop();

			builder.comment("Instant farm settings").push("farm");

			WEIGHT_WHEAT = builder
					.comment("Weight for wheat to be chosen to plant.\nDefault: 70")
					.defineInRange("WEIGHT_WHEAT", 70,0,100);

			WEIGHT_POTATOES = builder
					.comment("Weight for potatoes to be chosen to plant.\nDefault: 10")
					.defineInRange("WEIGHT_POTATOES", 10,0,100);

			WEIGHT_CARROTS = builder
					.comment("Weight for carrots to be chosen to plant.\nDefault: 10")
					.defineInRange("WEIGHT_CARROTS", 10,0,100);

			WEIGHT_BEETROOTS = builder
					.comment("Weight for beetroot to be chosen to plant.\nDefault: 10")
					.defineInRange("WEIGHT_BEETROOTS", 10,0,100);

			builder.pop();

			builder.comment("Instant wooden house settings").push("house");

			HOUSE_PLANKS_ONE = builder
					.comment("First set of planks.\nDefault: birch")
					.define("HOUSE_PLANKS_ONE", "birch");

			HOUSE_PLANKS_TWO = builder
					.comment("Second set of planks.\nDefault: spruce")
					.define("HOUSE_PLANKS_TWO", "spruce");

			HOUSE_LOG = builder
					.comment("Log.\nDefault: birch")
					.define("HOUSE_LOG", "birch");

			HOUSE_DOOR = builder
					.comment("Door.\nDefault: dark_oak")
					.define("HOUSE_DOOR", "dark_oak");

			builder.pop();

			builder.comment("Instant wand damage amount for activating instant blocks").push("damage");

			DAMAGE_WOODEN_HOUSE = builder
					.comment("Default: 40")
					.defineInRange("DAMAGE_WOODEN_HOUSE", 40,0,2000);

			DAMAGE_MINING_LADDER = builder
					.comment("Default: 30;")
					.defineInRange("DAMAGE_MINING_LADDER", 30,0,2000);

			DAMAGE_GLASS_DOME = builder
					.comment("Default: 10")
					.defineInRange("DAMAGE_GLASS_DOME", 10,0,2000);

			DAMAGE_FARM = builder
					.comment("Default: 50")
					.defineInRange("DAMAGE_FARM", 50,0,2000);

			DAMAGE_SKYDIVE = builder
					.comment("Default: 100")
					.defineInRange("DAMAGE_SKYDIVE", 100,0,2000);

			DAMAGE_GRINDER = builder
					.comment("Default: 100")
					.defineInRange("DAMAGE_GRINDER", 100,0,2000);

			DAMAGE_POOL = builder
					.comment("Default: 40")
					.defineInRange("DAMAGE_POOL", 40,0,2000);

			DAMAGE_ESCAPE_LADDER = builder
					.comment("Default: 30")
					.defineInRange("DAMAGE_ESCAPE_LADDER", 30,0,2000);

			DAMAGE_WATER = builder
					.comment("Default: 20")
					.defineInRange("DAMAGE_WATER", 20,0,2000);

			DAMAGE_LAVA = builder
					.comment("Default: 20")
					.defineInRange("DAMAGE_LAVA", 20,0,2000);

			DAMAGE_SUCTION = builder
					.comment("Default: 20")
					.defineInRange("DAMAGE_SUCTION", 20,0,2000);

			DAMAGE_RAIL = builder
					.comment("Default: 10")
					.defineInRange("DAMAGE_RAIL", 10,0,2000);

			DAMAGE_STATUE = builder
					.comment("Default: 100")
					.defineInRange("DAMAGE_STATUE", 100,0,2000);

			DAMAGE_HARVEST = builder
					.comment("Default: 60")
					.defineInRange("DAMAGE_HARVEST", 60,0,2000);

			DAMAGE_LIGHT = builder
					.comment("Default: 60")
					.defineInRange("DAMAGE_LIGHT", 60,0,2000);

			DAMAGE_SCHEMATIC = builder
					.comment("Default: 200")
					.defineInRange("DAMAGE_SCHEMATIC", 200,0,2000);

			DAMAGE_TREE = builder
					.comment("Default: 50")
					.defineInRange("DAMAGE_TREE", 50,0,2000);

			builder.pop();
		}
	}

	public static class Client {
		public static BooleanValue SHOW_MESSAGES;
		public static BooleanValue SHOW_EFFECTS;
		public static ConfigValue<String> SOUND_GENERATE;
		public static ConfigValue<String> SOUND_NO_LIQUID;

		Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Client only settings").push("client");

			SHOW_MESSAGES = builder
					.comment("Show messages.\nDefault: true")
					.define("SHOW_MESSAGES", true);

			SHOW_EFFECTS = builder
					.comment("Show particle effects.\nDefault: true")
					.define("SHOW_EFFECTS", true);

			SOUND_GENERATE = builder
					.comment("Sound that is played on activation.\nList of sounds can be found here: https://www.digminecraft.com/lists/sound_list_pc.php or by using the /playsound command in-game.\nDefault: entity.player.levelup")
					.define("SOUND_GENERATE", "entity.player.levelup");

			SOUND_NO_LIQUID = builder
					.comment("Sound that is played when no liquid is found.\nList of sounds can be found here: https://www.digminecraft.com/lists/sound_list_pc.php or by using the /playsound command in-game.\nDefault: entity.panda.sneeze")
					.define("SOUND_NO_LIQUID", "entity.panda.sneeze");

			builder.pop();
		}
	}

	public static final ForgeConfigSpec clientSpec;
	public static final Client CLIENT;

	static {
		final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
		clientSpec = specPair.getRight();
		CLIENT = specPair.getLeft();
	}

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static void init() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ForgeConfig.commonSpec);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ForgeConfig.clientSpec);
	}

	@SubscribeEvent
	public static void onLoad(ModConfigEvent.Loading event) {
		load();
	}

	@SubscribeEvent
	public static void onReload(ModConfigEvent.Loading event) {
		load();
	}

	private static void load() {
		Config.Common.USE_WANDS = Common.USE_WANDS.get();
		Config.Common.TP_GRINDER = Common.TP_GRINDER.get();
		Config.Common.KEEP_BLOCKS = Common.KEEP_BLOCKS.get();
		Config.Common.ALLOW_WATER_IN_NETHER = Common.ALLOW_WATER_IN_NETHER.get();
		Config.Common.RADIUS_HARVEST = Common.RADIUS_HARVEST.get();
		Config.Common.RADIUS_LIGHT = Common.RADIUS_LIGHT.get();
		Config.Common.RAILS_AMOUNT = Common.RAILS_AMOUNT.get();
		Config.Common.MINING_LADDER_LAYER = Common.MINING_LADDER_LAYER.get();
		Config.Common.XP_AMOUNT = Common.XP_AMOUNT.get();
		Config.Common.TREE_SIZE = Common.TREE_SIZE.get();
		Config.Common.RADIUS_DOME = Common.RADIUS_DOME.get();

		Config.Common.MAX_LIQUID = Common.MAX_LIQUID.get();
		Config.Common.MAX_FILL = Common.MAX_FILL.get();
		Config.Common.SIMPLE_LIQUID = Common.SIMPLE_LIQUID.get();

		Config.Common.SKYDIVE_MIN = Common.SKYDIVE_MIN.get();
		Config.Common.SKYDIVE_MAX = Common.SKYDIVE_MAX.get();
		Config.Common.SKYDIVE_WATER = Common.SKYDIVE_WATER.get();
		Config.Common.SKYDIVE_RADIUS = Common.SKYDIVE_RADIUS.get();

		Config.Common.WEIGHT_WHEAT = Common.WEIGHT_WHEAT.get();
		Config.Common.WEIGHT_POTATOES = Common.WEIGHT_POTATOES.get();
		Config.Common.WEIGHT_CARROTS = Common.WEIGHT_CARROTS.get();
		Config.Common.WEIGHT_BEETROOTS = Common.WEIGHT_BEETROOTS.get();

		Config.Common.HOUSE_PLANKS_ONE = Common.HOUSE_PLANKS_ONE.get();
		Config.Common.HOUSE_PLANKS_TWO = Common.HOUSE_PLANKS_TWO.get();
		Config.Common.HOUSE_LOG = Common.HOUSE_LOG.get();
		Config.Common.HOUSE_DOOR = Common.HOUSE_DOOR.get();

		Config.Common.GENERATE_IN_CHESTS = Common.GENERATE_IN_CHESTS.get();
		Config.Common.GENERATE_IN_CHESTS_BONUS = Common.GENERATE_IN_CHESTS_BONUS.get();

		Config.Common.DISABLE_WOODEN_HOUSE = Common.DISABLE_WOODEN_HOUSE.get();
		Config.Common.DISABLE_MINING_LADDER = Common.DISABLE_MINING_LADDER.get();
		Config.Common.DISABLE_GLASS_DOME = Common.DISABLE_GLASS_DOME.get();
		Config.Common.DISABLE_FARM = Common.DISABLE_FARM.get();
		Config.Common.DISABLE_SKYDIVE = Common.DISABLE_SKYDIVE.get();
		Config.Common.DISABLE_GRINDER = Common.DISABLE_GRINDER.get();
		Config.Common.DISABLE_POOL = Common.DISABLE_POOL.get();
		Config.Common.DISABLE_ESCAPE_LADDER = Common.DISABLE_ESCAPE_LADDER.get();
		Config.Common.DISABLE_WATER = Common.DISABLE_WATER.get();
		Config.Common.DISABLE_LAVA = Common.DISABLE_LAVA.get();
		Config.Common.DISABLE_SUCTION = Common.DISABLE_SUCTION.get();
		Config.Common.DISABLE_RAIL = Common.DISABLE_RAIL.get();
		Config.Common.DISABLE_STATUE = Common.DISABLE_STATUE.get();
		Config.Common.DISABLE_HARVEST = Common.DISABLE_HARVEST.get();
		Config.Common.DISABLE_LIGHT = Common.DISABLE_LIGHT.get();
		Config.Common.DISABLE_SCHEMATIC = Common.DISABLE_SCHEMATIC.get();
		Config.Common.DISABLE_TREE = Common.DISABLE_TREE.get();

		Config.Common.DAMAGE_WOODEN_HOUSE = Common.DAMAGE_WOODEN_HOUSE.get();
		Config.Common.DAMAGE_MINING_LADDER = Common.DAMAGE_MINING_LADDER.get();
		Config.Common.DAMAGE_GLASS_DOME = Common.DAMAGE_GLASS_DOME.get();
		Config.Common.DAMAGE_FARM = Common.DAMAGE_FARM.get();
		Config.Common.DAMAGE_SKYDIVE = Common.DAMAGE_SKYDIVE.get();
		Config.Common.DAMAGE_GRINDER = Common.DAMAGE_GRINDER.get();
		Config.Common.DAMAGE_POOL = Common.DAMAGE_POOL.get();
		Config.Common.DAMAGE_ESCAPE_LADDER = Common.DAMAGE_ESCAPE_LADDER.get();
		Config.Common.DAMAGE_WATER = Common.DAMAGE_WATER.get();
		Config.Common.DAMAGE_LAVA = Common.DAMAGE_LAVA.get();
		Config.Common.DAMAGE_SUCTION = Common.DAMAGE_SUCTION.get();
		Config.Common.DAMAGE_RAIL = Common.DAMAGE_RAIL.get();
		Config.Common.DAMAGE_STATUE = Common.DAMAGE_STATUE.get();
		Config.Common.DAMAGE_HARVEST = Common.DAMAGE_HARVEST.get();
		Config.Common.DAMAGE_LIGHT = Common.DAMAGE_LIGHT.get();
		Config.Common.DAMAGE_SCHEMATIC = Common.DAMAGE_SCHEMATIC.get();
		Config.Common.DAMAGE_TREE = Common.DAMAGE_TREE.get();
		
		Config.Client.SHOW_MESSAGES = Client.SHOW_MESSAGES.get();
		Config.Client.SHOW_EFFECTS = Client.SHOW_EFFECTS.get();
		Config.Client.SOUND_GENERATE = Client.SOUND_GENERATE.get();
		Config.Client.SOUND_NO_LIQUID = Client.SOUND_NO_LIQUID.get();
	}
}