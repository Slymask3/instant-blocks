package com.slymask3.instantblocks.handler;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.config.ClothConfig;
import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.platform.Services;
import com.slymask3.instantblocks.util.ClientHelper;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Common.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler {
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register((state,world,pos,tintIndex) -> world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1, ModBlocks.INSTANT_WATER);
        event.getBlockColors().register(new ClientHelper.Color(), ModBlocks.COLOR);
        event.getBlockColors().register(new ClientHelper.Color(), ModBlocks.COLOR_LAYER);
        event.getBlockColors().register(new ClientHelper.Color(), ModBlocks.SKYDIVE_TP);
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register((itemStack,tintIndex) -> 4159204, ModBlocks.INSTANT_WATER.asItem());
    }

    @SubscribeEvent
    public static void registerConfigScreen(FMLClientSetupEvent event) {
        if(Services.PLATFORM.isModLoaded("cloth_config")) {
            ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> AutoConfig.getConfigScreen(ClothConfig.class, parent).get()));
        }
    }
}