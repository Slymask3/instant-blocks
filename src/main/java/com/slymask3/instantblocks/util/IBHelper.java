package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.item.ItemInstantWand;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public class IBHelper {
	public static boolean isClient(Level world) {
		return world.isClientSide();
	}

	public static boolean isServer(Level world) {
		return !world.isClientSide();
	}

	public static void xp(Level world, Player player, int xpAmount) {
		if(xpAmount > 0 && isServer(world)) {
			player.giveExperiencePoints(xpAmount);
		}
	}

	public static void sound(Level world, int x, int y, int z) {
		world.playSound(Minecraft.getInstance().player, new BlockPos(x,y,z), new SoundEvent(new ResourceLocation("minecraft",Config.Client.SOUND.get())), SoundSource.BLOCKS,0.4F,1.0F);
	}

	public static void effectFull(Level world, int x, int y, int z) {
		if(Config.Client.SHOW_EFFECTS.get()) {
			for(double i = 0; i <= 1; i = i + 0.2) {
				for(double n = 0; n <= 1; n = n + 0.2) {
					for(double v = 0; v <= 1; v = v + 0.2) {
						world.addParticle(DustParticleOptions.REDSTONE,x+i, y+v, z+n, 0.0D, 0.0D, 0.0D);
					}
				}
			}
		}
	}
	
	public static void msg(Player player, String msg, String color) {
		if(Config.Client.SHOW_MESSAGES.get() && isClient(player.getLevel())) {
			//player.sendMessage(new TextComponent(Strings.PREFIX + Colors.colorEveryWord(msg, color)),player.getUUID());
			player.displayClientMessage(new TextComponent(Strings.PREFIX + Colors.colorEveryWord(msg, color)),true);
		}
	}
	
	public static void keepBlocks(Level world, int x, int y, int z, Block block) {
		if(Config.Common.KEEP_BLOCKS.get()) {
			BuildHelper.setBlock(world, x, y, z, block);
		}
	}
	
	public static void teleport(Level world, Player player, int x, int y, int z, boolean allow) {
		if(isServer(world) && allow) {
			sound(world, x, y, z);
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
}