package com.slymask3.instantblocks.handler;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.config.ClothConfig;
import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.platform.Services;
import com.slymask3.instantblocks.util.ClientHelper;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Common.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler {
    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_GLASS_DOME, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_ESCAPE_LADDER, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_RAIL, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_WATER, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_TREE, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_LIGHT, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_GRINDER, RenderType.translucent());
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((state,world,pos,tintIndex) -> world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1, ModBlocks.INSTANT_WATER);
        event.getBlockColors().register(new ClientHelper.Color(), ModBlocks.COLOR);
        event.getBlockColors().register(new ClientHelper.Color(), ModBlocks.COLOR_LAYER);
        event.getBlockColors().register(new ClientHelper.Color(), ModBlocks.SKYDIVE_TP);
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().register((itemStack,tintIndex) -> 4159204, ModBlocks.INSTANT_WATER.asItem());
    }

    @SubscribeEvent
    public static void registerConfigScreen(FMLClientSetupEvent event) {
        if(Services.PLATFORM.isModLoaded("cloth_config")) {
            ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory((client, parent) -> AutoConfig.getConfigScreen(ClothConfig.class, parent).get()));
        }
    }
}