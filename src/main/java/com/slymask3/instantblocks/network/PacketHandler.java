package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.reference.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Reference.MOD_ID,
                    "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    public static void register() {
        INSTANCE.registerMessage(0,
                PacketSkydive.class,
                PacketSkydive::encode,
                PacketSkydive::decode,
                PacketSkydive.Handler::handle);
    }

    public static void sendToServer(Object message) {
        INSTANCE.sendToServer(message);
    }
}