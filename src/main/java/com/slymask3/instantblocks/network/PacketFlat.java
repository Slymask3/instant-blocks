package com.slymask3.instantblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import com.slymask3.instantblocks.block.instant.BlockInstantFlat;

import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketFlat extends AbstractPacket {
	int _dim, _x, _y, _z;
	String _player;
	int _up, _down, _forward, _back, _left, _right;
	boolean _harvest;
	

	public PacketFlat() {
		
	}

	public PacketFlat(World world, int x, int y, int z, String player, int up, int down, int forward, int back, int left, int right, boolean harvest) {
		_dim = world.provider.dimensionId;
		_x = x;
		_y = y;
		_z = z;
		_player = player;
		_up = up;
		_down = down;
		_forward = forward;
		_back = back;
		_left = left;
		_right = right;
		_harvest = harvest;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(_dim);
		buffer.writeInt(_x);
		buffer.writeInt(_y);
		buffer.writeInt(_z);
		ByteBufUtils.writeUTF8String(buffer, _player);
		buffer.writeInt(_up);
		buffer.writeInt(_down);
		buffer.writeInt(_forward);
		buffer.writeInt(_back);
		buffer.writeInt(_left);
		buffer.writeInt(_right);
		buffer.writeBoolean(_harvest);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		_dim = buffer.readInt();
		_x = buffer.readInt();
		_y = buffer.readInt();
		_z = buffer.readInt();
		_player = ByteBufUtils.readUTF8String(buffer);
		_up = buffer.readInt();
		_down = buffer.readInt();
		_forward = buffer.readInt();
		_back = buffer.readInt();
		_left = buffer.readInt();
		_right = buffer.readInt();
		_harvest = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		World world = DimensionManager.getWorld(_dim);
		BlockInstantFlat block = (BlockInstantFlat)world.getBlock(_x, _y, _z);
		block.build(world, _x, _y, _z, _player, _up, _down, _forward, _back, _left, _right, _harvest);
	}
}
