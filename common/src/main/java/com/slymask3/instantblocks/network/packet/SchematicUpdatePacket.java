package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;

public class SchematicUpdatePacket extends AbstractPacket {
	public final int amount;
	public final ArrayList<String> schematics;
	public final BlockPos pos;

	public SchematicUpdatePacket(ArrayList<String> schematics, BlockPos pos) {
		super(PacketHelper.PacketID.SCHEMATIC_UPDATE);
		this.amount = schematics.size();
		this.schematics = schematics;
		this.pos = pos;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		SchematicUpdatePacket message = (SchematicUpdatePacket)packet;
		buffer.writeInt(message.amount);
		for(int i=0; i < message.schematics.size(); i++) {
			buffer.writeUtf(message.schematics.get(i));
		}
		buffer.writeBlockPos(message.pos);
		return buffer;
	}

	public static SchematicUpdatePacket decode(FriendlyByteBuf buffer) {
		int amount = buffer.readInt();
		ArrayList<String> schematics = new ArrayList<>();
		for(int i=0; i < amount; i++) {
			schematics.add(buffer.readUtf());
		}
		BlockPos pos = buffer.readBlockPos();
		return new SchematicUpdatePacket(schematics,pos);
	}
}