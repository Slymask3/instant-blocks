package com.slymask3.instantblocks.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Colors;

public class ItemBlockInstantBlocks extends ItemBlock {
	public Block block;
	
	public ItemBlockInstantBlocks(Block b) {
         super(b);
         block = b;
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
		if (block == ModBlocks.ibWood) {
			list.add("Creates a simple wooden house.");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibLadder) {
			list.add("Creates a ladder down to layer 12.");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibGlassDome) {
			list.add("Creates a simple glass dome.");
			list.add("Useful for underwater.");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibFarm) {
			list.add("Creates a simple farm with wheat.");
			list.add("5% chance with carrots.");
			list.add("5% chance with potatoes.");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibFall) {
			list.add("Creates a structure from layer");
			list.add("1 to 256 out of coloured wool.");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibGrinder) {
			list.add("Creates a simple grinder.");
			list.add("Place above a Zombie/Skeleton spawner.");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibPool) {
			list.add("Creates a simple pool.");
		} else if (block == ModBlocks.ibUp) {
			list.add("Creates a ladder to the surface.");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibWater) {
			list.add("Fills an area with water.");
			list.add("Maximum: " + ConfigurationHandler.max + " Water Blocks.");
			if (ConfigurationHandler.simpleWL == true) {
				list.add("Mode: Simple.");
			} else {
				list.add("Mode: Full.");
			}
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibLava) {
			list.add("Fills an area with lava.");
			list.add("Maximum: " + ConfigurationHandler.max + " Lava Blocks.");
			if (ConfigurationHandler.simpleWL == true) {
				list.add("Mode: Simple.");
			} else {
				list.add("Mode: Full.");
			}
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibSucker) {
			list.add("Sucks in water/lava.");
			list.add("Maximum: " + ConfigurationHandler.maxSuck + ".");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibRail) {
			list.add("Creates a railway going forward 37 blocks.");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibStatue) {
			list.add("Creates a statue of any Minecraft player.");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibHarvest) {
			list.add("Harvests renewable resources in a radius.");
			list.add("Radius: " + ConfigurationHandler.radiusHarvest);
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibLight) {
			list.add("Lights up dark areas in a radius.");
			list.add("Radius: " + ConfigurationHandler.radiusLight);
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.ibSchematic) {
			list.add("Creates a structure from any schematic file.");
			list.add("Right-click to activate.");
		} else if (block == ModBlocks.color) {
			list.add("This block is used by Instant Skydive and Statue.");
			list.add("When placed, will generate with a random color.");
			list.add(Colors.c+"CREATIVE MODE ONLY");
		} else if (block == ModBlocks.colorLadder) {
			list.add("This block is used by Instant Skydive.");
			list.add("When placed, will generate with a random color.");
			list.add(Colors.c+"CREATIVE MODE ONLY");
		} else if (block == ModBlocks.skydiveTP) {
			list.add("This block is used by Instant Skydive.");
			list.add("It simply teleports you up to layer 256.");
			list.add(Colors.c+"CREATIVE MODE ONLY");
		} else {
			list.add("Error loading description.");
		}
	}
}