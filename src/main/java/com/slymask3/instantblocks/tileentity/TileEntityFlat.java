package com.slymask3.instantblocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFlat extends TileEntity {
	public int up, down, forward, back, left, right;
	//public boolean harvest;

	public TileEntityFlat() {
		super();
		this.up = 0;
		this.down = 0;
		this.forward = 0;
		this.back = 0;
		this.left = 0;
		this.right = 0;
		//this.harvest = false;
	}
	
	public void set(int up, int down, int forward, int back, int left, int right) {
		this.up=up;
		this.down=down;
		this.forward=forward;
		this.back=back;
		this.left=left;
		this.right=right;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		up = nbt.getInteger("Up");
		down = nbt.getInteger("Down");
		forward = nbt.getInteger("Forward");
		back = nbt.getInteger("Back");
		left = nbt.getInteger("Left");
		right = nbt.getInteger("Right");
		//harvest = nbt.getBoolean("Harvest");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Up", up);
		nbt.setInteger("Down", down);
		nbt.setInteger("Forward", forward);
		nbt.setInteger("Back", back);
		nbt.setInteger("Left", left);
		nbt.setInteger("Right", right);
		//nbt.setBoolean("Harvest", harvest);
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