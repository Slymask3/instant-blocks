package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.block.ColorBlock;
import com.slymask3.instantblocks.block.SkydiveTPBlock;
import com.slymask3.instantblocks.block.instant.*;
import com.slymask3.instantblocks.item.InstantBlockItem;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Reference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

	public static final RegistryObject<Block> INSTANT_WOOD_HOUSE = BLOCKS.register(Names.Blocks.IB_WOOD_HOUSE, InstantHouseWoodBlock::new);
	public static final RegistryObject<Block> INSTANT_MINING_LADDER = BLOCKS.register(Names.Blocks.IB_MINING_LADDER, InstantMiningLadderBlock::new);
	public static final RegistryObject<Block> INSTANT_GLASS_DOME = BLOCKS.register(Names.Blocks.IB_GLASS_DOME, InstantGlassDomeBlock::new);
	public static final RegistryObject<Block> INSTANT_FARM = BLOCKS.register(Names.Blocks.IB_FARM, InstantFarmBlock::new);
	public static final RegistryObject<Block> INSTANT_SKYDIVE = BLOCKS.register(Names.Blocks.IB_SKYDIVE, InstantSkydiveBlock::new);
	public static final RegistryObject<Block> INSTANT_GRINDER = BLOCKS.register(Names.Blocks.IB_GRINDER, InstantGrinderBlock::new);
	public static final RegistryObject<Block> INSTANT_POOL = BLOCKS.register(Names.Blocks.IB_POOL, InstantPoolBlock::new);
	public static final RegistryObject<Block> INSTANT_ESCAPE_LADDER = BLOCKS.register(Names.Blocks.IB_ESCAPE_LADDER, InstantEscapeLadderBlock::new);
	public static final RegistryObject<Block> INSTANT_WATER = BLOCKS.register(Names.Blocks.IB_WATER, InstantWaterBlock::new);
	public static final RegistryObject<Block> INSTANT_LAVA = BLOCKS.register(Names.Blocks.IB_LAVA, InstantLavaBlock::new);
	public static final RegistryObject<Block> INSTANT_SUCTION = BLOCKS.register(Names.Blocks.IB_SUCTION, InstantSuctionBlock::new);
	public static final RegistryObject<Block> INSTANT_RAIL = BLOCKS.register(Names.Blocks.IB_RAIL, InstantRailBlock::new);
	public static final RegistryObject<Block> INSTANT_STATUE = BLOCKS.register(Names.Blocks.IB_STATUE, InstantStatueBlock::new);
	public static final RegistryObject<Block> INSTANT_HARVEST = BLOCKS.register(Names.Blocks.IB_HARVEST, InstantHarvestBlock::new);
	public static final RegistryObject<Block> INSTANT_LIGHT = BLOCKS.register(Names.Blocks.IB_LIGHT, InstantLightBlock::new);
	public static final RegistryObject<Block> INSTANT_SCHEMATIC = BLOCKS.register(Names.Blocks.IB_SCHEMATIC, InstantSchematicBlock::new);
	public static final RegistryObject<Block> INSTANT_TREE = BLOCKS.register(Names.Blocks.IB_TREE, InstantTreeBlock::new);

	public static final RegistryObject<Block> COLOR = BLOCKS.register(Names.Blocks.COLOR, ColorBlock::new);
	public static final RegistryObject<Block> SKYDIVE_TP = BLOCKS.register(Names.Blocks.SKYDIVE_TP, SkydiveTPBlock::new);

	// automatically creates items for all your blocks
	// you could do it manually instead by registering BlockItems in your ItemInit class
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		// for each block we registered above...
		BLOCKS.getEntries().stream().map(RegistryObject::get).forEach( (block) -> {
			// make a block item that places the block
			// note, if you have a special block that needs a custom implementation for the BlockItem, just add an if statement here
			final BlockItem blockItem = new InstantBlockItem(block);

			// register the block item with the same name as the block
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
	}
}