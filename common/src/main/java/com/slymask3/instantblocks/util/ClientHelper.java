package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantLiquidBlock;
import com.slymask3.instantblocks.block.entity.ColorBlockEntity;
import com.slymask3.instantblocks.gui.screens.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ClientHelper {
    public enum Screen { STATUE, HARVEST, SKYDIVE, SCHEMATIC, TREE }
    public enum Particles { NONE, GENERATE, NO_LIQUID }

    public static void playSound(Level world, BlockPos pos, Particles particles) {
        SoundEvent sound = switch(particles) {
            case GENERATE -> new SoundEvent(new ResourceLocation("minecraft", Common.CONFIG.SOUND_GENERATE()));
            case NO_LIQUID -> new SoundEvent(new ResourceLocation("minecraft", Common.CONFIG.SOUND_NO_LIQUID()));
            default -> SoundEvents.PLAYER_LEVELUP;
        };
        world.playSound(Minecraft.getInstance().player, pos, sound, SoundSource.BLOCKS,0.4F,1.0F);
    }

    public static void showParticles(Level world, BlockPos pos, Particles particles) {
        if(Common.CONFIG.SHOW_EFFECTS()) {
            switch(particles) {
                case GENERATE -> {
                    for(double i = 0; i <= 1; i = i + 0.2) {
                        for(double n = 0; n <= 1; n = n + 0.2) {
                            for(double v = 0; v <= 1; v = v + 0.2) {
                                world.addParticle(DustParticleOptions.REDSTONE,pos.getX()+i, pos.getY()+v, pos.getZ()+n, 0.0D, 0.0D, 0.0D);
                            }
                        }
                    }
                }
                case NO_LIQUID -> {
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.2D, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)pos.getX() + 1.2D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 1.2D, 0.0D, 0.0D, 0.0D);
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)pos.getX() + 0.5D, (double)pos.getY() - 0.2D, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)pos.getX() - 0.2D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() - 0.2D, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    public static void sendMessage(Player player, String message, String variable) {
        if(Common.CONFIG.SHOW_MESSAGES() && Helper.isClient(player.getLevel())) {
            player.displayClientMessage(Component.translatable(message, variable.isEmpty() ? new Object[0] : variable),true);
        }
    }

    public static void showScreen(Screen screen, Player player, Level world, BlockPos pos) {
        if(Helper.isClient(world)) {
            switch(screen) {
                case SKYDIVE -> Minecraft.getInstance().setScreen(new SkydiveScreen(player,world,pos));
                case STATUE -> Minecraft.getInstance().setScreen(new StatueScreen(player,world,pos));
                case HARVEST -> Minecraft.getInstance().setScreen(new HarvestScreen(player,world,pos));
                case TREE -> Minecraft.getInstance().setScreen(new TreeScreen(player,world,pos));
                case SCHEMATIC -> Minecraft.getInstance().setScreen(new SchematicScreen(player,world,pos));
            }
        }
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