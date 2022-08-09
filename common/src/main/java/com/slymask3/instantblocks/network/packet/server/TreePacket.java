package com.slymask3.instantblocks.network.packet.server;

import com.slymask3.instantblocks.config.entry.HugeTree;
import com.slymask3.instantblocks.network.PacketHelper;
import com.slymask3.instantblocks.network.packet.AbstractPacket;
import com.slymask3.instantblocks.network.packet.InstantPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class TreePacket extends InstantPacket {
	public final HugeTree tree;
	public final boolean hollowLogs;
	public final boolean hollowLeaves;
	public final boolean airInside;
	public final int hugeTreesIndex;

	public TreePacket(boolean activate, BlockPos pos, HugeTree tree, boolean hollowLogs, boolean hollowLeaves, boolean airInside, int hugeTreesIndex) {
		super(PacketHelper.PacketID.TREE,activate,pos);
		this.tree = tree;
		this.hollowLogs = hollowLogs;
		this.hollowLeaves = hollowLeaves;
		this.airInside = airInside;
		this.hugeTreesIndex = hugeTreesIndex;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		buffer = super.write(packet,buffer);
		TreePacket message = (TreePacket)packet;
		buffer.writeUtf(message.tree.name);
		buffer.writeInt(message.tree.type.ordinal());
		buffer.writeUtf(message.tree.logs);
		buffer.writeUtf(message.tree.leaves);
		buffer.writeBoolean(message.hollowLogs);
		buffer.writeBoolean(message.hollowLeaves);
		buffer.writeBoolean(message.airInside);
		buffer.writeInt(message.hugeTreesIndex);
		return buffer;
	}

	public static TreePacket decode(FriendlyByteBuf buffer) {
		boolean activate = buffer.readBoolean();
		BlockPos pos = buffer.readBlockPos();
		String name = buffer.readUtf();
		int type = buffer.readInt();
		String logs = buffer.readUtf();
		String leaves = buffer.readUtf();
		HugeTree tree = new HugeTree(name,HugeTree.Type.values()[type],logs,leaves);
		boolean hollowLogs = buffer.readBoolean();
		boolean hollowLeaves = buffer.readBoolean();
		boolean airInside = buffer.readBoolean();
		int hugeTreesIndex = buffer.readInt();
		return new TreePacket(activate,pos,tree,hollowLogs,hollowLeaves,airInside,hugeTreesIndex);
	}
}