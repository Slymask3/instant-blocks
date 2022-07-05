package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.ColorBlock;
import com.slymask3.instantblocks.block.SkydiveTPBlock;
import com.slymask3.instantblocks.block.instant.*;
import com.slymask3.instantblocks.item.InstantBlockItem;
import com.slymask3.instantblocks.reference.Names;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, InstantBlocks.MOD_ID);

	public static final RegistryObject<Block> INSTANT_WOOD_HOUSE = register(Names.Blocks.IB_WOOD_HOUSE, InstantHouseWoodBlock::new);
	public static final RegistryObject<Block> INSTANT_MINING_LADDER = register(Names.Blocks.IB_MINING_LADDER, InstantMiningLadderBlock::new);
	public static final RegistryObject<Block> INSTANT_GLASS_DOME = register(Names.Blocks.IB_GLASS_DOME, InstantGlassDomeBlock::new);
	public static final RegistryObject<Block> INSTANT_FARM = register(Names.Blocks.IB_FARM, InstantFarmBlock::new);
	public static final RegistryObject<Block> INSTANT_SKYDIVE = register(Names.Blocks.IB_SKYDIVE, InstantSkydiveBlock::new);
	public static final RegistryObject<Block> INSTANT_GRINDER = register(Names.Blocks.IB_GRINDER, InstantGrinderBlock::new);
	public static final RegistryObject<Block> INSTANT_POOL = register(Names.Blocks.IB_POOL, InstantPoolBlock::new);
	public static final RegistryObject<Block> INSTANT_ESCAPE_LADDER = register(Names.Blocks.IB_ESCAPE_LADDER, InstantEscapeLadderBlock::new);
	public static final RegistryObject<Block> INSTANT_WATER = register(Names.Blocks.IB_WATER, InstantWaterBlock::new);
	public static final RegistryObject<Block> INSTANT_LAVA = register(Names.Blocks.IB_LAVA, InstantLavaBlock::new);
	public static final RegistryObject<Block> INSTANT_SUCTION = register(Names.Blocks.IB_SUCTION, InstantSuctionBlock::new);
	public static final RegistryObject<Block> INSTANT_RAIL = register(Names.Blocks.IB_RAIL, InstantRailBlock::new);
	public static final RegistryObject<Block> INSTANT_STATUE = register(Names.Blocks.IB_STATUE, InstantStatueBlock::new);
	public static final RegistryObject<Block> INSTANT_HARVEST = register(Names.Blocks.IB_HARVEST, InstantHarvestBlock::new);
	public static final RegistryObject<Block> INSTANT_LIGHT = register(Names.Blocks.IB_LIGHT, InstantLightBlock::new);
	public static final RegistryObject<Block> INSTANT_SCHEMATIC = register(Names.Blocks.IB_SCHEMATIC, InstantSchematicBlock::new);
	public static final RegistryObject<Block> INSTANT_TREE = register(Names.Blocks.IB_TREE, InstantTreeBlock::new);

	public static final RegistryObject<Block> COLOR = register(Names.Blocks.COLOR, ColorBlock::new);
	public static final RegistryObject<Block> SKYDIVE_TP = register(Names.Blocks.SKYDIVE_TP, SkydiveTPBlock::new);

	private static RegistryObject<Block> register(String name, Supplier<? extends Block> sup) {
		RegistryObject<Block> registryObject = BLOCKS.register(name,sup);
		ModItems.ITEMS.register(name,() -> new InstantBlockItem(registryObject.get()));
		return registryObject;
	}
}