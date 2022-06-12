package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.util.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientPacketHandler {
    public static void handle(PacketMessage message, Supplier<NetworkEvent.Context> context) {
        Player player = Minecraft.getInstance().player;
        if(player != null) {
            Level world = player.getLevel();
            if(message.effects) {
                ClientHelper.playSound(world, message.x, message.y, message.z);
                ClientHelper.showParticles(world, message.x, message.y, message.z);
            }
            ClientHelper.sendMessage(player, message.message, message.color);
        }
    }
}