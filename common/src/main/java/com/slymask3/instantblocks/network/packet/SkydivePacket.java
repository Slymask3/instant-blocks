package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class SkydivePacket extends InstantPacket {
	public final String[] colors;
	public final int radius;
	public final boolean teleport;
	public final int colorSetsIndex;

	public SkydivePacket(boolean activate, BlockPos pos, String[] colors, int radius, boolean teleport, int colorSetsIndex) {
		super(PacketHelper.PacketID.SKYDIVE,activate,pos);
		this.colors = colors;
		this.radius = radius;
		this.teleport = teleport;
		this.colorSetsIndex = colorSetsIndex;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		buffer = super.write(packet,buffer);
		SkydivePacket message = (SkydivePacket)packet;
		buffer.writeInt(message.colors.length);
		for(int i=0; i < message.colors.length; i++) {
			buffer.writeUtf(message.colors[i]);
		}
		buffer.writeInt(message.radius);
		buffer.writeBoolean(message.teleport);
		buffer.writeInt(message.colorSetsIndex);
		return buffer;
	}

	public static SkydivePacket decode(FriendlyByteBuf buffer) {
		boolean activate = buffer.readBoolean();
		BlockPos pos = buffer.readBlockPos();
		int colors_amount = buffer.readInt();
		String[] colors = new String[colors_amount];
		for(int i=0; i < colors_amount; i++) {
			colors[i] = buffer.readUtf();
		}
		int radius = buffer.readInt();
		boolean teleport = buffer.readBoolean();
		int colorSetsIndex = buffer.readInt();
		return new SkydivePacket(activate,pos,colors,radius,teleport,colorSetsIndex);
	}
}