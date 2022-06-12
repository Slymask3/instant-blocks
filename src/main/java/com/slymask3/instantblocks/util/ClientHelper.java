package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ClientHelper {
    public static boolean isClient(Level world) {
        return world.isClientSide();
    }

    public static void playSound(Level world, int x, int y, int z) {
        world.playSound(Minecraft.getInstance().player, new BlockPos(x,y,z), new SoundEvent(new ResourceLocation("minecraft", Config.Client.SOUND.get())), SoundSource.BLOCKS,0.4F,1.0F);
    }

    public static void showParticles(Level world, int x, int y, int z) {
        if(Config.Client.SHOW_EFFECTS.get()) {
            for(double i = 0; i <= 1; i = i + 0.2) {
                for(double n = 0; n <= 1; n = n + 0.2) {
                    for(double v = 0; v <= 1; v = v + 0.2) {
                        world.addParticle(DustParticleOptions.REDSTONE,x+i, y+v, z+n, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
    }

    public static void sendMessage(Player player, String msg, String color) {
        if(Config.Client.SHOW_MESSAGES.get() && isClient(player.getLevel())) {
            //player.sendMessage(new TextComponent(Strings.PREFIX + Colors.colorEveryWord(msg, color)),player.getUUID());
            player.displayClientMessage(new TextComponent(Strings.PREFIX + Colors.colorEveryWord(msg, color)),true);
        }
    }
}