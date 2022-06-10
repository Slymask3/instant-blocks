package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.reference.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Reference.MOD_ID,"main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register() {
        int index = 100;
        INSTANCE.registerMessage(++index, PacketMessage.class, PacketMessage::encode, PacketMessage::decode, PacketMessage.Handler::handle);
        INSTANCE.registerMessage(++index, PacketSkydive.class, PacketSkydive::encode, PacketSkydive::decode, PacketSkydive.Handler::handle);
        INSTANCE.registerMessage(++index, PacketStatue.class, PacketStatue::encode, PacketStatue::decode, PacketStatue.Handler::handle);
        INSTANCE.registerMessage(++index, PacketHarvest.class, PacketHarvest::encode, PacketHarvest::decode, PacketHarvest.Handler::handle);
        INSTANCE.registerMessage(++index, PacketTree.class, PacketTree::encode, PacketTree::decode, PacketTree.Handler::handle);
    }

    public static void sendToServer(Object message) {
        INSTANCE.sendToServer(message);
    }

    public static void sendToClient(ServerPlayer player, Object message) {
        INSTANCE.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }
}