package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.block.BlockColor;
import com.slymask3.instantblocks.block.BlockSkydiveTP;
import com.slymask3.instantblocks.block.instant.*;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.item.ItemBlockInstantBlocks;
import com.slymask3.instantblocks.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {
	public static Block ibWoodHouse = new BlockInstantHouseWood();
	public static Block ibMiningLadder = new BlockInstantMiningLadder();
	public static Block ibGlassDome = new BlockInstantGlassDome();
	public static Block ibFarm = new BlockInstantFarm();
	public static Block ibSkydive = new BlockInstantSkydive();
	public static Block ibGrinder = new BlockInstantGrinder();
	public static Block ibPool = new BlockInstantPool();
	public static Block ibUp = new BlockInstantEscapeLadder();
	public static Block ibWater = new BlockInstantWater();
	public static Block ibLava = new BlockInstantLava();
	public static Block ibSuction = new BlockInstantSuction();
	public static Block ibRail = new BlockInstantRail();
	public static Block ibStatue = new BlockInstantStatue();
	public static Block ibHarvest = new BlockInstantHarvest();
	public static Block ibLight = new BlockInstantLight();
	public static Block ibSchematic = new BlockInstantSchematic();
	public static Block ibTree = new BlockInstantTree();
	public static Block color = new BlockColor();
	public static Block skydiveTP = new BlockSkydiveTP();
	
	public static void init() {
		add(ibWoodHouse, Names.Blocks.IB_WOOD_HOUSE, Config.ADD_WOODEN_HOUSE);
		add(ibMiningLadder, Names.Blocks.IB_MINING_LADDER, Config.ADD_MINING_LADDER);
		add(ibGlassDome, Names.Blocks.IB_GLASS_DOME, Config.ADD_GLASS_DOME);
		add(ibFarm, Names.Blocks.IB_FARM, Config.ADD_FARM);
		add(ibSkydive, Names.Blocks.IB_SKYDIVE, Config.ADD_SKYDIVE);
		add(ibGrinder, Names.Blocks.IB_GRINDER, Config.ADD_GRINDER);
		add(ibPool, Names.Blocks.IB_POOL, Config.ADD_POOL);
		add(ibUp, Names.Blocks.IB_ESCAPE_LADDER, Config.ADD_ESCAPE_LADDER);
		add(ibWater, Names.Blocks.IB_WATER, Config.ADD_WATER);
		add(ibLava, Names.Blocks.IB_LAVA, Config.ADD_LAVA);
		add(ibSuction, Names.Blocks.IB_SUCTION, Config.ADD_SUCTION);
		add(ibRail, Names.Blocks.IB_RAIL, Config.ADD_RAIL);
		add(ibStatue, Names.Blocks.IB_STATUE, Config.ADD_STATUE);
		add(ibHarvest, Names.Blocks.IB_HARVEST, Config.ADD_HARVEST);
		add(ibLight, Names.Blocks.IB_LIGHT, Config.ADD_LIGHT);
		add(ibSchematic, Names.Blocks.IB_SCHEMATIC, Config.ADD_SCHEMATIC);
		add(ibTree, Names.Blocks.IB_TREE, Config.ADD_TREE);
		add(color, Names.Blocks.COLOR, Config.ADD_SKYDIVE || Config.ADD_STATUE);
		add(skydiveTP, Names.Blocks.SKYDIVE_TP, Config.ADD_SKYDIVE);
	}

	private static void add(Block block, String name, Boolean add) {
		if(add) {
			GameRegistry.registerBlock(block, ItemBlockInstantBlocks.class, name);
		}
	}
}