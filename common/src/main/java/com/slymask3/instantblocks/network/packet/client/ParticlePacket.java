package com.slymask3.instantblocks.network.packet.client;

import com.slymask3.instantblocks.network.PacketHelper;
import com.slymask3.instantblocks.network.packet.AbstractPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class ParticlePacket extends AbstractPacket {
	public final BlockPos pos;
	public final int particles;

	public ParticlePacket(BlockPos pos, int particles) {
		super(PacketHelper.PacketID.PARTICLE);
		this.pos = pos;
		this.particles = particles;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		ParticlePacket message = (ParticlePacket)packet;
		buffer.writeBlockPos(message.pos);
		buffer.writeInt(message.particles);
		return buffer;
	}

	public static ParticlePacket decode(FriendlyByteBuf buffer) {
		BlockPos pos = buffer.readBlockPos();
		int particles = buffer.readInt();
		return new ParticlePacket(pos,particles);
	}
}