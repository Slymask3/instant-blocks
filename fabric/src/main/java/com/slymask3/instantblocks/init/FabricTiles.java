package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.block.entity.*;
import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.core.ModTiles;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

public class FabricTiles implements ITileHelper {
    public void init() {
        ModTiles.SKYDIVE = FabricBlockEntityTypeBuilder.create(SkydiveBlockEntity::new, ModBlocks.INSTANT_SKYDIVE).build(null);
        ModTiles.STATUE = FabricBlockEntityTypeBuilder.create(StatueBlockEntity::new, ModBlocks.INSTANT_STATUE).build(null);
        ModTiles.HARVEST = FabricBlockEntityTypeBuilder.create(HarvestBlockEntity::new, ModBlocks.INSTANT_HARVEST).build(null);
        ModTiles.SCHEMATIC = FabricBlockEntityTypeBuilder.create(SchematicBlockEntity::new, ModBlocks.INSTANT_SCHEMATIC).build(null);
        ModTiles.TREE = FabricBlockEntityTypeBuilder.create(TreeBlockEntity::new, ModBlocks.INSTANT_TREE).build(null);
        ModTiles.COLOR = FabricBlockEntityTypeBuilder.create(ColorBlockEntity::new, ModBlocks.COLOR, ModBlocks.COLOR_LAYER, ModBlocks.SKYDIVE_TP).build(null);
    }
}