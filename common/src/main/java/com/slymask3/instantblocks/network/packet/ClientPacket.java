package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class ClientPacket extends AbstractPacket {
	public final String message;
	public final String variable;
	public final BlockPos pos;
	public final int particles;

	public ClientPacket(String message, String variable, BlockPos pos, int particles) {
		super(PacketHelper.PacketID.CLIENT);
		this.message = message;
		this.variable = variable;
		this.pos = pos;
		this.particles = particles;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		ClientPacket message = (ClientPacket)packet;
		buffer.writeUtf(message.message);
		buffer.writeUtf(message.variable);
		buffer.writeBlockPos(message.pos);
		buffer.writeInt(message.particles);
		return buffer;
	}

	public static ClientPacket decode(FriendlyByteBuf buffer) {
		String message = buffer.readUtf();
		String variable = buffer.readUtf();
		BlockPos pos = buffer.readBlockPos();
		int particles = buffer.readInt();
		return new ClientPacket(message,variable,pos,particles);
	}
}