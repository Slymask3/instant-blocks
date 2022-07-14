package com.slymask3.instantblocks.core;

public class Config {
    public static class Common {
        public static boolean USE_WANDS = true;
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

        public static boolean DISABLE_WOODEN_HOUSE = false;
        public static boolean DISABLE_MINING_LADDER = false;
        public static boolean DISABLE_GLASS_DOME = false;
        public static boolean DISABLE_FARM = false;
        public static boolean DISABLE_SKYDIVE = false;
        public static boolean DISABLE_GRINDER = false;
        public static boolean DISABLE_POOL = false;
        public static boolean DISABLE_ESCAPE_LADDER = false;
        public static boolean DISABLE_WATER = false;
        public static boolean DISABLE_LAVA = false;
        public static boolean DISABLE_SUCTION = false;
        public static boolean DISABLE_RAIL = false;
        public static boolean DISABLE_STATUE = false;
        public static boolean DISABLE_HARVEST = false;
        public static boolean DISABLE_LIGHT = false;
        public static boolean DISABLE_SCHEMATIC = false;
        public static boolean DISABLE_TREE = false;

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
    }
    
    public static class Client {
        public static boolean SHOW_MESSAGES = true;
        public static boolean SHOW_EFFECTS = true;
        public static String SOUND_GENERATE = "entity.player.levelup";
        public static String SOUND_NO_LIQUID = "entity.panda.sneeze";
    }
}