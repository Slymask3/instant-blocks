package com.slymask3.instantblocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTree extends TileEntity {
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