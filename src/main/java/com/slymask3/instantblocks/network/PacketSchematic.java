package com.slymask3.instantblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import com.slymask3.instantblocks.block.instant.BlockInstantSchematic;

import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketSchematic extends AbstractPacket {
	int _dim, _x, _y, _z;
	String _player;
	String _schematic;
	boolean _center;
	

	public PacketSchematic() {
		
	}

	public PacketSchematic(World world, int x, int y, int z, String player, String schematic, boolean center) {
		_dim = world.provider.dimensionId;
		_x = x;
		_y = y;
		_z = z;
		_player = player;
		_schematic = schematic;
		_center = center;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(_dim);
		buffer.writeInt(_x);
		buffer.writeInt(_y);
		buffer.writeInt(_z);
		ByteBufUtils.writeUTF8String(buffer, _player);
		ByteBufUtils.writeUTF8String(buffer, _schematic);
		buffer.writeBoolean(_center);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		_dim = buffer.readInt();
		_x = buffer.readInt();
		_y = buffer.readInt();
		_z = buffer.readInt();
		_player = ByteBufUtils.readUTF8String(buffer);
		_schematic = ByteBufUtils.readUTF8String(buffer);
		_center = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		World world = DimensionManager.getWorld(_dim);
		BlockInstantSchematic block = (BlockInstantSchematic)world.getBlock(_x, _y, _z);
		block.build(world, _x, _y, _z, world.getTileEntity(_x, _y, _z).getBlockMetadata(), _player, this._schematic, this._center);
	}
}
