package com.slymask3.instantblocks.config;

import java.util.Arrays;
import java.util.List;

public interface IConfig {
    default void reload() {}

    default boolean USE_WANDS() { return Defaults.USE_WANDS; }
    default boolean WAND_OVER_DURABILITY() { return Defaults.WAND_OVER_DURABILITY; }
    default boolean TP_GRINDER() { return Defaults.TP_GRINDER; }
    default boolean KEEP_BLOCKS() { return Defaults.KEEP_BLOCKS; }
    default boolean ALLOW_WATER_IN_NETHER() { return Defaults.ALLOW_WATER_IN_NETHER; }
    default int RADIUS_HARVEST() { return Defaults.RADIUS_HARVEST; }
    default int RADIUS_LIGHT() { return Defaults.RADIUS_LIGHT; }
    default int RAILS_AMOUNT() { return Defaults.RAILS_AMOUNT; }
    default int MINING_LADDER_LAYER() { return Defaults.MINING_LADDER_LAYER; }
    default int XP_AMOUNT() { return Defaults.XP_AMOUNT; }
    default int TREE_SIZE() { return Defaults.TREE_SIZE; }
    default int RADIUS_DOME() { return Defaults.RADIUS_DOME; }

    default int MAX_LIQUID() { return Defaults.MAX_LIQUID; }
    default int MAX_FILL() { return Defaults.MAX_FILL; }
    default boolean SIMPLE_LIQUID() { return Defaults.SIMPLE_LIQUID; }

    default int SKYDIVE_MIN() { return Defaults.SKYDIVE_MIN; }
    default int SKYDIVE_MAX() { return Defaults.SKYDIVE_MAX; }
    default int SKYDIVE_WATER() { return Defaults.SKYDIVE_WATER; }
    default int SKYDIVE_RADIUS() { return Defaults.SKYDIVE_RADIUS; }
    default List<ColorSet> SKYDIVE_PRESETS() { return Defaults.SKYDIVE_PRESETS; }

    default int WEIGHT_WHEAT() { return Defaults.WEIGHT_WHEAT; }
    default int WEIGHT_POTATOES() { return Defaults.WEIGHT_POTATOES; }
    default int WEIGHT_CARROTS() { return Defaults.WEIGHT_CARROTS; }
    default int WEIGHT_BEETROOTS() { return Defaults.WEIGHT_BEETROOTS; }

    default String HOUSE_PLANKS_ONE() { return Defaults.HOUSE_PLANKS_ONE; }
    default String HOUSE_PLANKS_TWO() { return Defaults.HOUSE_PLANKS_TWO; }
    default String HOUSE_LOG() { return Defaults.HOUSE_LOG; }
    default String HOUSE_DOOR() { return Defaults.HOUSE_DOOR; }

    default boolean GENERATE_IN_CHESTS() { return Defaults.GENERATE_IN_CHESTS; }
    default boolean GENERATE_IN_CHESTS_BONUS() { return Defaults.GENERATE_IN_CHESTS_BONUS; }

    default boolean ENABLE_WOODEN_HOUSE() { return Defaults.ENABLE_WOODEN_HOUSE; }
    default boolean ENABLE_MINING_LADDER() { return Defaults.ENABLE_MINING_LADDER; }
    default boolean ENABLE_GLASS_DOME() { return Defaults.ENABLE_GLASS_DOME; }
    default boolean ENABLE_FARM() { return Defaults.ENABLE_FARM; }
    default boolean ENABLE_SKYDIVE() { return Defaults.ENABLE_SKYDIVE; }
    default boolean ENABLE_GRINDER() { return Defaults.ENABLE_GRINDER; }
    default boolean ENABLE_POOL() { return Defaults.ENABLE_POOL; }
    default boolean ENABLE_ESCAPE_LADDER() { return Defaults.ENABLE_ESCAPE_LADDER; }
    default boolean ENABLE_WATER() { return Defaults.ENABLE_WATER; }
    default boolean ENABLE_LAVA() { return Defaults.ENABLE_LAVA; }
    default boolean ENABLE_SUCTION() { return Defaults.ENABLE_SUCTION; }
    default boolean ENABLE_RAIL() { return Defaults.ENABLE_RAIL; }
    default boolean ENABLE_STATUE() { return Defaults.ENABLE_STATUE; }
    default boolean ENABLE_HARVEST() { return Defaults.ENABLE_HARVEST; }
    default boolean ENABLE_LIGHT() { return Defaults.ENABLE_LIGHT; }
    default boolean ENABLE_SCHEMATIC() { return Defaults.ENABLE_SCHEMATIC; }
    default boolean ENABLE_TREE() { return Defaults.ENABLE_TREE; }

