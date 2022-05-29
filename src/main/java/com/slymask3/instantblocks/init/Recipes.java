package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.handler.Config;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {
	public static void init() {
		if(Config.ALLOW_CRAFTING) {
			if(Config.ADD_WOODEN_HOUSE) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibWoodHouse, 1), new Object[] {
					"WVW",
					"GBG",	//Wooden House
					"LDL",
					Character.valueOf('W'), new ItemStack(Blocks.planks, 1, 1),
					Character.valueOf('V'), new ItemStack(Blocks.planks, 1, 2),
					Character.valueOf('G'), Blocks.glass,
					Character.valueOf('B'), Items.bed,
					Character.valueOf('L'), new ItemStack(Blocks.log, 1, 2),
					Character.valueOf('D'), Items.wooden_door
				});
			}

			if(Config.ADD_MINING_LADDER) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibMiningLadder, 1), new Object[] {
					"LST",
					"LSI",	//Mining Ladder
					"LSW",
					Character.valueOf('L'), Blocks.ladder,
					Character.valueOf('S'), Blocks.stone,
					Character.valueOf('T'), Blocks.torch,
					Character.valueOf('I'), Items.sign,
					Character.valueOf('W'), Items.water_bucket
				});
			}

			if(Config.ADD_GLASS_DOME) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibGlassDome, 1), new Object[] {
					"GGG",
					"GTG",	//Glass Dome
					"SSS",
					Character.valueOf('G'), Blocks.glass,
					Character.valueOf('T'), Blocks.torch,
					Character.valueOf('S'), Blocks.stone
				});
			}

			if(Config.ADD_FARM) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibFarm, 1), new Object[] {
					"S S",
					"DWD", //Farm
					"BBB",
					Character.valueOf('S'), Items.wheat_seeds,
					Character.valueOf('D'), Blocks.dirt,
					Character.valueOf('W'), Items.water_bucket,
					Character.valueOf('B'), new ItemStack(Blocks.stonebrick, 1, 0)
				});
			}

			if(Config.ADD_SKYDIVE) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibSkydive, 1), new Object[] {
					"WWW",
					"WDW", //Rainbow Skydive
					"WWW",
					Character.valueOf('W'), Blocks.wool,
					Character.valueOf('D'), Items.diamond
				});
			}

			if(Config.ADD_GRINDER) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibGrinder, 1), new Object[] {
					"WPW",
					"GDG", //Grinder
					"CBC",
					Character.valueOf('W'), Items.water_bucket,
					Character.valueOf('P'), new ItemStack(Items.iron_pickaxe, 1, 0),
					Character.valueOf('G'), Blocks.glass,
					Character.valueOf('D'), Items.wooden_door,
					Character.valueOf('C'), Blocks.chest,
					Character.valueOf('B'), Blocks.crafting_table
				});
			}

			if(Config.ADD_POOL) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibPool, 1), new Object[] {
					"S S",
					"GWG", //Pool
					"SGS",
					Character.valueOf('S'), new ItemStack(Blocks.stone_slab, 1, 0),
					Character.valueOf('G'), Blocks.glowstone,
					Character.valueOf('W'), Items.water_bucket
				});
			}

			if(Config.ADD_ESCAPE_LADDER) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibUp, 1), new Object[] {
					"SLS",
					"SLS", //Escape Ladder
					"SLS",
					Character.valueOf('S'), Blocks.stone,
					Character.valueOf('L'), Blocks.ladder
				});
			}

			if(Config.ADD_WATER) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibWater, 2), new Object[] {
					"LLL",
					"LDL", //Water
					"LLL",
					Character.valueOf('D'), Items.diamond,
					Character.valueOf('L'), Items.water_bucket
				});
			}

			if(Config.ADD_LAVA) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibLava, 2), new Object[] {
					"LLL",
					"LDL", //Lava
					"LLL",
					Character.valueOf('D'), Items.diamond,
					Character.valueOf('L'), Items.lava_bucket
				});
			}

			if(Config.ADD_SUCTION) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibSuction, 2), new Object[] {
					"III",
					"IHI", //Absorb
					"III",
					Character.valueOf('H'), Blocks.hopper,
					Character.valueOf('I'), Items.iron_ingot
				});
			}

			if(Config.ADD_RAIL) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibRail, 1), new Object[] {
					"I I",
					"ISI", //Rail
					"IBI",
					Character.valueOf('I'), Items.iron_ingot,
					Character.valueOf('B'), Blocks.iron_block,
					Character.valueOf('S'), Items.stick
				});
			}

			if(Config.ADD_STATUE) {
				GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 3), new Object[] {
					"LFL",
					"FBF", //Steve Head
					"LFL",
					Character.valueOf('L'), Items.leather,
					Character.valueOf('F'), Items.rotten_flesh,
					Character.valueOf('B'), Items.bone
				});
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibStatue, 1), new Object[] {
					"WWW",
					"WHW", //Statue
					"WWW",
					Character.valueOf('H'), new ItemStack(Items.skull, 1, 3),
					Character.valueOf('W'), Blocks.wool
				});
			}

			if(Config.ADD_HARVEST) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibHarvest, 1), new Object[] {
					" H ",
					"AWA", //Harvest
					" H ",
					Character.valueOf('H'), new ItemStack(Items.iron_hoe, 1, 0),
					Character.valueOf('A'), new ItemStack(Items.iron_axe, 1, 0),
					Character.valueOf('W'), Blocks.crafting_table
				});
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibHarvest, 1), new Object[] {
					" A ",
					"HWH", //Harvest
					" A ",
					Character.valueOf('H'), new ItemStack(Items.iron_hoe, 1, 0),
					Character.valueOf('A'), new ItemStack(Items.iron_axe, 1, 0),
					Character.valueOf('W'), Blocks.crafting_table
				});
			}

			if(Config.ADD_LIGHT) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibLight, 1), new Object[] {
					"TTT",
					"TCT", //Light
					"TTT",
					Character.valueOf('T'), Blocks.torch,
					Character.valueOf('C'), Blocks.coal_block
				});
			}

			if(Config.ADD_SCHEMATIC) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibSchematic, 1), new Object[] {
					"LDL",
					"LPL", //Schematic
					"WWW",
					Character.valueOf('L'), new ItemStack(Items.dye, 1, 4),
					Character.valueOf('D'), Items.diamond,
					Character.valueOf('P'), Items.paper,
					Character.valueOf('W'), new ItemStack(Blocks.planks, 1, 5),
				});
			}

			if(Config.ADD_TREE) {
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibTree, 1), new Object[] {
					"LLL",
					"WSW", //Tree
					"BBB",
					Character.valueOf('L'), Blocks.leaves,
					Character.valueOf('W'), Blocks.log,
					Character.valueOf('S'), Blocks.sapling,
					Character.valueOf('B'), new ItemStack(Items.dye, 1, 15),
				});
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibTree, 1), new Object[] {
					"LLL",
					"WSW", //Tree
					"BBB",
					Character.valueOf('L'), Blocks.leaves2,
					Character.valueOf('W'), Blocks.log,
					Character.valueOf('S'), Blocks.sapling,
					Character.valueOf('B'), new ItemStack(Items.dye, 1, 15),
				});
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibTree, 1), new Object[] {
					"LLL",
					"WSW", //Tree
					"BBB",
					Character.valueOf('L'), Blocks.leaves,
					Character.valueOf('W'), Blocks.log2,
					Character.valueOf('S'), Blocks.sapling,
					Character.valueOf('B'), new ItemStack(Items.dye, 1, 15),
				});
				GameRegistry.addRecipe(new ItemStack(ModBlocks.ibTree, 1), new Object[] {
					"LLL",
					"WSW", //Tree
					"BBB",
					Character.valueOf('L'), Blocks.leaves2,
					Character.valueOf('W'), Blocks.log2,
					Character.valueOf('S'), Blocks.sapling,
					Character.valueOf('B'), new ItemStack(Items.dye, 1, 15),
				});
			}

			////////////////// WANDS ////////////////////
			
			GameRegistry.addRecipe(new ItemStack(ModItems.ibWandWood, 1), new Object[] {
				" M ",
				"MRM", //Wooden Wand
				" S ",
				Character.valueOf('M'), Blocks.planks,
				Character.valueOf('R'), Blocks.redstone_block,
				Character.valueOf('S'), Items.stick
			});
			
			GameRegistry.addRecipe(new ItemStack(ModItems.ibWandStone, 1), new Object[] {
				" M ",
				"MRM", //Stone Wand
				" S ",
				Character.valueOf('M'), Blocks.cobblestone,
				Character.valueOf('R'), Blocks.redstone_block,
				Character.valueOf('S'), Items.stick
			});
			
			GameRegistry.addRecipe(new ItemStack(ModItems.ibWandIron, 1), new Object[] {
				" M ",
				"MRM", //Iron Wand
				" S ",
				Character.valueOf('M'), Items.iron_ingot,
				Character.valueOf('R'), Blocks.redstone_block,
				Character.valueOf('S'), Items.stick
			});
			
			GameRegistry.addRecipe(new ItemStack(ModItems.ibWandGold, 1), new Object[] {
				" M ",
				"MRM", //Golden Wand
				" S ",
				Character.valueOf('M'), Items.gold_ingot,
				Character.valueOf('R'), Blocks.redstone_block,
				Character.valueOf('S'), Items.stick
			});
			
			GameRegistry.addRecipe(new ItemStack(ModItems.ibWandDiamond, 1), new Object[] {
				" M ",
				"MRM", //Diamond Wand
				" S ",
				Character.valueOf('M'), Items.diamond,
				Character.valueOf('R'), Blocks.redstone_block,
				Character.valueOf('S'), Items.stick
			});
		}
	}
}