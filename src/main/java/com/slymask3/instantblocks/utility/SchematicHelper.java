package com.slymask3.instantblocks.utility;

import java.io.File;
import java.io.FileInputStream;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class SchematicHelper {

    public static void loadSchematic(World world, int x, int y, int z, File file, boolean center) {
        try {
        	FileInputStream fis = new FileInputStream(file);
            NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(fis);
 
            short width = nbtdata.getShort("Width");
            short height = nbtdata.getShort("Height");
            short length = nbtdata.getShort("Length");
 
            byte[] blocks = nbtdata.getByteArray("Blocks");
            byte[] data = nbtdata.getByteArray("Data");
 
            NBTTagList entities = nbtdata.getTagList("Entities", 0);
            NBTTagList tileentities = nbtdata.getTagList("TileEntities", 0);
            
            fis.close();
            
//            System.out.println("Width: "+width);
//            System.out.println("Height: "+height);
//            System.out.println("Length: "+length);
//            System.out.println("Entities: "+entities);
//            System.out.println("Tile Entities: "+tileentities);
//            
//            int max = width+height*length+1;
//            
//            for(int i=0;i<max;i++) {
//                System.out.println("Block "+i+" - ID: "+blocks[i]+", Meta: "+data[i]);
//            }
            
            buildSchematic(world, x, y, z, width, height, length, blocks, data, entities, tileentities, center);
            
        } catch (Exception e) {
        	LogHelper.info("Couldn't load schematic.");
        }
    }
    
    public static void buildSchematic(World world, int X, int Y, int Z, short width, short height, short length, byte[] block, byte meta[], NBTTagList entities, NBTTagList tileentities, boolean center) {
    	//int max = width+height*length+1;
    	
//    	for(int x=0;x<xMax;x++) {
//    		for(int z=0;z<zMax;z++) {
//    			for(int y=0;y<yMax;y++) {
//    	        	world.setBlock(X+x, Y+y, Z+z, Block.getBlockById(block[x*y*z]), meta[x*y*z], 2);
//    	        }
//            }
//        }
    	
//		int x = X;
//    	int y = Y;
//    	int z = Z;
//		for (int yTimes = 0; yTimes < yMax; yTimes++) {
//			for (int zTimes = 0; zTimes < zMax; zTimes++) {
//				for (int xTimes = 0; xTimes < xMax; xTimes++) {
//					world.setBlock(x, y, z, Block.getBlockById(block[(x-X)*(y-Y)*(z*Z)]), meta[(x-X)*(y-Y)*(z*Z)], 2);
//					z++;
//				}
//				z = Z;
//				x++;
//			}
//			x = X;
//			y++;
//		}
    	
		for (int x = 0; x < width; ++x) {
	        for (int y = 0; y < height; ++y) {
	            for (int z = 0; z < length; ++z) {
	                int index = y * width * length + z * width + x;
	                
	                if(center) {
	                	world.setBlock(x+X-(width/2), y+Y, z+Z-(length/2), Block.getBlockById(block[index] & 0xFF), meta[index], 2);
	                } else {
	                	world.setBlock(x+X, y+Y, z+Z, Block.getBlockById(block[index] & 0xFF), meta[index], 2);
	                }
	            }
	        }
		}
		
    	//LogHelper.info("done schematic");
    }
}
