package com.slymask3.instantblocks.config;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.config.entry.ColorSet;
import com.slymask3.instantblocks.config.entry.HugeTree;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

import java.util.List;

@Config(name = Common.MOD_ID)
public class ClothConfig implements ConfigData, IConfig {
    public static void register() {
        AutoConfig.register(ClothConfig.class, Toml4jConfigSerializer::new);
    }

    public static ClothConfig get() {
        return AutoConfig.getConfigHolder(ClothConfig.class).getConfig();
    }

    public void reload() {
        AutoConfig.getConfigHolder(ClothConfig.class).load();
        Common.CONFIG = get();
    }

    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.TransitiveObject
    SectionGeneral general = new SectionGeneral();
    static class SectionGeneral {
        boolean USE_WANDS = Defaults.USE_WANDS;
        boolean WAND_OVER_DURABILITY = Defaults.WAND_OVER_DURABILITY;
        boolean KEEP_BLOCKS = Defaults.KEEP_BLOCKS;
        boolean ALLOW_WATER_IN_NETHER = Defaults.ALLOW_WATER_IN_NETHER;
        int XP_AMOUNT = Defaults.XP_AMOUNT;
        boolean GENERATE_IN_CHESTS = Defaults.GENERATE_IN_CHESTS;
        boolean GENERATE_IN_CHESTS_BONUS = Defaults.GENERATE_IN_CHESTS_BONUS;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.CollapsibleObject()
    SectionHouse house = new SectionHouse();
    static class SectionHouse {
        String HOUSE_PLANKS_ONE = Defaults.HOUSE_PLANKS_ONE;
        String HOUSE_PLANKS_TWO = Defaults.HOUSE_PLANKS_TWO;
        String HOUSE_LOG = Defaults.HOUSE_LOG;
        String HOUSE_DOOR = Defaults.HOUSE_DOOR;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.CollapsibleObject
    SectionMining mining = new SectionMining();
    static class SectionMining {
        @ConfigEntry.BoundedDiscrete(min = -64, max = 320)
        int MINING_LADDER_LAYER = Defaults.MINING_LADDER_LAYER;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.CollapsibleObject
    SectionDome dome = new SectionDome();
    static class SectionDome {
        @ConfigEntry.BoundedDiscrete(min = 1, max = 100)
        int RADIUS_DOME = Defaults.RADIUS_DOME;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.CollapsibleObject
    SectionFarm farm = new SectionFarm();
    static class SectionFarm {
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        int WEIGHT_WHEAT = Defaults.WEIGHT_WHEAT;
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        int WEIGHT_POTATOES = Defaults.WEIGHT_POTATOES;
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        int WEIGHT_CARROTS = Defaults.WEIGHT_CARROTS;
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        int WEIGHT_BEETROOTS = Defaults.WEIGHT_BEETROOTS;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.CollapsibleObject
    SectionSkydive skydive = new SectionSkydive();
    static class SectionSkydive {
        @ConfigEntry.BoundedDiscrete(min = -64, max = 320)
        int SKYDIVE_MIN = Defaults.SKYDIVE_MIN;
        @ConfigEntry.BoundedDiscrete(min = -64, max = 320)
        int SKYDIVE_MAX = Defaults.SKYDIVE_MAX;
        @ConfigEntry.BoundedDiscrete(min = 1, max = 300)
        int SKYDIVE_WATER = Defaults.SKYDIVE_WATER;
        int SKYDIVE_RADIUS = Defaults.SKYDIVE_RADIUS;
        List<ColorSet> SKYDIVE_PRESETS = Defaults.SKYDIVE_PRESETS;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.CollapsibleObject
    SectionGrinder grinder = new SectionGrinder();
    static class SectionGrinder {
        boolean TP_GRINDER = Defaults.TP_GRINDER;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.CollapsibleObject
    SectionLiquid liquid = new SectionLiquid();
    static class SectionLiquid {
        int MAX_LIQUID = Defaults.MAX_LIQUID;
        int MAX_FILL = Defaults.MAX_FILL;
        boolean SIMPLE_LIQUID = Defaults.SIMPLE_LIQUID;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.CollapsibleObject
    SectionRail rail = new SectionRail();
    static class SectionRail {
        int RAILS_AMOUNT = Defaults.RAILS_AMOUNT;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.CollapsibleObject
    SectionHarvest harvest = new SectionHarvest();
    static class SectionHarvest {
        int RADIUS_HARVEST = Defaults.RADIUS_HARVEST;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.CollapsibleObject
    SectionLight light = new SectionLight();
    static class SectionLight {
        int RADIUS_LIGHT = Defaults.RADIUS_LIGHT;
    }

