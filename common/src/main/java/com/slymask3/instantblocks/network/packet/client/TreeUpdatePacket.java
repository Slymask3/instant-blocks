package com.slymask3.instantblocks.network.packet.client;

import com.slymask3.instantblocks.config.entry.HugeTree;
import com.slymask3.instantblocks.network.PacketHelper;
import com.slymask3.instantblocks.network.packet.AbstractPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;
import java.util.List;

public class TreeUpdatePacket extends AbstractPacket {
	public final List<HugeTree> hugeTrees;
	public final BlockPos pos;

	public TreeUpdatePacket(List<HugeTree> hugeTrees, BlockPos pos) {
		super(PacketHelper.PacketID.TREE_UPDATE);
		this.hugeTrees = hugeTrees;
		this.pos = pos;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		TreeUpdatePacket message = (TreeUpdatePacket)packet;
		buffer.writeInt(message.hugeTrees.size());
		for(HugeTree hugeTree : message.hugeTrees) {
			buffer.writeUtf(hugeTree.name);
			buffer.writeInt(hugeTree.type.ordinal());
			buffer.writeUtf(hugeTree.logs);
			buffer.writeUtf(hugeTree.leaves);
		}
		buffer.writeBlockPos(message.pos);
		return buffer;
	}

	public static TreeUpdatePacket decode(FriendlyByteBuf buffer) {
		int amount = buffer.readInt();
		List<HugeTree> hugeTrees = new ArrayList<>();
		for(int i=0; i < amount; i++) {
			String name = buffer.readUtf();
			int type = buffer.readInt();
			String logs = buffer.readUtf();
			String leaves = buffer.readUtf();
			hugeTrees.add(new HugeTree(name,HugeTree.Type.values()[type],logs,leaves));
		}
		BlockPos pos = buffer.readBlockPos();
		return new TreeUpdatePacket(hugeTrees,pos);
	}
}