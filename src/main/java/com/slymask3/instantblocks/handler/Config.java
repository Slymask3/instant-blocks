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
		public static BooleanValue GENERATE_IN_CHESTS_DUNGEON;
		public static BooleanValue GENERATE_IN_CHESTS_BONUS;
		public static BooleanValue GENERATE_IN_CHESTS_VILLAGE;
		public static BooleanValue GENERATE_IN_CHESTS_MINESHAFT;
		public static BooleanValue GENERATE_IN_CHESTS_STRONGHOLD;
		public static BooleanValue GENERATE_IN_CHESTS_TEMPLE;
		public static BooleanValue TP_GRINDER;
		public static IntValue MAX_LIQUID;
		public static IntValue MAX_FILL;
		public static BooleanValue SIMPLE_LIQUID;
		public static BooleanValue USE_WANDS;
		//public static BooleanValue PACK_HOUSE;
		public static IntValue RADIUS_HARVEST;
		public static IntValue RADIUS_LIGHT;
		public static BooleanValue KEEP_BLOCKS;
		public static IntValue XP_AMOUNT;
		public static IntValue RAILS_AMOUNT;
		public static IntValue SKYDIVE_RADIUS;
		public static IntValue SKYDIVE_MIN;
		public static IntValue SKYDIVE_MAX;
		public static IntValue SKYDIVE_WATER;
		public static IntValue MINING_LADDER_LAYER;

		public static BooleanValue ALLOW_CRAFTING;
		public static BooleanValue ADD_WOODEN_HOUSE;
		public static BooleanValue ADD_MINING_LADDER;
		public static BooleanValue ADD_GLASS_DOME;
		public static BooleanValue ADD_FARM;
		public static BooleanValue ADD_SKYDIVE;
		public static BooleanValue ADD_GRINDER;
		public static BooleanValue ADD_POOL;
		public static BooleanValue ADD_ESCAPE_LADDER;
		public static BooleanValue ADD_WATER;
		public static BooleanValue ADD_LAVA;
		public static BooleanValue ADD_SUCTION;
		public static BooleanValue ADD_RAIL;
		public static BooleanValue ADD_STATUE;
		public static BooleanValue ADD_HARVEST;
		public static BooleanValue ADD_LIGHT;
		public static BooleanValue ADD_SCHEMATIC;
		public static BooleanValue ADD_TREE;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General settings").push("general");

			USE_WANDS = builder
					.comment("Whether to use wands to create Instant Blocks.\nDefault: true")
					.define("USE_WANDS", true);

			TP_GRINDER = builder
					.comment("Whether to teleport the player to the collection room of the Instant Grinder upon right-click.\nDefault: true")
					.define("TP_GRINDER", true);

