package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.network.packet.client.*;
import com.slymask3.instantblocks.network.packet.server.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class FabricPacketHandler {
    public static class Common {
        public static void init() {
            ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.SKYDIVE.toString().toLowerCase()), (server, player, handler, buf, responseSender) -> {
                if(player != null) {
                    SkydivePacket message = SkydivePacket.decode(buf);
                    server.execute(() -> PacketHelper.handleSkydive(message, player));
                }
            });

            ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.STATUE.toString().toLowerCase()), (server, player, handler, buf, responseSender) -> {
                if(player != null) {
                    StatuePacket message = StatuePacket.decode(buf);
                    server.execute(() -> PacketHelper.handleStatue(message, player));
                }
            });

            ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.HARVEST.toString().toLowerCase()), (server, player, handler, buf, responseSender) -> {
                if(player != null) {
                    HarvestPacket message = HarvestPacket.decode(buf);
                    server.execute(() -> PacketHelper.handleHarvest(message, player));
                }
            });

            ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.TREE.toString().toLowerCase()), (server, player, handler, buf, responseSender) -> {
                if(player != null) {
                    TreePacket message = TreePacket.decode(buf);
                    server.execute(() -> PacketHelper.handleTree(message, player));
                }
            });

            ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.SCHEMATIC.toString().toLowerCase()), (server, player, handler, buf, responseSender) -> {
                if(player != null) {
                    SchematicPacket message = SchematicPacket.decode(buf);
                    server.execute(() -> PacketHelper.handleSchematic(message, player));
                }
            });
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Client {
        public static void init() {
            ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.MESSAGE.toString().toLowerCase()), (client, handler, buf, responseSender) -> {
                if(client.player != null) {
                    MessagePacket message = MessagePacket.decode(buf);
                    client.execute(() -> PacketHelper.handleMessage(message, client.player));
                }
            });

            ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.PARTICLE.toString().toLowerCase()), (client, handler, buf, responseSender) -> {
                if(client.player != null) {
                    ParticlePacket message = ParticlePacket.decode(buf);
                    client.execute(() -> PacketHelper.handleParticle(message, client.player));
                }
            });

            ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.SOUND.toString().toLowerCase()), (client, handler, buf, responseSender) -> {
                if(client.player != null) {
                    SoundPacket message = SoundPacket.decode(buf);
                    client.execute(() -> PacketHelper.handleSound(message, client.player));
                }
            });

            ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.SCHEMATIC_UPDATE.toString().toLowerCase()), (client, handler, buf, responseSender) -> {
                if(client.player != null) {
                    SchematicUpdatePacket message = SchematicUpdatePacket.decode(buf);
                    client.execute(() -> PacketHelper.handleSchematicUpdate(message, client.player));
                }
            });

            ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.SKYDIVE_UPDATE.toString().toLowerCase()), (client, handler, buf, responseSender) -> {
                if(client.player != null) {
                    SkydiveUpdatePacket message = SkydiveUpdatePacket.decode(buf);
                    client.execute(() -> PacketHelper.handleSkydiveUpdate(message, client.player));
                }
            });

            ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(PacketHelper.PacketID.TREE_UPDATE.toString().toLowerCase()), (client, handler, buf, responseSender) -> {
                if(client.player != null) {
                    TreeUpdatePacket message = TreeUpdatePacket.decode(buf);
                    client.execute(() -> PacketHelper.handleTreeUpdate(message, client.player));
                }
            });
        }
    }
}