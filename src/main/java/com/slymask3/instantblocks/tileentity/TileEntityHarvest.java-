package com.slymask3.instantblocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityHarvest extends TileEntityInstant {
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
}