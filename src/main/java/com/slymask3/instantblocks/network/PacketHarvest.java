package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.block.instant.BlockInstantHarvest;
import com.slymask3.instantblocks.util.BuildHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketHarvest {
	int _x, _y, _z;
	boolean _logOak, _logSpruce, _logBirch, _logJungle, _logAcacia, _logDark;
	boolean _wheat, _carrot, _potato;
	boolean _cactus, _pumpkin, _melon, _sugarcane;
	boolean _cocoa, _mushroom, _netherwart;
	boolean _replant;

	public PacketHarvest(int x, int y, int z, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
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

	public static void encode(PacketHarvest message, FriendlyByteBuf buffer) {
		buffer.writeInt(message._x);
		buffer.writeInt(message._y);
		buffer.writeInt(message._z);
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
	}

	public static PacketHarvest decode(FriendlyByteBuf buffer) {
		int x = buffer.readInt();
		int y = buffer.readInt();
		int z = buffer.readInt();
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
		return new PacketHarvest(x,y,z,logOak,logSpruce,logBirch,logJungle,logAcacia,logDark,wheat,carrot,potato,cactus,pumpkin,melon,sugarcane,cocoa,mushroom,netherwart,replant);
	}

	public static class Handler {
		public static void handle(PacketHarvest message, Supplier<NetworkEvent.Context> context) {
			context.get().enqueueWork(() -> {
				Player player = context.get().getSender();
				Level world = player.getLevel();

				BlockInstantHarvest block = (BlockInstantHarvest) BuildHelper.getBlock(world,message._x, message._y, message._z);
				if(block.build(world, message._x, message._y, message._z, message._logOak, message._logSpruce, message._logBirch, message._logJungle, message._logAcacia, message._logDark, message._wheat, message._carrot, message._potato, message._cactus, message._pumpkin, message._melon, message._sugarcane, message._cocoa, message._mushroom, message._netherwart, message._replant)) {
					block.afterBuild(world, message._x, message._y, message._z, player);
				}
			});
			context.get().setPacketHandled(true);
		}
	}
}