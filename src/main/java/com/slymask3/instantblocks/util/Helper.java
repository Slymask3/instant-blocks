package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.item.ItemInstantWand;
import com.slymask3.instantblocks.network.PacketHandler;
import com.slymask3.instantblocks.network.PacketMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public class Helper {
	public static boolean isServer(Level world) {
		return !world.isClientSide();
	}

	public static void xp(Level world, Player player, int xpAmount) {
		if(xpAmount > 0 && isServer(world)) {
			player.giveExperiencePoints(xpAmount);
		}
	}
	
	public static void teleport(Level world, Player player, int x, int y, int z, boolean allow) {
		if(isServer(world) && allow) {
			//sound(world, x, y, z);
			player.teleportTo(x + 0.5, y + 0.5, z + 0.5);
		}
	}
	
	public static void addItemsToChest(ChestBlockEntity chest, Block block, int amount) {
		addItemsToChest(chest, block.asItem(), amount);
	}
	
	public static void addItemsToChest(ChestBlockEntity chest, Item item, int amount) {
		ItemStack itemStack = new ItemStack(item, amount);
		for(int i=0; i<chest.getContainerSize(); i++) {
			ItemStack itemStackSlot = chest.getItem(i);
			if(itemStackSlot.sameItem(itemStack) && itemStackSlot.getCount() < itemStackSlot.getMaxStackSize()) {
				chest.setItem(i, new ItemStack(item, itemStackSlot.getCount() + amount));
				break;
			} else if(chest.getItem(i) == ItemStack.EMPTY) {
				chest.setItem(i, new ItemStack(item, amount));
				break;
			}
		}
	}

	public static boolean isWand(ItemStack is) {
		return is.getItem() instanceof ItemInstantWand;
	}

	public static int wandDamage(ItemStack is) {
		Item item = is.getItem();
		if(ModItems.ibWandWood.get().equals(item)) {
			return 60;
		} else if(ModItems.ibWandStone.get().equals(item)) {
			return 44;
		} else if(ModItems.ibWandIron.get().equals(item)) {
			return 35;
		} else if(ModItems.ibWandGold.get().equals(item)) {
			return 2;
		} else if(ModItems.ibWandDiamond.get().equals(item)) {
			return 30;
		}
		return 1;
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

	public static boolean isDoubleChest(ChestBlockEntity chest) {
		return chest.getContainerSize() == 54;
	}

	public static int getMaxSkydive(Level world) {
		int max = Config.Common.SKYDIVE_MAX.get();
		if(max > world.getMaxBuildHeight() - 3) {
			max = world.getMaxBuildHeight() - 4;
		}
		return max;
	}

	public static void sendMessage(Player player, String message, String color) {
		sendMessage(player, message, color, 0, 0, 0, false);
	}

	public static void sendMessage(Player player, String message, String color, int x, int y, int z) {
		sendMessage(player, message, color, x, y, z, true);
	}

	public static void sendMessage(Player player, String message, String color, int x, int y, int z, boolean effects) {
		if(isServer(player.getLevel())) {
			PacketHandler.sendToClient((ServerPlayer)player,new PacketMessage(message,color,x,y,z,effects));
		}
	}
}