package com.slymask3.instantblocks.utility;

import java.awt.image.BufferedImage;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.BlockInstantHouseWood;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.tileentity.TileEntityColor;

import cpw.mods.fml.common.registry.GameRegistry;

public class InstantBlocksFunctions {
	private static ConfigurationHandler config = new ConfigurationHandler();
	private static InstantBlocks ib = new InstantBlocks();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
	public static int checkTick = 0;
	public static int checkParticle = 0;
	
	public String wandReq = "\u00a7cYou must use an Instant Wand.";
	public String woodCreate = "\u00a7aInstant Wooden House created.";
	public String woodPack = "\u00a7aInstant Wooden House packed up.";
	public String woodError = "\u00a7cInstant Wooden House cannot be re-created. You must pack up this house first.";
	public String fallCreate = "\u00a7aInstant \u00a7cR\u00a76a\u00a7ei\u00a7an\u00a72b\u00a73o\u00a7bw \u00a7aSkydive created.";
	public String farmCreateW = "\u00a7aInstant Farm created with wheat.";
	public String farmCreateC = "\u00a7aInstant Farm created with carrots.";
	public String farmCreateP = "\u00a7aInstant Farm created with potatos.";
	public String domeCreate = "\u00a7aInstant Glass Dome created.";
	public String grinderCreate = "\u00a7aInstant Grinder created.";
	public String grinderError = "\u00a7cYou must place this above a Zombie/Skeleton spawner.";
	public String ladderCreate = "\u00a7aInstant Mining Ladder created.";
	public String ladderError = "\u00a7cInstant Mining Ladder cannot be created below layer 16.";
	public String lavaCreate1 = "\u00a7aInstant Lava created with 1 lava block.";
	public String poolCreate = "\u00a7aInstant Pool created.";
	public String upError = "\u00a7cThis block only works underground.";
	public String waterCreate1 = "\u00a7aInstant Water created with 1 water block.";
	
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
	
	public static void setBlockIfNoBedrock(World world, int x, int y, int z, Block block, int meta, int flag) {
		if (world.getBlock(x, y, z) != Blocks.bedrock) {
			world.setBlock(x, y, z, block, meta, flag);
		}
	}
	
