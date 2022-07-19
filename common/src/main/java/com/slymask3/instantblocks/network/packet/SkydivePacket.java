package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class SkydivePacket extends InstantPacket {
	public final int _colors_amount;
	public final String[] _colors;
	public final int _radius;
	public final boolean _tp;

	public SkydivePacket(boolean activate, BlockPos pos, int colors_amount, String[] colors, int radius, boolean tp) {
		super(PacketHelper.PacketID.SKYDIVE,activate,pos);
		_colors_amount = colors_amount;
		_colors = colors;
		_radius = radius;
		_tp = tp;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		buffer = super.write(packet,buffer);
		SkydivePacket message = (SkydivePacket)packet;
		buffer.writeInt(message._colors_amount);
		for(int i=0; i < message._colors.length; i++) {
			buffer.writeUtf(message._colors[i]);
		}
		buffer.writeInt(message._radius);
		buffer.writeBoolean(message._tp);
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
		boolean tp = buffer.readBoolean();
		return new SkydivePacket(activate,pos,colors_amount,colors,radius,tp);
	}
}