package com.slymask3.instantblocks.config;

import com.slymask3.instantblocks.Common;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

@Config(name = Common.MOD_ID)
public class ClothConfig implements ConfigData, IConfig {
    public static void register() {
        AutoConfig.register(ClothConfig.class, Toml4jConfigSerializer::new);
    }

    @ConfigEntry.Category("general")
    SectionGeneral general = new SectionGeneral();
    static class SectionGeneral {
        boolean USE_WANDS = Defaults.USE_WANDS;
        boolean TP_GRINDER = Defaults.TP_GRINDER;
        boolean KEEP_BLOCKS = Defaults.KEEP_BLOCKS;
        boolean ALLOW_WATER_IN_NETHER = Defaults.ALLOW_WATER_IN_NETHER;
        int RADIUS_HARVEST = Defaults.RADIUS_HARVEST;
        int RADIUS_LIGHT = Defaults.RADIUS_LIGHT;
        int RAILS_AMOUNT = Defaults.RAILS_AMOUNT;
        int MINING_LADDER_LAYER = Defaults.MINING_LADDER_LAYER;
        int XP_AMOUNT = Defaults.XP_AMOUNT;
        int TREE_SIZE = Defaults.TREE_SIZE;
        int RADIUS_DOME = Defaults.RADIUS_DOME;
    }


    @ConfigEntry.Category("liquid")
    SectionLiquid liquid = new SectionLiquid();
    static class SectionLiquid {
        int MAX_LIQUID = Defaults.MAX_LIQUID;
        int MAX_FILL = Defaults.MAX_FILL;
        boolean SIMPLE_LIQUID = Defaults.SIMPLE_LIQUID;
    }

    @ConfigEntry.Category("skydive")
    SectionSkydive skydive = new SectionSkydive();
    static class SectionSkydive {
        int SKYDIVE_MIN = Defaults.SKYDIVE_MIN;
        int SKYDIVE_MAX = Defaults.SKYDIVE_MAX;
        int SKYDIVE_WATER = Defaults.SKYDIVE_WATER;
        int SKYDIVE_RADIUS = Defaults.SKYDIVE_RADIUS;
    }

    @ConfigEntry.Category("farm")
    SectionFarm farm = new SectionFarm();
    static class SectionFarm {
        int WEIGHT_WHEAT = Defaults.WEIGHT_WHEAT;
        int WEIGHT_POTATOES = Defaults.WEIGHT_POTATOES;
        int WEIGHT_CARROTS = Defaults.WEIGHT_CARROTS;
        int WEIGHT_BEETROOTS = Defaults.WEIGHT_BEETROOTS;
    }

    @ConfigEntry.Category("house")
    SectionHouse house = new SectionHouse();
    static class SectionHouse {
        String HOUSE_PLANKS_ONE = Defaults.HOUSE_PLANKS_ONE;
        String HOUSE_PLANKS_TWO = Defaults.HOUSE_PLANKS_TWO;
        String HOUSE_LOG = Defaults.HOUSE_LOG;
        String HOUSE_DOOR = Defaults.HOUSE_DOOR;
    }

    @ConfigEntry.Category("structures")
    SectionStructures structures = new SectionStructures();
    static class SectionStructures {
        boolean GENERATE_IN_CHESTS = Defaults.GENERATE_IN_CHESTS;
        boolean GENERATE_IN_CHESTS_BONUS = Defaults.GENERATE_IN_CHESTS_BONUS;
    }

    @ConfigEntry.Category("toggle")
    SectionToggle toggle = new SectionToggle();
    static class SectionToggle {
        boolean DISABLE_WOODEN_HOUSE = Defaults.DISABLE_WOODEN_HOUSE;
        boolean DISABLE_MINING_LADDER = Defaults.DISABLE_MINING_LADDER;
        boolean DISABLE_GLASS_DOME = Defaults.DISABLE_GLASS_DOME;
        boolean DISABLE_FARM = Defaults.DISABLE_FARM;
        boolean DISABLE_SKYDIVE = Defaults.DISABLE_SKYDIVE;
        boolean DISABLE_GRINDER = Defaults.DISABLE_GRINDER;
        boolean DISABLE_POOL = Defaults.DISABLE_POOL;
        boolean DISABLE_ESCAPE_LADDER = Defaults.DISABLE_ESCAPE_LADDER;
        boolean DISABLE_WATER = Defaults.DISABLE_WATER;
        boolean DISABLE_LAVA = Defaults.DISABLE_LAVA;
        boolean DISABLE_SUCTION = Defaults.DISABLE_SUCTION;
        boolean DISABLE_RAIL = Defaults.DISABLE_RAIL;
        boolean DISABLE_STATUE = Defaults.DISABLE_STATUE;
        boolean DISABLE_HARVEST = Defaults.DISABLE_HARVEST;
        boolean DISABLE_LIGHT = Defaults.DISABLE_LIGHT;
        boolean DISABLE_SCHEMATIC = Defaults.DISABLE_SCHEMATIC;
        boolean DISABLE_TREE = Defaults.DISABLE_TREE;
    }

