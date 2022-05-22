package com.slymask3.instantblocks.util;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.io.File;
import java.io.FileInputStream;

public class SchematicHelper {

    public static void loadSchematic(World world, int x, int y, int z, File file, boolean center, boolean ignoreAir) {
        try {
        	FileInputStream fis = new FileInputStream(file);
            NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(fis);
 
            short width = nbtdata.getShort("Width");
            short height = nbtdata.getShort("Height");
            short length = nbtdata.getShort("Length");
 
            byte[] blocks = nbtdata.getByteArray("Blocks");
            byte[] data = nbtdata.getByteArray("Data");
            
            fis.close();
            
            buildSchematic(world, x, y, z, width, height, length, blocks, data, center, ignoreAir);
            
        } catch (Exception e) {
        	LogHelper.info("Couldn't load schematic.");
        }
    }
    
    public static void buildSchematic(World world, int X, int Y, int Z, short width, short height, short length, byte[] block, byte meta[], boolean center, boolean ignoreAir) {
    	for (int x = 0; x < width; ++x) {
	        for (int y = 0; y < height; ++y) {
	            for (int z = 0; z < length; ++z) {
	                int index = y * width * length + z * width + x;
	                if((block[index] & 0xFF) != 0 && ignoreAir) {
	                	if(center) {
	                		world.setBlock(x+X-(width/2), y+Y, z+Z-(length/2), Block.getBlockById(block[index] & 0xFF), meta[index], 2);
	                	} else {
	                		world.setBlock(x+X, y+Y, z+Z, Block.getBlockById(block[index] & 0xFF), meta[index], 2);
	                	}
	                } else if(ignoreAir) {
	                	//Ignore Air
	                } else {
	                	if(center) {
	                		world.setBlock(x+X-(width/2), y+Y, z+Z-(length/2), Block.getBlockById(block[index] & 0xFF), meta[index], 2);
	                	} else {
	                		world.setBlock(x+X, y+Y, z+Z, Block.getBlockById(block[index] & 0xFF), meta[index], 2);
	                	}
	                }
	            }
	        }
		}
    }

	public static void createSchematicsDir() {
		File dir = new File("schematics");
		if (!dir.exists()) {
			try{
				dir.mkdir();
				LogHelper.info("created schematics directory");
			} catch(SecurityException se){
				LogHelper.error("failed to create schematics directory: " + se.getMessage());
			}
		}
	}
}
