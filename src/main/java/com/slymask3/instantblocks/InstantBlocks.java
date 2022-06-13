package com.slymask3.instantblocks;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.init.ModTiles;
import com.slymask3.instantblocks.network.PacketHandler;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class InstantBlocks {
	public static final Logger LOGGER = LogManager.getLogger();

	public InstantBlocks() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		Config.init();
		modEventBus.addListener(this::setupClient);
		modEventBus.addListener(this::setupCommon);
		ModBlocks.BLOCKS.register(modEventBus);
		ModItems.ITEMS.register(modEventBus);
		ModTiles.TILES.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setupClient(final FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_GLASS_DOME.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_ESCAPE_LADDER.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_RAIL.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_WATER.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_TREE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_LIGHT.get(), RenderType.cutout());
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		PacketHandler.register();
		SchematicHelper.createSchematicsDir();
	}
}