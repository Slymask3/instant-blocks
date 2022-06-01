package com.slymask3.instantblocks.util;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;
import java.io.FileInputStream;

public class SchematicHelper {
	public static Schematic readSchematic(String schematicName) {
		try {
			File file = new File("schematics/"+schematicName+".schematic");
			FileInputStream fis = new FileInputStream(file);
			NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(fis);
			short width = nbtdata.getShort("Width");
			short height = nbtdata.getShort("Height");
			short length = nbtdata.getShort("Length");
			byte[] blocks = nbtdata.getByteArray("Blocks");
			byte[] data = nbtdata.getByteArray("Data");
			fis.close();
			return new Schematic(width,height,length,blocks,data);
		} catch(Exception e) {
			LogHelper.info("Couldn't read schematic.");
		}
		return null;
	}

	public static void createSchematicsDir() {
		File dir = new File("schematics");
		if(!dir.exists()) {
			try{
				dir.mkdir();
				LogHelper.info("created schematics directory");
			} catch(SecurityException se){
				LogHelper.error("failed to create schematics directory: " + se.getMessage());
			}
		}
	}

	public static class Schematic {
		public short width;
		public short height;
		public short length;
		public byte[] blocks, data;
		public Schematic(short width, short height, short length, byte[] blocks, byte[] data) {
			this.width = width;
			this.height = height;
			this.length = length;
			this.blocks = blocks;
			this.data = data;
		}
	}
}
