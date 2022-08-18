package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.block.entity.*;
import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.core.ModTiles;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ForgeTiles implements ITileHelper {
    public void init() {
        ModTiles.SKYDIVE = BlockEntityType.Builder.of(SkydiveBlockEntity::new, ModBlocks.INSTANT_SKYDIVE).build(null);
        ModTiles.STATUE = BlockEntityType.Builder.of(StatueBlockEntity::new, ModBlocks.INSTANT_STATUE).build(null);
        ModTiles.HARVEST = BlockEntityType.Builder.of(HarvestBlockEntity::new, ModBlocks.INSTANT_HARVEST).build(null);
        ModTiles.SCHEMATIC = BlockEntityType.Builder.of(SchematicBlockEntity::new, ModBlocks.INSTANT_SCHEMATIC).build(null);
        ModTiles.TREE = BlockEntityType.Builder.of(TreeBlockEntity::new, ModBlocks.INSTANT_TREE).build(null);
        ModTiles.COLOR = BlockEntityType.Builder.of(ColorBlockEntity::new, ModBlocks.COLOR, ModBlocks.COLOR_LAYER, ModBlocks.SKYDIVE_TP).build(null);
    }
}