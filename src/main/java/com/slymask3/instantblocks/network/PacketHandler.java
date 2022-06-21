package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.network.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(InstantBlocks.MOD_ID,"main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register() {
        int index = 100;
        INSTANCE.registerMessage(++index, MessagePacket.class, MessagePacket::encode, MessagePacket::decode, MessagePacket.Handler::handle);
        INSTANCE.registerMessage(++index, SkydivePacket.class, SkydivePacket::encode, SkydivePacket::decode, SkydivePacket.Handler::handle);
        INSTANCE.registerMessage(++index, StatuePacket.class, StatuePacket::encode, StatuePacket::decode, StatuePacket.Handler::handle);
        INSTANCE.registerMessage(++index, HarvestPacket.class, HarvestPacket::encode, HarvestPacket::decode, HarvestPacket.Handler::handle);
        INSTANCE.registerMessage(++index, TreePacket.class, TreePacket::encode, TreePacket::decode, TreePacket.Handler::handle);
        INSTANCE.registerMessage(++index, SchematicPacket.class, SchematicPacket::encode, SchematicPacket::decode, SchematicPacket.Handler::handle);
    }

    public static void sendToServer(Object message) {
        INSTANCE.sendToServer(message);
    }

    public static void sendToClient(ServerPlayer player, Object message) {
        INSTANCE.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }
}