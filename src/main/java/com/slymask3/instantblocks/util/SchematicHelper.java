package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.reference.Reference;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.state.BlockState;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SchematicHelper {
	public static Schematic readSchematic(String schematicName) {
		try {
			File file = new File(Reference.SCHEMATICS_DIR + "/" + schematicName);
			FileInputStream fis = new FileInputStream(file);
			CompoundTag nbtdata = NbtIo.readCompressed(fis);
			short width = nbtdata.getShort("Width");
			short height = nbtdata.getShort("Height");
			short length = nbtdata.getShort("Length");
			byte[] blocks = nbtdata.getByteArray("BlockData");

			HashMap<Integer,BlockState> map = new HashMap<>();
			CompoundTag palette = nbtdata.getCompound("Palette");
			for(String blockStateString : palette.getAllKeys()) {
				int key = palette.getInt(blockStateString);
				BlockState state = readBlockState(blockStateString);
				map.put(key,state);
			}

			fis.close();
			return new Schematic(width,height,length,blocks,map);
		} catch(Exception e) {
			InstantBlocks.LOGGER.info("Couldn't read schematic.");
		}
		return null;
	}

	private static BlockState readBlockState(String string) {
		CompoundTag tag = new CompoundTag();

		String[] split = string.split("\\[",2);

		tag.putString("Name",split[0]);

		if(split.length == 2) {
			CompoundTag propertiesTag = new CompoundTag();
			String[] properties = split[1].replace("]","").split(",");
			for(String property : properties) {
				String[] values = property.split("=");
				if(values.length == 2) {
					propertiesTag.putString(values[0],values[1]);
				}
			}
			tag.put("Properties",propertiesTag);
		}

		return NbtUtils.readBlockState(tag);
	}



	public static void createSchematicsDir() {
		File dir = new File(Reference.SCHEMATICS_DIR);
		if(!dir.exists()) {
			try {
				dir.mkdir();
				InstantBlocks.LOGGER.info("created schematics directory");
			} catch(SecurityException se) {
				InstantBlocks.LOGGER.error("failed to create schematics directory: " + se.getMessage());
			}
		}
	}

	public static ArrayList<String> getSchematics() {
		ArrayList<String> schematics = new ArrayList<>();
		File dir = new File(Reference.SCHEMATICS_DIR + "/");
		File[] files = dir.listFiles(file -> file.isFile() && (file.getName().toLowerCase().endsWith(".schematic") || file.getName().toLowerCase().endsWith(".schem")));
		if(files != null) {
			for(File file : files) {
				schematics.add(file.getName());
			}
		}
		return schematics;
	}

	public static class Schematic {
		public short width;
		public short height;
		public short length;
		public byte[] blocks;
		private final HashMap<Integer,BlockState> map;
		public Schematic(short width, short height, short length, byte[] blocks, HashMap<Integer,BlockState> map) {
			this.width = width;
			this.height = height;
			this.length = length;
			this.blocks = blocks;
			this.map = map;
		}
		public BlockState getBlockState(int index) {
			return this.map.get((int)this.blocks[index]);
		}
	}
}