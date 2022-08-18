package com.slymask3.instantblocks.core;

import com.slymask3.instantblocks.block.ColorBlock;
import com.slymask3.instantblocks.block.ColorLayerBlock;
import com.slymask3.instantblocks.block.SkydiveTPBlock;
import com.slymask3.instantblocks.block.instant.*;
import net.minecraft.world.level.block.Block;

public class ModBlocks {
    public static Block INSTANT_WOOD_HOUSE;
    public static Block INSTANT_MINING_LADDER;
    public static Block INSTANT_GLASS_DOME;
    public static Block INSTANT_FARM;
    public static Block INSTANT_SKYDIVE;
    public static Block INSTANT_GRINDER;
    public static Block INSTANT_POOL;
    public static Block INSTANT_ESCAPE_LADDER;
    public static Block INSTANT_WATER;
    public static Block INSTANT_LAVA;
    public static Block INSTANT_SUCTION;
    public static Block INSTANT_RAIL;
    public static Block INSTANT_STATUE;
    public static Block INSTANT_HARVEST;
    public static Block INSTANT_LIGHT;
    public static Block INSTANT_SCHEMATIC;
    public static Block INSTANT_TREE;
    public static Block COLOR;
    public static Block COLOR_LAYER;
    public static Block SKYDIVE_TP;

    public static void init() {
        INSTANT_WOOD_HOUSE = new InstantHouseWoodBlock();
        INSTANT_MINING_LADDER = new InstantMiningLadderBlock();
        INSTANT_GLASS_DOME = new InstantGlassDomeBlock();
        INSTANT_FARM = new InstantFarmBlock();
        INSTANT_SKYDIVE = new InstantSkydiveBlock();
        INSTANT_GRINDER = new InstantGrinderBlock();
        INSTANT_POOL = new InstantPoolBlock();
        INSTANT_ESCAPE_LADDER = new InstantEscapeLadderBlock();
        INSTANT_WATER = new InstantWaterBlock();
        INSTANT_LAVA = new InstantLavaBlock();
        INSTANT_SUCTION = new InstantSuctionBlock();
        INSTANT_RAIL = new InstantRailBlock();
        INSTANT_STATUE = new InstantStatueBlock();
        INSTANT_HARVEST = new InstantHarvestBlock();
        INSTANT_LIGHT = new InstantLightBlock();
        INSTANT_SCHEMATIC = new InstantSchematicBlock();
        INSTANT_TREE = new InstantTreeBlock();
        COLOR = new ColorBlock();
        COLOR_LAYER = new ColorLayerBlock();
        SKYDIVE_TP = new SkydiveTPBlock();
    }
}