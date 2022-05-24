package com.slymask3.instantblocks.tileentity;

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
		this.color[0] = "red";
		this.color[1] = "orange";
		this.color[2] = "yellow";
		this.color[3] = "lime";
		this.color[4] = "green";
		this.color[5] = "cyan";
		this.color[6] = "light blue";
		this.color[7] = "blue";
		this.color[8] = "purple";
		this.color[9] = "magenta";
		this.color[10] = "pink";
		this.colorCode[0] = getColor(150, 52, 48);
		this.colorCode[1] =	getColor(219, 125, 62);
		this.colorCode[2] =	getColor(177, 166, 39);
		this.colorCode[3] =	getColor(65, 174, 56);
		this.colorCode[4] = getColor(53, 70, 27);
		this.colorCode[5] = getColor(46, 110, 137);
		this.colorCode[6] = getColor(107, 138, 201);
		this.colorCode[7] = getColor(46, 56, 141);
		this.colorCode[8] = getColor(126, 61, 181);
		this.colorCode[9] = getColor(179, 80, 188);
		this.colorCode[10] = getColor(208, 132, 153);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.color[0] = nbt.getString("Color0");
		this.color[1] = nbt.getString("Color1");
		this.color[2] = nbt.getString("Color2");
		this.color[3] = nbt.getString("Color3");
		this.color[4] = nbt.getString("Color4");
		this.color[5] = nbt.getString("Color5");
		this.color[6] = nbt.getString("Color6");
		this.color[7] = nbt.getString("Color7");
		this.color[8] = nbt.getString("Color8");
		this.color[9] = nbt.getString("Color9");
		this.color[10] = nbt.getString("Color10");
		this.colorCode[0] = nbt.getInteger("ColorCode0");
		this.colorCode[1] =	nbt.getInteger("ColorCode1");
		this.colorCode[2] =	nbt.getInteger("ColorCode2");
		this.colorCode[3] =	nbt.getInteger("ColorCode3");
		this.colorCode[4] = nbt.getInteger("ColorCode4");
		this.colorCode[5] = nbt.getInteger("ColorCode5");
		this.colorCode[6] = nbt.getInteger("ColorCode6");
		this.colorCode[7] = nbt.getInteger("ColorCode7");
		this.colorCode[8] = nbt.getInteger("ColorCode8");
		this.colorCode[9] = nbt.getInteger("ColorCode9");
		this.colorCode[10] = nbt.getInteger("ColorCode10");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("Color0", color[0]);
		nbt.setString("Color1", color[1]);
		nbt.setString("Color2", color[2]);
		nbt.setString("Color3", color[3]);
		nbt.setString("Color4", color[4]);
		nbt.setString("Color5", color[5]);
		nbt.setString("Color6", color[6]);
		nbt.setString("Color7", color[7]);
		nbt.setString("Color8", color[8]);
		nbt.setString("Color9", color[9]);
		nbt.setString("Color10", color[10]);
		nbt.setInteger("ColorCode0", colorCode[0]);
		nbt.setInteger("ColorCode1", colorCode[1]);
		nbt.setInteger("ColorCode2", colorCode[2]);
		nbt.setInteger("ColorCode3", colorCode[3]);
		nbt.setInteger("ColorCode4", colorCode[4]);
		nbt.setInteger("ColorCode5", colorCode[5]);
		nbt.setInteger("ColorCode6", colorCode[6]);
		nbt.setInteger("ColorCode7", colorCode[7]);
		nbt.setInteger("ColorCode8", colorCode[8]);
		nbt.setInteger("ColorCode9", colorCode[9]);
		nbt.setInteger("ColorCode10", colorCode[10]);
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
	
	public int getColor(int r, int g, int b) {
		return (r * 65536) + (g * 256) + b;
	}
	
	public void setColorCode(int i, String input) {
		int color = 16777215;
		
		if(input.equalsIgnoreCase("red")) {
			color = getColor(150, 52, 48);
		} else if(input.equalsIgnoreCase("orange")) {
			color = getColor(219, 125, 62);
		} else if(input.equalsIgnoreCase("yellow")) {
			color = getColor(177, 166, 39);
		} else if(input.equalsIgnoreCase("lime")) {
			color = getColor(65, 174, 56);
		} else if(input.equalsIgnoreCase("green")) {
			color = getColor(53, 70, 27);
		} else if(input.equalsIgnoreCase("cyan")) {
			color = getColor(46, 110, 137);
		} else if(input.equalsIgnoreCase("light blue") || input.equalsIgnoreCase("lightblue")) {
			color = getColor(107, 138, 201);
		} else if(input.equalsIgnoreCase("blue")) {
			color = getColor(46, 56, 141);
		} else if(input.equalsIgnoreCase("purple")) {
			color = getColor(126, 61, 181);
		} else if(input.equalsIgnoreCase("magenta")) {
			color = getColor(179, 80, 188);
		} else if(input.equalsIgnoreCase("pink")) {
			color = getColor(208, 132, 153);
		} else if(input.equalsIgnoreCase("white")) {
			color = getColor(221, 221, 221);
		} else if(input.equalsIgnoreCase("gray") || input.equalsIgnoreCase("grey")) {
			color = getColor(64, 64, 64);
		} else if(input.equalsIgnoreCase("light gray") || input.equalsIgnoreCase("lightgray") || input.equalsIgnoreCase("light grey") || input.equalsIgnoreCase("lightgrey")) {
			color = getColor(154, 161, 161);
		} else if(input.equalsIgnoreCase("brown")) {
			color = getColor(79, 50, 31);
		} else if(input.equalsIgnoreCase("black")) {
			color = getColor(25, 22, 22);
		} else {
			try {
				color = Integer.parseInt(input, 16);
			} catch(Exception e) {}
		}
		
		this.colorCode[i] = color;
	}
	
	public void setColor(int i, String input) {
		this.color[i] = input;
	}
}