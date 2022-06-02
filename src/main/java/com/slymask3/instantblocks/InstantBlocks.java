package com.slymask3.instantblocks;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Reference;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
		ModItems.ITEMS.register(modEventBus);
		ModBlocks.BLOCKS.register(modEventBus);
		modEventBus.addListener(this::registerBlockColors);
		modEventBus.addListener(this::registerItemColors);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setupClient(final FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ibGlassDome.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ibEscapeLadder.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ibRail.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ibWater.get(), RenderType.translucent());
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		//AnvilHandler.initAnvilRecipes();
	}

	@SubscribeEvent
	public void registerBlockColors(ColorHandlerEvent.Block event) {
		event.getBlockColors().register((state,world,pos,tintIndex) -> world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1, ModBlocks.ibWater.get());
	}

	@SubscribeEvent
	public void registerItemColors(ColorHandlerEvent.Item event) {
		event.getItemColors().register((p_92702_, p_92703_) -> 0x00FF00, ModBlocks.ibWater.get().asItem());
	}
}