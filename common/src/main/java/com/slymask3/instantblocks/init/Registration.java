package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.core.ModItems;
import com.slymask3.instantblocks.core.ModTiles;
import com.slymask3.instantblocks.item.InstantBlockItem;
import com.slymask3.instantblocks.reference.Names;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class Registration {
    public static void registerBlocks(IRegistryHelper<Block> helper) {
        ModBlocks.init();
        helper.register(Names.Blocks.IB_WOOD_HOUSE, ModBlocks.INSTANT_WOOD_HOUSE);
        helper.register(Names.Blocks.IB_MINING_LADDER, ModBlocks.INSTANT_MINING_LADDER);
        helper.register(Names.Blocks.IB_GLASS_DOME, ModBlocks.INSTANT_GLASS_DOME);
        helper.register(Names.Blocks.IB_FARM, ModBlocks.INSTANT_FARM);
        helper.register(Names.Blocks.IB_SKYDIVE, ModBlocks.INSTANT_SKYDIVE);
        helper.register(Names.Blocks.IB_GRINDER, ModBlocks.INSTANT_GRINDER);
        helper.register(Names.Blocks.IB_POOL, ModBlocks.INSTANT_POOL);
        helper.register(Names.Blocks.IB_ESCAPE_LADDER, ModBlocks.INSTANT_ESCAPE_LADDER);
        helper.register(Names.Blocks.IB_WATER, ModBlocks.INSTANT_WATER);
        helper.register(Names.Blocks.IB_LAVA, ModBlocks.INSTANT_LAVA);
        helper.register(Names.Blocks.IB_SUCTION, ModBlocks.INSTANT_SUCTION);
        helper.register(Names.Blocks.IB_RAIL, ModBlocks.INSTANT_RAIL);
        helper.register(Names.Blocks.IB_STATUE, ModBlocks.INSTANT_STATUE);
        helper.register(Names.Blocks.IB_HARVEST, ModBlocks.INSTANT_HARVEST);
        helper.register(Names.Blocks.IB_LIGHT, ModBlocks.INSTANT_LIGHT);
        helper.register(Names.Blocks.IB_SCHEMATIC, ModBlocks.INSTANT_SCHEMATIC);
        helper.register(Names.Blocks.IB_TREE, ModBlocks.INSTANT_TREE);
        helper.register(Names.Blocks.COLOR, ModBlocks.COLOR);
        helper.register(Names.Blocks.SKYDIVE_TP, ModBlocks.SKYDIVE_TP);
    }

    public static void registerItems(IRegistryHelper<Item> helper) {
        ModItems.init();
        helper.register(Names.Blocks.IB_WOOD_HOUSE, new InstantBlockItem(ModBlocks.INSTANT_WOOD_HOUSE));
        helper.register(Names.Blocks.IB_MINING_LADDER, new InstantBlockItem(ModBlocks.INSTANT_MINING_LADDER));
        helper.register(Names.Blocks.IB_GLASS_DOME, new InstantBlockItem(ModBlocks.INSTANT_GLASS_DOME));
        helper.register(Names.Blocks.IB_FARM, new InstantBlockItem(ModBlocks.INSTANT_FARM));
        helper.register(Names.Blocks.IB_SKYDIVE, new InstantBlockItem(ModBlocks.INSTANT_SKYDIVE));
        helper.register(Names.Blocks.IB_GRINDER, new InstantBlockItem(ModBlocks.INSTANT_GRINDER));
        helper.register(Names.Blocks.IB_POOL, new InstantBlockItem(ModBlocks.INSTANT_POOL));
        helper.register(Names.Blocks.IB_ESCAPE_LADDER, new InstantBlockItem(ModBlocks.INSTANT_ESCAPE_LADDER));
        helper.register(Names.Blocks.IB_WATER, new InstantBlockItem(ModBlocks.INSTANT_WATER));
        helper.register(Names.Blocks.IB_LAVA, new InstantBlockItem(ModBlocks.INSTANT_LAVA));
        helper.register(Names.Blocks.IB_SUCTION, new InstantBlockItem(ModBlocks.INSTANT_SUCTION));
        helper.register(Names.Blocks.IB_RAIL, new InstantBlockItem(ModBlocks.INSTANT_RAIL));
        helper.register(Names.Blocks.IB_STATUE, new InstantBlockItem(ModBlocks.INSTANT_STATUE));
        helper.register(Names.Blocks.IB_HARVEST, new InstantBlockItem(ModBlocks.INSTANT_HARVEST));
        helper.register(Names.Blocks.IB_LIGHT, new InstantBlockItem(ModBlocks.INSTANT_LIGHT));
        helper.register(Names.Blocks.IB_SCHEMATIC, new InstantBlockItem(ModBlocks.INSTANT_SCHEMATIC));
        helper.register(Names.Blocks.IB_TREE, new InstantBlockItem(ModBlocks.INSTANT_TREE));
        helper.register(Names.Blocks.COLOR, new InstantBlockItem(ModBlocks.COLOR));
        helper.register(Names.Blocks.SKYDIVE_TP, new InstantBlockItem(ModBlocks.SKYDIVE_TP));
        helper.register(Names.Items.IB_WAND_WOOD, ModItems.WAND_WOOD);
        helper.register(Names.Items.IB_WAND_STONE, ModItems.WAND_STONE);
        helper.register(Names.Items.IB_WAND_IRON, ModItems.WAND_IRON);
        helper.register(Names.Items.IB_WAND_GOLD, ModItems.WAND_GOLD);
        helper.register(Names.Items.IB_WAND_DIAMOND, ModItems.WAND_DIAMOND);
        helper.register(Names.Items.IB_WAND_NETHERITE, ModItems.WAND_NETHERITE);
        helper.register(Names.Items.IB_WAND_CLEAR, ModItems.WAND_CLEAR);
    }

    public static void registerTiles(IRegistryHelper<BlockEntityType<?>> helper) {
        Common.TILES.init();
        helper.register(Names.Blocks.IB_SKYDIVE, ModTiles.SKYDIVE);
        helper.register(Names.Blocks.IB_STATUE, ModTiles.STATUE);
        helper.register(Names.Blocks.IB_HARVEST, ModTiles.HARVEST);
        helper.register(Names.Blocks.IB_TREE, ModTiles.TREE);
        helper.register(Names.Blocks.IB_SCHEMATIC, ModTiles.SCHEMATIC);
        helper.register(Names.Blocks.COLOR, ModTiles.COLOR);
    }
}