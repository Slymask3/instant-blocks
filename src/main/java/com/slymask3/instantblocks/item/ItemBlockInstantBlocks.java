package com.slymask3.instantblocks.item;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;

public class ItemBlockInstantBlocks extends ItemBlock {
	public InstantBlocks ib = new InstantBlocks();
	private ConfigurationHandler config = new ConfigurationHandler();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
	public Block block;
	
	public ItemBlockInstantBlocks(Block b) {
         super(b);
         block = b;
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
		//if (block == config.idWood - 256) {
		if (block == mb.ibWood) {
			list.add("Creates a simple wooden house.");
			list.add("Right-click to activate.");
		} else if (block == mb.ibLadder) {
			list.add("Creates a ladder down to layer 12.");
			list.add("Right-click to activate.");
		} else if (block == mb.ibGlassDome) {
			list.add("Creates a simple glass dome.");
			list.add("Useful for underwater.");
			list.add("Right-click to activate.");
		} else if (block == mb.ibFarm) {
			list.add("Creates a simple farm with wheat.");
			list.add("5% chance with carrots.");
			list.add("5% chance with potatoes.");
			list.add("Right-click to activate.");
		} else if (block == mb.ibFall) {
			list.add("Creates a structure from layer");
			list.add("1 to 256 out of coloured wool.");
			list.add("Right-click to activate.");
		} else if (block == mb.ibGrinder) {
			list.add("Creates a simple grinder.");
			list.add("Place above a Zombie/Skeleton spawner.");
			list.add("Right-click to activate.");
		} else if (block == mb.ibPool) {
			list.add("Creates a simple pool.");
		} else if (block == mb.ibUp) {
			list.add("Creates a ladder to the surface.");
			list.add("Right-click to activate.");
		} else if (block == mb.ibWater) {
			list.add("Fills an area with water.");
			list.add("Maximum: " + config.max + " Water Blocks.");
			if (config.simpleWL == true) {
				list.add("Mode: Simple.");
			} else {
				list.add("Mode: Full.");
			}
			list.add("Right-click to activate.");
		} else if (block == mb.ibLava) {
			list.add("Fills an area with lava.");
			list.add("Maximum: " + config.max + " Lava Blocks.");
			if (config.simpleWL == true) {
				list.add("Mode: Simple.");
			} else {
				list.add("Mode: Full.");
			}
			list.add("Right-click to activate.");
		} else if (block == mb.ibSucker) {
			list.add("Sucks in water/lava.");
			list.add("Maximum: " + config.maxSuck + ".");
			list.add("Right-click to activate.");
		} else if (block == mb.ibRail) {
			list.add("Creates a railway going forward 37 blocks.");
			list.add("Right-click to activate.");
		} else if (block == mb.ibStatue) {
			list.add("Creates a statue of any Minecraft player.");
			list.add("Right-click to activate.");
		} else if (block == mb.color) {
			list.add("CREATIVE ONLY");
		} else {
			list.add("Error loading description.");
		}

	}
}