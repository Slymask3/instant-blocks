package com.slymask3.instantblocks.util;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.item.InstantWandItem;
import com.slymask3.instantblocks.network.packet.ClientPacket;
import com.slymask3.instantblocks.reference.Names;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
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

	public static boolean isClient(Level world) {
		return world.isClientSide();
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
		return switch(block.getDescriptionId().substring(Common.MOD_ID.length()+7)) {
			case Names.Blocks.IB_WOOD_HOUSE -> Common.CONFIG.DAMAGE_WOODEN_HOUSE();
			case Names.Blocks.IB_MINING_LADDER -> Common.CONFIG.DAMAGE_MINING_LADDER();
			case Names.Blocks.IB_GLASS_DOME -> Common.CONFIG.DAMAGE_GLASS_DOME();
			case Names.Blocks.IB_FARM -> Common.CONFIG.DAMAGE_FARM();
			case Names.Blocks.IB_SKYDIVE -> Common.CONFIG.DAMAGE_SKYDIVE();
			case Names.Blocks.IB_GRINDER -> Common.CONFIG.DAMAGE_GRINDER();
			case Names.Blocks.IB_POOL -> Common.CONFIG.DAMAGE_POOL();
			case Names.Blocks.IB_ESCAPE_LADDER -> Common.CONFIG.DAMAGE_ESCAPE_LADDER();
			case Names.Blocks.IB_WATER -> Common.CONFIG.DAMAGE_WATER();
			case Names.Blocks.IB_LAVA -> Common.CONFIG.DAMAGE_LAVA();
			case Names.Blocks.IB_SUCTION -> Common.CONFIG.DAMAGE_SUCTION();
			case Names.Blocks.IB_RAIL -> Common.CONFIG.DAMAGE_RAIL();
			case Names.Blocks.IB_STATUE -> Common.CONFIG.DAMAGE_STATUE();
			case Names.Blocks.IB_HARVEST -> Common.CONFIG.DAMAGE_HARVEST();
			case Names.Blocks.IB_LIGHT -> Common.CONFIG.DAMAGE_LIGHT();
			case Names.Blocks.IB_SCHEMATIC -> Common.CONFIG.DAMAGE_SCHEMATIC();
			case Names.Blocks.IB_TREE -> Common.CONFIG.DAMAGE_TREE();
			default -> 1;
		};
	}

	public static boolean isPositive(int i) {
		if(i == 0) return true;
		return i >> 31 == 0;
	}

	public static boolean isDoubleChest(ChestBlockEntity chest) {
		return chest.getContainerSize() == 54;
	}

	public static int getMinSkydive(Level world) {
		int min = Common.CONFIG.SKYDIVE_MIN();
		if(min < world.getMinBuildHeight() - 4) {
			min = world.getMinBuildHeight() - 5;
		}
		return min;
	}

	public static int getMaxSkydive(Level world) {
		int max = Common.CONFIG.SKYDIVE_MAX();
		if(max > world.getMaxBuildHeight() - 3) {
			max = world.getMaxBuildHeight() - 4;
		}
		return max;
	}

	public static void sendMessage(Player player, String message) {
		sendMessage(player, message, "", BlockPos.ZERO, ClientHelper.Particles.NONE);
	}

	public static void sendMessage(Player player, String message, String variable) {
		sendMessage(player, message, variable, BlockPos.ZERO, ClientHelper.Particles.NONE);
	}

	public static void sendMessage(Player player, String message, String variable, BlockPos pos) {
		sendMessage(player, message, variable, pos, ClientHelper.Particles.GENERATE);
	}

	public static void sendMessage(Player player, String message, String variable, BlockPos pos, ClientHelper.Particles particles) {
		if(isServer(player.getLevel())) {
			Common.NETWORK.sendToClient(player,new ClientPacket(message,variable,pos,particles.ordinal()));
		}
	}

	public static Block getBlock(Level world, int x, int y, int z) {
		return getBlock(world,new BlockPos(x,y,z));
	}

	public static Block getBlock(Level world, BlockPos pos) {
		return Builder.Single.setup(world,pos).getBlock();
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

	public static String serializeBlock(Block block) {
		return Registry.BLOCK.getKey(block).toString();
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
}