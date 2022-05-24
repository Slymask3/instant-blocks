package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.handler.Config;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

import java.util.ArrayList;
import java.util.Iterator;

public class Loot {
	private static ArrayList<Block> blocks;

	private static void setupBlocks() {
		blocks = new ArrayList<>();
		add(ModBlocks.ibWood,Config.ADD_WOODEN_HOUSE);
		add(ModBlocks.ibLadder,Config.ADD_MINING_LADDER);
		add(ModBlocks.ibGlassDome,Config.ADD_GLASS_DOME);
		add(ModBlocks.ibFarm,Config.ADD_FARM);
		add(ModBlocks.ibFall,Config.ADD_SKYDIVE);
		add(ModBlocks.ibGrinder,Config.ADD_GRINDER);
		add(ModBlocks.ibPool,Config.ADD_POOL);
		add(ModBlocks.ibUp,Config.ADD_ESCAPE_LADDER);
		add(ModBlocks.ibWater,Config.ADD_WATER);
		add(ModBlocks.ibLava,Config.ADD_LAVA);
		add(ModBlocks.ibSucker,Config.ADD_SUCTION);
		add(ModBlocks.ibRail,Config.ADD_RAIL);
		add(ModBlocks.ibStatue,Config.ADD_STATUE);
		add(ModBlocks.ibHarvest,Config.ADD_HARVEST);
		add(ModBlocks.ibLight,Config.ADD_LIGHT);
		add(ModBlocks.ibSchematic,Config.ADD_SCHEMATIC);
		add(ModBlocks.ibTree,Config.ADD_TREE);
	}

	private static void add(Block block, Boolean add) {
		if(add) {
			blocks.add(block);
		}
	}
	
	public static void init() {
		setupBlocks();

		if(blocks.size() > 0) {
			Iterator<Block> iterator = blocks.iterator();

			if(Config.GENERATE_IN_CHESTS_BONUS) {
				if(Config.ADD_WOODEN_HOUSE) {
					ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(ModBlocks.ibWood), 1, 1, 4));
				}
				ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.ibWandWood), 1, 1, 4));
				ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.ibWandStone), 1, 1, 4));
			}

			if(Config.GENERATE_IN_CHESTS_DUNGEON) {
				while(iterator.hasNext()) {
					ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(iterator.next()), 1, 1, 1));
				}
			}

			if(Config.GENERATE_IN_CHESTS_VILLAGE) {
				while(iterator.hasNext()) {
					ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(iterator.next()), 1, 1, 1));
				}
			}

			if(Config.GENERATE_IN_CHESTS_MINESHAFT) {
				while(iterator.hasNext()) {
					ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(iterator.next()), 1, 1, 1));
				}
			}

			if(Config.GENERATE_IN_CHESTS_STRONGHOLD) {
				while(iterator.hasNext()) {
					ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(iterator.next()), 1, 1, 1));
					ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(iterator.next()), 1, 1, 1));
					ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(iterator.next()), 1, 1, 1));
				}
			}

			if(Config.GENERATE_IN_CHESTS_TEMPLE) {
				while(iterator.hasNext()) {
					ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(iterator.next()), 1, 1, 1));
					ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(iterator.next()), 1, 1, 1));
					ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(iterator.next()), 1, 1, 1));
				}
			}
		}
	}
}
