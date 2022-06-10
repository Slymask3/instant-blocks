package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.block.BlockColor;
import com.slymask3.instantblocks.block.BlockSkydiveTP;
import com.slymask3.instantblocks.block.instant.*;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.item.ItemBlockInstantBlocks;
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

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

	public static final RegistryObject<Block> ibWoodHouse = register(Names.Blocks.IB_WOOD_HOUSE, BlockInstantHouseWood::new, Config.Common.ADD_WOODEN_HOUSE.get());
	public static final RegistryObject<Block> ibMiningLadder = register(Names.Blocks.IB_MINING_LADDER, BlockInstantMiningLadder::new, Config.Common.ADD_MINING_LADDER.get());
	public static final RegistryObject<Block> ibGlassDome = register(Names.Blocks.IB_GLASS_DOME, BlockInstantGlassDome::new, Config.Common.ADD_GLASS_DOME.get());
	public static final RegistryObject<Block> ibFarm = register(Names.Blocks.IB_FARM, BlockInstantFarm::new, Config.Common.ADD_FARM.get());
	public static final RegistryObject<Block> ibSkydive = register(Names.Blocks.IB_SKYDIVE, BlockInstantSkydive::new, Config.Common.ADD_SKYDIVE.get());
	public static final RegistryObject<Block> ibGrinder = register(Names.Blocks.IB_GRINDER, BlockInstantGrinder::new, Config.Common.ADD_GRINDER.get());
	public static final RegistryObject<Block> ibPool = register(Names.Blocks.IB_POOL, BlockInstantPool::new, Config.Common.ADD_POOL.get());
	public static final RegistryObject<Block> ibEscapeLadder = register(Names.Blocks.IB_ESCAPE_LADDER, BlockInstantEscapeLadder::new, Config.Common.ADD_ESCAPE_LADDER.get());
	public static final RegistryObject<Block> ibWater = register(Names.Blocks.IB_WATER, BlockInstantWater::new, Config.Common.ADD_WATER.get());
	public static final RegistryObject<Block> ibLava = register(Names.Blocks.IB_LAVA, BlockInstantLava::new, Config.Common.ADD_LAVA.get());
	public static final RegistryObject<Block> ibSuction = register(Names.Blocks.IB_SUCTION, BlockInstantSuction::new, Config.Common.ADD_SUCTION.get());
	public static final RegistryObject<Block> ibRail = register(Names.Blocks.IB_RAIL, BlockInstantRail::new, Config.Common.ADD_RAIL.get());
	public static final RegistryObject<Block> ibStatue = register(Names.Blocks.IB_STATUE, BlockInstantStatue::new, Config.Common.ADD_STATUE.get());
	public static final RegistryObject<Block> ibHarvest = register(Names.Blocks.IB_HARVEST, BlockInstantHarvest::new, Config.Common.ADD_HARVEST.get());
	public static final RegistryObject<Block> ibLight = register(Names.Blocks.IB_LIGHT, BlockInstantLight::new, Config.Common.ADD_LIGHT.get());
	public static final RegistryObject<Block> ibTree = register(Names.Blocks.IB_TREE, BlockInstantTree::new, Config.Common.ADD_TREE.get());

	public static final RegistryObject<Block> color = register(Names.Blocks.COLOR, BlockColor::new, Config.Common.ADD_SKYDIVE.get() || Config.Common.ADD_STATUE.get());
	public static final RegistryObject<Block> skydiveTP = register(Names.Blocks.SKYDIVE_TP, BlockSkydiveTP::new, Config.Common.ADD_SKYDIVE.get());

	private static RegistryObject<Block> register(final String name, final Supplier<? extends Block> supplier, boolean add) {
		return add ? BLOCKS.register(name,supplier) : null;
	}

	// automatically creates items for all your blocks
	// you could do it manually instead by registering BlockItems in your ItemInit class
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		// for each block we registered above...
		BLOCKS.getEntries().stream().map(RegistryObject::get).forEach( (block) -> {
			// make a block item that places the block
			// note, if you have a special block that needs a custom implementation for the BlockItem, just add an if statement here
			final BlockItem blockItem = new ItemBlockInstantBlocks(block);

			// register the block item with the same name as the block
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
	}
}