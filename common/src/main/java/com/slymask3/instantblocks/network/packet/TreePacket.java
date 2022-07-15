package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketID;
import net.minecraft.network.FriendlyByteBuf;

public class TreePacket extends AbstractPacket {
	public final int _x;
	public final int _y;
	public final int _z;
	public final int _type;
	public final boolean _log;
	public final boolean _leaves;
	public final boolean _air;

	public TreePacket(int x, int y, int z, int type, boolean fullLogs, boolean fullLeaves, boolean air) {
		super(PacketID.TREE);
		_x = x;
		_y = y;
		_z = z;
		_type = type;
		_log = fullLogs;
		_leaves = fullLeaves;
		_air = air;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		TreePacket message = (TreePacket)packet;
		buffer.writeInt(message._x);
		buffer.writeInt(message._y);
		buffer.writeInt(message._z);
		buffer.writeInt(message._type);
		buffer.writeBoolean(message._log);
		buffer.writeBoolean(message._leaves);
		buffer.writeBoolean(message._air);
		return buffer;
	}

	public static TreePacket decode(FriendlyByteBuf buffer) {
		int x = buffer.readInt();
		int y = buffer.readInt();
		int z = buffer.readInt();
		int type = buffer.readInt();
		boolean log = buffer.readBoolean();
		boolean leaves = buffer.readBoolean();
		boolean air = buffer.readBoolean();
		return new TreePacket(x,y,z,type,log,leaves,air);
	}
}