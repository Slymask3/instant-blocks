package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.block.instant.BlockInstantHouseWood;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.awt.image.BufferedImage;

public class BuildHelper {
	/***************
	 * @INFO	   *
	 * x+# = EAST  *
	 * x-# = WEST  *
	 * z+# = SOUTH *
	 * z-# = NORTH *
	 * 			   *
	 * y+# = UP    *
	 * y-# = DOWN  *
	 ***************/
	
	public static void setBlock(World world, int x, int y, int z, Block block) {
		setBlock(world, x, y, z, block, 0, 2);
	}
	
	public static void setBlock(World world, int x, int y, int z, Block block, int meta, int flag) {
		if(getBlock(world,x, y, z) != Blocks.bedrock) {
			world.setBlock(x, y, z, block, meta, flag);
		}
	}

	public static void setColorBlock(World world, int x, int y, int z, int color) {
		setBlock(world, x, y, z, ModBlocks.color);
		try {
			((TileEntityColor) world.getTileEntity(x, y, z)).color = color;
		} catch(Exception e) {LogHelper.info(e);}
		world.markBlockForUpdate(x, y, z);
	}

	public static Block getBlock(World world, int x, int y, int z) {
		return world.getBlock(x,y,z);
	}

	public static void build(World world, int x, int y, int z, Block block, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		build(world, x, y, z, block, 0, 2, xTimesTotal, yTimesTotal, zTimesTotal);
	}
	
	public static void build(World world, int x, int y, int z, Block block, int meta, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		build(world, x, y, z, block, meta, 2, xTimesTotal, yTimesTotal, zTimesTotal);
	}
	
	public static void build(World world, int x, int y, int z, Block block, int meta, int flag, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		int z2 = z;
		int x2 = x;
		for(int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for(int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for(int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					setBlock(world, x2, y, z2, block, meta, flag);
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y++;
		}
	}
	
	public static void ifNoBlockThenStop(World world, int x, int y, int z, Block block, EntityPlayer player, String blockName) {
		if(getBlock(world,x, y, z) != block) {
			IBHelper.msg(player, Strings.ERROR_MISSING.replace("%block%",blockName).replace("%x%",String.valueOf(x)).replace("%y%",String.valueOf(y)).replace("%z%",String.valueOf(z)), Colors.c);
			BlockInstantHouseWood.notThere = true;
		}
	}
	
    public static void setBlockDirectional(World world, int x, int y, int z, Block block, int metaDirection, int forward, int back, int left, int right) {
    	setBlockDirectional(world, x, y, z, block, metaDirection, forward, back, left, right, 0, 1);
    }
    
    public static void setBlockDirectional(World world, int x, int y, int z, Block block, int metaDirection, int forward, int back, int left, int right, int meta, int flag) {
    	if(metaDirection==0) {
    		setBlock(world, x-left+right, y, z-forward+back, block, meta, flag);
		} else if(metaDirection==1) {
			setBlock(world, x+forward-back, y, z+left-right, block, meta, flag);
		} else if(metaDirection==2) {
			setBlock(world, x+left-right, y, z+forward-back, block, meta, flag);
		} else if(metaDirection==3) {
			setBlock(world, x-forward+back, y, z-left+right, block, meta, flag);
		}
    }
    
    public static void setColorBlockComplex(World world, int x, int y, int z, BufferedImage img, int imgx, int imgy, int metaDirection, int forwardBack, int leftRight, boolean rgb) {
    	if(rgb) {
    		int forward;
    		int back;
    		int left;
    		int right;
    		
    		if(IBHelper.isPositive(forwardBack)) {
    			back = forwardBack;
    			forward = 0;
    		} else {
    			back = 0;
    			forward = IBHelper.toPositive(forwardBack);
    		}
    		
    		if(IBHelper.isPositive(leftRight)) {
    			right = 0;
    			left = leftRight;
    		} else {
    			right = IBHelper.toPositive(leftRight);
    			left = 0;
    		}
    		
    		if(metaDirection==0) {
        		setBlock(world, x+left-right, y, z-forward+back, ModBlocks.color);
        		((TileEntityColor) world.getTileEntity(x+left-right, y, z-forward+back)).color = img.getRGB(imgx, imgy);
        		//((TileEntityColor) world.getTileEntity(x+left-right, y, z-forward+back)).color = 0xFF0000;
    			world.markBlockForUpdate(x+left-right, y, z-forward+back);
    		} else if(metaDirection==1) {
    			setBlock(world, x+forward-back, y, z+left-right, ModBlocks.color);
    			((TileEntityColor) world.getTileEntity(x+forward-back, y, z+left-right)).color = img.getRGB(imgx, imgy);
    			//((TileEntityColor) world.getTileEntity(x+forward-back, y, z+left-right)).color = 0x00FF00;
    			world.markBlockForUpdate(x+forward-back, y, z+left-right);
    		} else if(metaDirection==2) {
    			setBlock(world, x-left+right, y, z+forward-back, ModBlocks.color);
    			((TileEntityColor) world.getTileEntity(x-left+right, y, z+forward-back)).color = img.getRGB(imgx, imgy);
    			//((TileEntityColor) world.getTileEntity(x-left+right, y, z+forward-back)).color = 0x0000FF;
    			world.markBlockForUpdate(x-left+right, y, z+forward-back);
    		} else if(metaDirection==3) {
    			setBlock(world, x-forward+back, y, z-left+right, ModBlocks.color);
    			((TileEntityColor) world.getTileEntity(x-forward+back, y, z-left+right)).color = img.getRGB(imgx, imgy);
    			//((TileEntityColor) world.getTileEntity(x-forward+back, y, z-left+right)).color = 0xFFFF00;
    			world.markBlockForUpdate(x-forward+back, y, z-left+right);
    		}
		} else {
			int forward;
    		int back;
    		int left;
    		int right;
    		
    		if(IBHelper.isPositive(forwardBack)) {
    			back = forwardBack;
    			forward = 0;
    		} else {
    			back = 0;
    			forward = IBHelper.toPositive(forwardBack);
    		}
    		
    		if(IBHelper.isPositive(leftRight)) {
    			right = 0;
    			left = leftRight;
    		} else {
    			right = IBHelper.toPositive(leftRight);
    			left = 0;
    		}
		
    		if(metaDirection==0) {
        		setBlock(world, x+left-right, y, z-forward+back, Blocks.wool, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 2);
    		} else if(metaDirection==1) {
    			setBlock(world, x+forward-back, y, z+left-right, Blocks.wool, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 2);
    		} else if(metaDirection==2) {
    			setBlock(world, x-left+right, y, z+forward-back, Blocks.wool, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 2);
    		} else if(metaDirection==3) {
    			setBlock(world, x-forward+back, y, z-left+right, Blocks.wool, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 2);
    		}
    		
		}
    }
}