package com.slymask3.instantblocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntitySchematic extends TileEntityInstant {
	public String schematic;
	public boolean center;

	public TileEntitySchematic() {
		super();
		this.schematic = "";
		this.center = false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		schematic = nbt.getString("Schematic");
		center = nbt.getBoolean("Center");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("Schematic", schematic);
		nbt.setBoolean("Center", center);
	}
}