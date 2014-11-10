package com.slymask3.instantblocks.utility;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.reference.Reference;

public class IBHelper {
	//@SideOnly(Side.CLIENT)
	public static void msgClean(EntityPlayer player, String msg, String color) {
		//if (ConfigurationHandler.msg == true) {
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] " + ColorHelper.colorEveryWord(msg, color)));
		//}
	}
		
	public static void msgCleanBypass(EntityPlayer player, String msg, String color) {
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] " + ColorHelper.colorEveryWord(msg, color)));
	}
	
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
		if (ConfigurationHandler.effect.getBoolean(true)) {
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
		if (ConfigurationHandler.effect == true) {
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
	
	public static void msg(EntityPlayer player, String msg, String color) {
		if (ConfigurationHandler.msg == true) {
			World world = player.worldObj;
		
			if (world.isRemote) { //IF CLIENT	
				player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] " + ColorHelper.colorEveryWord(msg, color)));
			}
		}
	}
	
	public static void keepBlocks(World world, int x, int y, int z, Block block) {
		if (ConfigurationHandler.keepBlocks == true) {
			BuildHelper.setBlockIfNoBedrock(world, x, y, z, block);
		} else {
			//Do not keep block.
		}
	}
	
	public void tp(World world, EntityPlayer player, int x, int y, int z, boolean property) {
		if (!world.isRemote) { //IF SERVER
			if (property) {
				IBHelper.sound(world, ConfigurationHandler.sound, x, y, z);
				player.setPositionAndUpdate(x + 0.5, y + 0.5, z + 0.5);
			}
		}
	}
	
	public static void addItemsToChest(TileEntityChest chest, Block block, int amount, int meta) {
		addItemsToChest(chest, Item.getItemFromBlock(block), amount, meta);
	}
	
	public static void addItemsToChest(TileEntityChest chest, Item item, int amount, int meta) {
		for (int i=0; i<chest.getSizeInventory(); i++) {
			ItemStack is = new ItemStack(item, amount, meta);
			
			//LogHelper.info("is == " + is + " || " + "stackInSlot["+i+"] " + chest.getStackInSlot(i));
			
			if (chest.getStackInSlot(i) != null && chest.getStackInSlot(i).getItem() == is.getItem() && chest.getStackInSlot(i).getItemDamage() == is.getItemDamage() && chest.getStackInSlot(i).stackSize < 64) {
				chest.setInventorySlotContents(i, new ItemStack(item, amount + chest.getStackInSlot(i).stackSize, meta));
				//LogHelper.info("Added to existing stack. == " + is);
				break;
			} else if (chest.getStackInSlot(i) != null && chest.getStackInSlot(i).getItem() == is.getItem() && chest.getStackInSlot(i).getItemDamage() == is.getItemDamage() && chest.getStackInSlot(i).stackSize > 64) {
				chest.setInventorySlotContents(i+1, new ItemStack(item, amount, meta));
				//LogHelper.info("Created new stack because old stack full. == " + is);
				break;
			} else if (chest.getStackInSlot(i) == null) {
				chest.setInventorySlotContents(i, new ItemStack(item, amount, meta));
				//LogHelper.info("Created new stack because no similar stacks. == " + is);
				break;
			} else {
				//LogHelper.info("Chest full. == " + is);
			}
		}
	}
}
