package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.block.instant.BlockInstantHouseWood;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
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
			setBlock(world, x+forward-back, y, z-left+right, block, meta, flag);
		} else if(metaDirection==2) {
			setBlock(world, x+left-right, y, z+forward-back, block, meta, flag);
		} else if(metaDirection==3) {
			setBlock(world, x-forward+back, y, z+left-right, block, meta, flag);
		}
    }

	public static void buildDirectional(World world, int x, int y, int z, Block block, int metaDirection, int forward, int back, int left, int right, int forwardX, int backX, int leftX, int rightX, int upX, int downX) {
		buildDirectional(world, x, y, z, block, metaDirection, forward, back, left, right, forwardX, backX, leftX, rightX, upX, downX, 0, 1);
	}

	public static void buildDirectional(World world, int x, int y, int z, Block block, int metaDirection, int forward, int back, int left, int right, int forwardX, int backX, int leftX, int rightX, int upX, int downX, int meta, int flag) {
		int x1 = x, z1 = z, x2 = x, z2 = z;
		int y1 = y;
		int y2 = y1+upX-downX;
		if(metaDirection == 0) {
			x1 = x-left+right;
			z1 = z-forward+back;
			x2 = x1-leftX+rightX;
			z2 = z1-forwardX+backX;
		} else if(metaDirection == 1) {
			x1 = x+forward-back;
			z1 = z-left+right;
			x2 = x1+forwardX-backX;
			z2 = z1-leftX+rightX;
		} else if(metaDirection == 2) {
			x1 = x+left-right;
			z1 = z+forward-back;
			x2 = x1+leftX-rightX;
			z2 = z1+forwardX-backX;
		} else if(metaDirection == 3) {
			x1 = x-forward+back;
			z1 = z+left-right;
			x2 = x1-forwardX+backX;
			z2 = z1+leftX-rightX;
		}

		int xDif = IBHelper.toPositive(x1 - x2);
		int yDif = IBHelper.toPositive(y1 - y2);
		int zDif = IBHelper.toPositive(z1 - z2);

		int x_cur = x1;
		int y_cur = y1;
		int z_cur = z1;

		int bx = x1;
		int bz = z1;

		for (int i=0; i<yDif+1; i++) {
			for (int j=0; j<zDif+1; j++) {
				for (int k=0; k<xDif+1; k++) {

					BuildHelper.setBlock(world,x_cur, y_cur, z_cur, block, meta, flag);

					if(IBHelper.isPositive(x1 - x2)) {
						x_cur--;
					} else {
						x_cur++;
					}
				}
				x_cur = bx;

				if(IBHelper.isPositive(z1 - z2)) {
					z_cur--;
				} else {
					z_cur++;
				}
			}
			z_cur = bz;

			if(IBHelper.isPositive(y1 - y2)) {
				y_cur--;
			} else {
				y_cur++;
			}
		}
	}
    
    public static void setColorBlockComplex(World world, int x, int y, int z, BufferedImage img, int imgx, int imgy, int metaDirection, int forwardBack, int leftRight, boolean rgb) {
		int forward;
		int back;
		int left;
		int right;
    	if(rgb) {
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
    			world.markBlockForUpdate(x+left-right, y, z-forward+back);
    		} else if(metaDirection==1) {
    			setBlock(world, x+forward-back, y, z+left-right, ModBlocks.color);
    			((TileEntityColor) world.getTileEntity(x+forward-back, y, z+left-right)).color = img.getRGB(imgx, imgy);
    			world.markBlockForUpdate(x+forward-back, y, z+left-right);
    		} else if(metaDirection==2) {
    			setBlock(world, x-left+right, y, z+forward-back, ModBlocks.color);
    			((TileEntityColor) world.getTileEntity(x-left+right, y, z+forward-back)).color = img.getRGB(imgx, imgy);
    			world.markBlockForUpdate(x-left+right, y, z+forward-back);
    		} else if(metaDirection==3) {
    			setBlock(world, x-forward+back, y, z-left+right, ModBlocks.color);
    			((TileEntityColor) world.getTileEntity(x-forward+back, y, z-left+right)).color = img.getRGB(imgx, imgy);
    			world.markBlockForUpdate(x-forward+back, y, z-left+right);
    		}
		} else {
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
        		setBlock(world, x+left-right, y, z-forward+back, Blocks.wool, Colors.getWoolColor(Colors.getColorAt(img, imgx, imgy)), 2);
    		} else if(metaDirection==1) {
    			setBlock(world, x+forward-back, y, z+left-right, Blocks.wool, Colors.getWoolColor(Colors.getColorAt(img, imgx, imgy)), 2);
    		} else if(metaDirection==2) {
    			setBlock(world, x-left+right, y, z+forward-back, Blocks.wool, Colors.getWoolColor(Colors.getColorAt(img, imgx, imgy)), 2);
    		} else if(metaDirection==3) {
    			setBlock(world, x-forward+back, y, z-left+right, Blocks.wool, Colors.getWoolColor(Colors.getColorAt(img, imgx, imgy)), 2);
    		}
		}
    }

	public static TileEntity getTileEntityDirectional(World world, int x, int y, int z, int metaDirection, int forward, int back, int left, int right) {
		if(metaDirection==0) {
			return world.getTileEntity(x-left+right, y, z-forward+back);
		} else if(metaDirection==1) {
			return world.getTileEntity(x+forward-back, y, z+left-right);
		} else if(metaDirection==2) {
			return world.getTileEntity(x+left-right, y, z+forward-back);
		} else if(metaDirection==3) {
			return world.getTileEntity(x-forward+back, y, z-left+right);
		}
		return null;
	}
}