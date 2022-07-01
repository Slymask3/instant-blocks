package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.network.packet.ClientPacket;
import com.slymask3.instantblocks.util.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class ClientPacketHandler {
    public static void handle(ClientPacket message, Supplier<NetworkEvent.Context> context) {
        Player player = Minecraft.getInstance().player;
        if(player != null) {
            if(message.particles != ClientHelper.Particles.NONE.ordinal()) {
                Level world = player.getLevel();
                ClientHelper.playSound(world, message.x, message.y, message.z, ClientHelper.Particles.values()[message.particles]);
                ClientHelper.showParticles(world, message.x, message.y, message.z, ClientHelper.Particles.values()[message.particles]);
            }
            if(!message.message.isEmpty()) {
                ClientHelper.sendMessage(player, message.message, message.variable);
            }
        }
    }
}