package com.slymask3.instantblocks.handler;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
	public static class Common {
		public static BooleanValue GENERATE_IN_CHESTS;
		public static BooleanValue GENERATE_IN_CHESTS_BONUS;
		public static BooleanValue TP_GRINDER;
		public static IntValue MAX_LIQUID;
		public static IntValue MAX_FILL;
		public static BooleanValue SIMPLE_LIQUID;
		public static BooleanValue USE_WANDS;
		public static IntValue RADIUS_HARVEST;
		public static IntValue RADIUS_LIGHT;
		public static BooleanValue KEEP_BLOCKS;
		public static IntValue XP_AMOUNT;
		public static IntValue RAILS_AMOUNT;
		public static IntValue SKYDIVE_MIN;
		public static IntValue SKYDIVE_MAX;
		public static IntValue SKYDIVE_WATER;
		public static IntValue MINING_LADDER_LAYER;
		public static IntValue WEIGHT_WHEAT;
		public static IntValue WEIGHT_POTATOES;
		public static IntValue WEIGHT_CARROTS;
		public static IntValue WEIGHT_BEETROOTS;
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
					.comment("Mining layer for the Instant Mining Ladder.\nDefault: 12")
					.defineInRange("MINING_LADDER_LAYER", 12,-50,255);

			XP_AMOUNT = builder
					.comment("How much experience activating instant blocks gives you.\nDefault: 0")
					.defineInRange("XP_AMOUNT", 0,0,10000);

			builder.pop();

			builder.comment("Toggling instant blocks").push("toggle");

			DISABLE_WOODEN_HOUSE = builder
					.comment("Disable Instant Wooden House\nDefault: false")
					.worldRestart()
					.define("DISABLE_WOODEN_HOUSE", false);

			DISABLE_MINING_LADDER = builder
					.comment("Disable Instant Mining Ladder\nDefault: false")
					.worldRestart()
					.define("DISABLE_MINING_LADDER", false);

			DISABLE_GLASS_DOME = builder
					.comment("Disable Instant Glass Dome\nDefault: false")
					.worldRestart()
					.define("DISABLE_GLASS_DOME", false);

			DISABLE_FARM = builder
					.comment("Disable Instant Farm\nDefault: false")
					.worldRestart()
					.define("DISABLE_FARM", false);

			DISABLE_SKYDIVE = builder
					.comment("Disable Instant Rainbow Skydive\nDefault: false")
					.worldRestart()
					.define("DISABLE_SKYDIVE", false);

			DISABLE_GRINDER = builder
					.comment("Disable Instant Grinder\nDefault: false")
					.worldRestart()
					.define("DISABLE_GRINDER", false);

			DISABLE_POOL = builder
					.comment("Disable Instant Pool\nDefault: false")
					.worldRestart()
					.define("DISABLE_POOL", false);

			DISABLE_ESCAPE_LADDER = builder
					.comment("Disable Instant Escape Ladder\nDefault: false")
					.worldRestart()
					.define("DISABLE_ESCAPE_LADDER", false);

			DISABLE_WATER = builder
					.comment("Disable Instant Water\nDefault: false")
					.worldRestart()
					.define("DISABLE_WATER", false);

			DISABLE_LAVA = builder
					.comment("Disable Instant Lava\nDefault: false")
					.worldRestart()
					.define("DISABLE_LAVA", false);

			DISABLE_SUCTION = builder
					.comment("Disable Instant Suction\nDefault: false")
					.worldRestart()
					.define("DISABLE_SUCTION", false);

			DISABLE_RAIL = builder
					.comment("Disable Instant Rail\nDefault: false")
					.worldRestart()
					.define("DISABLE_RAIL", false);

			DISABLE_STATUE = builder
					.comment("Disable Instant Statue\nDefault: false")
					.worldRestart()
					.define("DISABLE_STATUE", false);

			DISABLE_HARVEST = builder
					.comment("Disable Instant Harvest\nDefault: false")
					.worldRestart()
					.define("DISABLE_HARVEST", false);

			DISABLE_LIGHT = builder
					.comment("Disable Instant Light\nDefault: false")
					.worldRestart()
					.define("DISABLE_LIGHT", false);

			DISABLE_SCHEMATIC = builder
					.comment("Disable Instant Schematic\nDefault: false")
					.worldRestart()
					.define("DISABLE_SCHEMATIC", false);

			DISABLE_TREE = builder
					.comment("Disable Instant Tree\nDefault: false")
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

			builder.comment("Instant skydive block settings").push("skydive");

			SKYDIVE_MIN = builder
					.comment("Minimum height for the Instant Rainbow Skydive.\nDefault: -59")
					.defineInRange("SKYDIVE_MIN", -59,-64,320);

			SKYDIVE_MAX = builder
					.comment("Maximum height for the Instant Rainbow Skydive.\nDefault: 320")
					.defineInRange("SKYDIVE_MAX", 320,-64,320);

			SKYDIVE_WATER = builder
					.comment("Water height for the Instant Rainbow Skydive.\nDefault: 1")
					.defineInRange("SKYDIVE_WATER", 1,1,300);

			builder.pop();

			builder.comment("Instant farm block settings").push("farm");

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
		}
	}

	public static class Client {
		public static BooleanValue SHOW_MESSAGES;
		public static BooleanValue SHOW_EFFECTS;
		public static ConfigValue<String> SOUND;
		public static IntValue SKYDIVE_RADIUS;

		Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Client only settings").push("client");

			SHOW_MESSAGES = builder
					.comment("Show messages.\nDefault: true")
					.define("SHOW_MESSAGES", true);

			SHOW_EFFECTS = builder
					.comment("Show particle effects.\nDefault: true")
					.define("SHOW_EFFECTS", true);

			SOUND = builder
					.comment("Sound that is played on activation.\nList of sounds can be found here: https://www.digminecraft.com/lists/sound_list_pc.php or by using the /playsound command in-game.\nDefault: entity.player.levelup")
					.define("SOUND", "entity.player.levelup");

			SKYDIVE_RADIUS = builder
					.comment("Default radius for the Instant Rainbow Skydive GUI.\nDefault: 5")
					.defineInRange("SKYDIVE_RADIUS", 5,1,1000);

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
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientSpec);
	}
}