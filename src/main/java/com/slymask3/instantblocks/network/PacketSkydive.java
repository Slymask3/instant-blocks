package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.block.instant.BlockInstantSkydive;
import com.slymask3.instantblocks.util.BuildHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketSkydive extends AbstractPacket {
	int _dim, _x, _y, _z;
	int[] _colors = new int[11];
	int _radius;
	boolean _tp;

	public PacketSkydive() {}
	public PacketSkydive(World world, int x, int y, int z, int[] colors, int radius, boolean tp) {
		_dim = world.provider.dimensionId;
		_x = x;
		_y = y;
		_z = z;
		_colors = colors;
		_radius = radius;
		_tp = tp;
		//_actualRainbow = actualRainbow;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(_dim);
		buffer.writeInt(_x);
		buffer.writeInt(_y);
		buffer.writeInt(_z);
		for(int i=0; i<_colors.length; i++) {
			buffer.writeInt(_colors[i]);
		}
		buffer.writeInt(_radius);
		buffer.writeBoolean(_tp);
		//buffer.writeBoolean(_actualRainbow);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		_dim = buffer.readInt();
		_x = buffer.readInt();
		_y = buffer.readInt();
		_z = buffer.readInt();
		for(int i=0; i<_colors.length; i++) {
			_colors[i] = buffer.readInt();
		}
		_radius = buffer.readInt();
		_tp = buffer.readBoolean();
		//_actualRainbow = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		World world = DimensionManager.getWorld(_dim);
		BlockInstantSkydive block = (BlockInstantSkydive) BuildHelper.getBlock(world,_x, _y, _z);
		block.build(world, _x, _y, _z, player, _colors, _radius, _tp);
		block.afterBuild(world, _x, _y, _z, player);
	}
}