    default int DAMAGE_WOODEN_HOUSE() { return Defaults.DAMAGE_WOODEN_HOUSE; }
    default int DAMAGE_MINING_LADDER() { return Defaults.DAMAGE_MINING_LADDER; }
    default int DAMAGE_GLASS_DOME() { return Defaults.DAMAGE_GLASS_DOME; }
    default int DAMAGE_FARM() { return Defaults.DAMAGE_FARM; }
    default int DAMAGE_SKYDIVE() { return Defaults.DAMAGE_SKYDIVE; }
    default int DAMAGE_GRINDER() { return Defaults.DAMAGE_GRINDER; }
    default int DAMAGE_POOL() { return Defaults.DAMAGE_POOL; }
    default int DAMAGE_ESCAPE_LADDER() { return Defaults.DAMAGE_ESCAPE_LADDER; }
    default int DAMAGE_WATER() { return Defaults.DAMAGE_WATER; }
    default int DAMAGE_LAVA() { return Defaults.DAMAGE_LAVA; }
    default int DAMAGE_SUCTION() { return Defaults.DAMAGE_SUCTION; }
    default int DAMAGE_RAIL() { return Defaults.DAMAGE_RAIL; }
    default int DAMAGE_STATUE() { return Defaults.DAMAGE_STATUE; }
    default int DAMAGE_HARVEST() { return Defaults.DAMAGE_HARVEST; }
    default int DAMAGE_LIGHT() { return Defaults.DAMAGE_LIGHT; }
    default int DAMAGE_SCHEMATIC() { return Defaults.DAMAGE_SCHEMATIC; }
    default int DAMAGE_TREE() { return Defaults.DAMAGE_TREE; }

    default boolean SHOW_MESSAGES() { return Defaults.SHOW_MESSAGES; }
    default boolean SHOW_EFFECTS() { return Defaults.SHOW_EFFECTS; }
    default String SOUND_GENERATE() { return Defaults.SOUND_GENERATE; }
    default String SOUND_NO_LIQUID() { return Defaults.SOUND_NO_LIQUID; }

    class Defaults {
        public static boolean USE_WANDS = true;
        public static boolean WAND_OVER_DURABILITY = false;
        public static boolean TP_GRINDER = true;
        public static boolean KEEP_BLOCKS = false;
        public static boolean ALLOW_WATER_IN_NETHER = false;
        public static int RADIUS_HARVEST = 25;
        public static int RADIUS_LIGHT = 25;
        public static int RAILS_AMOUNT = 37;
        public static int MINING_LADDER_LAYER = -59;
        public static int XP_AMOUNT = 0;
        public static int TREE_SIZE = 4;
        public static int RADIUS_DOME = 4;

        public static int MAX_LIQUID = 1000;
        public static int MAX_FILL = 1000;
        public static boolean SIMPLE_LIQUID = false;

        public static int SKYDIVE_MIN = -59;
        public static int SKYDIVE_MAX = 320;
        public static int SKYDIVE_WATER = 1;
        public static int SKYDIVE_RADIUS = 5;

        public static int WEIGHT_WHEAT = 70;
        public static int WEIGHT_POTATOES = 10;
        public static int WEIGHT_CARROTS = 10;
        public static int WEIGHT_BEETROOTS = 10;

        public static String HOUSE_PLANKS_ONE = "birch";
        public static String HOUSE_PLANKS_TWO = "spruce";
        public static String HOUSE_LOG = "birch";
        public static String HOUSE_DOOR = "dark_oak";

        public static boolean GENERATE_IN_CHESTS = true;
        public static boolean GENERATE_IN_CHESTS_BONUS = true;

        public static boolean ENABLE_WOODEN_HOUSE = true;
        public static boolean ENABLE_MINING_LADDER = true;
        public static boolean ENABLE_GLASS_DOME = true;
        public static boolean ENABLE_FARM = true;
        public static boolean ENABLE_SKYDIVE = true;
        public static boolean ENABLE_GRINDER = true;
        public static boolean ENABLE_POOL = true;
        public static boolean ENABLE_ESCAPE_LADDER = true;
        public static boolean ENABLE_WATER = true;
        public static boolean ENABLE_LAVA = true;
        public static boolean ENABLE_SUCTION = true;
        public static boolean ENABLE_RAIL = true;
        public static boolean ENABLE_STATUE = true;
        public static boolean ENABLE_HARVEST = true;
        public static boolean ENABLE_LIGHT = true;
        public static boolean ENABLE_SCHEMATIC = true;
        public static boolean ENABLE_TREE = true;

        public static int DAMAGE_WOODEN_HOUSE = 40;
        public static int DAMAGE_MINING_LADDER = 30;
        public static int DAMAGE_GLASS_DOME = 10;
        public static int DAMAGE_FARM = 50;
        public static int DAMAGE_SKYDIVE = 100;
        public static int DAMAGE_GRINDER = 100;
        public static int DAMAGE_POOL = 40;
        public static int DAMAGE_ESCAPE_LADDER = 30;
        public static int DAMAGE_WATER = 20;
        public static int DAMAGE_LAVA = 20;
        public static int DAMAGE_SUCTION = 20;
        public static int DAMAGE_RAIL = 10;
        public static int DAMAGE_STATUE = 100;
        public static int DAMAGE_HARVEST = 60;
        public static int DAMAGE_LIGHT = 60;
        public static int DAMAGE_SCHEMATIC = 200;
        public static int DAMAGE_TREE = 50;

        public static boolean SHOW_MESSAGES = true;
        public static boolean SHOW_EFFECTS = true;
        public static String SOUND_GENERATE = "entity.player.levelup";
        public static String SOUND_NO_LIQUID = "entity.panda.sneeze";

        public static List<ColorSet> SKYDIVE_PRESETS = Arrays.asList(
            new ColorSet("Rainbow","red","orange","yellow","lime","green","cyan","light blue","blue","purple","magenta","pink"),
            new ColorSet("Test","lime","light blue","pink")
        );
    }

    class ColorSet {
        public String name;
        public List<String> colors;
        public ColorSet() {
            this.name = "Unnamed";
            this.colors = Arrays.asList("white");
        }
        public ColorSet(String name, String... colors) {
            this.name = name;
            this.colors = Arrays.asList(colors);
        }
    }
}