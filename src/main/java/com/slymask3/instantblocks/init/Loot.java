package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.handler.Config;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class Loot {
	
	private static Block[] blocks = {
		ModBlocks.ibWood,
		ModBlocks.ibLadder,
		ModBlocks.ibGlassDome,
		ModBlocks.ibFarm,
		ModBlocks.ibFall,
		ModBlocks.ibGrinder,
		ModBlocks.ibPool,
		ModBlocks.ibUp,
		ModBlocks.ibWater,
		ModBlocks.ibLava,
		ModBlocks.ibSucker,
		ModBlocks.ibRail,
		ModBlocks.ibStatue,
		ModBlocks.ibHarvest,
		ModBlocks.ibLight,
		ModBlocks.ibSchematic
	};
	
	public static void init() {
		if (Config.bonusChest.getBoolean(true)) {
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(ModBlocks.ibWood), 1, 1, 4));
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.ibWandWood), 1, 1, 4));
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.ibWandStone), 1, 1, 4));
		}

		if (Config.dungeonChests.getBoolean(true)) {
			for (int i=0; i<blocks.length; i++) {
				ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
			}
		}

		if (Config.villageChests.getBoolean(true)) {
			for (int i=0; i<blocks.length; i++) {
				ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
			}
		}

		if (Config.mineshaftChests.getBoolean(true)) {
			for (int i=0; i<blocks.length; i++) {
				ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
			}
		}

		if (Config.strongholdChests.getBoolean(true)) {
			for (int i=0; i<blocks.length; i++) {
				ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
				ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
				ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
			}
		}

		if (Config.templeChests.getBoolean(true)) {
			for (int i=0; i<blocks.length; i++) {
				ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
				ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
				ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
			}
		}
	}
}
