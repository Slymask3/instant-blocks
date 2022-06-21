package com.slymask3.instantblocks.handler;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.entity.ColorBlockEntity;
import com.slymask3.instantblocks.init.ModBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InstantBlocks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Client {
    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((state,world,pos,tintIndex) -> world != null && pos != null ? BiomeColors.getAverageWaterColor(world, pos) : -1, ModBlocks.INSTANT_WATER.get());
        event.getBlockColors().register(new Color(), ModBlocks.COLOR.get());
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        event.getItemColors().register((itemStack,tintIndex) -> 4159204, ModBlocks.INSTANT_WATER.get().asItem());
    }

    public static class Color implements BlockColor {
        @Override
        public int getColor(BlockState state, BlockAndTintGetter blockAndTintGetter, BlockPos pos, int tintIndex) {
            if(blockAndTintGetter == null) {
                return -1;
            }
            BlockEntity tileEntity = blockAndTintGetter.getBlockEntity(pos);
            if(tileEntity instanceof ColorBlockEntity) {
                return ((ColorBlockEntity) tileEntity).color;
            }
            return -1;
        }
    }
}