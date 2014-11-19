package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.ConfigurationHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	private static ConfigurationHandler config = new ConfigurationHandler();
	private static InstantBlocks ib = new InstantBlocks();
	private static ModBlocks mb = new ModBlocks();
	private static ModItems mi = new ModItems();
	
	public static void init() {
		if (config.crafting.getBoolean(true)) {
			GameRegistry.addRecipe(new ItemStack(mb.ibWood, 1), new Object[] {
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
			
			GameRegistry.addRecipe(new ItemStack(mb.ibLadder, 1), new Object[] {
				"LST",
				"LSI",	//Mining Ladder
				"LSW",
				Character.valueOf('L'), Blocks.ladder,
				Character.valueOf('S'), Blocks.stone,
				Character.valueOf('T'), Blocks.torch,
				Character.valueOf('I'), Items.sign,
				Character.valueOf('W'), Items.water_bucket
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibGlassDome, 1), new Object[] {
				"GGG",
				"GTG",	//Glass Dome
				"SSS",
				Character.valueOf('G'), Blocks.glass,
				Character.valueOf('T'), Blocks.torch,
				Character.valueOf('S'), Blocks.stone
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibFarm, 1), new Object[] {
				"S S",
				"DWD", //Farm
				"BBB",
				Character.valueOf('S'), Items.wheat_seeds,
				Character.valueOf('D'), Blocks.dirt,
				Character.valueOf('W'), Items.water_bucket,
				Character.valueOf('B'), new ItemStack(Blocks.stonebrick, 1, 0)
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibFall, 1), new Object[] {
//				"123",
//				"4D5", //Rainbow Skydive
//				"678",
//				Character.valueOf('1'), new ItemStack(Blocks.wool, 1, 14),
//				Character.valueOf('2'), new ItemStack(Blocks.wool, 1, 1),
//				Character.valueOf('3'), new ItemStack(Blocks.wool, 1, 4),
//				Character.valueOf('4'), new ItemStack(Blocks.wool, 1, 5),
//				Character.valueOf('D'), Items.diamond, 
//				Character.valueOf('5'), new ItemStack(Blocks.wool, 1, 13),
//				Character.valueOf('6'), new ItemStack(Blocks.wool, 1, 9),
//				Character.valueOf('7'), new ItemStack(Blocks.wool, 1, 3),
//				Character.valueOf('8'), new ItemStack(Blocks.wool, 1, 11)
				"WWW",
				"WDW", //Rainbow Skydive
				"WWW",
				Character.valueOf('W'), Blocks.wool,
				Character.valueOf('D'), Items.diamond
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibGrinder, 1), new Object[] {
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
			
			GameRegistry.addRecipe(new ItemStack(mb.ibPool, 1), new Object[] {
				"S S",
				"GWG", //Pool
				"SGS",
				Character.valueOf('S'), new ItemStack(Blocks.stone_slab, 1, 0),
				Character.valueOf('G'), Blocks.glowstone,
				Character.valueOf('W'), Items.water_bucket
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibUp, 1), new Object[] {
				"SLS",
				"SLS", //Escape Ladder
				"SLS",
				Character.valueOf('S'), Blocks.stone,
				Character.valueOf('L'), Blocks.ladder
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibWater, 2), new Object[] {
				"LLL",
				"LDL", //Water
				"LLL",
				Character.valueOf('D'), Items.diamond,
				Character.valueOf('L'), Items.water_bucket
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibLava, 2), new Object[] {
				"LLL",
				"LDL", //Lava
				"LLL",
				Character.valueOf('D'), Items.diamond,
				Character.valueOf('L'), Items.lava_bucket
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibSucker, 2), new Object[] {
				"III",
				"IHI", //Absorb
				"III",
				Character.valueOf('H'), Blocks.hopper,
				Character.valueOf('I'), Items.iron_ingot
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibRail, 1), new Object[] {
				"I I",
				"ISI", //Rail
				"IBI",
				Character.valueOf('I'), Items.iron_ingot,
				Character.valueOf('B'), Blocks.iron_block,
				Character.valueOf('S'), Items.stick
			});
			
			GameRegistry.addRecipe(new ItemStack(Items.skull, 1, 3), new Object[] {
				"LFL",
				"FBF", //Steve Head
				"LFL",
				Character.valueOf('L'), Items.leather,
				Character.valueOf('F'), Items.rotten_flesh,
				Character.valueOf('B'), Items.bone
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibStatue, 1), new Object[] {
				"WWW",
				"WHW", //Statue
				"WWW",
				Character.valueOf('H'), new ItemStack(Items.skull, 1, 3),
				Character.valueOf('W'), Blocks.wool
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibHarvest, 1), new Object[] {
				" H ",
				"AWA", //Harvest
				" H ",
				Character.valueOf('H'), new ItemStack(Items.iron_hoe, 1, 0),
				Character.valueOf('A'), new ItemStack(Items.iron_axe, 1, 0),
				Character.valueOf('W'), Blocks.crafting_table
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibHarvest, 1), new Object[] {
				" A ",
				"HWH", //Harvest
				" A ",
				Character.valueOf('H'), new ItemStack(Items.iron_hoe, 1, 0),
				Character.valueOf('A'), new ItemStack(Items.iron_axe, 1, 0),
				Character.valueOf('W'), Blocks.crafting_table
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibLight, 1), new Object[] {
				"TTT",
				"TCT", //Light
				"TTT",
				Character.valueOf('T'), Blocks.torch,
				Character.valueOf('C'), Blocks.coal_block
			});
			
			GameRegistry.addRecipe(new ItemStack(mb.ibSchematic, 1), new Object[] {
				"LDL",
				"LPL", //Schematic
				"WWW",
				Character.valueOf('L'), new ItemStack(Items.dye, 1, 4),
				Character.valueOf('D'), Items.diamond,
				Character.valueOf('P'), Items.paper,
				Character.valueOf('W'), new ItemStack(Blocks.planks, 1, 5),
			});
			
			////////////////// WANDS ////////////////////
			
			GameRegistry.addRecipe(new ItemStack(mi.ibWandWood, 1), new Object[] {
				" M ",
				"MRM", //Wooden Wand
				" S ",
				Character.valueOf('M'), Blocks.planks,
				Character.valueOf('R'), Blocks.redstone_block,
				Character.valueOf('S'), Items.stick
			});
			
			GameRegistry.addRecipe(new ItemStack(mi.ibWandStone, 1), new Object[] {
				" M ",
				"MRM", //Stone Wand
				" S ",
				Character.valueOf('M'), Blocks.cobblestone,
				Character.valueOf('R'), Blocks.redstone_block,
				Character.valueOf('S'), Items.stick
			});
			
			GameRegistry.addRecipe(new ItemStack(mi.ibWandIron, 1), new Object[] {
				" M ",
				"MRM", //Iron Wand
				" S ",
				Character.valueOf('M'), Items.iron_ingot,
				Character.valueOf('R'), Blocks.redstone_block,
				Character.valueOf('S'), Items.stick
			});
			
			GameRegistry.addRecipe(new ItemStack(mi.ibWandGold, 1), new Object[] {
				" M ",
				"MRM", //Golden Wand
				" S ",
				Character.valueOf('M'), Items.gold_ingot,
				Character.valueOf('R'), Blocks.redstone_block,
				Character.valueOf('S'), Items.stick
			});
			
			GameRegistry.addRecipe(new ItemStack(mi.ibWandDiamond, 1), new Object[] {
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
