package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.network.packet.AbstractPacket;
import net.minecraft.world.entity.player.Player;

public interface IPacketHandler {
    void sendToServer(AbstractPacket message);
    void sendToClient(Player player, AbstractPacket message);
}