	public static void setBlockIfNoBedrock(World world, int x, int y, int z, Block block) {
		if (world.getBlock(x, y, z) != Blocks.bedrock) {
			world.setBlock(x, y, z, block);
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
		int z2 = z;
		int x2 = x;
		for (int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for (int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for (int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					setBlockIfNoBedrock(world, x2, y, z2, block);
					z2++;
				}
				z2 = z;
				x2++;
			}
			x2 = x;
			y++;
		}
	}
	
	public static void buildMeta(World world, int x, int y, int z, Block block, int meta, int i, int xTimesTotal, int yTimesTotal, int zTimesTotal) {
		int z2 = z;
		int x2 = x;
		for (int yTimes = 0; yTimes < yTimesTotal; yTimes++) {
			for (int zTimes = 0; zTimes < zTimesTotal; zTimes++) {
				for (int xTimes = 0; xTimes < xTimesTotal; xTimes++) {
					setBlockIfNoBedrock(world, x2, y, z2, block, meta, i);
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
		if (world.isAirBlock(x, y, z) == true) {
			setBlockIfNoBedrock(world, x, y, z, block);
		}
	}
	
	public static void ifNoBlockThenStop(World world, int x, int y, int z, Block block, EntityPlayer player, String blockName) {
		if (world.getBlock(x, y, z) != block) {
			msg(player, "Missing Block: " + blockName + " at x=" + (x) + ", y=" + (y) + ", z=" + (z) + ".", "\u00a7c");
			BlockInstantHouseWood.notThere = true;
		}
	}
	
	public static boolean b = false;
	
	public static void msg(EntityPlayer player, String msg, String color) {
		if (config.msg == true) {
			World world = player.worldObj;
		
			if (world.isRemote) { //IF CLIENT	
				player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] " + ColorHelper.colorEveryWord(msg, color)));
			}
		}
	}
	
	public static boolean b2 = false;
	
	public static void xp(World world, EntityPlayer player, int xpAmount) {
		if (!world.isRemote) { //IF SERVER
			if  (xpAmount > 0) {
				EntityXPOrb xp = new EntityXPOrb(world, player.posX, player.posY, player.posZ, xpAmount);
				world.spawnEntityInWorld(xp);
				
				//EntityCow cow = new EntityCow(world);
				//cow.setPosition(player.posX, player.posY+5, player.posZ);
				//world.spawnEntityInWorld(cow);
			}
		}
	}
	
	
	//@SideOnly(Side.CLIENT)
	public static void msgClean(EntityPlayer player, String msg, String color) {
		//if (config.msg == true) {
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] " + ColorHelper.colorEveryWord(msg, color)));
		//}
    }
	
	public static void msgCleanBypass(EntityPlayer player, String msg, String color) {
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] " + ColorHelper.colorEveryWord(msg, color)));
    }
    
	public static void sound(World world, String sound, int x, int y, int z) {
		world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), sound, 2.0F, 1.0F);
	}
	
	/*public static void effect(World world, String particle, int x, int y, int z) {
		for (double i = 0; i <= 1; i = i + 0.1) {
			for (double n = 0; n <= 1; n = n + 0.1) {
				//world.spawnParticle("instantSpell", x+i, y+1, z+n, 0.0D, 0.0D, 0.0D);
				world.spawnParticle(particle, x+i, y+1, z+n, 0.0D, 0.0D, 0.0D);
			}
		}
	}*/
	
	/*public static void effectFull(World world, String particle, int x, int y, int z) {
		if (config.effect.getBoolean(true)) {
			for (double i = 0; i <= 1; i = i + 0.1) {
				for (double n = 0; n <= 1; n = n + 0.1) {
					for (double v = 0; v <= 1; v = v + 0.1) {
						world.spawnParticle(particle, x+i, y+v, z+n, 0.0D, 0.0D, 0.0D);
					}
				}
			}
		}
	}*/
	
	public static void effectFull(World world, String particle, int x, int y, int z) {
		if (config.effect == true) {
			//if (checkParticle > 0) {
				for (double i = 0; i <= 1; i = i + 0.1) {
					for (double n = 0; n <= 1; n = n + 0.1) {
						for (double v = 0; v <= 1; v = v + 0.1) {
							world.spawnParticle(particle, x+i, y+v, z+n, 0.0D, 0.0D, 0.0D);
							//System.out.println("PARTICLE");
						}
					}
				}
			//}
		}
	}
	
	public int counter = 0;
	public int c2 = 0;
	public int c5 = 0;
	
	public void buildLiquid(World world, int x, int y, int z, Block block) {
		c2++;
		if (c2 < 5000) {
			System.out.println("went through | counter == " + counter + " | c2 == " + c2);
		if (world.getBlock(x+1, y, z) == Blocks.air /*|| world.getBlockId(x+1, y, z) == block2*/) {
			setBlockIfNoBedrock(world, x+1, y, z, block, 0, 2);
			buildLiquid(world, x+1, y, z, block);
			counter++;
			System.out.println("counter == " + counter);
		}
		if (world.getBlock(x-1, y, z) == Blocks.air /*|| world.getBlockId(x-1, y, z) == block2*/) {
			setBlockIfNoBedrock(world, x-1, y, z, block, 0, 2);
			buildLiquid(world, x-1, y, z, block);
			counter++;
			System.out.println("counter == " + counter);
		}
		if (world.getBlock(x, y, z+1) == Blocks.air /*|| world.getBlockId(x, y, z+1) == block2*/) {
			setBlockIfNoBedrock(world, x, y, z+1, block, 0, 2);
			buildLiquid(world, x, y, z+1, block);
			counter++;
			System.out.println("counter == " + counter);
		}
		if (world.getBlock(x, y, z-1) == Blocks.air /*|| world.getBlockId(x, y, z-1) == block2*/) {
			setBlockIfNoBedrock(world, x, y, z-1, block, 0, 2);
			buildLiquid(world, x, y, z-1, block);
			counter++;
			System.out.println("counter == " + counter);
		}
		if (world.getBlock(x, y-1, z) == Blocks.air /*|| world.getBlockId(x, y-1, z) == block2*/) {
			setBlockIfNoBedrock(world, x, y-1, z, block, 0, 2);
			buildLiquid(world, x, y-1, z, block);
			counter++;
			System.out.println("counter == " + counter);
		}
		}
	}
	
	public void buildLiquid3(World world, int x, int y, int z, Block block) {
		c2++;
		if (c2 < 5000) {
			System.out.println("went through | counter == " + counter + " | c2 == " + c2);
		if (world.getBlock(x+1, y, z) == Blocks.air /*|| world.getBlockId(x+1, y, z) == block2*/) {
			buildLiquid3a(world, x+1, y, z, block);
		}
		if (world.getBlock(x-1, y, z) == Blocks.air /*|| world.getBlockId(x-1, y, z) == block2*/) {
			buildLiquid3a(world, x-1, y, z, block);
		}
		if (world.getBlock(x, y, z+1) == Blocks.air /*|| world.getBlockId(x, y, z+1) == block2*/) {
			buildLiquid3a(world, x, y, z+1, block);
		}
		if (world.getBlock(x, y, z-1) == Blocks.air /*|| world.getBlockId(x, y, z-1) == block2*/) {
			buildLiquid3a(world, x, y, z-1, block);
		}
		if (world.getBlock(x, y-1, z) == Blocks.air /*|| world.getBlockId(x, y-1, z) == block2*/) {
			buildLiquid3a(world, x, y-1, z, block);
		}
		}
	}
	
	public void buildLiquid3a(World world, int x, int y, int z, Block block) {
		setBlockIfNoBedrock(world, x, y, z, block, 0, 2);
		buildLiquid3(world, x, y, z, block);
		counter++;
		System.out.println("counter == " + counter);
	}
	
	public int[] xDone = new int[25000];
	public int[] yDone = new int[25000];
	public int[] zDone = new int[25000];
	public boolean[] checked = new boolean[25000];
	
	public boolean checkLiquid4a(int x, int y, int z) {
		if (xDone[counter] == x && yDone[counter] == y && zDone[counter] == z) {
			return true;
		}
		return false;
	}
	
	public void checkLiquid4(World world, int x, int y, int z, Block block) {
		c2++;
		if (c2 < 5000) {
			System.out.println("went through | counter == " + counter + " | c2 == " + c2);
			if (world.getBlock(x+1, y, z) == Blocks.air && checkLiquid4a(x+1, y, z) == false/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				System.out.println("counter == " + counter);
				xDone[counter] = x+1;
				yDone[counter] = y;
				zDone[counter] = z;
				checkLiquid4(world, x+1, y, z, block);
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && checkLiquid4a(x-1, y, z) == false/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				System.out.println("counter == " + counter);
				xDone[counter] = x-1;
				yDone[counter] = y;
				zDone[counter] = z;
				checkLiquid4(world, x-1, y, z, block);
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && checkLiquid4a(x, y, z+1) == false/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				System.out.println("counter == " + counter);
				xDone[counter] = x;
				yDone[counter] = y;
				zDone[counter] = z+1;
				checkLiquid4(world, x, y, z+1, block);
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && checkLiquid4a(x, y, z-1) == false/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				System.out.println("counter == " + counter);
				xDone[counter] = x;
				yDone[counter] = y;
				zDone[counter] = z-1;
				checkLiquid4(world, x, y, z-1, block);
			}
			if (world.getBlock(x, y-1, z) == Blocks.air && checkLiquid4a(x, y-1, z) == false/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				System.out.println("counter == " + counter);
				xDone[counter] = x;
				yDone[counter] = y-1;
				zDone[counter] = z;
				checkLiquid4(world, x, y-1, z, block);
			}
		}
	}
	
	public boolean built = false;
	
	public void checkLiquid5(World world, int x, int y, int z, Block block) {
		c2++;
		if (c2 < config.max) {
			if (world.getBlock(x+1, y, z) == Blocks.air && world.getBlockMetadata(x+1, y, z) != 15 && c2 < config.max/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				checkLiquid5a(world, x+1, y, z, block);
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && world.getBlockMetadata(x-1, y, z) != 15 && c2 < config.max/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				checkLiquid5a(world, x-1, y, z, block);
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && world.getBlockMetadata(x, y, z+1) != 15 && c2 < config.max/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				checkLiquid5a(world, x, y, z+1, block);
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && world.getBlockMetadata(x, y, z-1) != 15 && c2 < config.max/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				checkLiquid5a(world, x, y, z-1, block);
			}
			if (world.getBlock(x, y-1, z) == Blocks.air && world.getBlockMetadata(x, y-1, z) != 15 && c2 < config.max/*xDone[counter] == 0 && yDone[counter] == 0 && zDone[counter] == 0*/) {
				counter++;
				checkLiquid5a(world, x, y-1, z, block);
			}
		}
	}
	
	public int[] checkLiquid5aC = new int[25000];
	
	public void checkLiquid5a(World world, int x, int y, int z, Block block) {
			world.setBlockMetadataWithNotify(x, y, z, 15, 2);
			checkLiquid5(world, x, y, z, block);
	}
	
	public void checkLiquid5Undo(World world, int x, int y, int z, Block block) {
		c5++;
		if (c5 < config.max) {
			if (world.getBlock(x+1, y, z) == Blocks.air && world.getBlockMetadata(x+1, y, z) == 15 && c5 < config.max) {
				checkLiquid5aUndo(world, x+1, y, z, block);
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && world.getBlockMetadata(x-1, y, z) == 15 && c5 < config.max) {
				checkLiquid5aUndo(world, x-1, y, z, block);
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && world.getBlockMetadata(x, y, z+1) == 15 && c5 < config.max) {
				checkLiquid5aUndo(world, x, y, z+1, block);
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && world.getBlockMetadata(x, y, z-1) == 15 && c5 < config.max) {
				checkLiquid5aUndo(world, x, y, z-1, block);
			}
			if (world.getBlock(x, y-1, z) == Blocks.air && world.getBlockMetadata(x, y-1, z) == 15 && c5 < config.max) {
				checkLiquid5aUndo(world, x, y-1, z, block);
			}
		}
	}
	
	public void checkLiquid5aUndo(World world, int x, int y, int z, Block block) {
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		checkLiquid5Undo(world, x, y, z, block);
	}
	
	public void buildLiquid4(World world, int x, int y, int z, Block block) {
		if (c2 < config.max) {
			if (world.getBlock(x+1, y, z) == Blocks.air && c2 < config.max) {
				buildLiquid4a(world, x+1, y, z, block);
				built = true;
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && c2 < config.max) {
				buildLiquid4a(world, x-1, y, z, block);
				built = true;
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && c2 < config.max) {
				buildLiquid4a(world, x, y, z+1, block);
				built = true;
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && c2 < config.max) {
				buildLiquid4a(world, x, y, z-1, block);
				built = true;
			}
			if (world.getBlock(x, y-1, z) == Blocks.air && c2 < config.max) {
				buildLiquid4a(world, x, y-1, z, block);
				built = true;
			}
		}
	}
	
	public void buildLiquid4a(World world, int x, int y, int z, Block block) {
		setBlockIfNoBedrock(world, x, y, z, block, 0, 2);
		if (c2 < config.max) {
			buildLiquid4(world, x, y, z, block);
		}
	}
	
	/**************************************** SIMPLE VERSION ************************************************/
	
	public void checkLiquid5S(World world, int x, int y, int z, Block block) {
		c2++;
		if (c2 < config.max) {
			if (world.getBlock(x+1, y, z) == Blocks.air && world.getBlockMetadata(x+1, y, z) != 15 && c2 < config.max) {
				counter++;
				checkLiquid5aS(world, x+1, y, z, block);
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && world.getBlockMetadata(x-1, y, z) != 15 && c2 < config.max) {
				counter++;
				checkLiquid5aS(world, x-1, y, z, block);
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && world.getBlockMetadata(x, y, z+1) != 15 && c2 < config.max) {
				counter++;
				checkLiquid5aS(world, x, y, z+1, block);
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && world.getBlockMetadata(x, y, z-1) != 15 && c2 < config.max) {
				counter++;
				checkLiquid5aS(world, x, y, z-1, block);
			}
		}
	}
	
	public void checkLiquid5aS(World world, int x, int y, int z, Block block) {
		world.setBlockMetadataWithNotify(x, y, z, 15, 2);
		checkLiquid5S(world, x, y, z, block);
	}
	
	public void checkLiquid5UndoS(World world, int x, int y, int z, Block block) {
		c5++;
		if (c5 < config.max) {
			if (world.getBlock(x+1, y, z) == Blocks.air && world.getBlockMetadata(x+1, y, z) == 15 && c5 < config.max) {
				checkLiquid5aUndoS(world, x+1, y, z, block);
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && world.getBlockMetadata(x-1, y, z) == 15 && c5 < config.max) {
				checkLiquid5aUndoS(world, x-1, y, z, block);
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && world.getBlockMetadata(x, y, z+1) == 15 && c5 < config.max) {
				checkLiquid5aUndoS(world, x, y, z+1, block);
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && world.getBlockMetadata(x, y, z-1) == 15 && c5 < config.max) {
				checkLiquid5aUndoS(world, x, y, z-1, block);
			}
		}
	}
	
	public void checkLiquid5aUndoS(World world, int x, int y, int z, Block block) {
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		checkLiquid5UndoS(world, x, y, z, block);
	}
	
	public void buildLiquid4S(World world, int x, int y, int z, Block block) {
		if (c2 < config.max) {
			if (world.getBlock(x+1, y, z) == Blocks.air && c2 < config.max) {
				buildLiquid4aS(world, x+1, y, z, block);
				built = true;
			}
			if (world.getBlock(x-1, y, z) == Blocks.air && c2 < config.max) {
				buildLiquid4aS(world, x-1, y, z, block);
				built = true;
			}
			if (world.getBlock(x, y, z+1) == Blocks.air && c2 < config.max) {
				buildLiquid4aS(world, x, y, z+1, block);
				built = true;
			}
			if (world.getBlock(x, y, z-1) == Blocks.air && c2 < config.max) {
				buildLiquid4aS(world, x, y, z-1, block);
				built = true;
			}
		}
	}
	
	public void buildLiquid4aS(World world, int x, int y, int z, Block block) {
		setBlockIfNoBedrock(world, x, y, z, block, 0, 2);
		if (c2 < config.max) {
			buildLiquid4S(world, x, y, z, block);
		}
	}
	
	/******************************************************************************************************/
	
	/**************************************** SUCTION ************************************************/
	public int cs = 0;
	public int cs2 = 0;
	public int liq = 0;
	public int c3 = 0;
	public boolean sucked = false;
	
	public void checkSuck(World world, int x, int y, int z) {
		cs++;
		if (cs < config.maxSuck) {
			if ((world.getBlock(x+1, y, z) == Blocks.water || world.getBlock(x+1, y, z) == Blocks.lava) && world.getBlockMetadata(x+1, y, z) != 15 && cs < config.maxSuck) {
				checkSucka(world, x+1, y, z);
			}
			if ((world.getBlock(x-1, y, z) == Blocks.water || world.getBlock(x-1, y, z) == Blocks.lava) && world.getBlockMetadata(x-1, y, z) != 15 && cs < config.maxSuck) {
				checkSucka(world, x-1, y, z);
			}
			if ((world.getBlock(x, y, z+1) == Blocks.water || world.getBlock(x, y, z+1) == Blocks.lava) && world.getBlockMetadata(x, y, z+1) != 15 && cs < config.maxSuck) {
				checkSucka(world, x, y, z+1);
			}
			if ((world.getBlock(x, y, z-1) == Blocks.water || world.getBlock(x, y, z-1) == Blocks.lava) && world.getBlockMetadata(x, y, z-1) != 15 && cs < config.maxSuck) {
				checkSucka(world, x, y, z-1);
			}
			if ((world.getBlock(x, y+1, z) == Blocks.water || world.getBlock(x, y+1, z) == Blocks.lava) && world.getBlockMetadata(x, y+1, z) != 15 && cs < config.maxSuck) {
				checkSucka(world, x, y+1, z);
			}
			if ((world.getBlock(x, y-1, z) == Blocks.water || world.getBlock(x, y-1, z) == Blocks.lava) && world.getBlockMetadata(x, y-1, z) != 15 && cs < config.maxSuck) {
				checkSucka(world, x, y-1, z);
			}
		}
	}
	
	public void checkSucka(World world, int x, int y, int z) {
		counter++;
		liq = -1;
		world.setBlockMetadataWithNotify(x, y, z, 15, 2);
		checkSuck(world, x, y, z);
	}
	
	public void checkSuckUndo(World world, int x, int y, int z) {
		cs2++;
		if (cs2 < config.maxSuck) {
			if ((world.getBlock(x+1, y, z) == Blocks.water || world.getBlock(x+1, y, z) == Blocks.lava) && world.getBlockMetadata(x+1, y, z) == 15 && cs2 < config.maxSuck) {
				checkSuckaUndo(world, x+1, y, z);
			}
			if ((world.getBlock(x-1, y, z) == Blocks.water || world.getBlock(x-1, y, z) == Blocks.lava) && world.getBlockMetadata(x-1, y, z) == 15 && cs2 < config.maxSuck) {
				checkSuckaUndo(world, x-1, y, z);
			}
			if ((world.getBlock(x, y, z+1) == Blocks.water || world.getBlock(x, y, z+1) == Blocks.lava) && world.getBlockMetadata(x, y, z+1) == 15 && cs2 < config.maxSuck) {
				checkSuckaUndo(world, x, y, z+1);
			}
			if ((world.getBlock(x, y, z-1) == Blocks.water || world.getBlock(x, y, z-1) == Blocks.lava) && world.getBlockMetadata(x, y, z-1) == 15 && cs2 < config.maxSuck) {
				checkSuckaUndo(world, x, y, z-1);
			}
			if ((world.getBlock(x, y+1, z) == Blocks.water || world.getBlock(x, y+1, z) == Blocks.lava) && world.getBlockMetadata(x, y+1, z) == 15 && cs2 < config.maxSuck) {
				checkSuckaUndo(world, x, y+1, z);
			}
			if ((world.getBlock(x, y-1, z) == Blocks.water || world.getBlock(x, y-1, z) == Blocks.lava) && world.getBlockMetadata(x, y-1, z) == 15 && cs2 < config.maxSuck) {
				checkSuckaUndo(world, x, y-1, z);
			}
		}
	}
	
	public void checkSuckaUndo(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		checkSuckUndo(world, x, y, z);
	}
	
	public void buildSuck(World world, int x, int y, int z) {
		if (cs < config.maxSuck) {
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
	
	public void buildSucka(World world, int x, int y, int z, int liqP) {
		setBlockIfNoBedrock(world, x, y, z, Blocks.air, 0, 2);
		sucked = true;
		liq = liqP;
		if (cs < config.maxSuck) {
			buildSuck(world, x, y, z);
		}
	}
	
	/******************************************************************************************************/
	
	public void buildLiquid2(World world, int x, int y, int z, Block block, Block block2, int counter, int max) {
		if (counter < config.max) {
		if (world.getBlock(x+1, y, z) == Blocks.air /*|| world.getBlockId(x+1, y, z) == block2*/) {
			setBlockIfNoBedrock(world, x+1, y, z, block);
			buildLiquid2(world, x+1, y, z, block, block2, counter, config.max);
			counter++;
		}
		if (world.getBlock(x-1, y, z) == Blocks.air /*|| world.getBlockId(x-1, y, z) == block2*/) {
			setBlockIfNoBedrock(world, x-1, y, z, block);
			buildLiquid2(world, x-1, y, z, block, block2, counter, config.max);
			counter++;
		}
		if (world.getBlock(x, y, z+1) == Blocks.air /*|| world.getBlockId(x, y, z+1) == block2*/) {
			setBlockIfNoBedrock(world, x, y, z+1, block);
			buildLiquid2(world, x, y, z+1, block, block2, counter, config.max);
			counter++;
		}
		if (world.getBlock(x, y, z-1) == Blocks.air /*|| world.getBlockId(x, y, z-1) == block2*/) {
			setBlockIfNoBedrock(world, x, y, z-1, block);
			buildLiquid2(world, x, y, z-1, block, block2, counter, config.max);
			counter++;
		}
		if (world.getBlock(x, y-1, z) == Blocks.air /*|| world.getBlockId(x, y-1, z) == block2*/) {
			setBlockIfNoBedrock(world, x, y-1, z, block);
			buildLiquid2(world, x, y-1, z, block, block2, counter, config.max);
			counter++;
		}
		} else {
			counter = 1;
		}
	}
	
	public void keepBlocks(World world, int x, int y, int z, Block block) {
		if (config.keepBlocks == true) {
			setBlockIfNoBedrock(world, x, y, z, block);
		} else {
			//Do not keep block.
		}
	}
	
	public void tp(World world, EntityPlayer player, int x, int y, int z, boolean property) {
		if (!world.isRemote) { //IF SERVER
			if (property) {
				sound(world, config.sound, x, y, z);
				player.setPositionAndUpdate(x + 0.5, y + 0.5, z + 0.5);
			}
		}
	}
	

	
	public void setColorBlock(World world, int x, int y, int z, BufferedImage img, int imgx, int imgy) {
		if (config.rgbMode) {
			setBlockIfNoBedrock(world, x, y, z, mb.color);
			((TileEntityColor) world.getTileEntity(x, y, z)).color = img.getRGB(imgx, imgy);
			world.markBlockForUpdate(x, y, z);
		} else {
			setBlockIfNoBedrock(world, x, y, z, Blocks.wool, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 1);
		}
	}
	
	public void setColorBlock(World world, int x, int y, int z, BufferedImage img, int imgx, int imgy, int metaDirection, int forwardBack, int leftRight) {
		if (config.rgbMode) {
			setBlockDirectionalSimple(world, x, y, z, mb.color, metaDirection, forwardBack, leftRight);
			((TileEntityColor) world.getTileEntity(x, y, z)).color = img.getRGB(imgx, imgy);
			world.markBlockForUpdate(x, y, z);
		} else {
			setBlockDirectionalSimple(world, x, y, z, Blocks.wool, metaDirection, forwardBack, leftRight, ColorHelper.getWoolColor(ColorHelper.getColorAt(img, imgx, imgy)), 1);
		}
	}

	
	private boolean isPositive(int i) {
		if (i == 0) return true;
	    if (i >> 31 != 0) return false;
	    return true;
	}
	
	public int toPositive(int i) {
		if (i < 0) {
			return -i;
		}
		return i;
	}
	
	public void setBlockDirectionalSimple(World world, int x, int y, int z, Block block, int metaDirection, int forwardBack, int leftRight) {
		setBlockDirectionalSimple(world, x, y, z, block, metaDirection, forwardBack, leftRight, 0, 1);
    }
	
	public void setBlockDirectionalSimple(World world, int x, int y, int z, Block block, int metaDirection, int forwardBack, int leftRight, int meta, int flag) {
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
	
    public void setBlockDirectional(World world, int x, int y, int z, Block block, int metaDirection, int forward, int back, int left, int right) {
    	setBlockDirectional(world, x, y, z, block, metaDirection, forward, back, left, right, 0, 1);
    }
    
    public void setBlockDirectional(World world, int x, int y, int z, Block block, int metaDirection, int forward, int back, int left, int right, int meta, int flag) {
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
    
    public void setColorBlockComplex(World world, int x, int y, int z, BufferedImage img, int imgx, int imgy, int metaDirection, int forwardBack, int leftRight) {
    	if (config.rgbMode) {
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
        		setBlockIfNoBedrock(world, x+left-right, y, z-forward+back, mb.color, 0, 2);
        		((TileEntityColor) world.getTileEntity(x+left-right, y, z-forward+back)).color = img.getRGB(imgx, imgy);
    			world.markBlockForUpdate(x+left-right, y, z-forward+back);
    		} else if(metaDirection==1) {
    			setBlockIfNoBedrock(world, x+forward-back, y, z+left-right, mb.color, 0, 2);
    			((TileEntityColor) world.getTileEntity(x+forward-back, y, z+left-right)).color = img.getRGB(imgx, imgy);
    			world.markBlockForUpdate(x+forward-back, y, z+left-right);
    		} else if(metaDirection==2) {
    			setBlockIfNoBedrock(world, x-left+right, y, z+forward-back, mb.color, 0, 2);
    			((TileEntityColor) world.getTileEntity(x-left+right, y, z+forward-back)).color = img.getRGB(imgx, imgy);
    			world.markBlockForUpdate(x-left+right, y, z+forward-back);
    		} else if(metaDirection==3) {
    			setBlockIfNoBedrock(world, x-forward+back, y, z-left+right, mb.color, 0, 2);
    			((TileEntityColor) world.getTileEntity(x-forward+back, y, z-left+right)).color = img.getRGB(imgx, imgy);
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