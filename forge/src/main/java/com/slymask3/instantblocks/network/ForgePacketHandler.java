package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.network.packet.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
        INSTANCE.registerMessage(++index, ClientPacket.class, (ClientPacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), ClientPacket::decode, Handler::client);
        INSTANCE.registerMessage(++index, SkydivePacket.class, (SkydivePacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), SkydivePacket::decode, Handler::common);
        INSTANCE.registerMessage(++index, StatuePacket.class, (StatuePacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), StatuePacket::decode, Handler::common);
        INSTANCE.registerMessage(++index, HarvestPacket.class, (HarvestPacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), HarvestPacket::decode, Handler::common);
        INSTANCE.registerMessage(++index, TreePacket.class, (TreePacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), TreePacket::decode, Handler::common);
        INSTANCE.registerMessage(++index, SchematicPacket.class, (SchematicPacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), SchematicPacket::decode, Handler::common);
        INSTANCE.registerMessage(++index, SchematicUpdatePacket.class, (SchematicUpdatePacket message, FriendlyByteBuf buffer) -> message.write(message,buffer), SchematicUpdatePacket::decode, Handler::client);
    }

    public static class Handler {
        public static void common(AbstractPacket message, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                Player player = context.get().getSender();
                if(player != null) {
                    if(message.getClass().equals(SkydivePacket.class)) {
                        PacketHelper.handleSkydive((SkydivePacket)message, player);
                    } else if(message.getClass().equals(StatuePacket.class)) {
                        PacketHelper.handleStatue((StatuePacket)message, player);
                    } else if(message.getClass().equals(HarvestPacket.class)) {
                        PacketHelper.handleHarvest((HarvestPacket)message, player);
                    } else if(message.getClass().equals(TreePacket.class)) {
                        PacketHelper.handleTree((TreePacket)message, player);
                    } else if(message.getClass().equals(SchematicPacket.class)) {
                        PacketHelper.handleSchematic((SchematicPacket)message, player);
                    }
                }
            });
            context.get().setPacketHandled(true);
        }
        public static void client(AbstractPacket message, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHandler.handle(message,context));
            });
            context.get().setPacketHandled(true);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class ClientHandler {
        public static void handle(AbstractPacket message, Supplier<NetworkEvent.Context> context) {
            Player player = Minecraft.getInstance().player;
            if(player != null) {
                if(message.getClass().equals(ClientPacket.class)) {
                    PacketHelper.handleClient((ClientPacket)message, player);
                } else if(message.getClass().equals(SchematicUpdatePacket.class)) {
                    PacketHelper.handleSchematicUpdate((SchematicUpdatePacket)message, player);
                }
            }
        }
    }
}