    @ConfigEntry.Category("blocks")
    @ConfigEntry.Gui.CollapsibleObject
    SectionTree tree = new SectionTree();
    static class SectionTree {
        @ConfigEntry.BoundedDiscrete(min = 1, max = 24)
        int TREE_SIZE = Defaults.TREE_SIZE;
        List<HugeTree> HUGE_TREES = Defaults.HUGE_TREES;
    }

    @ConfigEntry.Category("toggle")
    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.TransitiveObject
    SectionToggle toggle = new SectionToggle();
    static class SectionToggle {
        boolean ENABLE_WOODEN_HOUSE = Defaults.ENABLE_WOODEN_HOUSE;
        boolean ENABLE_MINING_LADDER = Defaults.ENABLE_MINING_LADDER;
        boolean ENABLE_GLASS_DOME = Defaults.ENABLE_GLASS_DOME;
        boolean ENABLE_FARM = Defaults.ENABLE_FARM;
        boolean ENABLE_SKYDIVE = Defaults.ENABLE_SKYDIVE;
        boolean ENABLE_GRINDER = Defaults.ENABLE_GRINDER;
        boolean ENABLE_POOL = Defaults.ENABLE_POOL;
        boolean ENABLE_ESCAPE_LADDER = Defaults.ENABLE_ESCAPE_LADDER;
        boolean ENABLE_WATER = Defaults.ENABLE_WATER;
        boolean ENABLE_LAVA = Defaults.ENABLE_LAVA;
        boolean ENABLE_SUCTION = Defaults.ENABLE_SUCTION;
        boolean ENABLE_RAIL = Defaults.ENABLE_RAIL;
        boolean ENABLE_STATUE = Defaults.ENABLE_STATUE;
        boolean ENABLE_HARVEST = Defaults.ENABLE_HARVEST;
        boolean ENABLE_LIGHT = Defaults.ENABLE_LIGHT;
        boolean ENABLE_SCHEMATIC = Defaults.ENABLE_SCHEMATIC;
        boolean ENABLE_TREE = Defaults.ENABLE_TREE;
    }

    @ConfigEntry.Category("damage")
    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.TransitiveObject
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
    @ConfigEntry.Gui.PrefixText()
    @ConfigEntry.Gui.TransitiveObject
    SectionClient client = new SectionClient();
    static class SectionClient {
        boolean SHOW_MESSAGES = Defaults.SHOW_MESSAGES;
        boolean SHOW_EFFECTS = Defaults.SHOW_EFFECTS;
        String SOUND_GENERATE = Defaults.SOUND_GENERATE;
        String SOUND_NO_LIQUID = Defaults.SOUND_NO_LIQUID;
    }

