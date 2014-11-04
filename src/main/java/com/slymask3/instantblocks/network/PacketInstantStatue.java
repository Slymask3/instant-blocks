package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.block.BlockInstantStatue;
import com.slymask3.instantblocks.tileentity.TileEntityInstantStatue;
import com.slymask3.instantblocks.utility.LogHelper;

import cpw.mods.fml.common.network.ByteBufUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketInstantStatue extends AbstractPacket {
	int _dim, _x, _y, _z;
	//double _range;
	String _player;
	String _username;
	boolean _head, _body, _armLeft, _armRight, _legLeft, _legRight;
	boolean _rgb;
	

	public PacketInstantStatue() {
		
	}

	public PacketInstantStatue(World world, int x, int y, int z, String player, /*double range*/ String username, boolean head, boolean body, boolean armLeft, boolean armRight, boolean legLeft, boolean legRight, boolean rgb) {
		_dim = world.provider.dimensionId;
		_x = x;
		_y = y;
		_z = z;
		_player = player;
		//_range = range;
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
		ByteBufUtils.writeUTF8String(buffer, _player);
		//buffer.writeDouble(_range);
		//buffer.writeBytes(_username.getBytes());
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
		_player = ByteBufUtils.readUTF8String(buffer);
		//_range = buffer.readDouble();
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
		World world = DimensionManager.getWorld(_dim);
		TileEntity tileentity = world.getTileEntity(_x, _y, _z);
		((TileEntityInstantStatue)tileentity).setUsername(_username);
		((TileEntityInstantStatue)tileentity).setParts(_head, _body, _armLeft, _armRight, _legLeft, _legRight);
		LogHelper.info("handleCientSide(): CLIENT");

		BlockInstantStatue block = (BlockInstantStatue)world.getBlock(_x, _y, _z);
		
		block.build(world, _x, _y, _z, _player, tileentity.getBlockMetadata(), this._username, this._head, this._body, this._armLeft, this._armRight, this._legLeft, this._legRight, this._rgb);

		LogHelper.info("handleClientSide(): username = " + this._username);
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		World world = DimensionManager.getWorld(_dim);
		//if (world==null) return;

		//TileEntity tileentity = world.getTileEntity(_x, _y, _z);
		BlockInstantStatue block = (BlockInstantStatue)world.getBlock(_x, _y, _z);
		
		//if(tileentity != null && tileentity instanceof TileEntityInstantStatue) {
			//((TileEntityInstantStatue)tileentity).setDetectionRange(_range);
			//((TileEntityInstantStatue)tileentity).setUsername(_username);
			//((TileEntityInstantStatue)tileentity).setParts(_head, _body, _armLeft, _armRight, _legLeft, _legRight);
			LogHelper.info("handleServerSide(): SERVER");
			
			//if (this._username != "" && !world.isRemote) {
				//sendChangeToServer();
			block.build(world, _x, _y, _z, _player, world.getTileEntity(_x, _y, _z).getBlockMetadata(), this._username, this._head, this._body, this._armLeft, this._armRight, this._legLeft, this._legRight, this._rgb);
			//}
			LogHelper.info("handleServerSide(): username = " + this._username);
		//}
	}
}
