package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.block.instant.BlockInstantStatue;
import com.slymask3.instantblocks.util.BuildHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketStatue extends AbstractPacket {
	int _dim, _x, _y, _z;
	String _username;
	boolean _head, _body, _armLeft, _armRight, _legLeft, _legRight;
	boolean _rgb;

	public PacketStatue() {}
	public PacketStatue(World world, int x, int y, int z, /*double range*/ String username, boolean head, boolean body, boolean armLeft, boolean armRight, boolean legLeft, boolean legRight, boolean rgb) {
		_dim = world.provider.dimensionId;
		_x = x;
		_y = y;
		_z = z;
		_username = username;
		_head = head;
		_body = body;
		_armLeft = armLeft;
		_armRight = armRight;
		_legLeft = legLeft;
		_legRight = legRight;
		_rgb = rgb;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(_dim);
		buffer.writeInt(_x);
		buffer.writeInt(_y);
		buffer.writeInt(_z);
		ByteBufUtils.writeUTF8String(buffer, _username);
		buffer.writeBoolean(_head);
		buffer.writeBoolean(_body);
		buffer.writeBoolean(_armLeft);
		buffer.writeBoolean(_armRight);
		buffer.writeBoolean(_legLeft);
		buffer.writeBoolean(_legRight);
		buffer.writeBoolean(_rgb);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		_dim = buffer.readInt();
		_x = buffer.readInt();
		_y = buffer.readInt();
		_z = buffer.readInt();
		_username = ByteBufUtils.readUTF8String(buffer);
		_head = buffer.readBoolean();
		_body = buffer.readBoolean();
		_armLeft = buffer.readBoolean();
		_armRight = buffer.readBoolean();
		_legLeft = buffer.readBoolean();
		_legRight = buffer.readBoolean();
		_rgb = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		World world = DimensionManager.getWorld(_dim);
		BlockInstantStatue block = (BlockInstantStatue) BuildHelper.getBlock(world,_x, _y, _z);
		boolean built = block.build(world, _x, _y, _z, player, this._username, this._head, this._body, this._armLeft, this._armRight, this._legLeft, this._legRight, this._rgb);
		if(built) {
			block.afterBuild(world, _x, _y, _z, player);
		}
	}
}