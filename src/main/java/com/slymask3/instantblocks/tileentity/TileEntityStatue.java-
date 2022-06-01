package com.slymask3.instantblocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityStatue extends TileEntityInstant {
	public String username;
	public boolean head;
	public boolean body;
	public boolean armLeft;
	public boolean armRight;
	public boolean legLeft;
	public boolean legRight;
	public boolean rgb;

	public TileEntityStatue() {
		super();
		this.username = "";
		this.head = true;
		this.body = true;
		this.armLeft = true;
		this.armRight = true;
		this.legLeft = true;
		this.legRight = true;
		this.rgb = true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		username = nbt.getString("Username");
		head = nbt.getBoolean("Head");
		body = nbt.getBoolean("Body");
		armLeft = nbt.getBoolean("ArmLeft");
		armRight = nbt.getBoolean("ArmRight");
		legLeft = nbt.getBoolean("LegLeft");
		legRight = nbt.getBoolean("LegRight");
		rgb = nbt.getBoolean("RGB");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("Username", username);
		nbt.setBoolean("Head", head);
		nbt.setBoolean("Body", body);
		nbt.setBoolean("ArmLeft", armLeft);
		nbt.setBoolean("ArmRight", armRight);
		nbt.setBoolean("LegLeft", legLeft);
		nbt.setBoolean("LegRight", legRight);
		nbt.setBoolean("RGB", rgb);
	}
}