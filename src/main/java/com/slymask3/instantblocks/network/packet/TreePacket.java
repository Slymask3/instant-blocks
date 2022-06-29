package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.block.instant.InstantTreeBlock;
import com.slymask3.instantblocks.util.Builder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TreePacket {
	public int _x, _y, _z;
	public int _type;
	public boolean _log, _leaves, _air;

	public TreePacket(int x, int y, int z, int type, boolean fullLogs, boolean fullLeaves, boolean air) {
		_x = x;
		_y = y;
		_z = z;
		_type = type;
		_log = fullLogs;
		_leaves = fullLeaves;
		_air = air;
	}

	public static void encode(TreePacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message._x);
		buffer.writeInt(message._y);
		buffer.writeInt(message._z);
		buffer.writeInt(message._type);
		buffer.writeBoolean(message._log);
		buffer.writeBoolean(message._leaves);
		buffer.writeBoolean(message._air);
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

	public static class Handler {
		public static void handle(TreePacket message, Supplier<NetworkEvent.Context> context) {
			context.get().enqueueWork(() -> {
				Player player = context.get().getSender();
				Level world = player.getLevel();

				InstantTreeBlock block = (InstantTreeBlock) Builder.getBlock(world,message._x, message._y, message._z);
				if(block.build(world, message._x, message._y, message._z, message._type, message._log, message._leaves, message._air)) {
					block.afterBuild(world, message._x, message._y, message._z, player);
				}
			});
			context.get().setPacketHandled(true);
		}
	}
}