//			PACK_HOUSE = builder
//					.comment("Whether to be able to pack up an Instant Wooden House.\nDefault: false")
//					.define("PACK_HOUSE", false);

			KEEP_BLOCKS = builder
					.comment("Whether to keep Instant Blocks after activation.\nDefault: false")
					.define("KEEP_BLOCKS", false);

			RADIUS_HARVEST = builder
					.comment("Radius to Harvest blocks around Instant Harvester.\nDefault: 50")
					.defineInRange("RADIUS_HARVEST", 25,1,1000);

			RADIUS_LIGHT = builder
					.comment("Radius to light up dark areas around Instant Light Block.\nDefault: 25")
					.defineInRange("RADIUS_LIGHT", 25,1,1000);

			RAILS_AMOUNT = builder
					.comment("Amount of rail blocks to create for Instant Rail Block.\nDefault: 37")
					.defineInRange("RAILS_AMOUNT", 37,1,10000);

			MINING_LADDER_LAYER = builder
					.comment("Mining layer for the Instant Mining Ladder.\nDefault: 12")
					.defineInRange("MINING_LADDER_LAYER", 12,-50,255);

			XP_AMOUNT = builder
					.comment("How much experience activating Instant Blocks gives you.\nDefault: 0")
					.defineInRange("XP_AMOUNT", 0,0,10000);

			builder.pop();

			builder.comment("Everything to do with crafting").push("crafting");

			ALLOW_CRAFTING = builder
					.comment("Whether to allow crafting of Instant Blocks.\nDefault: true")
					.worldRestart()
					.define("ALLOW_CRAFTING", true);

			ADD_WOODEN_HOUSE = builder
					.comment("Add Instant Wooden House\nDefault: true")
					.worldRestart()
					.define("ADD_WOODEN_HOUSE", true);

			ADD_MINING_LADDER = builder
					.comment("Add Instant Mining Ladder\nDefault: true")
					.worldRestart()
					.define("ADD_MINING_LADDER", true);

			ADD_GLASS_DOME = builder
					.comment("Add Instant Glass Dome\nDefault: true")
					.worldRestart()
					.define("ADD_GLASS_DOME", true);

			ADD_FARM = builder
					.comment("Add Instant Farm\nDefault: true")
					.worldRestart()
					.define("ADD_FARM", true);

			ADD_SKYDIVE = builder
					.comment("Add Instant Rainbow Skydive\nDefault: true")
					.worldRestart()
					.define("ADD_SKYDIVE", true);

			ADD_GRINDER = builder
					.comment("Add Instant Grinder\nDefault: true")
					.worldRestart()
					.define("ADD_GRINDER", true);

			ADD_POOL = builder
					.comment("Add Instant Pool\nDefault: true")
					.worldRestart()
					.define("ADD_POOL", true);

			ADD_ESCAPE_LADDER = builder
					.comment("Add Instant Escape Ladder\nDefault: true")
					.worldRestart()
					.define("ADD_ESCAPE_LADDER", true);

			ADD_WATER = builder
					.comment("Add Instant Water\nDefault: true")
					.worldRestart()
					.define("ADD_WATER", true);

			ADD_LAVA = builder
					.comment("Add Instant Lava\nDefault: true")
					.worldRestart()
					.define("ADD_LAVA", true);

			ADD_SUCTION = builder
					.comment("Add Instant Suction\nDefault: true")
					.worldRestart()
					.define("ADD_SUCTION", true);

			ADD_RAIL = builder
					.comment("Add Instant Rail\nDefault: true")
					.worldRestart()
					.define("ADD_RAIL", true);

			ADD_STATUE = builder
					.comment("Add Instant Statue\nDefault: true")
					.worldRestart()
					.define("ADD_STATUE", true);

			ADD_HARVEST = builder
					.comment("Add Instant Harvest\nDefault: true")
					.worldRestart()
					.define("ADD_HARVEST", true);

			ADD_LIGHT = builder
					.comment("Add Instant Light\nDefault: true")
					.worldRestart()
					.define("ADD_LIGHT", true);

			ADD_SCHEMATIC = builder
					.comment("Add Instant Schematic\nDefault: true")
					.worldRestart()
					.define("ADD_SCHEMATIC", true);

			ADD_TREE = builder
					.comment("Add Instant Tree\nDefault: true")
					.worldRestart()
					.define("ADD_TREE", true);

			builder.pop();

			builder.comment("Generating items in structure chests").push("structures");

			GENERATE_IN_CHESTS_DUNGEON = builder
					.comment("Whether to generate Instant Blocks in Dungeon Chests.\nDefault: true")
					.worldRestart()
					.define("GENERATE_IN_CHESTS_DUNGEON", true);

			GENERATE_IN_CHESTS_BONUS = builder
					.comment("Whether to generate the Instant Wooden House Block in the Bonus Chest.\nDefault: true")
					.worldRestart()
					.define("GENERATE_IN_CHESTS_BONUS", true);

			GENERATE_IN_CHESTS_VILLAGE = builder
					.comment("Whether to generate Instant Blocks in Village Chests.\nDefault: true")
					.worldRestart()
					.define("GENERATE_IN_CHESTS_VILLAGE", true);

			GENERATE_IN_CHESTS_MINESHAFT = builder
					.comment("Whether to generate Instant Blocks in Mineshaft Chests.\nDefault: true")
					.worldRestart()
					.define("GENERATE_IN_CHESTS_MINESHAFT", true);

			GENERATE_IN_CHESTS_STRONGHOLD = builder
					.comment("Whether to generate Instant Blocks in  Stronghold Chests.\nDefault: true")
					.worldRestart()
					.define("GENERATE_IN_CHESTS_STRONGHOLD", true);

			GENERATE_IN_CHESTS_TEMPLE = builder
					.comment("Whether to generate Instant Blocks in Desert/Jungle Temple Chests.\nDefault: true")
					.worldRestart()
					.define("GENERATE_IN_CHESTS_TEMPLE", true);

			builder.pop();

			builder.comment("Instant liquid blocks settings").push("liquid");

			MAX_LIQUID = builder
					.comment("Maximum amount of liquid blocks to create.\nDefault: 1000")
					.defineInRange("MAX_LIQUID", 1000,1,100000);

			MAX_FILL = builder
					.comment("Maximum amount of liquid blocks to fill in.\nDefault: 1000")
					.defineInRange("MAX_FILL", 1000,1,100000);

			SIMPLE_LIQUID = builder
					.comment("Only create liquid source blocks on the block's layer, instead of the whole area.\nDefault: false")
					.define("SIMPLE_LIQUID", false);

			builder.pop();

			builder.comment("Instant skydive block settings").push("skydive");

			SKYDIVE_RADIUS = builder
					.comment("Default radius for the Instant Rainbow Skydive.\nDefault: 5")
					.defineInRange("SKYDIVE_RADIUS", 5,1,1000);

			SKYDIVE_MIN = builder
					.comment("Minimum height for the Instant Rainbow Skydive.\nDefault: 5")
					.defineInRange("SKYDIVE_MIN", -59,-64,320);

			SKYDIVE_MAX = builder
					.comment("Maximum height for the Instant Rainbow Skydive.\nDefault: 255")
					.defineInRange("SKYDIVE_MAX", 320,-64,320);

			SKYDIVE_WATER = builder
					.comment("Water height for the Instant Rainbow Skydive.\nDefault: 1")
					.defineInRange("SKYDIVE_WATER", 1,1,300);

			builder.pop();
		}
	}

	public static class Client {
		public static BooleanValue SHOW_MESSAGES;
		public static BooleanValue SHOW_EFFECTS;
		public static ConfigValue<String> PARTICLE;
		public static ConfigValue<String> SOUND;

		Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Client only settings").push("client");

			SHOW_MESSAGES = builder
					.comment("Whether to show mod messages.\nDefault: true")
					.define("SHOW_MESSAGES", true);

			SHOW_EFFECTS = builder
					.comment("Whether to show particle effects on activation.\nDefault: true")
					.define("SHOW_EFFECTS", true);

			PARTICLE = builder
					.comment("Which particles are generated on activation.\nDefault: reddust")
					.worldRestart()
					.define("PARTICLE", "reddust");

			SOUND = builder
					.comment("Which sound is played on activation.\nThe directory is .minecraft\\resources\\sound3\\.\nFor example, the default sound is .minecraft\\resources\\sound3\\random\\levelup.ogg\nDefault: random.levelup")
					.worldRestart()
					.define("SOUND", "entity.player.levelup");

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