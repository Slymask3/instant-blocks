package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.block.BlockColor;
import com.slymask3.instantblocks.block.BlockSkydiveTP;
import com.slymask3.instantblocks.block.instant.*;
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

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

	public static final RegistryObject<Block> ibWoodHouse = BLOCKS.register(Names.Blocks.IB_WOOD_HOUSE, BlockInstantHouseWood::new);
	public static final RegistryObject<Block> ibFarm = BLOCKS.register(Names.Blocks.IB_FARM, BlockInstantFarm::new);
	public static final RegistryObject<Block> ibGlassDome = BLOCKS.register(Names.Blocks.IB_GLASS_DOME, BlockInstantGlassDome::new);
	public static final RegistryObject<Block> ibEscapeLadder = BLOCKS.register(Names.Blocks.IB_ESCAPE_LADDER, BlockInstantEscapeLadder::new);
	public static final RegistryObject<Block> ibPool = BLOCKS.register(Names.Blocks.IB_POOL, BlockInstantPool::new);
	public static final RegistryObject<Block> ibMiningLadder = BLOCKS.register(Names.Blocks.IB_MINING_LADDER, BlockInstantMiningLadder::new);
	public static final RegistryObject<Block> ibRail = BLOCKS.register(Names.Blocks.IB_RAIL, BlockInstantRail::new);
	public static final RegistryObject<Block> ibGrinder = BLOCKS.register(Names.Blocks.IB_GRINDER, BlockInstantGrinder::new);
	public static final RegistryObject<Block> ibWater = BLOCKS.register(Names.Blocks.IB_WATER, BlockInstantWater::new);
	public static final RegistryObject<Block> ibLava = BLOCKS.register(Names.Blocks.IB_LAVA, BlockInstantLava::new);
	public static final RegistryObject<Block> ibSuction = BLOCKS.register(Names.Blocks.IB_SUCTION, BlockInstantSuction::new);
	public static final RegistryObject<Block> ibSkydive = BLOCKS.register(Names.Blocks.IB_SKYDIVE, BlockInstantSkydive::new);
	public static final RegistryObject<Block> ibStatue = BLOCKS.register(Names.Blocks.IB_STATUE, BlockInstantStatue::new);
	public static final RegistryObject<Block> ibHarvest = BLOCKS.register(Names.Blocks.IB_HARVEST, BlockInstantHarvest::new);
	public static final RegistryObject<Block> ibTree = BLOCKS.register(Names.Blocks.IB_TREE, BlockInstantTree::new);
	public static final RegistryObject<Block> ibLight = BLOCKS.register(Names.Blocks.IB_LIGHT, BlockInstantLight::new);

	public static final RegistryObject<Block> color = BLOCKS.register(Names.Blocks.COLOR, BlockColor::new);
	public static final RegistryObject<Block> skydiveTP = BLOCKS.register(Names.Blocks.SKYDIVE_TP, BlockSkydiveTP::new);

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