package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketID;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class ClientPacket extends AbstractPacket {
	public final String message;
	public final String variable;
	public final BlockPos pos;
	public final int particles;

	public ClientPacket(String message, String variable, BlockPos pos, int particles) {
		super(PacketID.CLIENT);
		this.message = message;
		this.variable = variable;
		this.pos = pos;
		this.particles = particles;
	}

	public FriendlyByteBuf getBuffer() {
		FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
		buffer.writeUtf(this.message);
		buffer.writeUtf(this.variable);
		buffer.writeBlockPos(this.pos);
		buffer.writeInt(this.particles);
		return buffer;
	}

	public static void encode(ClientPacket message, FriendlyByteBuf buffer) {
		buffer.writeUtf(message.message);
		buffer.writeUtf(message.variable);
		buffer.writeBlockPos(message.pos);
		buffer.writeInt(message.particles);
	}

	public static ClientPacket decode(FriendlyByteBuf buffer) {
		String message = buffer.readUtf();
		String variable = buffer.readUtf();
		BlockPos pos = buffer.readBlockPos();
		int particles = buffer.readInt();
		return new ClientPacket(message,variable,pos,particles);
	}
}