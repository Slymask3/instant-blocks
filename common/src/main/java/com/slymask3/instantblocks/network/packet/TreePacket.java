package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class TreePacket extends InstantPacket {
	public final int _type;
	public final boolean _log;
	public final boolean _leaves;
	public final boolean _air;

	public TreePacket(boolean activate, BlockPos pos, int type, boolean fullLogs, boolean fullLeaves, boolean air) {
		super(PacketHelper.PacketID.TREE,activate,pos);
		_type = type;
		_log = fullLogs;
		_leaves = fullLeaves;
		_air = air;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		buffer = super.write(packet,buffer);
		TreePacket message = (TreePacket)packet;
		buffer.writeBlockPos(message.pos);
		buffer.writeInt(message._type);
		buffer.writeBoolean(message._log);
		buffer.writeBoolean(message._leaves);
		buffer.writeBoolean(message._air);
		return buffer;
	}

	public static TreePacket decode(FriendlyByteBuf buffer) {
		boolean activate = buffer.readBoolean();
		BlockPos pos = buffer.readBlockPos();
		int type = buffer.readInt();
		boolean log = buffer.readBoolean();
		boolean leaves = buffer.readBoolean();
		boolean air = buffer.readBoolean();
		return new TreePacket(activate,pos,type,log,leaves,air);
	}
}