package com.slymask3.instantblocks.item;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
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
			list.add(new TranslatableComponent("ib.tooltip.wooden_house"));
		} else if(block == ModBlocks.INSTANT_MINING_LADDER.get()) {
			list.add(new TranslatableComponent("ib.tooltip.mining_ladder", Config.Common.MINING_LADDER_LAYER.get()));
		} else if(block == ModBlocks.INSTANT_GLASS_DOME.get()) {
			list.add(new TranslatableComponent("ib.tooltip.glass_dome.1"));
			list.add(new TranslatableComponent("ib.tooltip.glass_dome.2"));
		} else if(block == ModBlocks.INSTANT_FARM.get()) {
			list.add(new TranslatableComponent("ib.tooltip.farm"));
		} else if(block == ModBlocks.INSTANT_SKYDIVE.get()) {
			list.add(new TranslatableComponent("ib.tooltip.skydive.1"));
			list.add(new TranslatableComponent("ib.tooltip.skydive.2", Config.Common.SKYDIVE_MIN.get(), Config.Common.SKYDIVE_MAX.get()));
		} else if(block == ModBlocks.INSTANT_GRINDER.get()) {
			list.add(new TranslatableComponent("ib.tooltip.grinder.1"));
			list.add(new TranslatableComponent("ib.tooltip.grinder.2"));
		} else if(block == ModBlocks.INSTANT_POOL.get()) {
			list.add(new TranslatableComponent("ib.tooltip.pool"));
		} else if(block == ModBlocks.INSTANT_ESCAPE_LADDER.get()) {
			list.add(new TranslatableComponent("ib.tooltip.escape_ladder"));
		} else if(block == ModBlocks.INSTANT_WATER.get()) {
			list.add(new TranslatableComponent("ib.tooltip.water"));
			list.add(new TranslatableComponent("ib.tooltip.water.max", Config.Common.MAX_LIQUID.get()));
			list.add(new TranslatableComponent("ib.tooltip.liquid.mode", (Config.Common.SIMPLE_LIQUID.get() ? "Simple" : "Full")));
		} else if(block == ModBlocks.INSTANT_LAVA.get()) {
			list.add(new TranslatableComponent("ib.tooltip.lava"));
			list.add(new TranslatableComponent("ib.tooltip.lava.max", Config.Common.MAX_LIQUID.get()));
			list.add(new TranslatableComponent("ib.tooltip.liquid.mode", (Config.Common.SIMPLE_LIQUID.get() ? "Simple" : "Full")));
		} else if(block == ModBlocks.INSTANT_SUCTION.get()) {
			list.add(new TranslatableComponent("ib.tooltip.suction"));
			list.add(new TranslatableComponent("ib.tooltip.suction.max", Config.Common.MAX_FILL.get()));
		} else if(block == ModBlocks.INSTANT_RAIL.get()) {
			list.add(new TranslatableComponent("ib.tooltip.rail", Config.Common.RAILS_AMOUNT.get()));
		} else if(block == ModBlocks.INSTANT_STATUE.get()) {
			list.add(new TranslatableComponent("ib.tooltip.statue"));
		} else if(block == ModBlocks.INSTANT_HARVEST.get()) {
			list.add(new TranslatableComponent("ib.tooltip.harvest"));
			list.add(new TranslatableComponent("ib.tooltip.radius", Config.Common.RADIUS_HARVEST.get()));
		} else if(block == ModBlocks.INSTANT_LIGHT.get()) {
			list.add(new TranslatableComponent("ib.tooltip.light"));
			list.add(new TranslatableComponent("ib.tooltip.radius", Config.Common.RADIUS_LIGHT.get()));
		} else if(block == ModBlocks.INSTANT_SCHEMATIC.get()) {
			list.add(new TranslatableComponent("ib.tooltip.schematic"));
		} else if(block == ModBlocks.INSTANT_TREE.get()) {
			list.add(new TranslatableComponent("ib.tooltip.tree"));
		} else if(block == ModBlocks.COLOR.get()) {
			list.add(new TranslatableComponent("ib.tooltip.color.1"));
			list.add(new TranslatableComponent("ib.tooltip.color.2"));
			list.add(new TranslatableComponent("ib.tooltip.creative"));
		} else if(block == ModBlocks.SKYDIVE_TP.get()) {
			list.add(new TranslatableComponent("ib.tooltip.skydive_tp.1"));
			list.add(new TranslatableComponent("ib.tooltip.skydive_tp.2", Config.Common.SKYDIVE_MAX.get() + 2));
			list.add(new TranslatableComponent("ib.tooltip.creative"));
		}
		if(block instanceof InstantBlock) {
			list.add(new TranslatableComponent("ib.tooltip.activate"));
		}
	}
}