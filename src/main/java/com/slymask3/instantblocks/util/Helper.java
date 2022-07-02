package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.item.InstantWandItem;
import com.slymask3.instantblocks.network.PacketHandler;
import com.slymask3.instantblocks.network.packet.ClientPacket;
import com.slymask3.instantblocks.reference.Names;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;

public class Helper {
	public static boolean isServer(Level world) {
		return !world.isClientSide();
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

	public static int wandDamage(Block block) {
		return switch(block.getDescriptionId().substring(InstantBlocks.MOD_ID.length()+7)) {
			case Names.Blocks.IB_WOOD_HOUSE -> Config.Common.DAMAGE_WOODEN_HOUSE.get();
			case Names.Blocks.IB_MINING_LADDER -> Config.Common.DAMAGE_MINING_LADDER.get();
			case Names.Blocks.IB_GLASS_DOME -> Config.Common.DAMAGE_GLASS_DOME.get();
			case Names.Blocks.IB_FARM -> Config.Common.DAMAGE_FARM.get();
			case Names.Blocks.IB_SKYDIVE -> Config.Common.DAMAGE_SKYDIVE.get();
			case Names.Blocks.IB_GRINDER -> Config.Common.DAMAGE_GRINDER.get();
			case Names.Blocks.IB_POOL -> Config.Common.DAMAGE_POOL.get();
			case Names.Blocks.IB_ESCAPE_LADDER -> Config.Common.DAMAGE_ESCAPE_LADDER.get();
			case Names.Blocks.IB_WATER -> Config.Common.DAMAGE_WATER.get();
			case Names.Blocks.IB_LAVA -> Config.Common.DAMAGE_LAVA.get();
			case Names.Blocks.IB_SUCTION -> Config.Common.DAMAGE_SUCTION.get();
			case Names.Blocks.IB_RAIL -> Config.Common.DAMAGE_RAIL.get();
			case Names.Blocks.IB_STATUE -> Config.Common.DAMAGE_STATUE.get();
			case Names.Blocks.IB_HARVEST -> Config.Common.DAMAGE_HARVEST.get();
			case Names.Blocks.IB_LIGHT -> Config.Common.DAMAGE_LIGHT.get();
			case Names.Blocks.IB_SCHEMATIC -> Config.Common.DAMAGE_SCHEMATIC.get();
			case Names.Blocks.IB_TREE -> Config.Common.DAMAGE_TREE.get();
			default -> 1;
		};
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

	public static int getMinSkydive(Level world) {
		int min = Config.Common.SKYDIVE_MIN.get();
		if(min < world.getMinBuildHeight() - 4) {
			min = world.getMinBuildHeight() - 5;
		}
		return min;
	}

	public static int getMaxSkydive(Level world) {
		int max = Config.Common.SKYDIVE_MAX.get();
		if(max > world.getMaxBuildHeight() - 3) {
			max = world.getMaxBuildHeight() - 4;
		}
		return max;
	}

	public static void sendMessage(Player player, String message) {
		sendMessage(player, message, "", 0, 0, 0, ClientHelper.Particles.NONE);
	}

	public static void sendMessage(Player player, String message, String variable) {
		sendMessage(player, message, variable, 0, 0, 0, ClientHelper.Particles.NONE);
	}

	public static void sendMessage(Player player, String message, String variable, int x, int y, int z) {
		sendMessage(player, message, variable, x, y, z, ClientHelper.Particles.GENERATE);
	}

	public static void sendMessage(Player player, String message, String variable, int x, int y, int z, ClientHelper.Particles particles) {
		if(isServer(player.getLevel())) {
			PacketHandler.sendToClient((ServerPlayer)player,new ClientPacket(message,variable,x,y,z,particles.ordinal()));
		}
	}

	public static Block getRandomBlock(List<WeightedBlock> blocks) {
		Random random = new Random();
		int total = 0;
		for (WeightedBlock block : blocks) {
			total += block.getWeight();
		}
		int r = random.nextInt(total) + 1;
		int count = 0;
		for (WeightedBlock block : blocks) {
			count += block.getWeight();
			if(count >= r) {
				return block.getBlock();
			}
		}
		return blocks.get(0).getBlock();
	}

	public static Block readBlock(String string, Block fallback) {
		return readBlockState(string, fallback.defaultBlockState()).getBlock();
	}

	public static BlockState readBlockState(String string) {
		return readBlockState(string, Blocks.AIR.defaultBlockState());
	}

	public static BlockState readBlockState(String string, BlockState fallback) {
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
		BlockState state = NbtUtils.readBlockState(tag);
		if(state.getBlock().equals(Blocks.AIR)) {
			return fallback;
		}
		return state;
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