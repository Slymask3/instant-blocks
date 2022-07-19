package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class SchematicPacket extends InstantPacket {
	public final String _schematic;
	public final boolean _center;
	public final boolean _air;

	public SchematicPacket(boolean activate, BlockPos pos, String schematic, boolean center, boolean air) {
		super(PacketHelper.PacketID.SCHEMATIC,activate,pos);
		_schematic = schematic;
		_center = center;
		_air = air;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		buffer = super.write(packet,buffer);
		SchematicPacket message = (SchematicPacket)packet;
		buffer.writeUtf(message._schematic);
		buffer.writeBoolean(message._center);
		buffer.writeBoolean(message._air);
		return buffer;
	}

	public static SchematicPacket decode(FriendlyByteBuf buffer) {
		boolean activate = buffer.readBoolean();
		BlockPos pos = buffer.readBlockPos();
		String schematic = buffer.readUtf();
		boolean center = buffer.readBoolean();
		boolean air = buffer.readBoolean();
		return new SchematicPacket(activate,pos,schematic,center,air);
	}
}