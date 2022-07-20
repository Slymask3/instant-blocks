package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class TreePacket extends InstantPacket {
	public final int type;
	public final boolean hollowLogs;
	public final boolean hollowLeaves;
	public final boolean airInside;

	public TreePacket(boolean activate, BlockPos pos, int type, boolean hollowLogs, boolean hollowLeaves, boolean airInside) {
		super(PacketHelper.PacketID.TREE,activate,pos);
		this.type = type;
		this.hollowLogs = hollowLogs;
		this.hollowLeaves = hollowLeaves;
		this.airInside = airInside;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		buffer = super.write(packet,buffer);
		TreePacket message = (TreePacket)packet;
		buffer.writeInt(message.type);
		buffer.writeBoolean(message.hollowLogs);
		buffer.writeBoolean(message.hollowLeaves);
		buffer.writeBoolean(message.airInside);
		return buffer;
	}

	public static TreePacket decode(FriendlyByteBuf buffer) {
		boolean activate = buffer.readBoolean();
		BlockPos pos = buffer.readBlockPos();
		int type = buffer.readInt();
		boolean hollowLogs = buffer.readBoolean();
		boolean hollowLeaves = buffer.readBoolean();
		boolean airInside = buffer.readBoolean();
		return new TreePacket(activate,pos,type,hollowLogs,hollowLeaves,airInside);
	}
}