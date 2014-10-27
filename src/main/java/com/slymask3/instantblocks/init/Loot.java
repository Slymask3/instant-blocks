package com.slymask3.instantblocks.init;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.ConfigurationHandler;

public class Loot {
	private static ConfigurationHandler config = new ConfigurationHandler();
	private static InstantBlocks ib = new InstantBlocks();
	private static ModBlocks mb = new ModBlocks();
	private static ModItems mi = new ModItems();
	
	public static void init() {
		if (config.bonusChest.getBoolean(true)) {
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibWood), 1, 1, 4));
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(mi.ibWandWood), 1, 1, 4));
			ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(new ItemStack(mi.ibWandStone), 1, 1, 4));
		}

		if (config.dungeonChests.getBoolean(true)) {
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibWood), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibLadder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibGlassDome), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibFarm), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibFall), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibGrinder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibPool), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibUp), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibWater), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibLava), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibSucker), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibStatue), 1, 1, 1));
		}

		if (config.villageChests.getBoolean(true)) {
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibWood), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibLadder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibGlassDome), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibFarm), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibFall), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibGrinder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibPool), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibUp), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibWater), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibLava), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibSucker), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(mb.ibStatue), 1, 1, 1));
		}

		if (config.mineshaftChests.getBoolean(true)) {
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibWood), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibLadder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibGlassDome), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibFarm), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibFall), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibGrinder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibPool), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibUp), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibWater), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibLava), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibSucker), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibStatue), 1, 1, 1));
		}

		if (config.strongholdChests.getBoolean(true)) {
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibWood), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibLadder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibGlassDome), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibFarm), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibFall), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibGrinder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibPool), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibUp), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibWater), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibLava), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibSucker), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(mb.ibStatue), 1, 1, 1));

			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibWood), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibLadder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibGlassDome), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibFarm), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibFall), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibGrinder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibPool), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibUp), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibWater), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibLava), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibSucker), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(mb.ibStatue), 1, 1, 1));

			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibWood), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibLadder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibGlassDome), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibFarm), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibFall), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibGrinder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibPool), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibUp), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibWater), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibLava), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibSucker), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(mb.ibStatue), 1, 1, 1));
		}

		if (config.templeChests.getBoolean(true)) {
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibWood), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibLadder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibGlassDome), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibFarm), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibFall), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibGrinder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibPool), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibUp), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibWater), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibLava), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibSucker), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibStatue), 1, 1, 1));

			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibWood), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibLadder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibGlassDome), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibFarm), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibFall), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibGrinder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibPool), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibUp), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibWater), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibLava), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibSucker), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(mb.ibStatue), 1, 1, 1));

			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibWood), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibLadder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibGlassDome), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibFarm), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibFall), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibGrinder), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibPool), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibUp), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibWater), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibLava), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibSucker), 1, 1, 1));
			ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(mb.ibStatue), 1, 1, 1));
		}
	}
}
