package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.block.instant.BlockInstantTree;
import com.slymask3.instantblocks.util.BuildHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketTree extends AbstractPacket {
	int _dim, _x, _y, _z;
	String _player;
	int _type;
	boolean _log, _leaves, _air;

	public PacketTree() {}
	public PacketTree(World world, int x, int y, int z, String player, int type, boolean fullLogs, boolean fullLeaves, boolean air) {
		_dim = world.provider.dimensionId;
		_x = x;
		_y = y;
		_z = z;
		_player = player;
		_type = type;
		_log = fullLogs;
		_leaves = fullLeaves;
		_air = air;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(_dim);
		buffer.writeInt(_x);
		buffer.writeInt(_y);
		buffer.writeInt(_z);
		ByteBufUtils.writeUTF8String(buffer, _player);
		buffer.writeInt(_type);
		buffer.writeBoolean(_log);
		buffer.writeBoolean(_leaves);
		buffer.writeBoolean(_air);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		_dim = buffer.readInt();
		_x = buffer.readInt();
		_y = buffer.readInt();
		_z = buffer.readInt();
		_player = ByteBufUtils.readUTF8String(buffer);
		_type = buffer.readInt();
		_log = buffer.readBoolean();
		_leaves = buffer.readBoolean();
		_air = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		World world = DimensionManager.getWorld(_dim);
		BlockInstantTree block = (BlockInstantTree) BuildHelper.getBlock(world,_x, _y, _z);
		block.build(world, _x, _y, _z, _player, this._type, this._log, this._leaves, this._air);
	}
}