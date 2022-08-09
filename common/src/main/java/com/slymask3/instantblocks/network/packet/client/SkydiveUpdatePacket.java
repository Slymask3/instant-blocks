package com.slymask3.instantblocks.network.packet.client;

import com.slymask3.instantblocks.config.entry.ColorSet;
import com.slymask3.instantblocks.network.PacketHelper;
import com.slymask3.instantblocks.network.packet.AbstractPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;
import java.util.List;

public class SkydiveUpdatePacket extends AbstractPacket {
	public final List<ColorSet> presets;
	public final BlockPos pos;

	public SkydiveUpdatePacket(List<ColorSet> presets, BlockPos pos) {
		super(PacketHelper.PacketID.SKYDIVE_UPDATE);
		this.presets = presets;
		this.pos = pos;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		SkydiveUpdatePacket message = (SkydiveUpdatePacket)packet;
		buffer.writeInt(message.presets.size());
		for(ColorSet colorSet : message.presets) {
			buffer.writeUtf(colorSet.name);
			buffer.writeInt(colorSet.colors.size());
			for(String color : colorSet.colors) {
				buffer.writeUtf(color);
			}
		}
		buffer.writeBlockPos(message.pos);
		return buffer;
	}

	public static SkydiveUpdatePacket decode(FriendlyByteBuf buffer) {
		int amount = buffer.readInt();
		List<ColorSet> presets = new ArrayList<>();
		for(int i=0; i < amount; i++) {
			String name = buffer.readUtf();
			int amountColors = buffer.readInt();
			List<String> colors = new ArrayList<>();
			for(int j=0; j < amountColors; j++) {
				colors.add(buffer.readUtf());
			}
			presets.add(new ColorSet(name,colors.toArray(new String[0])));
		}
		BlockPos pos = buffer.readBlockPos();
		return new SkydiveUpdatePacket(presets,pos);
	}
}