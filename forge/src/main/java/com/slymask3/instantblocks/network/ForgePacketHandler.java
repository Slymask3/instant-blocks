package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.network.packet.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class ForgePacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Common.MOD_ID,"main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register() {
        int index = 100;
        INSTANCE.registerMessage(++index, ClientPacket.class, (ClientPacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), ClientPacket::decode, ClientPacketHandler::handle);
        INSTANCE.registerMessage(++index, SkydivePacket.class, (SkydivePacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), SkydivePacket::decode, SkydivePacketHandler::handle);
        INSTANCE.registerMessage(++index, StatuePacket.class, (StatuePacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), StatuePacket::decode, StatuePacketHandler::handle);
        INSTANCE.registerMessage(++index, HarvestPacket.class, (HarvestPacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), HarvestPacket::decode, HarvestPacketHandler::handle);
        INSTANCE.registerMessage(++index, TreePacket.class, (TreePacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), TreePacket::decode, TreePacketHandler::handle);
        INSTANCE.registerMessage(++index, SchematicPacket.class, (SchematicPacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), SchematicPacket::decode, SchematicPacketHandler::handle);
    }

    public static class ClientPacketHandler {
        public static void handle(ClientPacket message, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                    PacketHelper.handleClient(message, Minecraft.getInstance().player);
                });
            });
            context.get().setPacketHandled(true);
        }
    }

    public static class SkydivePacketHandler {
        public static void handle(SkydivePacket message, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                PacketHelper.handleSkydive(message, context.get().getSender());
            });
            context.get().setPacketHandled(true);
        }
    }

    public static class StatuePacketHandler {
        public static void handle(StatuePacket message, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                PacketHelper.handleStatue(message, context.get().getSender());
            });
            context.get().setPacketHandled(true);
        }
    }

    public static class HarvestPacketHandler {
        public static void handle(HarvestPacket message, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                PacketHelper.handleHarvest(message, context.get().getSender());
            });
            context.get().setPacketHandled(true);
        }
    }

    public static class TreePacketHandler {
        public static void handle(TreePacket message, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                PacketHelper.handleTree(message, context.get().getSender());
            });
            context.get().setPacketHandled(true);
        }
    }

    public static class SchematicPacketHandler {
        public static void handle(SchematicPacket message, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                PacketHelper.handleSchematic(message, context.get().getSender());
            });
            context.get().setPacketHandled(true);
        }
    }
}