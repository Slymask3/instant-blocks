package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.item.InstantWandItem;
import com.slymask3.instantblocks.network.PacketHandler;
import com.slymask3.instantblocks.network.packet.MessagePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;

public class Helper {
	public static boolean isServer(Level world) {
		return !world.isClientSide();
	}

	public static String translate(String key) {
		return new TranslatableComponent(key).getString();
	}

	public static void giveExp(Level world, Player player, int amount) {
		if(amount > 0 && isServer(world)) {
			player.giveExperiencePoints(amount);
		}
	}
	
	public static void teleport(Level world, Player player, int x, int y, int z) {
		if(isServer(world)) {
			player.teleportTo(x + 0.5, y + 0.5, z + 0.5);
		}
	}
	
	public static void addToChest(ChestBlockEntity chest, Block block, int amount) {
		addToChest(chest, block.asItem(), amount);
	}
	
	public static void addToChest(ChestBlockEntity chest, Item item, int amount) {
		if(chest != null) {
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
	}

	public static boolean isWand(ItemStack is) {
		return is.getItem() instanceof InstantWandItem;
	}

	public static int wandDamage(ItemStack is) {
		Item item = is.getItem();
		if(ModItems.WAND_WOOD.get().equals(item)) {
			return 60;
		} else if(ModItems.WAND_STONE.get().equals(item)) {
			return 44;
		} else if(ModItems.WAND_IRON.get().equals(item)) {
			return 35;
		} else if(ModItems.WAND_GOLD.get().equals(item)) {
			return 2;
		} else if(ModItems.WAND_DIAMOND.get().equals(item)) {
			return 30;
		} else if(ModItems.WAND_NETHERITE.get().equals(item)) {
			return 25;
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

	public static void sendMessage(Player player, String message) {
		sendMessage(player, message, "", 0, 0, 0, false);
	}

	public static void sendMessage(Player player, String message, String variable) {
		sendMessage(player, message, variable, 0, 0, 0, false);
	}

	public static void sendMessage(Player player, String message, String variable, int x, int y, int z) {
		sendMessage(player, message, variable, x, y, z, true);
	}

	public static void sendMessage(Player player, String message, String variable, int x, int y, int z, boolean effects) {
		if(isServer(player.getLevel())) {
			PacketHandler.sendToClient((ServerPlayer)player,new MessagePacket(message,variable,x,y,z,effects));
		}
	}

	public static Block getRandomBlock(List<WeightedBlock> blocks) {
		Random random = new Random();
		int total = 0;
		for (WeightedBlock block : blocks) {
			total += block.getWeight();
		}
		int r = random.nextInt(total) + 1;
		InstantBlocks.LOGGER.info(r);
		int count = 0;
		for (WeightedBlock block : blocks) {
			count += block.getWeight();
			if(count >= r) {
				return block.getBlock();
			}
		}
		return blocks.get(0).getBlock();
	}

	public static BlockState readBlockState(String string) {
		CompoundTag tag = new CompoundTag();
		String[] split = string.split("\\[",2);
		tag.putString("Name",split[0]);
		if(split.length == 2) {
			CompoundTag propertiesTag = new CompoundTag();
			String[] properties = split[1].replace("]","").split(",");
			for(String property : properties) {
				String[] values = property.split("=");
				if(values.length == 2) {
					propertiesTag.putString(values[0],values[1]);
				}
			}
			tag.put("Properties",propertiesTag);
		}
		return NbtUtils.readBlockState(tag);
	}

	public static class WeightedBlock {
		private final Block block;
		private final int weight;
		public WeightedBlock(Block block, int weight) {
			this.block = block;
			this.weight = weight;
		}
		public Block getBlock() {
			return this.block;
		}
		public int getWeight() {
			return this.weight;
		}
	}

	public static class Coords {
		private final int x;
		private final int y;
		private final int z;

		public Coords(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public int getZ() {
			return z;
		}
		public BlockPos getBlockPos() {
			return new BlockPos(x,y,z);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			result = prime * result + z;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if(this == obj)
				return true;
			if(obj == null)
				return false;
			if(getClass() != obj.getClass())
				return false;
			Coords other = (Coords)obj;
			return x == other.getX() && y == other.getY() && z == other.getZ();
		}
	}
}