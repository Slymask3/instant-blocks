package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketID;
import net.minecraft.network.FriendlyByteBuf;

public class SchematicPacket extends AbstractPacket {
	public final int _x;
	public final int _y;
	public final int _z;
	public final String _schematic;
	public final boolean _center;
	public final boolean _air;

	public SchematicPacket(int x, int y, int z, String schematic, boolean center, boolean air) {
		super(PacketID.SCHEMATIC);
		_x = x;
		_y = y;
		_z = z;
		_schematic = schematic;
		_center = center;
		_air = air;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		SchematicPacket message = (SchematicPacket)packet;
		buffer.writeInt(message._x);
		buffer.writeInt(message._y);
		buffer.writeInt(message._z);
		buffer.writeUtf(message._schematic);
		buffer.writeBoolean(message._center);
		buffer.writeBoolean(message._air);
		return buffer;
	}

	public static SchematicPacket decode(FriendlyByteBuf buffer) {
		int x = buffer.readInt();
		int y = buffer.readInt();
		int z = buffer.readInt();
		String schematic = buffer.readUtf();
		boolean center = buffer.readBoolean();
		boolean air = buffer.readBoolean();
		return new SchematicPacket(x,y,z,schematic,center,air);
	}
}