package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.network.packet.AbstractPacket;
import net.minecraft.server.level.ServerPlayer;

public interface IPacketHandler {
    void sendToServer(AbstractPacket message);
    void sendToClient(ServerPlayer player, AbstractPacket message);
}
