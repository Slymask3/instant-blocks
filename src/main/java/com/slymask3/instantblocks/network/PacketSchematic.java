package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.block.instant.BlockInstantSchematic;
import com.slymask3.instantblocks.util.BuildHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketSchematic extends AbstractPacket {
	int _dim, _x, _y, _z;
	String _schematic;
	boolean _center, _air;

	public PacketSchematic() {}
	public PacketSchematic(World world, int x, int y, int z, String schematic, boolean center, boolean air) {
		_dim = world.provider.dimensionId;
		_x = x;
		_y = y;
		_z = z;
		_schematic = schematic;
		_center = center;
		_air = air;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(_dim);
		buffer.writeInt(_x);
		buffer.writeInt(_y);
		buffer.writeInt(_z);
		ByteBufUtils.writeUTF8String(buffer, _schematic);
		buffer.writeBoolean(_center);
		buffer.writeBoolean(_air);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		_dim = buffer.readInt();
		_x = buffer.readInt();
		_y = buffer.readInt();
		_z = buffer.readInt();
		_schematic = ByteBufUtils.readUTF8String(buffer);
		_center = buffer.readBoolean();
		_air = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		World world = DimensionManager.getWorld(_dim);
		BlockInstantSchematic block = (BlockInstantSchematic) BuildHelper.getBlock(world,_x, _y, _z);
		boolean built = block.build(world, _x, _y, _z, this._schematic, this._center, this._air);
		if(built) {
			block.afterBuild(world, _x, _y, _z, player);
		}
	}
}