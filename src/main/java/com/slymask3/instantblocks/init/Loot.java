package com.slymask3.instantblocks.init;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

import com.slymask3.instantblocks.handler.ConfigurationHandler;

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
		if (ConfigurationHandler.bonusChest.getBoolean(true)) {
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(ModBlocks.ibWood), 1, 1, 4));
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.ibWandWood), 1, 1, 4));
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.ibWandStone), 1, 1, 4));
		}

		if (ConfigurationHandler.dungeonChests.getBoolean(true)) {
			for (int i=0; i<blocks.length; i++) {
				ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
			}
		}

		if (ConfigurationHandler.villageChests.getBoolean(true)) {
			for (int i=0; i<blocks.length; i++) {
				ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
			}
		}

		if (ConfigurationHandler.mineshaftChests.getBoolean(true)) {
			for (int i=0; i<blocks.length; i++) {
				ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
			}
		}

		if (ConfigurationHandler.strongholdChests.getBoolean(true)) {
			for (int i=0; i<blocks.length; i++) {
				ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
				ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
				ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
			}
		}

		if (ConfigurationHandler.templeChests.getBoolean(true)) {
			for (int i=0; i<blocks.length; i++) {
				ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
				ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
				ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(blocks[i]), 1, 1, 1));
			}
		}
	}
}
