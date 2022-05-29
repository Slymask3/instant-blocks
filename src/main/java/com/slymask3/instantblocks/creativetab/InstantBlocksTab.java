package com.slymask3.instantblocks.creativetab;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class InstantBlocksTab {
	public static final CreativeTabs INSTANTBLOCKS_TAB = new CreativeTabs(Reference.MOD_ID) {
		@Override
		public Item getTabIconItem() {
			Item item;
			if(Config.ADD_WOODEN_HOUSE) {
				item = Item.getItemFromBlock(ModBlocks.ibWoodHouse);
			} else if(Config.ADD_MINING_LADDER) {
				item = Item.getItemFromBlock(ModBlocks.ibMiningLadder);
			} else if(Config.ADD_GLASS_DOME) {
				item = Item.getItemFromBlock(ModBlocks.ibGlassDome);
			} else if(Config.ADD_FARM) {
				item = Item.getItemFromBlock(ModBlocks.ibFarm);
			} else if(Config.ADD_SKYDIVE) {
				item = Item.getItemFromBlock(ModBlocks.ibSkydive);
			} else if(Config.ADD_GRINDER) {
				item = Item.getItemFromBlock(ModBlocks.ibGrinder);
			} else if(Config.ADD_POOL) {
				item = Item.getItemFromBlock(ModBlocks.ibPool);
			} else if(Config.ADD_ESCAPE_LADDER) {
				item = Item.getItemFromBlock(ModBlocks.ibUp);
			} else if(Config.ADD_WATER) {
				item = Item.getItemFromBlock(ModBlocks.ibWater);
			} else if(Config.ADD_LAVA) {
				item = Item.getItemFromBlock(ModBlocks.ibLava);
			} else if(Config.ADD_SUCTION) {
				item = Item.getItemFromBlock(ModBlocks.ibSuction);
			} else if(Config.ADD_RAIL) {
				item = Item.getItemFromBlock(ModBlocks.ibRail);
			} else if(Config.ADD_STATUE) {
				item = Item.getItemFromBlock(ModBlocks.ibStatue);
			} else if(Config.ADD_HARVEST) {
				item = Item.getItemFromBlock(ModBlocks.ibHarvest);
			} else if(Config.ADD_LIGHT) {
				item = Item.getItemFromBlock(ModBlocks.ibLight);
			} else if(Config.ADD_SCHEMATIC) {
				item = Item.getItemFromBlock(ModBlocks.ibSchematic);
			} else if(Config.ADD_TREE) {
				item = Item.getItemFromBlock(ModBlocks.ibTree);
			} else {
				item = ModItems.ibWandIron;
			}
			return item;
		}
	};
}