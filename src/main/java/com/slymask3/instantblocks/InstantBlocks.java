package com.slymask3.instantblocks;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.init.ModTiles;
import com.slymask3.instantblocks.network.PacketHandler;
import com.slymask3.instantblocks.reference.Reference;
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
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ibGlassDome.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ibEscapeLadder.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ibRail.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ibWater.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ibTree.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ibLight.get(), RenderType.cutout());
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		PacketHandler.register();
	}
}