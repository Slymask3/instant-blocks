package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.block.InstantLiquidBlock;
import com.slymask3.instantblocks.gui.screens.*;
import com.slymask3.instantblocks.handler.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientHelper {
    public enum Screen { STATUE, HARVEST, SKYDIVE, SCHEMATIC, TREE }
    public enum Particles { NONE, GENERATE, NO_LIQUID }

    public static boolean isClient(Level world) {
        return world.isClientSide();
    }

    public static void playSound(Level world, int x, int y, int z, Particles particles) {
        SoundEvent sound = switch(particles) {
            case GENERATE -> new SoundEvent(new ResourceLocation("minecraft", Config.Client.SOUND_GENERATE.get()));
            case NO_LIQUID -> new SoundEvent(new ResourceLocation("minecraft", Config.Client.SOUND_NO_LIQUID.get()));
            default -> SoundEvents.PLAYER_LEVELUP;
        };
        world.playSound(Minecraft.getInstance().player, new BlockPos(x,y,z), sound, SoundSource.BLOCKS,0.4F,1.0F);
    }

    public static void showParticles(Level world, int x, int y, int z, Particles particles) {
        if(Config.Client.SHOW_EFFECTS.get()) {
            switch(particles) {
                case GENERATE -> {
                    for(double i = 0; i <= 1; i = i + 0.2) {
                        for(double n = 0; n <= 1; n = n + 0.2) {
                            for(double v = 0; v <= 1; v = v + 0.2) {
                                world.addParticle(DustParticleOptions.REDSTONE,x+i, y+v, z+n, 0.0D, 0.0D, 0.0D);
                            }
                        }
                    }
                }
                case NO_LIQUID -> {
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)x + 0.5D, (double)y + 1.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)x + 1.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)x + 0.5D, (double)y + 0.5D, (double)z + 1.2D, 0.0D, 0.0D, 0.0D);
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)x + 0.5D, (double)y - 0.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)x - 0.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
                    world.addParticle(InstantLiquidBlock.WHITE_DUST, (double)x + 0.5D, (double)y + 0.5D, (double)z - 0.2D, 0.0D, 0.0D, 0.0D);
                }
            }

        }
    }

    public static void sendMessage(Player player, String message, String variable) {
        if(Config.Client.SHOW_MESSAGES.get() && isClient(player.getLevel())) {
            player.displayClientMessage(new TranslatableComponent(message, variable.isEmpty() ? new Object[0] : variable),true);
        }
    }

    public static void showScreen(Screen screen, Player player, Level world, BlockPos pos) {
        if(isClient(world)) {
            switch(screen) {
                case SKYDIVE -> Minecraft.getInstance().setScreen(new SkydiveScreen(player,world,pos.getX(),pos.getY(),pos.getZ()));
                case STATUE -> Minecraft.getInstance().setScreen(new StatueScreen(player,world,pos.getX(),pos.getY(),pos.getZ()));
                case HARVEST -> Minecraft.getInstance().setScreen(new HarvestScreen(player,world,pos.getX(),pos.getY(),pos.getZ()));
                case TREE -> Minecraft.getInstance().setScreen(new TreeScreen(player,world,pos.getX(),pos.getY(),pos.getZ()));
                case SCHEMATIC -> Minecraft.getInstance().setScreen(new SchematicScreen(player,world,pos.getX(),pos.getY(),pos.getZ()));
            }
        }
    }
}