package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.entity.*;
import com.slymask3.instantblocks.reference.Names;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTiles {
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Common.MOD_ID);

	public static final RegistryObject<BlockEntityType<SkydiveBlockEntity>> SKYDIVE = TILES.register(Names.Blocks.IB_SKYDIVE, () -> BlockEntityType.Builder.of(SkydiveBlockEntity::new, ModBlocks.INSTANT_SKYDIVE).build(null));
	public static final RegistryObject<BlockEntityType<StatueBlockEntity>> STATUE = TILES.register(Names.Blocks.IB_STATUE, () -> BlockEntityType.Builder.of(StatueBlockEntity::new, ModBlocks.INSTANT_STATUE).build(null));
	public static final RegistryObject<BlockEntityType<HarvestBlockEntity>> HARVEST = TILES.register(Names.Blocks.IB_HARVEST, () -> BlockEntityType.Builder.of(HarvestBlockEntity::new, ModBlocks.INSTANT_HARVEST).build(null));
	public static final RegistryObject<BlockEntityType<SchematicBlockEntity>> SCHEMATIC = TILES.register(Names.Blocks.IB_SCHEMATIC, () -> BlockEntityType.Builder.of(SchematicBlockEntity::new, ModBlocks.INSTANT_SCHEMATIC).build(null));
	public static final RegistryObject<BlockEntityType<TreeBlockEntity>> TREE = TILES.register(Names.Blocks.IB_TREE, () -> BlockEntityType.Builder.of(TreeBlockEntity::new, ModBlocks.INSTANT_TREE).build(null));
	public static final RegistryObject<BlockEntityType<ColorBlockEntity>> COLOR = TILES.register(Names.Blocks.COLOR, () -> BlockEntityType.Builder.of(ColorBlockEntity::new, ModBlocks.COLOR, ModBlocks.SKYDIVE_TP).build(null));
}