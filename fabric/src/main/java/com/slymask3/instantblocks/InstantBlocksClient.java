package com.slymask3.instantblocks;

import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.util.ClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;

public class InstantBlocksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1, ModBlocks.INSTANT_WATER);
        ColorProviderRegistry.BLOCK.register(new ClientHelper.Color(), ModBlocks.COLOR);
        ColorProviderRegistry.BLOCK.register(new ClientHelper.Color(), ModBlocks.SKYDIVE_TP);
        ColorProviderRegistry.ITEM.register((itemStack,tintIndex) -> 4159204, ModBlocks.INSTANT_WATER.asItem());

        BlockRenderLayerMapImpl.INSTANCE.putBlocks(RenderType.translucent(), ModBlocks.INSTANT_GLASS_DOME, ModBlocks.INSTANT_ESCAPE_LADDER, ModBlocks.INSTANT_RAIL, ModBlocks.INSTANT_WATER, ModBlocks.INSTANT_GRINDER);
        BlockRenderLayerMapImpl.INSTANCE.putBlocks(RenderType.cutout(), ModBlocks.INSTANT_TREE, ModBlocks.INSTANT_LIGHT);
    }
}