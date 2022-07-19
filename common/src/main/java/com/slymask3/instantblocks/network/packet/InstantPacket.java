package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public abstract class InstantPacket extends AbstractPacket {
	public final boolean activate;
	public final BlockPos pos;

	public InstantPacket(PacketHelper.PacketID packetID, boolean activate, BlockPos pos) {
		super(packetID);
		this.activate = activate;
		this.pos = pos;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		InstantPacket message = (InstantPacket)packet;
		buffer.writeBoolean(message.activate);
		buffer.writeBlockPos(message.pos);
		return buffer;
	}
}