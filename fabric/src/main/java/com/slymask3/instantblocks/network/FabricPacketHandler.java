package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.network.packet.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class FabricPacketHandler {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketID.CLIENT.toString().toLowerCase()), (client, handler, buf, responseSender) -> {
            ClientPacket message = ClientPacket.decode(buf);
            client.execute(() -> {
                PacketHelper.handleClient(message, client.player);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketID.SKYDIVE.toString().toLowerCase()), (server, player, handler, buf, responseSender) -> {
            SkydivePacket message = SkydivePacket.decode(buf);
            server.execute(() -> {
                PacketHelper.handleSkydive(message, player);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketID.STATUE.toString().toLowerCase()), (server, player, handler, buf, responseSender) -> {
            StatuePacket message = StatuePacket.decode(buf);
            server.execute(() -> {
                PacketHelper.handleStatue(message, player);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketID.HARVEST.toString().toLowerCase()), (server, player, handler, buf, responseSender) -> {
            HarvestPacket message = HarvestPacket.decode(buf);
            server.execute(() -> {
                PacketHelper.handleHarvest(message, player);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketID.TREE.toString().toLowerCase()), (server, player, handler, buf, responseSender) -> {
            TreePacket message = TreePacket.decode(buf);
            server.execute(() -> {
                PacketHelper.handleTree(message, player);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketID.SCHEMATIC.toString().toLowerCase()), (server, player, handler, buf, responseSender) -> {
            SchematicPacket message = SchematicPacket.decode(buf);
            server.execute(() -> {
                PacketHelper.handleSchematic(message, player);
            });
        });
    }
}