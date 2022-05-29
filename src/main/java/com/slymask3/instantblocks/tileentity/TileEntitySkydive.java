package com.slymask3.instantblocks.tileentity;

import com.slymask3.instantblocks.util.Colors;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySkydive extends TileEntity {
	public String[] color = new String[11];
	public int[] colorCode = new int[11];

	public TileEntitySkydive() {
		super();
		for(int i=0; i<color.length; i++) {
			String color = Colors.indexRainbowToString(i);
			this.color[i] = color;
			this.colorCode[i] = Colors.textToColor(color).getRGB();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		for(int i=0; i<color.length; i++) {
			this.color[i] = nbt.getString("Color" + i);
			this.colorCode[i] = nbt.getInteger("ColorCode" + i);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		for(int i=0; i<color.length; i++) {
			nbt.setString("Color" + i, color[i]);
			nbt.setInteger("ColorCode" + i, colorCode[i]);
		}
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
	
	public void setColorCode(int i, String input) {
		this.colorCode[i] = Colors.textToColor(input).getRGB();
	}
	
	public void setColor(int i, String input) {
		this.color[i] = input;
	}
}