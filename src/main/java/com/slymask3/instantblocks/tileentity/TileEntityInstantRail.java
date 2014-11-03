package com.slymask3.instantblocks.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.Level;

import com.slymask3.instantblocks.InstantBlocks;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInstantRail extends TileEntity {
	/**
	 * Direction 0 = NORTH
	 * Direction 1 = EAST
	 * Direction 2 = SOUTH
	 * Direction 3 = WEST
	 */
	public int direction = 0;

	public TileEntityInstantRail() {
		super();
		direction = 0;
	}

//	public void setColor(final int red, final int green, final int blue) {
//		color = ((red & 0xFF) << 16) + ((green & 0xFF) << 8) + (blue & 0xFF);
//	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		direction = nbt.getInteger("Direction");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Direction", direction);
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
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
}