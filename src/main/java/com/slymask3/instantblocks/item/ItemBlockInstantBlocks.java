package com.slymask3.instantblocks.item;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.util.Colors;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockInstantBlocks extends BlockItem {
	public Block block;
	
	public ItemBlockInstantBlocks(Block block) {
         super(block,new Item.Properties().tab(ModItems.ModCreativeTab.instance));
         this.block = block;
	}

	public void appendHoverText(ItemStack is, @Nullable Level world, List<Component> list, TooltipFlag flag) {
		if(block == ModBlocks.ibWoodHouse.get()) {
			list.add(new TextComponent("Creates a simple wooden house."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibMiningLadder.get()) {
			list.add(new TextComponent("Creates a ladder down to layer " + Config.Common.MINING_LADDER_LAYER.get() + "."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibGlassDome.get()) {
			list.add(new TextComponent("Creates a simple glass dome."));
			list.add(new TextComponent("Useful for underwater."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibFarm.get()) {
			list.add(new TextComponent("Creates a simple wheat farm."));
			list.add(new TextComponent("5% chance with carrots."));
			list.add(new TextComponent("5% chance with potatoes."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibSkydive.get()) {
			list.add(new TextComponent("Creates a structure from layer"));
			list.add(new TextComponent(Config.Common.SKYDIVE_MIN.get() + " to " + Config.Common.SKYDIVE_MAX.get() + " out of coloured wool."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibGrinder.get()) {
			list.add(new TextComponent("Creates a simple grinder."));
			list.add(new TextComponent("Place above a Zombie/Skeleton spawner."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibPool.get()) {
			list.add(new TextComponent("Creates a simple pool."));
		} else if(block == ModBlocks.ibEscapeLadder.get()) {
			list.add(new TextComponent("Creates a ladder to the surface."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibWater.get()) {
			list.add(new TextComponent("Fills an area with water."));
			list.add(new TextComponent("Maximum: " + Config.Common.MAX_LIQUID.get() + " Water Blocks."));
			if(Config.Common.SIMPLE_LIQUID.get()) {
				list.add(new TextComponent("Mode: Simple."));
			} else {
				list.add(new TextComponent("Mode: Full."));
			}
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibLava.get()) {
			list.add(new TextComponent("Fills an area with lava."));
			list.add(new TextComponent("Maximum: " + Config.Common.MAX_LIQUID.get() + " Lava Blocks."));
			if(Config.Common.SIMPLE_LIQUID.get()) {
				list.add(new TextComponent("Mode: Simple."));
			} else {
				list.add(new TextComponent("Mode: Full."));
			}
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibSuction.get()) {
			list.add(new TextComponent("Sucks in water/lava."));
			list.add(new TextComponent("Maximum: " + Config.Common.MAX_FILL.get() + "."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibRail.get()) {
			list.add(new TextComponent("Creates a railway going forward " + Config.Common.RAILS_AMOUNT.get() + " blocks."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibStatue.get()) {
			list.add(new TextComponent("Creates a statue of any Minecraft player."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibHarvest.get()) {
			list.add(new TextComponent("Harvests renewable resources in a radius."));
			list.add(new TextComponent("Radius: " + Config.Common.RADIUS_HARVEST.get()));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibLight.get()) {
			list.add(new TextComponent("Lights up dark areas in a radius."));
			list.add(new TextComponent("Radius: " + Config.Common.RADIUS_LIGHT.get()));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibSchematic.get()) {
			list.add(new TextComponent("Creates a structure from any schematic file."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.ibTree.get()) {
			list.add(new TextComponent("Creates a huge tree of any type."));
			list.add(new TextComponent("Right-click to activate."));
		} else if(block == ModBlocks.color.get()) {
			list.add(new TextComponent("This block is used by Instant Skydive and Statue."));
			list.add(new TextComponent("When placed, will generate with a random color."));
			list.add(new TextComponent(Colors.c+"CREATIVE MODE ONLY"));
		} else if(block == ModBlocks.skydiveTP.get()) {
			list.add(new TextComponent("This block is used by Instant Skydive."));
			list.add(new TextComponent("It simply teleports you up to layer " + (Config.Common.SKYDIVE_MAX.get() + 2) + "."));
			list.add(new TextComponent(Colors.c+"CREATIVE MODE ONLY"));
		} else {
			list.add(new TextComponent("Error loading description."));
		}
	}
}