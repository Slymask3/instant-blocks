package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.block.instant.BlockInstantHouseWood;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.tileentity.TileEntityColorLadder;
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
	
	public static void setBlockIfNoBedrock(World world, int x, int y, int z, Block block) {
		setBlockIfNoBedrock(world, x, y, z, block, 0, 2);
	}
	
	public static void setBlockIfNoBedrock(World world, int x, int y, int z, Block block, int meta, int flag) {
		if (world.getBlock(x, y, z) != Blocks.bedrock) {
			world.setBlock(x, y, z, block, meta, flag);
		}
	}
	
	public static void setMulti(World world, int x, int y, int z, int back, int front, int up, int down, int left, int right, Block block) {
		setMulti(world, x, y, z, back, front, up, down, left, right, block, 0, 2);
	}
	
	public static void setMulti(World world, int x, int y, int z, int back, int front, int up, int down, int left, int right, Block block, int meta, int flag) {
		if (world.getBlockMetadata(x, y, z) == 0) { //NORTH - GOOD
			setBlockIfNoBedrock(world, x+right-left, y+up-down, z-back+front, block, meta, flag);
		} else if (world.getBlockMetadata(x, y, z) == 1) { //EAST - GOOD
			setBlockIfNoBedrock(world, x+back-front, y+up-down, z+right-left, block, meta, flag);
		} else if (world.getBlockMetadata(x, y, z) == 2) { //SOUTH - GOOD
			setBlockIfNoBedrock(world, x-right+left, y+up-down, z+back-front, block, meta, flag);
		} else if (world.getBlockMetadata(x, y, z) == 3) { //WEST - GOOD
			setBlockIfNoBedrock(world, x-back+front, y+up-down, z-right+left, block, meta, flag);
		}
	}
	
	public static void setMulti2(World world, int x, int y, int z, int backFront, int upDown, int leftRight, Block block) {
		setMulti2(world, x, y, z, backFront, upDown, leftRight, block, 0, 2);
	}
	
	public static void setMulti2(World world, int x, int y, int z, int backFront, int upDown, int leftRight, Block block, int meta, int flag) {
		if (world.getBlockMetadata(x, y, z) == 0) { //NORTH
			setBlockIfNoBedrock(world, x-leftRight, y+upDown, z-backFront, block, meta, flag); //GOOD
		} else if (world.getBlockMetadata(x, y, z) == 1) { //EAST
			setBlockIfNoBedrock(world, x+backFront, y+upDown, z-leftRight, block, meta, flag); //GOOD
		} else if (world.getBlockMetadata(x, y, z) == 2) { //SOUTH
			setBlockIfNoBedrock(world, x+leftRight, y+upDown, z+backFront, block, meta, flag); //GOOD
		} else if (world.getBlockMetadata(x, y, z) == 3) { //WEST
			setBlockIfNoBedrock(world, x-backFront, y+upDown, z+leftRight, block, meta, flag); //GOOD
		}
	}
	
	public static void buildMulti2(World world, int x, int y, int z, int backFront, int upDown, int leftRight, Block block, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		buildMulti2(world, x, y, z, backFront, upDown, leftRight, block, xTimesTotal, yTimesTotal, zTimesTotal, 0, 2);
	}
	
	public static void buildMulti2(World world, int x, int y, int z, int backFront, int upDown, int leftRight, Block block, int meta, int flag, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		int z2 = z;
		int x2 = x;
		int y2 = y;
		for (int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for (int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for (int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					setMulti2(world, x, y, z, x2, y, z2, block, meta, flag);
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y2++;
		}
	}
	
	public static void buildMulti(World world, int x, int y, int z, int back, int front, int up, int down, int left, int right, Block block, int meta, int flag, int backT, int frontT, int upT, int downT, int leftT, int rightT) {
		int back2 = back;
		int front2 = front;
		int up2 = up;
		int down2 = down;
		int left2 = left;
		int right2 = right;
		for (int backTs = 0; backTs < backT; backTs++) {
			for (int frontTs = 0; frontTs < frontT; frontTs++) {
				for (int upTs = 0; upTs < upT; upTs++) {
					for (int downTs = 0; downTs < downT; downTs++) {
						for (int leftTs = 0; leftTs < leftT; leftTs++) {
							for (int rightTs = 0; rightTs < rightT; rightTs++) {
								setMulti(world, x, y, z, back2, front2, up2, down2, left2, right2, block, meta, flag);
								right2++;
							}
							right2 = right;
							left2++;
						}
						left2 = left;
						down2++;
					}
					down2 = down;
					up2++;
				}
				up2 = up;
				front2++;
			}
			front2 = front;
			back2++;
		}
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
		for (int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for (int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for (int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					setBlockIfNoBedrock(world, x2, y, z2, block, meta, flag);
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y++;
		}
	}
	
	public static void buildClean(World world, int x, int y, int z, Block block, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		buildClean(world, x, y, z, block, 0, 2, xTimesTotal, yTimesTotal, zTimesTotal);
	}
	
	public static void buildClean(World world, int x, int y, int z, Block block, int meta, int flag, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		int z2 = z;
		int x2 = x;
		for (int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for (int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for (int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					world.setBlock(x2, y, z2, block, meta, flag);
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y++;
		}
	}
	
	public static void buildColorBlock(World world, int x, int y, int z, int xTimesTotal, int yTimesTotal, int zTimesTotal, int c) {
		int z2 = z;
		int x2 = x;
		for (int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for (int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for (int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					world.setBlock(x2, y, z2, ModBlocks.color, 0, 2);
					try {
						((TileEntityColor) world.getTileEntity(x2, y, z2)).color = c;
					} catch(Exception e) {LogHelper.info(e);}
					world.markBlockForUpdate(x2, y, z2);
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y++;
		}
	}
	
	public static void buildColorLadder(World world, int x, int y, int z, int xTimesTotal, int yTimesTotal, int zTimesTotal, int meta, int c) {
		int z2 = z;
		int x2 = x;
		for (int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for (int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for (int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					world.setBlock(x2, y, z2, ModBlocks.colorLadder, meta, 2);
					try {
						((TileEntityColorLadder) world.getTileEntity(x2, y, z2)).color = c;
					} catch(Exception e) {LogHelper.info(e);}
					world.markBlockForUpdate(x2, y, z2);
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y++;
		}
	}
	
	public static void buildIfAir(World world, int x, int y, int z, Block block) {
		if (world.isAirBlock(x, y, z)) {
			setBlockIfNoBedrock(world, x, y, z, block);
		}
	}
	
	public static void ifNoBlockThenStop(World world, int x, int y, int z, Block block, EntityPlayer player, String blockName) {
		if (world.getBlock(x, y, z) != block) {
			IBHelper.msg(player, Strings.ERROR_MISSING.replace("%block%",blockName).replace("%x%",String.valueOf(x)).replace("%y%",String.valueOf(y)).replace("%z%",String.valueOf(z)), Colors.c);
			BlockInstantHouseWood.notThere = true;
		}
	}
	
	/**************************************** WATER/LAVA ************************************************/
	
	public static int counter = 0;
	public static int c2 = 0;
	public static int c5 = 0;
	
	public static boolean built = false;
	
	public static void checkLiquid5(World world, int x, int y, int z, Block block) {
		c2++;
		if (c2 < Config.MAX_LIQUID) {
			if (world.getBlock(x+1, y, z) == Blocks.air && world.getBlockMetadata(x+1, y, z) != 15 && c2 < Config.MAX_LIQUID/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				checkLiquid5a(world, x+1, y, z, block);
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && world.getBlockMetadata(x-1, y, z) != 15 && c2 < Config.MAX_LIQUID/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				checkLiquid5a(world, x-1, y, z, block);
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && world.getBlockMetadata(x, y, z+1) != 15 && c2 < Config.MAX_LIQUID/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				checkLiquid5a(world, x, y, z+1, block);
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && world.getBlockMetadata(x, y, z-1) != 15 && c2 < Config.MAX_LIQUID/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				checkLiquid5a(world, x, y, z-1, block);
			}
			if (world.getBlock(x, y-1, z) == Blocks.air && world.getBlockMetadata(x, y-1, z) != 15 && c2 < Config.MAX_LIQUID/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				checkLiquid5a(world, x, y-1, z, block);
			}
		}
	}
	
	public int[] checkLiquid5aC = new int[25000];
	
	public static void checkLiquid5a(World world, int x, int y, int z, Block block) {
			world.setBlockMetadataWithNotify(x, y, z, 15, 2);
			checkLiquid5(world, x, y, z, block);
	}
	
	public static void checkLiquid5Undo(World world, int x, int y, int z, Block block) {
		c5++;
		if (c5 < Config.MAX_LIQUID) {
			if (world.getBlock(x+1, y, z) == Blocks.air && world.getBlockMetadata(x+1, y, z) == 15 && c5 < Config.MAX_LIQUID) {
				checkLiquid5aUndo(world, x+1, y, z, block);
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && world.getBlockMetadata(x-1, y, z) == 15 && c5 < Config.MAX_LIQUID) {
				checkLiquid5aUndo(world, x-1, y, z, block);
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && world.getBlockMetadata(x, y, z+1) == 15 && c5 < Config.MAX_LIQUID) {
				checkLiquid5aUndo(world, x, y, z+1, block);
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && world.getBlockMetadata(x, y, z-1) == 15 && c5 < Config.MAX_LIQUID) {
				checkLiquid5aUndo(world, x, y, z-1, block);
			}
			if (world.getBlock(x, y-1, z) == Blocks.air && world.getBlockMetadata(x, y-1, z) == 15 && c5 < Config.MAX_LIQUID) {
				checkLiquid5aUndo(world, x, y-1, z, block);
			}
		}
	}
	
	public static void checkLiquid5aUndo(World world, int x, int y, int z, Block block) {
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		checkLiquid5Undo(world, x, y, z, block);
	}
	
	public static void buildLiquid4(World world, int x, int y, int z, Block block) {
		if (c2 < Config.MAX_LIQUID) {
			if (world.getBlock(x+1, y, z) == Blocks.air && c2 < Config.MAX_LIQUID) {
				buildLiquid4a(world, x+1, y, z, block);
				built = true;
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && c2 < Config.MAX_LIQUID) {
				buildLiquid4a(world, x-1, y, z, block);
				built = true;
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && c2 < Config.MAX_LIQUID) {
				buildLiquid4a(world, x, y, z+1, block);
				built = true;
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && c2 < Config.MAX_LIQUID) {
				buildLiquid4a(world, x, y, z-1, block);
				built = true;
			}
			if (world.getBlock(x, y-1, z) == Blocks.air && c2 < Config.MAX_LIQUID) {
				buildLiquid4a(world, x, y-1, z, block);
				built = true;
			}
		}
	}
	
	public static void buildLiquid4a(World world, int x, int y, int z, Block block) {
		setBlockIfNoBedrock(world, x, y, z, block, 0, 2);
		if (c2 < Config.MAX_LIQUID) {
			buildLiquid4(world, x, y, z, block);
		}
	}
	
	/**************************************** SIMPLE VERSION ************************************************/
	
	public static void checkLiquid5S(World world, int x, int y, int z, Block block) {
		c2++;
		if (c2 < Config.MAX_LIQUID) {
			if (world.getBlock(x+1, y, z) == Blocks.air && world.getBlockMetadata(x+1, y, z) != 15 && c2 < Config.MAX_LIQUID) {
				counter++;
				checkLiquid5aS(world, x+1, y, z, block);
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && world.getBlockMetadata(x-1, y, z) != 15 && c2 < Config.MAX_LIQUID) {
				counter++;
				checkLiquid5aS(world, x-1, y, z, block);
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && world.getBlockMetadata(x, y, z+1) != 15 && c2 < Config.MAX_LIQUID) {
				counter++;
				checkLiquid5aS(world, x, y, z+1, block);
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && world.getBlockMetadata(x, y, z-1) != 15 && c2 < Config.MAX_LIQUID) {
				counter++;
				checkLiquid5aS(world, x, y, z-1, block);
			}
		}
	}
	
	public static void checkLiquid5aS(World world, int x, int y, int z, Block block) {
		world.setBlockMetadataWithNotify(x, y, z, 15, 2);
		checkLiquid5S(world, x, y, z, block);
	}
	
	public static void checkLiquid5UndoS(World world, int x, int y, int z, Block block) {
		c5++;
		if (c5 < Config.MAX_LIQUID) {
			if (world.getBlock(x+1, y, z) == Blocks.air && world.getBlockMetadata(x+1, y, z) == 15 && c5 < Config.MAX_LIQUID) {
				checkLiquid5aUndoS(world, x+1, y, z, block);
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && world.getBlockMetadata(x-1, y, z) == 15 && c5 < Config.MAX_LIQUID) {
				checkLiquid5aUndoS(world, x-1, y, z, block);
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && world.getBlockMetadata(x, y, z+1) == 15 && c5 < Config.MAX_LIQUID) {
				checkLiquid5aUndoS(world, x, y, z+1, block);
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && world.getBlockMetadata(x, y, z-1) == 15 && c5 < Config.MAX_LIQUID) {
				checkLiquid5aUndoS(world, x, y, z-1, block);
			}
		}
	}
	
	public static void checkLiquid5aUndoS(World world, int x, int y, int z, Block block) {
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		checkLiquid5UndoS(world, x, y, z, block);
	}
	
	public static void buildLiquid4S(World world, int x, int y, int z, Block block) {
		if (c2 < Config.MAX_LIQUID) {
			if (world.getBlock(x+1, y, z) == Blocks.air && c2 < Config.MAX_LIQUID) {
				buildLiquid4aS(world, x+1, y, z, block);
				built = true;
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && c2 < Config.MAX_LIQUID) {
				buildLiquid4aS(world, x-1, y, z, block);
				built = true;
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && c2 < Config.MAX_LIQUID) {
				buildLiquid4aS(world, x, y, z+1, block);
				built = true;
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && c2 < Config.MAX_LIQUID) {
				buildLiquid4aS(world, x, y, z-1, block);
				built = true;
			}
		}
	}
	
	public static void buildLiquid4aS(World world, int x, int y, int z, Block block) {
		setBlockIfNoBedrock(world, x, y, z, block, 0, 2);
		if (c2 < Config.MAX_LIQUID) {
			buildLiquid4S(world, x, y, z, block);
		}
	}
	
	/******************************************************************************************************/
	
	/**************************************** SUCTION ************************************************/
	public static int cs = 0;
	public static int cs2 = 0;
	public static int liq = 0;
	public static int c3 = 0;
	public static boolean sucked = false;
	
	public static void checkSuck(World world, int x, int y, int z) {
		cs++;
		if (cs < Config.MAX_FILL) {
			if ((world.getBlock(x+1, y, z) == Blocks.water || world.getBlock(x+1, y, z) == Blocks.lava) && world.getBlockMetadata(x+1, y, z) != 15 && cs < Config.MAX_FILL) {
				checkSucka(world, x+1, y, z);
			}
			if ((world.getBlock(x-1, y, z) == Blocks.water || world.getBlock(x-1, y, z) == Blocks.lava) && world.getBlockMetadata(x-1, y, z) != 15 && cs < Config.MAX_FILL) {
				checkSucka(world, x-1, y, z);
			}
			if ((world.getBlock(x, y, z+1) == Blocks.water || world.getBlock(x, y, z+1) == Blocks.lava) && world.getBlockMetadata(x, y, z+1) != 15 && cs < Config.MAX_FILL) {
				checkSucka(world, x, y, z+1);
			}
			if ((world.getBlock(x, y, z-1) == Blocks.water || world.getBlock(x, y, z-1) == Blocks.lava) && world.getBlockMetadata(x, y, z-1) != 15 && cs < Config.MAX_FILL) {
				checkSucka(world, x, y, z-1);
			}
			if ((world.getBlock(x, y+1, z) == Blocks.water || world.getBlock(x, y+1, z) == Blocks.lava) && world.getBlockMetadata(x, y+1, z) != 15 && cs < Config.MAX_FILL) {
				checkSucka(world, x, y+1, z);
			}
			if ((world.getBlock(x, y-1, z) == Blocks.water || world.getBlock(x, y-1, z) == Blocks.lava) && world.getBlockMetadata(x, y-1, z) != 15 && cs < Config.MAX_FILL) {
				checkSucka(world, x, y-1, z);
			}
		}
	}
	
	public static void checkSucka(World world, int x, int y, int z) {
		counter++;
		liq = -1;
		world.setBlockMetadataWithNotify(x, y, z, 15, 2);
		checkSuck(world, x, y, z);
	}
	
	public static void checkSuckUndo(World world, int x, int y, int z) {
		cs2++;
		if (cs2 < Config.MAX_FILL) {
			if ((world.getBlock(x+1, y, z) == Blocks.water || world.getBlock(x+1, y, z) == Blocks.lava) && world.getBlockMetadata(x+1, y, z) == 15 && cs2 < Config.MAX_FILL) {
				checkSuckaUndo(world, x+1, y, z);
			}
			if ((world.getBlock(x-1, y, z) == Blocks.water || world.getBlock(x-1, y, z) == Blocks.lava) && world.getBlockMetadata(x-1, y, z) == 15 && cs2 < Config.MAX_FILL) {
				checkSuckaUndo(world, x-1, y, z);
			}
			if ((world.getBlock(x, y, z+1) == Blocks.water || world.getBlock(x, y, z+1) == Blocks.lava) && world.getBlockMetadata(x, y, z+1) == 15 && cs2 < Config.MAX_FILL) {
				checkSuckaUndo(world, x, y, z+1);
			}
			if ((world.getBlock(x, y, z-1) == Blocks.water || world.getBlock(x, y, z-1) == Blocks.lava) && world.getBlockMetadata(x, y, z-1) == 15 && cs2 < Config.MAX_FILL) {
				checkSuckaUndo(world, x, y, z-1);
			}
			if ((world.getBlock(x, y+1, z) == Blocks.water || world.getBlock(x, y+1, z) == Blocks.lava) && world.getBlockMetadata(x, y+1, z) == 15 && cs2 < Config.MAX_FILL) {
				checkSuckaUndo(world, x, y+1, z);
			}
			if ((world.getBlock(x, y-1, z) == Blocks.water || world.getBlock(x, y-1, z) == Blocks.lava) && world.getBlockMetadata(x, y-1, z) == 15 && cs2 < Config.MAX_FILL) {
				checkSuckaUndo(world, x, y-1, z);
			}
		}
	}
	
	public static void checkSuckaUndo(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		checkSuckUndo(world, x, y, z);
	}
	
	public static void buildSuck(World world, int x, int y, int z) {
		if (cs < Config.MAX_FILL) {
		if (world.getBlock(x+1, y, z) == Blocks.water) {
			buildSucka(world, x+1, y, z, 1);
		}
		if (world.getBlock(x-1, y, z) == Blocks.water) {
			buildSucka(world, x-1, y, z, 1);
		}
		if (world.getBlock(x, y, z+1) == Blocks.water) {
			buildSucka(world, x, y, z+1, 1);
		}
		if (world.getBlock(x, y, z-1) == Blocks.water) {
			buildSucka(world, x, y, z-1, 1);
		}
		if (world.getBlock(x, y-1, z) == Blocks.water) {
			buildSucka(world, x, y-1, z, 1);
		}
		if (world.getBlock(x, y+1, z) == Blocks.water) {
			buildSucka(world, x, y+1, z, 1);
		}
		/********************************************************************/
		if (world.getBlock(x+1, y, z) == Blocks.lava) {
			buildSucka(world, x+1, y, z, 2);
		}
		if (world.getBlock(x-1, y, z) == Blocks.lava) {
			buildSucka(world, x-1, y, z, 2);
		}
		if (world.getBlock(x, y, z+1) == Blocks.lava) {
			buildSucka(world, x, y, z+1, 2);
		}
		if (world.getBlock(x, y, z-1) == Blocks.lava) {
			buildSucka(world, x, y, z-1, 2);
		}
		if (world.getBlock(x, y-1, z) == Blocks.lava) {
			buildSucka(world, x, y-1, z, 2);
		}
		if (world.getBlock(x, y+1, z) == Blocks.lava) {
			buildSucka(world, x, y+1, z, 2);
		}
		}
	}
	
	public static void buildSucka(World world, int x, int y, int z, int liqP) {
		setBlockIfNoBedrock(world, x, y, z, Blocks.air, 0, 2);
		sucked = true;
		liq = liqP;
		if (cs < Config.MAX_FILL) {
			buildSuck(world, x, y, z);
		}
	}
	
	/******************************************************************************************************/
	
	public void buildLiquid2(World world, int x, int y, int z, Block block, Block block2, int counter, int max) {
		if (counter < Config.MAX_LIQUID) {
		if (world.getBlock(x+1, y, z) == Blocks.air /*|| world.getBlockId(x+1, y, z) == block2*/) {
			setBlockIfNoBedrock(world, x+1, y, z, block);
			buildLiquid2(world, x+1, y, z, block, block2, counter, Config.MAX_LIQUID);
			counter++;
		}
		if (world.getBlock(x-1, y, z) == Blocks.air /*|| world.getBlockId(x-1, y, z) == block2*/) {
			setBlockIfNoBedrock(world, x-1, y, z, block);
			buildLiquid2(world, x-1, y, z, block, block2, counter, Config.MAX_LIQUID);
			counter++;
		}
		if (world.getBlock(x, y, z+1) == Blocks.air /*|| world.getBlockId(x, y, z+1) == block2*/) {
			setBlockIfNoBedrock(world, x, y, z+1, block);
			buildLiquid2(world, x, y, z+1, block, block2, counter, Config.MAX_LIQUID);
			counter++;
		}
		if (world.getBlock(x, y, z-1) == Blocks.air /*|| world.getBlockId(x, y, z-1) == block2*/) {
			setBlockIfNoBedrock(world, x, y, z-1, block);
			buildLiquid2(world, x, y, z-1, block, block2, counter, Config.MAX_LIQUID);
			counter++;
		}
		if (world.getBlock(x, y-1, z) == Blocks.air /*|| world.getBlockId(x, y-1, z) == block2*/) {
			setBlockIfNoBedrock(world, x, y-1, z, block);
			buildLiquid2(world, x, y-1, z, block, block2, counter, Config.MAX_LIQUID);
			counter++;
		}
		} else {
			counter = 1;
		}
	}
	
	public void setColorBlock(World world, int x, int y, int z, BufferedImage img, int imgx, int imgy, boolean rgb) {
		if (rgb) {
			setBlockIfNoBedrock(world, x, y, z, ModBlocks.color);
			((TileEntityColor) world.getTileEntity(x, y, z)).color = img.getRGB(imgx, imgy);
			world.markBlockForUpdate(x, y, z);
		} else {
			setBlockIfNoBedrock(world, x, y, z, Blocks.wool, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 1);
		}
	}
	
	public void setColorBlock(World world, int x, int y, int z, BufferedImage img, int imgx, int imgy, int metaDirection, int forwardBack, int leftRight, boolean rgb) {
		if (rgb) {
			setBlockDirectionalSimple(world, x, y, z, ModBlocks.color, metaDirection, forwardBack, leftRight);
			((TileEntityColor) world.getTileEntity(x, y, z)).color = img.getRGB(imgx, imgy);
			world.markBlockForUpdate(x, y, z);
		} else {
			setBlockDirectionalSimple(world, x, y, z, Blocks.wool, metaDirection, forwardBack, leftRight, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 1);
		}
	}

	
	private static boolean isPositive(int i) {
		if (i == 0) return true;
	    if (i >> 31 != 0) return false;
	    return true;
	}
	
	public static int toPositive(int i) {
		if (i < 0) {
			return -i;
		}
		return i;
	}
	
	public static void setBlockDirectionalSimple(World world, int x, int y, int z, Block block, int metaDirection, int forwardBack, int leftRight) {
		setBlockDirectionalSimple(world, x, y, z, block, metaDirection, forwardBack, leftRight, 0, 1);
    }
	
	public static void setBlockDirectionalSimple(World world, int x, int y, int z, Block block, int metaDirection, int forwardBack, int leftRight, int meta, int flag) {
    	// +forwardBack == forward
    	// -forwardBack == back
    	// +leftRight == right
    	// -leftRight == left
		
		int forward;
		int back;
		int left;
		int right;
		
		if(isPositive(forwardBack)) {
			forward = forwardBack;
			back = 0;
		} else {
			forward = 0;
			back = toPositive(forwardBack);
		}
		
		if(isPositive(leftRight)) {
			left = 0;
			right = leftRight;
		} else {
			left = toPositive(leftRight);
			right = 0;
		}
		setBlockDirectional(world, x, y, z, block, metaDirection, forward, back, left, right, meta, flag);
    }
	
    public static void setBlockDirectional(World world, int x, int y, int z, Block block, int metaDirection, int forward, int back, int left, int right) {
    	setBlockDirectional(world, x, y, z, block, metaDirection, forward, back, left, right, 0, 1);
    }
    
    public static void setBlockDirectional(World world, int x, int y, int z, Block block, int metaDirection, int forward, int back, int left, int right, int meta, int flag) {
    	if(metaDirection==0) {
    		setBlockIfNoBedrock(world, x-left+right, y, z-forward+back, block, meta, flag);
		} else if(metaDirection==1) {
			setBlockIfNoBedrock(world, x+forward-back, y, z+left-right, block, meta, flag);
		} else if(metaDirection==2) {
			setBlockIfNoBedrock(world, x+left-right, y, z+forward-back, block, meta, flag);
		} else if(metaDirection==3) {
			setBlockIfNoBedrock(world, x-forward+back, y, z-left+right, block, meta, flag);
		}
    }
    
    public static void buildDirectionalSimple(World world, int x, int y, int z, Block block, int metaDirection, int forwardBack, int leftRight, int forwardBackT, int yT, int leftRightT) {
    	//setBlockDirectional(world, x, y, z, block, metaDirection, forward, back, left, right);
    	//setBlockDirectionalSimple(world, x, y, z, block, metaDirection, forwardBack, leftRight);
    	
    	int z2 = z;
		int x2 = x;
		for (int yTimes = 0; yTimes < yT; yTimes++) {
			for (int zTimes = 0; zTimes < forwardBackT; zTimes++) {
				for (int xTimes = 0; xTimes < leftRightT; xTimes++) {
					//setBlockIfNoBedrock(world, x2, y, z2, block);
					setBlockDirectionalSimple(world, x, y, z, block, metaDirection, z2, x2);
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y++;
		}
    }
    
    public static void setColorBlockComplex(World world, int x, int y, int z, BufferedImage img, int imgx, int imgy, int metaDirection, int forwardBack, int leftRight, boolean rgb) {
    	if (rgb) {
    		int forward;
    		int back;
    		int left;
    		int right;
    		
    		if(isPositive(forwardBack)) {
    			back = forwardBack;
    			forward = 0;
    		} else {
    			back = 0;
    			forward = toPositive(forwardBack);
    		}
    		
    		if(isPositive(leftRight)) {
    			right = 0;
    			left = leftRight;
    		} else {
    			right = toPositive(leftRight);
    			left = 0;
    		}
    		
    		if(metaDirection==0) {
        		setBlockIfNoBedrock(world, x+left-right, y, z-forward+back, ModBlocks.color, 0, 2);
        		((TileEntityColor) world.getTileEntity(x+left-right, y, z-forward+back)).color = img.getRGB(imgx, imgy);
        		//((TileEntityColor) world.getTileEntity(x+left-right, y, z-forward+back)).color = 0xFF0000;
    			world.markBlockForUpdate(x+left-right, y, z-forward+back);
    		} else if(metaDirection==1) {
    			setBlockIfNoBedrock(world, x+forward-back, y, z+left-right, ModBlocks.color, 0, 2);
    			((TileEntityColor) world.getTileEntity(x+forward-back, y, z+left-right)).color = img.getRGB(imgx, imgy);
    			//((TileEntityColor) world.getTileEntity(x+forward-back, y, z+left-right)).color = 0x00FF00;
    			world.markBlockForUpdate(x+forward-back, y, z+left-right);
    		} else if(metaDirection==2) {
    			setBlockIfNoBedrock(world, x-left+right, y, z+forward-back, ModBlocks.color, 0, 2);
    			((TileEntityColor) world.getTileEntity(x-left+right, y, z+forward-back)).color = img.getRGB(imgx, imgy);
    			//((TileEntityColor) world.getTileEntity(x-left+right, y, z+forward-back)).color = 0x0000FF;
    			world.markBlockForUpdate(x-left+right, y, z+forward-back);
    		} else if(metaDirection==3) {
    			setBlockIfNoBedrock(world, x-forward+back, y, z-left+right, ModBlocks.color, 0, 2);
    			((TileEntityColor) world.getTileEntity(x-forward+back, y, z-left+right)).color = img.getRGB(imgx, imgy);
    			//((TileEntityColor) world.getTileEntity(x-forward+back, y, z-left+right)).color = 0xFFFF00;
    			world.markBlockForUpdate(x-forward+back, y, z-left+right);
    		}
		} else {
			int forward;
    		int back;
    		int left;
    		int right;
    		
    		if(isPositive(forwardBack)) {
    			back = forwardBack;
    			forward = 0;
    		} else {
    			back = 0;
    			forward = toPositive(forwardBack);
    		}
    		
    		if(isPositive(leftRight)) {
    			right = 0;
    			left = leftRight;
    		} else {
    			right = toPositive(leftRight);
    			left = 0;
    		}
		
    		if(metaDirection==0) {
        		setBlockIfNoBedrock(world, x+left-right, y, z-forward+back, Blocks.wool, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 2);
    		} else if(metaDirection==1) {
    			setBlockIfNoBedrock(world, x+forward-back, y, z+left-right, Blocks.wool, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 2);
    		} else if(metaDirection==2) {
    			setBlockIfNoBedrock(world, x-left+right, y, z+forward-back, Blocks.wool, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 2);
    		} else if(metaDirection==3) {
    			setBlockIfNoBedrock(world, x-forward+back, y, z-left+right, Blocks.wool, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 2);
    		}
    		
		}
    }
	
}