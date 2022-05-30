package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.block.instant.BlockInstantHarvest;
import com.slymask3.instantblocks.util.BuildHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketHarvest extends AbstractPacket {
	int _dim, _x, _y, _z;
	boolean _logOak, _logSpruce, _logBirch, _logJungle, _logAcacia, _logDark;
	boolean _wheat, _carrot, _potato;
	boolean _cactus, _pumpkin, _melon, _sugarcane;
	boolean _cocoa, _mushroom, _netherwart;
	boolean _replant;

	public PacketHarvest() {}
	public PacketHarvest(World world, int x, int y, int z, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
		_dim = world.provider.dimensionId;
		_x = x;
		_y = y;
		_z = z;
		_logOak = logOak;
		_logSpruce = logSpruce;
		_logBirch = logBirch;
		_logJungle = logJungle;
		_logAcacia = logAcacia;
		_logDark = logDark;
		_wheat = wheat;
		_carrot = carrot;
		_potato = potato;
		_cactus = cactus;
		_pumpkin = pumpkin;
		_melon = melon;
		_sugarcane = sugarcane;
		_cocoa = cocoa;
		_mushroom = mushroom;
		_netherwart = netherwart;
		_replant = replant;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(_dim);
		buffer.writeInt(_x);
		buffer.writeInt(_y);
		buffer.writeInt(_z);
		buffer.writeBoolean(_logOak);
		buffer.writeBoolean(_logSpruce);
		buffer.writeBoolean(_logBirch);
		buffer.writeBoolean(_logJungle);
		buffer.writeBoolean(_logAcacia);
		buffer.writeBoolean(_logDark);
		buffer.writeBoolean(_wheat);
		buffer.writeBoolean(_carrot);
		buffer.writeBoolean(_potato);
		buffer.writeBoolean(_cactus);
		buffer.writeBoolean(_pumpkin);
		buffer.writeBoolean(_melon);
		buffer.writeBoolean(_sugarcane);
		buffer.writeBoolean(_cocoa);
		buffer.writeBoolean(_mushroom);
		buffer.writeBoolean(_netherwart);
		buffer.writeBoolean(_replant);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		_dim = buffer.readInt();
		_x = buffer.readInt();
		_y = buffer.readInt();
		_z = buffer.readInt();
		_logOak = buffer.readBoolean();
		_logSpruce = buffer.readBoolean();
		_logBirch = buffer.readBoolean();
		_logJungle = buffer.readBoolean();
		_logAcacia = buffer.readBoolean();
		_logDark = buffer.readBoolean();
		_wheat = buffer.readBoolean();
		_carrot = buffer.readBoolean();
		_potato = buffer.readBoolean();
		_cactus = buffer.readBoolean();
		_pumpkin = buffer.readBoolean();
		_melon = buffer.readBoolean();
		_sugarcane = buffer.readBoolean();
		_cocoa = buffer.readBoolean();
		_mushroom = buffer.readBoolean();
		_netherwart = buffer.readBoolean();
		_replant = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		World world = DimensionManager.getWorld(_dim);
		BlockInstantHarvest block = (BlockInstantHarvest) BuildHelper.getBlock(world,_x, _y, _z);
		block.build(world, _x, _y, _z, _logOak, _logSpruce, _logBirch, _logJungle, _logAcacia, _logDark, _wheat, _carrot, _potato, _cactus, _pumpkin, _melon, _sugarcane, _cocoa, _mushroom, _netherwart, _replant);
		block.afterBuild(world, _x, _y, _z, player);
	}
}