package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.item.ItemInstantWand;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class IBHelper {
	public static boolean isClient(World world) {
		return world.isRemote;
	}

	public static boolean isServer(World world) {
		return !world.isRemote;
	}

	public static void xp(World world, EntityPlayer player, int xpAmount) {
		if(isServer(world)) {
			if(xpAmount > 0) {
				EntityXPOrb xp = new EntityXPOrb(world, player.posX, player.posY, player.posZ, xpAmount);
				world.spawnEntityInWorld(xp);
			}
		}
	}

	public static void sound(World world, String sound, int x, int y, int z) {
		world.playSound(x+0.5D, y+0.5D, z+0.5D, sound, 2.0F, 1.0F, false);
	}

	public static void effectFull(World world, String particle, int x, int y, int z) {
		if(Config.SHOW_EFFECTS) {
			for(double i = 0; i <= 1; i = i + 0.1) {
				for(double n = 0; n <= 1; n = n + 0.1) {
					for(double v = 0; v <= 1; v = v + 0.1) {
						world.spawnParticle(particle, x+i, y+v, z+n, 0.0D, 0.0D, 0.0D);
					}
				}
			}
		}
	}
	
	public static void msg(EntityPlayer player, String msg, String color) {
		if(Config.SHOW_MESSAGES) {
			World world = player.worldObj;
			if(isClient(world)) {
				player.addChatMessage(new ChatComponentText(Strings.PREFIX + Colors.colorEveryWord(msg, color)));
			}
		}
	}
	
	public static void keepBlocks(World world, int x, int y, int z, Block block) {
		if(Config.KEEP_BLOCKS) {
			BuildHelper.setBlock(world, x, y, z, block);
		}
	}
	
	public static void teleport(World world, EntityPlayer player, int x, int y, int z, boolean allow) {
		if(isServer(world) && allow) {
			sound(world, "portal.trigger", x, y, z);
			player.setPositionAndUpdate(x + 0.5, y + 0.5, z + 0.5);
		}
	}
	
	public static void addItemsToChest(TileEntityChest chest, Block block, int amount, int meta) {
		addItemsToChest(chest, Item.getItemFromBlock(block), amount, meta);
	}
	
	public static void addItemsToChest(TileEntityChest chest, Item item, int amount, int meta) {
		for(int i=0; i<chest.getSizeInventory(); i++) {
			ItemStack is = new ItemStack(item, amount, meta);
			if(chest.getStackInSlot(i) != null && chest.getStackInSlot(i).getItem() == is.getItem() && chest.getStackInSlot(i).getItemDamage() == is.getItemDamage() && chest.getStackInSlot(i).stackSize < 64) {
				chest.setInventorySlotContents(i, new ItemStack(item, amount + chest.getStackInSlot(i).stackSize, meta));
				break;
			} else if(chest.getStackInSlot(i) != null && chest.getStackInSlot(i).getItem() == is.getItem() && chest.getStackInSlot(i).getItemDamage() == is.getItemDamage() && chest.getStackInSlot(i).stackSize > 64) {
				chest.setInventorySlotContents(i+1, new ItemStack(item, amount, meta));
				break;
			} else if(chest.getStackInSlot(i) == null) {
				chest.setInventorySlotContents(i, new ItemStack(item, amount, meta));
				break;
			}
		}
	}

	public static boolean isWand(ItemStack is) {
		return is != null && is.getItem() instanceof ItemInstantWand;
	}

	public static boolean isPositive(int i) {
		if(i == 0) return true;
		return i >> 31 == 0;
	}

	public static int toPositive(int i) {
		if(i < 0) {
			return -i;
		}
		return i;
	}

	public static boolean isDoubleChest(TileEntityChest chest) {
		chest.checkForAdjacentChests();
		return chest.adjacentChestXNeg != null || chest.adjacentChestXPos != null || chest.adjacentChestZNeg != null || chest.adjacentChestZPos != null;
	}
}