    public boolean USE_WANDS() { return general.USE_WANDS; }
    public boolean WAND_OVER_DURABILITY() { return general.WAND_OVER_DURABILITY; }
    public boolean TP_GRINDER() { return grinder.TP_GRINDER; }
    public boolean KEEP_BLOCKS() { return general.KEEP_BLOCKS; }
    public boolean ALLOW_WATER_IN_NETHER() { return general.ALLOW_WATER_IN_NETHER; }
    public int RADIUS_HARVEST() { return harvest.RADIUS_HARVEST; }
    public int RADIUS_LIGHT() { return light.RADIUS_LIGHT; }
    public int RAILS_AMOUNT() { return rail.RAILS_AMOUNT; }
    public int MINING_LADDER_LAYER() { return mining.MINING_LADDER_LAYER; }
    public int XP_AMOUNT() { return general.XP_AMOUNT; }
    public int RADIUS_DOME() { return dome.RADIUS_DOME; }
    public int MAX_LIQUID() { return liquid.MAX_LIQUID; }
    public int MAX_FILL() { return liquid.MAX_FILL; }
    public boolean SIMPLE_LIQUID() { return liquid.SIMPLE_LIQUID; }
    public int SKYDIVE_MIN() { return skydive.SKYDIVE_MIN; }
    public int SKYDIVE_MAX() { return skydive.SKYDIVE_MAX; }
    public int SKYDIVE_WATER() { return skydive.SKYDIVE_WATER; }
    public int SKYDIVE_RADIUS() { return skydive.SKYDIVE_RADIUS; }
    public List<ColorSet> SKYDIVE_PRESETS() { return skydive.SKYDIVE_PRESETS.size() == 1 && skydive.SKYDIVE_PRESETS.get(0).colors.size() == 0 ? List.of() : skydive.SKYDIVE_PRESETS; }
    public int TREE_SIZE() { return tree.TREE_SIZE; }
    public List<HugeTree> HUGE_TREES() { return tree.HUGE_TREES.size() == 1 && tree.HUGE_TREES.get(0).name.isEmpty() ? List.of() : tree.HUGE_TREES; }
    public int WEIGHT_WHEAT() { return farm.WEIGHT_WHEAT; }
    public int WEIGHT_POTATOES() { return farm.WEIGHT_POTATOES; }
    public int WEIGHT_CARROTS() { return farm.WEIGHT_CARROTS; }
    public int WEIGHT_BEETROOTS() { return farm.WEIGHT_BEETROOTS; }
    public String HOUSE_PLANKS_ONE() { return house.HOUSE_PLANKS_ONE; }
    public String HOUSE_PLANKS_TWO() { return house.HOUSE_PLANKS_TWO; }
    public String HOUSE_LOG() { return house.HOUSE_LOG; }
    public String HOUSE_DOOR() { return house.HOUSE_DOOR; }
    public boolean GENERATE_IN_CHESTS() { return general.GENERATE_IN_CHESTS; }
    public boolean GENERATE_IN_CHESTS_BONUS() { return general.GENERATE_IN_CHESTS_BONUS; }
    public boolean ENABLE_WOODEN_HOUSE() { return toggle.ENABLE_WOODEN_HOUSE; }
    public boolean ENABLE_MINING_LADDER() { return toggle.ENABLE_MINING_LADDER; }
    public boolean ENABLE_GLASS_DOME() { return toggle.ENABLE_GLASS_DOME; }
    public boolean ENABLE_FARM() { return toggle.ENABLE_FARM; }
    public boolean ENABLE_SKYDIVE() { return toggle.ENABLE_SKYDIVE; }
    public boolean ENABLE_GRINDER() { return toggle.ENABLE_GRINDER; }
    public boolean ENABLE_POOL() { return toggle.ENABLE_POOL; }
    public boolean ENABLE_ESCAPE_LADDER() { return toggle.ENABLE_ESCAPE_LADDER; }
    public boolean ENABLE_WATER() { return toggle.ENABLE_WATER; }
    public boolean ENABLE_LAVA() { return toggle.ENABLE_LAVA; }
    public boolean ENABLE_SUCTION() { return toggle.ENABLE_SUCTION; }
    public boolean ENABLE_RAIL() { return toggle.ENABLE_RAIL; }
    public boolean ENABLE_STATUE() { return toggle.ENABLE_STATUE; }
    public boolean ENABLE_HARVEST() { return toggle.ENABLE_HARVEST; }
    public boolean ENABLE_LIGHT() { return toggle.ENABLE_LIGHT; }
    public boolean ENABLE_SCHEMATIC() { return toggle.ENABLE_SCHEMATIC; }
    public boolean ENABLE_TREE() { return toggle.ENABLE_TREE; }
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