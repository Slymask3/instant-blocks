package com.slymask3.instantblocks.network.packet.server;

import com.slymask3.instantblocks.network.PacketHelper;
import com.slymask3.instantblocks.network.packet.AbstractPacket;
import com.slymask3.instantblocks.network.packet.InstantPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class HarvestPacket extends InstantPacket {
	public final boolean _logOak;
	public final boolean _logSpruce;
	public final boolean _logBirch;
	public final boolean _logJungle;
	public final boolean _logAcacia;
	public final boolean _logDark;
	public final boolean _wheat;
	public final boolean _carrot;
	public final boolean _potato;
	public final boolean _cactus;
	public final boolean _pumpkin;
	public final boolean _melon;
	public final boolean _sugarcane;
	public final boolean _cocoa;
	public final boolean _mushroom;
	public final boolean _netherwart;
	public final boolean _replant;

	public HarvestPacket(boolean activate, BlockPos pos, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
		super(PacketHelper.PacketID.HARVEST,activate,pos);
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

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		buffer = super.write(packet,buffer);
		HarvestPacket message = (HarvestPacket)packet;
		buffer.writeBoolean(message._logOak);
		buffer.writeBoolean(message._logSpruce);
		buffer.writeBoolean(message._logBirch);
		buffer.writeBoolean(message._logJungle);
		buffer.writeBoolean(message._logAcacia);
		buffer.writeBoolean(message._logDark);
		buffer.writeBoolean(message._wheat);
		buffer.writeBoolean(message._carrot);
		buffer.writeBoolean(message._potato);
		buffer.writeBoolean(message._cactus);
		buffer.writeBoolean(message._pumpkin);
		buffer.writeBoolean(message._melon);
		buffer.writeBoolean(message._sugarcane);
		buffer.writeBoolean(message._cocoa);
		buffer.writeBoolean(message._mushroom);
		buffer.writeBoolean(message._netherwart);
		buffer.writeBoolean(message._replant);
		return buffer;
	}

	public static HarvestPacket decode(FriendlyByteBuf buffer) {
		boolean activate = buffer.readBoolean();
		BlockPos pos = buffer.readBlockPos();
		boolean logOak = buffer.readBoolean();
		boolean logSpruce = buffer.readBoolean();
		boolean logBirch = buffer.readBoolean();
		boolean logJungle = buffer.readBoolean();
		boolean logAcacia = buffer.readBoolean();
		boolean logDark = buffer.readBoolean();
		boolean wheat = buffer.readBoolean();
		boolean carrot = buffer.readBoolean();
		boolean potato = buffer.readBoolean();
		boolean cactus = buffer.readBoolean();
		boolean pumpkin = buffer.readBoolean();
		boolean melon = buffer.readBoolean();
		boolean sugarcane = buffer.readBoolean();
		boolean cocoa = buffer.readBoolean();
		boolean mushroom = buffer.readBoolean();
		boolean netherwart = buffer.readBoolean();
		boolean replant = buffer.readBoolean();
		return new HarvestPacket(activate,pos,logOak,logSpruce,logBirch,logJungle,logAcacia,logDark,wheat,carrot,potato,cactus,pumpkin,melon,sugarcane,cocoa,mushroom,netherwart,replant);
	}
}