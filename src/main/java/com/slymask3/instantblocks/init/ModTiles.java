package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.tileentity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTiles {
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Reference.MOD_ID);

	public static final RegistryObject<BlockEntityType<TileEntitySkydive>> SKYDIVE = TILES.register(Names.Blocks.IB_SKYDIVE, () -> BlockEntityType.Builder.of(TileEntitySkydive::new, ModBlocks.ibSkydive.get()).build(null));
	public static final RegistryObject<BlockEntityType<TileEntityStatue>> STATUE = TILES.register(Names.Blocks.IB_STATUE, () -> BlockEntityType.Builder.of(TileEntityStatue::new, ModBlocks.ibStatue.get()).build(null));
	public static final RegistryObject<BlockEntityType<TileEntityHarvest>> HARVEST = TILES.register(Names.Blocks.IB_HARVEST, () -> BlockEntityType.Builder.of(TileEntityHarvest::new, ModBlocks.ibHarvest.get()).build(null));
	public static final RegistryObject<BlockEntityType<TileEntitySchematic>> SCHEMATIC = TILES.register(Names.Blocks.IB_SCHEMATIC, () -> BlockEntityType.Builder.of(TileEntitySchematic::new, ModBlocks.ibSchematic.get()).build(null));
	public static final RegistryObject<BlockEntityType<TileEntityTree>> TREE = TILES.register(Names.Blocks.IB_TREE, () -> BlockEntityType.Builder.of(TileEntityTree::new, ModBlocks.ibTree.get()).build(null));
	public static final RegistryObject<BlockEntityType<TileEntityColor>> COLOR = TILES.register(Names.Blocks.COLOR, () -> BlockEntityType.Builder.of(TileEntityColor::new, ModBlocks.color.get()).build(null));
}