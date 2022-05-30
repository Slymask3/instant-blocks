package com.slymask3.instantblocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityTree extends TileEntityInstant {
	public int type;
	public boolean fullLog;
	public boolean fullLeaves;

	public TileEntityTree() {
		super();
		this.type = 0;
		this.fullLog = false;
		this.fullLeaves = false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		type = nbt.getInteger("Type");
		fullLog = nbt.getBoolean("FullLog");
		fullLeaves = nbt.getBoolean("FullLeaves");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Type", type);
		nbt.setBoolean("FullLog", fullLog);
		nbt.setBoolean("FullLeaves", fullLeaves);
	}
}