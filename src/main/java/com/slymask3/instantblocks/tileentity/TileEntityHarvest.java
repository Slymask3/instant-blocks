package com.slymask3.instantblocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHarvest extends TileEntity {
	boolean logOak, logSpruce, logBirch, logJungle, logAcacia, logDark;
	boolean wheat, carrot, potato;
	boolean cactus, pumpkin, melon, sugarcane;
	boolean cocoa, mushroom, netherwart;
	boolean replant;

	public TileEntityHarvest() {
		super();
		this.logOak=true;
		this.logSpruce=true;
		this.logBirch=true;
		this.logJungle=true;
		this.logAcacia=true;
		this.logDark=true;
		this.wheat=true;
		this.carrot=true;
		this.potato=true;
		this.cactus=true;
		this.pumpkin=true;
		this.melon=true;
		this.sugarcane=true;
		this.cocoa=true;
		this.mushroom=true;
		this.netherwart=true;
		this.replant=true;
	}
	
	public void setLogs(boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark) {
		this.logOak=logOak;
		this.logSpruce=logSpruce;
		this.logBirch=logBirch;
		this.logJungle=logJungle;
		this.logAcacia=logAcacia;
		this.logDark=logDark;
	}
	
	public void setOther(boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart) {
		this.wheat=wheat;
		this.carrot=carrot;
		this.potato=potato;
		this.cactus=cactus;
		this.pumpkin=pumpkin;
		this.melon=melon;
		this.sugarcane=sugarcane;
		this.cocoa=cocoa;
		this.mushroom=mushroom;
		this.netherwart=netherwart;
	}
	
	public void setReplant(boolean replant) {
		this.replant=replant;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		logOak = nbt.getBoolean("LogOak");
		logSpruce = nbt.getBoolean("LogSpruce");
		logBirch = nbt.getBoolean("LogBirch");
		logJungle = nbt.getBoolean("LogJungle");
		logAcacia = nbt.getBoolean("LogAcacia");
		logDark = nbt.getBoolean("LogDark");
		wheat = nbt.getBoolean("Wheat");
		carrot = nbt.getBoolean("Carrot");
		potato = nbt.getBoolean("Potato");
		cactus = nbt.getBoolean("Cactus");
		pumpkin = nbt.getBoolean("Pumpkin");
		melon = nbt.getBoolean("Melon");
		sugarcane = nbt.getBoolean("Sugarcane");
		cocoa = nbt.getBoolean("Cocoa");
		mushroom = nbt.getBoolean("Mushroom");
		netherwart = nbt.getBoolean("Netherwart");
		replant = nbt.getBoolean("Replant");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("LogOak", logOak);
		nbt.setBoolean("LogSpruce", logSpruce);
		nbt.setBoolean("LogBirch", logBirch);
		nbt.setBoolean("LogJungle", logJungle);
		nbt.setBoolean("LogAcacia", logAcacia);
		nbt.setBoolean("LogDark", logDark);
		nbt.setBoolean("Wheat", wheat);
		nbt.setBoolean("Carrot", carrot);
		nbt.setBoolean("Potato", potato);
		nbt.setBoolean("Cactus", cactus);
		nbt.setBoolean("Pumpkin", pumpkin);
		nbt.setBoolean("Melon", melon);
		nbt.setBoolean("Sugarcane", sugarcane);
		nbt.setBoolean("Cocoa", cocoa);
		nbt.setBoolean("Mushroom", mushroom);
		nbt.setBoolean("Netherwart", netherwart);
		nbt.setBoolean("Replant", replant);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
    }
}