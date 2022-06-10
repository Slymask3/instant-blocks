package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientPacketHandler {
    public static void handle(PacketMessage message, Supplier<NetworkEvent.Context> context) {
        InstantBlocks.LOGGER.info("PacketMessage handle()");
        Player player = Minecraft.getInstance().player;
        Level world = player.getLevel();
        InstantBlocks.LOGGER.info("message.effects: " + message.effects);
        if(message.effects) {
            IBHelper.sound(world, message.x, message.y, message.z);
            IBHelper.effectFull(world, message.x, message.y, message.z);
        }
        IBHelper.msg(player, message.message, message.color);
    }
}