    @ConfigEntry.Category("damage")
    SectionDamage damage = new SectionDamage();
    static class SectionDamage {
        int DAMAGE_WOODEN_HOUSE = Defaults.DAMAGE_WOODEN_HOUSE;
        int DAMAGE_MINING_LADDER = Defaults.DAMAGE_MINING_LADDER;
        int DAMAGE_GLASS_DOME = Defaults.DAMAGE_GLASS_DOME;
        int DAMAGE_FARM = Defaults.DAMAGE_FARM;
        int DAMAGE_SKYDIVE = Defaults.DAMAGE_SKYDIVE;
        int DAMAGE_GRINDER = Defaults.DAMAGE_GRINDER;
        int DAMAGE_POOL = Defaults.DAMAGE_POOL;
        int DAMAGE_ESCAPE_LADDER = Defaults.DAMAGE_ESCAPE_LADDER;
        int DAMAGE_WATER = Defaults.DAMAGE_WATER;
        int DAMAGE_LAVA = Defaults.DAMAGE_LAVA;
        int DAMAGE_SUCTION = Defaults.DAMAGE_SUCTION;
        int DAMAGE_RAIL = Defaults.DAMAGE_RAIL;
        int DAMAGE_STATUE = Defaults.DAMAGE_STATUE;
        int DAMAGE_HARVEST = Defaults.DAMAGE_HARVEST;
        int DAMAGE_LIGHT = Defaults.DAMAGE_LIGHT;
        int DAMAGE_SCHEMATIC = Defaults.DAMAGE_SCHEMATIC;
        int DAMAGE_TREE = Defaults.DAMAGE_TREE;
    }

    @ConfigEntry.Category("client")
    SectionClient client = new SectionClient();
    static class SectionClient {
        boolean SHOW_MESSAGES = Defaults.SHOW_MESSAGES;
        boolean SHOW_EFFECTS = Defaults.SHOW_EFFECTS;
        String SOUND_GENERATE = Defaults.SOUND_GENERATE;
        String SOUND_NO_LIQUID = Defaults.SOUND_NO_LIQUID;
    }

