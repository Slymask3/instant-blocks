package com.slymask3.instantblocks.item;

import com.slymask3.instantblocks.block.InstantBlock;
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

public class InstantBlockItem extends BlockItem {
	public Block block;
	
	public InstantBlockItem(Block block) {
         super(block,new Item.Properties().tab(ModItems.ModCreativeTab.instance));
         this.block = block;
	}

	public void appendHoverText(ItemStack is, @Nullable Level world, List<Component> list, TooltipFlag flag) {
		if(block == ModBlocks.INSTANT_WOOD_HOUSE.get()) {
			list.add(new TextComponent("Creates a small wooden house."));
		} else if(block == ModBlocks.INSTANT_MINING_LADDER.get()) {
			list.add(new TextComponent("Creates a ladder down to layer " + Config.Common.MINING_LADDER_LAYER.get() + "."));
		} else if(block == ModBlocks.INSTANT_GLASS_DOME.get()) {
			list.add(new TextComponent("Creates a small glass dome."));
			list.add(new TextComponent("Useful for underwater."));
		} else if(block == ModBlocks.INSTANT_FARM.get()) {
			list.add(new TextComponent("Creates a small farm."));
		} else if(block == ModBlocks.INSTANT_SKYDIVE.get()) {
			list.add(new TextComponent("Creates a structure from layer"));
			list.add(new TextComponent(Config.Common.SKYDIVE_MIN.get() + " to " + Config.Common.SKYDIVE_MAX.get() + " out of coloured wool."));
		} else if(block == ModBlocks.INSTANT_GRINDER.get()) {
			list.add(new TextComponent("Creates a simple grinder."));
			list.add(new TextComponent("Place above a Zombie/Skeleton spawner."));
		} else if(block == ModBlocks.INSTANT_POOL.get()) {
			list.add(new TextComponent("Creates a small pool."));
		} else if(block == ModBlocks.INSTANT_ESCAPE_LADDER.get()) {
			list.add(new TextComponent("Creates a ladder to the surface."));
		} else if(block == ModBlocks.INSTANT_WATER.get()) {
			list.add(new TextComponent("Fills an area with water."));
			list.add(new TextComponent("Maximum: " + Config.Common.MAX_LIQUID.get() + " Water Blocks."));
			list.add(new TextComponent("Mode: " + (Config.Common.SIMPLE_LIQUID.get() ? "Simple" : "Full") + "."));
		} else if(block == ModBlocks.INSTANT_LAVA.get()) {
			list.add(new TextComponent("Fills an area with lava."));
			list.add(new TextComponent("Maximum: " + Config.Common.MAX_LIQUID.get() + " Lava Blocks."));
			list.add(new TextComponent("Mode: " + (Config.Common.SIMPLE_LIQUID.get() ? "Simple" : "Full") + "."));
		} else if(block == ModBlocks.INSTANT_SUCTION.get()) {
			list.add(new TextComponent("Sucks in water/lava."));
			list.add(new TextComponent("Maximum: " + Config.Common.MAX_FILL.get() + "."));
		} else if(block == ModBlocks.INSTANT_RAIL.get()) {
			list.add(new TextComponent("Creates a railway going forward " + Config.Common.RAILS_AMOUNT.get() + " blocks."));
		} else if(block == ModBlocks.INSTANT_STATUE.get()) {
			list.add(new TextComponent("Creates a statue from a Minecraft username."));
		} else if(block == ModBlocks.INSTANT_HARVEST.get()) {
			list.add(new TextComponent("Harvests renewable resources in a radius."));
			list.add(new TextComponent("Radius: " + Config.Common.RADIUS_HARVEST.get()));
		} else if(block == ModBlocks.INSTANT_LIGHT.get()) {
			list.add(new TextComponent("Lights up dark areas in a radius."));
			list.add(new TextComponent("Radius: " + Config.Common.RADIUS_LIGHT.get()));
		} else if(block == ModBlocks.INSTANT_SCHEMATIC.get()) {
			list.add(new TextComponent("Creates a structure from a schematic file."));
		} else if(block == ModBlocks.INSTANT_TREE.get()) {
			list.add(new TextComponent("Creates a huge tree."));
		} else if(block == ModBlocks.COLOR.get()) {
			list.add(new TextComponent("This block is used by Instant Skydive and Statue."));
			list.add(new TextComponent("When placed, will generate with a random color."));
			list.add(new TextComponent(Colors.c+"CREATIVE MODE ONLY"));
		} else if(block == ModBlocks.SKYDIVE_TP.get()) {
			list.add(new TextComponent("This block is used by Instant Skydive."));
			list.add(new TextComponent("Teleports you up to layer " + (Config.Common.SKYDIVE_MAX.get() + 2) + "."));
			list.add(new TextComponent(Colors.c+"CREATIVE MODE ONLY"));
		}
		if(block instanceof InstantBlock) {
			list.add(new TextComponent("Right-click to activate."));
		}
	}
}