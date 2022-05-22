package com.slymask3.instantblocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStatue extends TileEntity {
	public String username="";
	public boolean head;
	public boolean body;
	public boolean armLeft;
	public boolean armRight;
	public boolean legLeft;
	public boolean legRight;
	public boolean rgb;

	public TileEntityStatue() {
		super();
		this.username="";
		this.head=true;
		this.body=true;
		this.armLeft=true;
		this.armRight=true;
		this.legLeft=true;
		this.legRight=true;
		this.rgb=true;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setParts(boolean head, boolean body, boolean armLeft, boolean armRight, boolean legLeft, boolean legRight) {
		this.head = head;
		this.body = body;
		this.armLeft = armLeft;
		this.armRight = armRight;
		this.legLeft = legLeft;
		this.legRight = legRight;
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