    public boolean USE_WANDS() { return general.USE_WANDS; }
    public boolean TP_GRINDER() { return general.TP_GRINDER; }
    public boolean KEEP_BLOCKS() { return general.KEEP_BLOCKS; }
    public boolean ALLOW_WATER_IN_NETHER() { return general.ALLOW_WATER_IN_NETHER; }
    public int RADIUS_HARVEST() { return general.RADIUS_HARVEST; }
    public int RADIUS_LIGHT() { return general.RADIUS_LIGHT; }
    public int RAILS_AMOUNT() { return general.RAILS_AMOUNT; }
    public int MINING_LADDER_LAYER() { return general.MINING_LADDER_LAYER; }
    public int XP_AMOUNT() { return general.XP_AMOUNT; }
    public int TREE_SIZE() { return general.TREE_SIZE; }
    public int RADIUS_DOME() { return general.RADIUS_DOME; }
    public int MAX_LIQUID() { return liquid.MAX_LIQUID; }
    public int MAX_FILL() { return liquid.MAX_FILL; }
    public boolean SIMPLE_LIQUID() { return liquid.SIMPLE_LIQUID; }
    public int SKYDIVE_MIN() { return skydive.SKYDIVE_MIN; }
    public int SKYDIVE_MAX() { return skydive.SKYDIVE_MAX; }
    public int SKYDIVE_WATER() { return skydive.SKYDIVE_WATER; }
    public int SKYDIVE_RADIUS() { return skydive.SKYDIVE_RADIUS; }
    public int WEIGHT_WHEAT() { return farm.WEIGHT_WHEAT; }
    public int WEIGHT_POTATOES() { return farm.WEIGHT_POTATOES; }
    public int WEIGHT_CARROTS() { return farm.WEIGHT_CARROTS; }
    public int WEIGHT_BEETROOTS() { return farm.WEIGHT_BEETROOTS; }
    public String HOUSE_PLANKS_ONE() { return house.HOUSE_PLANKS_ONE; }
    public String HOUSE_PLANKS_TWO() { return house.HOUSE_PLANKS_TWO; }
    public String HOUSE_LOG() { return house.HOUSE_LOG; }
    public String HOUSE_DOOR() { return house.HOUSE_DOOR; }
    public boolean GENERATE_IN_CHESTS() { return structures.GENERATE_IN_CHESTS; }
    public boolean GENERATE_IN_CHESTS_BONUS() { return structures.GENERATE_IN_CHESTS_BONUS; }
    public boolean DISABLE_WOODEN_HOUSE() { return toggle.DISABLE_WOODEN_HOUSE; }
    public boolean DISABLE_MINING_LADDER() { return toggle.DISABLE_MINING_LADDER; }
    public boolean DISABLE_GLASS_DOME() { return toggle.DISABLE_GLASS_DOME; }
    public boolean DISABLE_FARM() { return toggle.DISABLE_FARM; }
    public boolean DISABLE_SKYDIVE() { return toggle.DISABLE_SKYDIVE; }
    public boolean DISABLE_GRINDER() { return toggle.DISABLE_GRINDER; }
    public boolean DISABLE_POOL() { return toggle.DISABLE_POOL; }
    public boolean DISABLE_ESCAPE_LADDER() { return toggle.DISABLE_ESCAPE_LADDER; }
    public boolean DISABLE_WATER() { return toggle.DISABLE_WATER; }
    public boolean DISABLE_LAVA() { return toggle.DISABLE_LAVA; }
    public boolean DISABLE_SUCTION() { return toggle.DISABLE_SUCTION; }
    public boolean DISABLE_RAIL() { return toggle.DISABLE_RAIL; }
    public boolean DISABLE_STATUE() { return toggle.DISABLE_STATUE; }
    public boolean DISABLE_HARVEST() { return toggle.DISABLE_HARVEST; }
    public boolean DISABLE_LIGHT() { return toggle.DISABLE_LIGHT; }
    public boolean DISABLE_SCHEMATIC() { return toggle.DISABLE_SCHEMATIC; }
    public boolean DISABLE_TREE() { return toggle.DISABLE_TREE; }
    public int DAMAGE_WOODEN_HOUSE() { return damage.DAMAGE_WOODEN_HOUSE; }
    public int DAMAGE_MINING_LADDER() { return damage.DAMAGE_MINING_LADDER; }
    public int DAMAGE_GLASS_DOME() { return damage.DAMAGE_GLASS_DOME; }
    public int DAMAGE_FARM() { return damage.DAMAGE_FARM; }
    public int DAMAGE_SKYDIVE() { return damage.DAMAGE_SKYDIVE; }
    public int DAMAGE_GRINDER() { return damage.DAMAGE_GRINDER; }
    public int DAMAGE_POOL() { return damage.DAMAGE_POOL; }
    public int DAMAGE_ESCAPE_LADDER() { return damage.DAMAGE_ESCAPE_LADDER; }
    public int DAMAGE_WATER() { return damage.DAMAGE_WATER; }
    public int DAMAGE_LAVA() { return damage.DAMAGE_LAVA; }
    public int DAMAGE_SUCTION() { return damage.DAMAGE_SUCTION; }
    public int DAMAGE_RAIL() { return damage.DAMAGE_RAIL; }
    public int DAMAGE_STATUE() { return damage.DAMAGE_STATUE; }
    public int DAMAGE_HARVEST() { return damage.DAMAGE_HARVEST; }
    public int DAMAGE_LIGHT() { return damage.DAMAGE_LIGHT; }
    public int DAMAGE_SCHEMATIC() { return damage.DAMAGE_SCHEMATIC; }
    public int DAMAGE_TREE() { return damage.DAMAGE_TREE; }
    public boolean SHOW_MESSAGES() { return client.SHOW_MESSAGES; }
    public boolean SHOW_EFFECTS() { return client.SHOW_EFFECTS; }
    public String SOUND_GENERATE() { return client.SOUND_GENERATE; }
    public String SOUND_NO_LIQUID() { return client.SOUND_NO_LIQUID; }
}