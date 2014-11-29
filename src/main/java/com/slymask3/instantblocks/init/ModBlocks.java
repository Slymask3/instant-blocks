package com.slymask3.instantblocks.init;

import net.minecraft.block.Block;

import com.slymask3.instantblocks.block.BlockColor;
import com.slymask3.instantblocks.block.BlockColorLadder;
import com.slymask3.instantblocks.block.BlockCraftInstant;
import com.slymask3.instantblocks.block.BlockSkydiveTP;
import com.slymask3.instantblocks.block.instant.BlockInstantFall;
import com.slymask3.instantblocks.block.instant.BlockInstantFarm;
import com.slymask3.instantblocks.block.instant.BlockInstantFlat;
import com.slymask3.instantblocks.block.instant.BlockInstantGlassDome;
import com.slymask3.instantblocks.block.instant.BlockInstantGrinder;
import com.slymask3.instantblocks.block.instant.BlockInstantHarvest;
import com.slymask3.instantblocks.block.instant.BlockInstantHouseWood;
import com.slymask3.instantblocks.block.instant.BlockInstantLadder;
import com.slymask3.instantblocks.block.instant.BlockInstantLava;
import com.slymask3.instantblocks.block.instant.BlockInstantLight;
import com.slymask3.instantblocks.block.instant.BlockInstantPool;
import com.slymask3.instantblocks.block.instant.BlockInstantRail;
import com.slymask3.instantblocks.block.instant.BlockInstantSchematic;
import com.slymask3.instantblocks.block.instant.BlockInstantStatue;
import com.slymask3.instantblocks.block.instant.BlockInstantSuction;
import com.slymask3.instantblocks.block.instant.BlockInstantTree;
import com.slymask3.instantblocks.block.instant.BlockInstantUp;
import com.slymask3.instantblocks.block.instant.BlockInstantWater;
import com.slymask3.instantblocks.item.ItemBlockInstantBlocks;
import com.slymask3.instantblocks.reference.Names;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static Block ibWood = new BlockInstantHouseWood();
	public static Block ibLadder = new BlockInstantLadder();
	public static Block ibGlassDome = new BlockInstantGlassDome();
	public static Block ibFarm = new BlockInstantFarm();
	public static Block ibFall = new BlockInstantFall();
	public static Block ibGrinder = new BlockInstantGrinder();
	public static Block ibPool = new BlockInstantPool();
	public static Block ibUp = new BlockInstantUp();
	public static Block ibWater = new BlockInstantWater();
	public static Block ibLava = new BlockInstantLava();
	public static Block ibSucker = new BlockInstantSuction();
	public static Block ibRail = new BlockInstantRail();
	public static Block ibStatue = new BlockInstantStatue();
	public static Block ibHarvest = new BlockInstantHarvest();
	public static Block ibLight = new BlockInstantLight();
	public static Block ibSchematic = new BlockInstantSchematic();
	public static Block ibTree = new BlockInstantTree();
	public static Block ibFlat = new BlockInstantFlat();
	public static Block color = new BlockColor();
	public static Block colorLadder = new BlockColorLadder();
	public static Block skydiveTP = new BlockSkydiveTP();
	public static Block instantcraft = new BlockCraftInstant();
	
	public static void init() {
		GameRegistry.registerBlock(ibWood, ItemBlockInstantBlocks.class, Names.Blocks.IB_WOOD_HOUSE);
		GameRegistry.registerBlock(ibLadder, ItemBlockInstantBlocks.class, Names.Blocks.IB_MINING_LADDER);
		GameRegistry.registerBlock(ibGlassDome, ItemBlockInstantBlocks.class, Names.Blocks.IB_GLASS_DOME);
		GameRegistry.registerBlock(ibFarm, ItemBlockInstantBlocks.class, Names.Blocks.IB_FARM);
		GameRegistry.registerBlock(ibFall, ItemBlockInstantBlocks.class, Names.Blocks.IB_SKYDIVE);
		GameRegistry.registerBlock(ibGrinder, ItemBlockInstantBlocks.class, Names.Blocks.IB_GRINDER);
		GameRegistry.registerBlock(ibPool, ItemBlockInstantBlocks.class, Names.Blocks.IB_POOL);
		GameRegistry.registerBlock(ibUp, ItemBlockInstantBlocks.class, Names.Blocks.IB_ESCAPE_LADDER);
		GameRegistry.registerBlock(ibWater, ItemBlockInstantBlocks.class, Names.Blocks.IB_WATER);
		GameRegistry.registerBlock(ibLava, ItemBlockInstantBlocks.class, Names.Blocks.IB_LAVA);
		GameRegistry.registerBlock(ibSucker, ItemBlockInstantBlocks.class, Names.Blocks.IB_SUCTION);
		GameRegistry.registerBlock(ibRail, ItemBlockInstantBlocks.class, Names.Blocks.IB_RAIL);
		GameRegistry.registerBlock(ibStatue, ItemBlockInstantBlocks.class, Names.Blocks.IB_STATUE);
		GameRegistry.registerBlock(ibHarvest, ItemBlockInstantBlocks.class, Names.Blocks.IB_HARVEST);
		GameRegistry.registerBlock(ibLight, ItemBlockInstantBlocks.class, Names.Blocks.IB_LIGHT);
		GameRegistry.registerBlock(ibSchematic, ItemBlockInstantBlocks.class, Names.Blocks.IB_SCHEMATIC);
		GameRegistry.registerBlock(ibTree, ItemBlockInstantBlocks.class, Names.Blocks.IB_TREE);
		GameRegistry.registerBlock(ibFlat, ItemBlockInstantBlocks.class, Names.Blocks.IB_FLAT);
		GameRegistry.registerBlock(color, ItemBlockInstantBlocks.class, Names.Blocks.COLOR);
		GameRegistry.registerBlock(colorLadder, ItemBlockInstantBlocks.class, Names.Blocks.COLOR_LADDER);
		GameRegistry.registerBlock(skydiveTP, ItemBlockInstantBlocks.class, Names.Blocks.SKYDIVE_TP);
		GameRegistry.registerBlock(instantcraft, ItemBlockInstantBlocks.class, Names.Blocks.INSTANT_CRAFT);
	}
}