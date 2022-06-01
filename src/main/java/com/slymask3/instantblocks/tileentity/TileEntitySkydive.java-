package com.slymask3.instantblocks.tileentity;

import com.slymask3.instantblocks.util.Colors;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntitySkydive extends TileEntityInstant {
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
	
	public void setColorCode(int i, String input) {
		this.colorCode[i] = Colors.textToColor(input).getRGB();
	}
	
	public void setColor(int i, String input) {
		this.color[i] = input;
	}
}