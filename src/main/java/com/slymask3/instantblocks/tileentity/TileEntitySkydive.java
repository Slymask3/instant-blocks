package com.slymask3.instantblocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySkydive extends TileEntity {
	public String[] color = {
			"red",
			"orange",
			"yellow",
			"lime",
			"green",
			"cyan",
			"light blue",
			"blue",
			"purple",
			"magenta",
			"pink",
			"white"
	};
	
	public int[] colorCode = {
			getColor(150, 52, 48),
			getColor(219, 125, 62),
			getColor(177, 166, 39),
			getColor(65, 174, 56),
			getColor(53, 70, 27),
			getColor(46, 110, 137),
			getColor(107, 138, 201),
			getColor(46, 56, 141),
			getColor(126, 61, 181),
			getColor(179, 80, 188),
			getColor(208, 132, 153)
	};

	public TileEntitySkydive() {
		super();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
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
	
	public int getAARRGGBB(int id) {
		switch(id) {
			case 0:
				return getColor(150, 52, 48);
			case 1:
				return getColor(219, 125, 62);
			case 2:
				return getColor(177, 166, 39);
			case 3:
				return getColor(65, 174, 56);
			case 4:
				return getColor(53, 70, 27);
			case 5:
				return getColor(46, 110, 137);
			case 6:
				return getColor(107, 138, 201);
			case 7:
				return getColor(46, 56, 141);
			case 8:
				return getColor(126, 61, 181);
			case 9:
				return getColor(179, 80, 188);
			case 10:
				return getColor(208, 132, 153);
			default:
				return getColor(255, 255, 255);
		}
	}
	
	public int getColor(int r, int g, int b) {
		return (r * 65536) + (g * 256) + b;
	}
	
	public void setColorCode(int i, String input) {
		int color = 16777215;
		
		if(input.equalsIgnoreCase("red")) {
			color = getAARRGGBB(0);
		} else if(input.equalsIgnoreCase("orange")) {
			color = getAARRGGBB(1);
		} else if(input.equalsIgnoreCase("yellow")) {
			color = getAARRGGBB(2);
		} else if(input.equalsIgnoreCase("lime")) {
			color = getAARRGGBB(3);
		} else if(input.equalsIgnoreCase("green")) {
			color = getAARRGGBB(4);
		} else if(input.equalsIgnoreCase("cyan")) {
			color = getAARRGGBB(5);
		} else if(input.equalsIgnoreCase("light blue") || input.equalsIgnoreCase("lightblue")) {
			color = getAARRGGBB(6);
		} else if(input.equalsIgnoreCase("blue")) {
			color = getAARRGGBB(7);
		} else if(input.equalsIgnoreCase("purple")) {
			color = getAARRGGBB(8);
		} else if(input.equalsIgnoreCase("magenta")) {
			color = getAARRGGBB(9);
		} else if(input.equalsIgnoreCase("pink")) {
			color = getAARRGGBB(10);
		} else {
			try {
				color = Integer.parseInt(input, 16);
			} catch (Exception e) {}
		}
		
		this.colorCode[i] = color;
	}
	
	public void setColor(int i, String input) {
		this.color[i] = input;
	}
}