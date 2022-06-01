package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.block.instant.BlockInstantFarm;
import com.slymask3.instantblocks.block.instant.BlockInstantGlassDome;
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

	public static final RegistryObject<Block> ibFarm = BLOCKS.register(Names.Blocks.IB_FARM, BlockInstantFarm::new);
	public static final RegistryObject<Block> ibGlassDome = BLOCKS.register(Names.Blocks.IB_GLASS_DOME, BlockInstantGlassDome::new);

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