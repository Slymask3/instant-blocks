package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.block.instant.BlockInstantFall;
import com.slymask3.instantblocks.util.BuildHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketSkydive extends AbstractPacket {
	int _dim, _x, _y, _z;
	String _player;
	int _color0 , _color1, _color2, _color3, _color4, _color5, _color6, _color7, _color8, _color9, _color10;
	int _radius;
	boolean _tp, _actualRainbow;

	public PacketSkydive() {
		
	}
	public PacketSkydive(World world, int x, int y, int z, String player, int c0, int c1, int c2, int c3, int c4, int c5, int c6, int c7, int c8, int c9, int c10, int radius, boolean tp, boolean actualRainbow) {
		_dim = world.provider.dimensionId;
		_x = x;
		_y = y;
		_z = z;
		_player = player;
		_color0 = c0;
		_color1 = c1;
		_color2 = c2;
		_color3 = c3;
		_color4 = c4;
		_color5 = c5;
		_color6 = c6;
		_color7 = c7;
		_color8 = c8;
		_color9 = c9;
		_color10 = c10;
		_radius = radius;
		_tp = tp;
		_actualRainbow = actualRainbow;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(_dim);
		buffer.writeInt(_x);
		buffer.writeInt(_y);
		buffer.writeInt(_z);
		ByteBufUtils.writeUTF8String(buffer, _player);
		buffer.writeInt(_color0);
		buffer.writeInt(_color1);
		buffer.writeInt(_color2);
		buffer.writeInt(_color3);
		buffer.writeInt(_color4);
		buffer.writeInt(_color5);
		buffer.writeInt(_color6);
		buffer.writeInt(_color7);
		buffer.writeInt(_color8);
		buffer.writeInt(_color9);
		buffer.writeInt(_color10);
		buffer.writeInt(_radius);
		buffer.writeBoolean(_tp);
		buffer.writeBoolean(_actualRainbow);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		_dim = buffer.readInt();
		_x = buffer.readInt();
		_y = buffer.readInt();
		_z = buffer.readInt();
		_player = ByteBufUtils.readUTF8String(buffer);
		_color0 = buffer.readInt();
		_color1 = buffer.readInt();
		_color2 = buffer.readInt();
		_color3 = buffer.readInt();
		_color4 = buffer.readInt();
		_color5 = buffer.readInt();
		_color6 = buffer.readInt();
		_color7 = buffer.readInt();
		_color8 = buffer.readInt();
		_color9 = buffer.readInt();
		_color10 = buffer.readInt();
		_radius = buffer.readInt();
		_tp = buffer.readBoolean();
		_actualRainbow = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		World world = DimensionManager.getWorld(_dim);
		BlockInstantFall block = (BlockInstantFall) BuildHelper.getBlock(world,_x, _y, _z);
		block.build(world, _x, _y, _z, _player, _color0, _color1, _color2, _color3, _color4, _color5, _color6, _color7, _color8, _color9, _color10, _radius, _tp, _actualRainbow);
	}
}
