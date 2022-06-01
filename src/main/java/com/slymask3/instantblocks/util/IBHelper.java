package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class IBHelper {
	public static boolean isClient(Level world) {
		return world.isClientSide();
	}

	public static boolean isServer(Level world) {
		return !world.isClientSide();
	}

	public static void xp(Level world, Player player, int xpAmount) {
		if(isServer(world)) {
			if(xpAmount > 0) {
				player.giveExperiencePoints(xpAmount);
			}
		}
	}

	public static void sound(Level world, String sound, int x, int y, int z) {
		world.playSound(Minecraft.getInstance().player, new BlockPos(x,y,z), SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS,2.0F,1.0F);
	}

	public static void effectFull(Level world, String particle, int x, int y, int z) {
		if(Config.Client.SHOW_EFFECTS.get()) {
			for(double i = 0; i <= 1; i = i + 0.1) {
				for(double n = 0; n <= 1; n = n + 0.1) {
					for(double v = 0; v <= 1; v = v + 0.1) {
						world.addParticle(DustParticleOptions.REDSTONE,x+i, y+v, z+n, 0.0D, 0.0D, 0.0D);
					}
				}
			}
		}
	}
	
	public static void msg(Player player, String msg, String color) {
		if(Config.Client.SHOW_MESSAGES.get()) {
			Level world = player.getLevel();
			if(isClient(world)) {
				player.sendMessage(new TextComponent(Strings.PREFIX + Colors.colorEveryWord(msg, color)),player.getUUID());
			}
		}
	}
	
	public static void keepBlocks(Level world, int x, int y, int z, Block block) {
		if(Config.Common.KEEP_BLOCKS.get()) {
			BuildHelper.setBlock(world, x, y, z, block);
		}
	}
	
	public static void teleport(Level world, Player player, int x, int y, int z, boolean allow) {
		if(isServer(world) && allow) {
			sound(world, "portal.trigger", x, y, z);
			player.teleportTo(x + 0.5, y + 0.5, z + 0.5);
		}
	}
	
//	public static void addItemsToChest(TileEntityChest chest, Block block, int amount, int meta) {
//		addItemsToChest(chest, Item.getItemFromBlock(block), amount, meta);
//	}
	
//	public static void addItemsToChest(TileEntityChest chest, Item item, int amount, int meta) {
//		for(int i=0; i<chest.getSizeInventory(); i++) {
//			ItemStack is = new ItemStack(item, amount, meta);
//			if(chest.getStackInSlot(i) != null && chest.getStackInSlot(i).getItem() == is.getItem() && chest.getStackInSlot(i).getItemDamage() == is.getItemDamage() && chest.getStackInSlot(i).stackSize < 64) {
//				chest.setInventorySlotContents(i, new ItemStack(item, amount + chest.getStackInSlot(i).stackSize, meta));
//				break;
//			} else if(chest.getStackInSlot(i) != null && chest.getStackInSlot(i).getItem() == is.getItem() && chest.getStackInSlot(i).getItemDamage() == is.getItemDamage() && chest.getStackInSlot(i).stackSize > 64) {
//				chest.setInventorySlotContents(i+1, new ItemStack(item, amount, meta));
//				break;
//			} else if(chest.getStackInSlot(i) == null) {
//				chest.setInventorySlotContents(i, new ItemStack(item, amount, meta));
//				break;
//			}
//		}
//	}

	public static boolean isWand(ItemStack is) {
		Item item = is.getItem();
		return is != null && item == ModItems.ibWandGold.get();
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

//	public static boolean isDoubleChest(TileEntityChest chest) {
//		chest.checkForAdjacentChests();
//		return chest.adjacentChestXNeg != null || chest.adjacentChestXPos != null || chest.adjacentChestZNeg != null || chest.adjacentChestZPos != null;
//	}
}