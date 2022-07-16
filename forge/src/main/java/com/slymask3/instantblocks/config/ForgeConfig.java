package com.slymask3.instantblocks.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class ForgeConfig implements IConfig {
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
					.define("USE_WANDS", Defaults.USE_WANDS);

			TP_GRINDER = builder
					.comment("Teleport the player to the collection room of the Instant Grinder.\nDefault: true")
					.define("TP_GRINDER", Defaults.TP_GRINDER);

			KEEP_BLOCKS = builder
					.comment("Keep instant blocks after activation.\nDefault: false")
					.define("KEEP_BLOCKS", Defaults.KEEP_BLOCKS);

			ALLOW_WATER_IN_NETHER = builder
					.comment("Allow generating water in the nether.\nDefault: false")
					.define("ALLOW_WATER_IN_NETHER", Defaults.ALLOW_WATER_IN_NETHER);

			RADIUS_HARVEST = builder
					.comment("Radius to harvest blocks around Instant Harvest.\nDefault: 25")
					.defineInRange("RADIUS_HARVEST", Defaults.RADIUS_HARVEST,1,1000);

			RADIUS_LIGHT = builder
					.comment("Radius to light up dark areas around Instant Light.\nDefault: 25")
					.defineInRange("RADIUS_LIGHT", Defaults.RADIUS_LIGHT,1,1000);

			RAILS_AMOUNT = builder
					.comment("Amount of rail blocks to create for Instant Rail.\nDefault: 37")
					.defineInRange("RAILS_AMOUNT", Defaults.RAILS_AMOUNT,1,10000);

			MINING_LADDER_LAYER = builder
					.comment("Mining layer for the Instant Mining Ladder.\nDefault: -59")
					.defineInRange("MINING_LADDER_LAYER", Defaults.MINING_LADDER_LAYER,-59,320);

			XP_AMOUNT = builder
					.comment("How much experience activating instant blocks gives you.\nDefault: 0")
					.defineInRange("XP_AMOUNT", Defaults.XP_AMOUNT,0,10000);

			TREE_SIZE = builder
					.comment("Block size for the huge tree.\nDefault: 4")
					.defineInRange("TREE_SIZE", Defaults.TREE_SIZE,1,24);

			RADIUS_DOME = builder
					.comment("Glass dome radius.\nDefault: 4")
					.defineInRange("RADIUS_DOME", Defaults.RADIUS_DOME,1,200);

			builder.pop();

			builder.comment("Toggling instant blocks").push("toggle");

			DISABLE_WOODEN_HOUSE = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_WOODEN_HOUSE", Defaults.DISABLE_WOODEN_HOUSE);

			DISABLE_MINING_LADDER = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_MINING_LADDER", Defaults.DISABLE_MINING_LADDER);

			DISABLE_GLASS_DOME = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_GLASS_DOME", Defaults.DISABLE_GLASS_DOME);

			DISABLE_FARM = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_FARM", Defaults.DISABLE_FARM);

			DISABLE_SKYDIVE = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_SKYDIVE", Defaults.DISABLE_SKYDIVE);

			DISABLE_GRINDER = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_GRINDER", Defaults.DISABLE_GRINDER);

			DISABLE_POOL = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_POOL", Defaults.DISABLE_POOL);

			DISABLE_ESCAPE_LADDER = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_ESCAPE_LADDER", Defaults.DISABLE_ESCAPE_LADDER);

			DISABLE_WATER = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_WATER", Defaults.DISABLE_WATER);

			DISABLE_LAVA = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_LAVA", Defaults.DISABLE_LAVA);

			DISABLE_SUCTION = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_SUCTION", Defaults.DISABLE_SUCTION);

			DISABLE_RAIL = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_RAIL", Defaults.DISABLE_RAIL);

			DISABLE_STATUE = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_STATUE", Defaults.DISABLE_STATUE);

			DISABLE_HARVEST = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_HARVEST", Defaults.DISABLE_HARVEST);

			DISABLE_LIGHT = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_LIGHT", Defaults.DISABLE_LIGHT);

			DISABLE_SCHEMATIC = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_SCHEMATIC", Defaults.DISABLE_SCHEMATIC);

			DISABLE_TREE = builder
					.comment("Default: false")
					.worldRestart()
					.define("DISABLE_TREE", Defaults.DISABLE_TREE);

			builder.pop();

			builder.comment("Generating items in structure chests").push("structures");

			GENERATE_IN_CHESTS = builder
					.comment("Add instant blocks in loot chests.\nDefault: true")
					.worldRestart()
					.define("GENERATE_IN_CHESTS", Defaults.GENERATE_IN_CHESTS);

			GENERATE_IN_CHESTS_BONUS = builder
					.comment("Add an Instant Wooden House and wand in the bonus chest.\nDefault: true")
					.worldRestart()
					.define("GENERATE_IN_CHESTS_BONUS", Defaults.GENERATE_IN_CHESTS_BONUS);

			builder.pop();

			builder.comment("Instant liquid blocks settings").push("liquid");

			MAX_LIQUID = builder
					.comment("Maximum amount of liquid blocks to generate.\nDefault: 1000")
					.defineInRange("MAX_LIQUID", Defaults.MAX_LIQUID,1,100000);

			MAX_FILL = builder
					.comment("Maximum amount of liquid blocks to fill in.\nDefault: 1000")
					.defineInRange("MAX_FILL", Defaults.MAX_FILL,1,100000);

			SIMPLE_LIQUID = builder
					.comment("Only generate liquid blocks on the block's layer, not below it.\nDefault: false")
					.define("SIMPLE_LIQUID", Defaults.SIMPLE_LIQUID);

			builder.pop();

			builder.comment("Instant skydive settings").push("skydive");

			SKYDIVE_MIN = builder
					.comment("Minimum height for the Instant Rainbow Skydive.\nDefault: -59")
					.defineInRange("SKYDIVE_MIN", Defaults.SKYDIVE_MIN,-64,320);

			SKYDIVE_MAX = builder
					.comment("Maximum height for the Instant Rainbow Skydive.\nDefault: 320")
					.defineInRange("SKYDIVE_MAX", Defaults.SKYDIVE_MAX,-64,320);

			SKYDIVE_WATER = builder
					.comment("Water height for the Instant Rainbow Skydive.\nDefault: 1")
					.defineInRange("SKYDIVE_WATER", Defaults.SKYDIVE_WATER,1,300);

			SKYDIVE_RADIUS = builder
					.comment("Default radius for the Instant Rainbow Skydive GUI.\nDefault: 5")
					.defineInRange("SKYDIVE_RADIUS", Defaults.SKYDIVE_RADIUS,1,9999);

			builder.pop();

			builder.comment("Instant farm settings").push("farm");

			WEIGHT_WHEAT = builder
					.comment("Weight for wheat to be chosen to plant.\nDefault: 70")
					.defineInRange("WEIGHT_WHEAT", Defaults.WEIGHT_WHEAT,0,100);

			WEIGHT_POTATOES = builder
					.comment("Weight for potatoes to be chosen to plant.\nDefault: 10")
					.defineInRange("WEIGHT_POTATOES", Defaults.WEIGHT_POTATOES,0,100);

			WEIGHT_CARROTS = builder
					.comment("Weight for carrots to be chosen to plant.\nDefault: 10")
					.defineInRange("WEIGHT_CARROTS", Defaults.WEIGHT_CARROTS,0,100);

			WEIGHT_BEETROOTS = builder
					.comment("Weight for beetroot to be chosen to plant.\nDefault: 10")
					.defineInRange("WEIGHT_BEETROOTS", Defaults.WEIGHT_BEETROOTS,0,100);

			builder.pop();

			builder.comment("Instant wooden house settings").push("house");

			HOUSE_PLANKS_ONE = builder
					.comment("First set of planks.\nDefault: birch")
					.define("HOUSE_PLANKS_ONE", Defaults.HOUSE_PLANKS_ONE);

			HOUSE_PLANKS_TWO = builder
					.comment("Second set of planks.\nDefault: spruce")
					.define("HOUSE_PLANKS_TWO", Defaults.HOUSE_PLANKS_TWO);

			HOUSE_LOG = builder
					.comment("Log.\nDefault: birch")
					.define("HOUSE_LOG", Defaults.HOUSE_LOG);

			HOUSE_DOOR = builder
					.comment("Door.\nDefault: dark_oak")
					.define("HOUSE_DOOR", Defaults.HOUSE_DOOR);

			builder.pop();

			builder.comment("Instant wand damage amount for activating instant blocks").push("damage");

			DAMAGE_WOODEN_HOUSE = builder
					.comment("Default: 40")
					.defineInRange("DAMAGE_WOODEN_HOUSE", Defaults.DAMAGE_WOODEN_HOUSE,0,2000);

			DAMAGE_MINING_LADDER = builder
					.comment("Default: 30;")
					.defineInRange("DAMAGE_MINING_LADDER", Defaults.DAMAGE_MINING_LADDER,0,2000);

			DAMAGE_GLASS_DOME = builder
					.comment("Default: 10")
					.defineInRange("DAMAGE_GLASS_DOME", Defaults.DAMAGE_GLASS_DOME,0,2000);

			DAMAGE_FARM = builder
					.comment("Default: 50")
					.defineInRange("DAMAGE_FARM", Defaults.DAMAGE_FARM,0,2000);

			DAMAGE_SKYDIVE = builder
					.comment("Default: 100")
					.defineInRange("DAMAGE_SKYDIVE", Defaults.DAMAGE_SKYDIVE,0,2000);

			DAMAGE_GRINDER = builder
					.comment("Default: 100")
					.defineInRange("DAMAGE_GRINDER", Defaults.DAMAGE_GRINDER,0,2000);

			DAMAGE_POOL = builder
					.comment("Default: 40")
					.defineInRange("DAMAGE_POOL", Defaults.DAMAGE_POOL,0,2000);

			DAMAGE_ESCAPE_LADDER = builder
					.comment("Default: 30")
					.defineInRange("DAMAGE_ESCAPE_LADDER", Defaults.DAMAGE_ESCAPE_LADDER,0,2000);

			DAMAGE_WATER = builder
					.comment("Default: 20")
					.defineInRange("DAMAGE_WATER", Defaults.DAMAGE_WATER,0,2000);

			DAMAGE_LAVA = builder
					.comment("Default: 20")
					.defineInRange("DAMAGE_LAVA", Defaults.DAMAGE_LAVA,0,2000);

			DAMAGE_SUCTION = builder
					.comment("Default: 20")
					.defineInRange("DAMAGE_SUCTION", Defaults.DAMAGE_SUCTION,0,2000);

			DAMAGE_RAIL = builder
					.comment("Default: 10")
					.defineInRange("DAMAGE_RAIL", Defaults.DAMAGE_RAIL,0,2000);

			DAMAGE_STATUE = builder
					.comment("Default: 100")
					.defineInRange("DAMAGE_STATUE", Defaults.DAMAGE_STATUE,0,2000);

			DAMAGE_HARVEST = builder
					.comment("Default: 60")
					.defineInRange("DAMAGE_HARVEST", Defaults.DAMAGE_HARVEST,0,2000);

			DAMAGE_LIGHT = builder
					.comment("Default: 60")
					.defineInRange("DAMAGE_LIGHT", Defaults.DAMAGE_LIGHT,0,2000);

			DAMAGE_SCHEMATIC = builder
					.comment("Default: 200")
					.defineInRange("DAMAGE_SCHEMATIC", Defaults.DAMAGE_SCHEMATIC,0,2000);

			DAMAGE_TREE = builder
					.comment("Default: 50")
					.defineInRange("DAMAGE_TREE", Defaults.DAMAGE_TREE,0,2000);

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
					.define("SHOW_MESSAGES", Defaults.SHOW_MESSAGES);

			SHOW_EFFECTS = builder
					.comment("Show particle effects.\nDefault: true")
					.define("SHOW_EFFECTS", Defaults.SHOW_EFFECTS);

			SOUND_GENERATE = builder
					.comment("Sound that is played on activation.\nList of sounds can be found here: https://www.digminecraft.com/lists/sound_list_pc.php or by using the /playsound command in-game.\nDefault: entity.player.levelup")
					.define("SOUND_GENERATE", Defaults.SOUND_GENERATE);

			SOUND_NO_LIQUID = builder
					.comment("Sound that is played when no liquid is found.\nList of sounds can be found here: https://www.digminecraft.com/lists/sound_list_pc.php or by using the /playsound command in-game.\nDefault: entity.panda.sneeze")
					.define("SOUND_NO_LIQUID", Defaults.SOUND_NO_LIQUID);

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

	public boolean USE_WANDS() { return Common.USE_WANDS.get(); }
	public boolean TP_GRINDER() { return Common.TP_GRINDER.get(); }
	public boolean KEEP_BLOCKS() { return Common.KEEP_BLOCKS.get(); }
	public boolean ALLOW_WATER_IN_NETHER() { return Common.ALLOW_WATER_IN_NETHER.get(); }
	public int RADIUS_HARVEST() { return Common.RADIUS_HARVEST.get(); }
	public int RADIUS_LIGHT() { return Common.RADIUS_LIGHT.get(); }
	public int RAILS_AMOUNT() { return Common.RAILS_AMOUNT.get(); }
	public int MINING_LADDER_LAYER() { return Common.MINING_LADDER_LAYER.get(); }
	public int XP_AMOUNT() { return Common.XP_AMOUNT.get(); }
	public int TREE_SIZE() { return Common.TREE_SIZE.get(); }
	public int RADIUS_DOME() { return Common.RADIUS_DOME.get(); }
	public int MAX_LIQUID() { return Common.MAX_LIQUID.get(); }
	public int MAX_FILL() { return Common.MAX_FILL.get(); }
	public boolean SIMPLE_LIQUID() { return Common.SIMPLE_LIQUID.get(); }
	public int SKYDIVE_MIN() { return Common.SKYDIVE_MIN.get(); }
	public int SKYDIVE_MAX() { return Common.SKYDIVE_MAX.get(); }
	public int SKYDIVE_WATER() { return Common.SKYDIVE_WATER.get(); }
	public int SKYDIVE_RADIUS() { return Common.SKYDIVE_RADIUS.get(); }
	public int WEIGHT_WHEAT() { return Common.WEIGHT_WHEAT.get(); }
	public int WEIGHT_POTATOES() { return Common.WEIGHT_POTATOES.get(); }
	public int WEIGHT_CARROTS() { return Common.WEIGHT_CARROTS.get(); }
	public int WEIGHT_BEETROOTS() { return Common.WEIGHT_BEETROOTS.get(); }
	public String HOUSE_PLANKS_ONE() { return Common.HOUSE_PLANKS_ONE.get(); }
	public String HOUSE_PLANKS_TWO() { return Common.HOUSE_PLANKS_TWO.get(); }
	public String HOUSE_LOG() { return Common.HOUSE_LOG.get(); }
	public String HOUSE_DOOR() { return Common.HOUSE_DOOR.get(); }
	public boolean GENERATE_IN_CHESTS() { return Common.GENERATE_IN_CHESTS.get(); }
	public boolean GENERATE_IN_CHESTS_BONUS() { return Common.GENERATE_IN_CHESTS_BONUS.get(); }
	public boolean DISABLE_WOODEN_HOUSE() { return Common.DISABLE_WOODEN_HOUSE.get(); }
	public boolean DISABLE_MINING_LADDER() { return Common.DISABLE_MINING_LADDER.get(); }
	public boolean DISABLE_GLASS_DOME() { return Common.DISABLE_GLASS_DOME.get(); }
	public boolean DISABLE_FARM() { return Common.DISABLE_FARM.get(); }
	public boolean DISABLE_SKYDIVE() { return Common.DISABLE_SKYDIVE.get(); }
	public boolean DISABLE_GRINDER() { return Common.DISABLE_GRINDER.get(); }
	public boolean DISABLE_POOL() { return Common.DISABLE_POOL.get(); }
	public boolean DISABLE_ESCAPE_LADDER() { return Common.DISABLE_ESCAPE_LADDER.get(); }
	public boolean DISABLE_WATER() { return Common.DISABLE_WATER.get(); }
	public boolean DISABLE_LAVA() { return Common.DISABLE_LAVA.get(); }
	public boolean DISABLE_SUCTION() { return Common.DISABLE_SUCTION.get(); }
	public boolean DISABLE_RAIL() { return Common.DISABLE_RAIL.get(); }
	public boolean DISABLE_STATUE() { return Common.DISABLE_STATUE.get(); }
	public boolean DISABLE_HARVEST() { return Common.DISABLE_HARVEST.get(); }
	public boolean DISABLE_LIGHT() { return Common.DISABLE_LIGHT.get(); }
	public boolean DISABLE_SCHEMATIC() { return Common.DISABLE_SCHEMATIC.get(); }
	public boolean DISABLE_TREE() { return Common.DISABLE_TREE.get(); }
	public int DAMAGE_WOODEN_HOUSE() { return Common.DAMAGE_WOODEN_HOUSE.get(); }
	public int DAMAGE_MINING_LADDER() { return Common.DAMAGE_MINING_LADDER.get(); }
	public int DAMAGE_GLASS_DOME() { return Common.DAMAGE_GLASS_DOME.get(); }
	public int DAMAGE_FARM() { return Common.DAMAGE_FARM.get(); }
	public int DAMAGE_SKYDIVE() { return Common.DAMAGE_SKYDIVE.get(); }
	public int DAMAGE_GRINDER() { return Common.DAMAGE_GRINDER.get(); }
	public int DAMAGE_POOL() { return Common.DAMAGE_POOL.get(); }
	public int DAMAGE_ESCAPE_LADDER() { return Common.DAMAGE_ESCAPE_LADDER.get(); }
	public int DAMAGE_WATER() { return Common.DAMAGE_WATER.get(); }
	public int DAMAGE_LAVA() { return Common.DAMAGE_LAVA.get(); }
	public int DAMAGE_SUCTION() { return Common.DAMAGE_SUCTION.get(); }
	public int DAMAGE_RAIL() { return Common.DAMAGE_RAIL.get(); }
	public int DAMAGE_STATUE() { return Common.DAMAGE_STATUE.get(); }
	public int DAMAGE_HARVEST() { return Common.DAMAGE_HARVEST.get(); }
	public int DAMAGE_LIGHT() { return Common.DAMAGE_LIGHT.get(); }
	public int DAMAGE_SCHEMATIC() { return Common.DAMAGE_SCHEMATIC.get(); }
	public int DAMAGE_TREE() { return Common.DAMAGE_TREE.get(); }
	public boolean SHOW_MESSAGES() { return Client.SHOW_MESSAGES.get(); }
	public boolean SHOW_EFFECTS() { return Client.SHOW_EFFECTS.get(); }
	public String SOUND_GENERATE() { return Client.SOUND_GENERATE.get(); }
	public String SOUND_NO_LIQUID() { return Client.SOUND_NO_LIQUID.get(); }
}