package com.slymask3.instantblocks.item;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.core.Config;
import com.slymask3.instantblocks.core.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

public class InstantBlockItem extends BlockItem {
	private final Block block;
	
	public InstantBlockItem(Block block, Properties properties) {
         super(block,properties);
         this.block = block;
	}

	public void appendHoverText(ItemStack is, @Nullable Level world, List<Component> list, TooltipFlag flag) {
		if(block == ModBlocks.INSTANT_WOOD_HOUSE) {
			list.add(Component.translatable("ib.tooltip.wooden_house"));
		} else if(block == ModBlocks.INSTANT_MINING_LADDER) {
			list.add(Component.translatable("ib.tooltip.mining_ladder", Config.Common.MINING_LADDER_LAYER));
		} else if(block == ModBlocks.INSTANT_GLASS_DOME) {
			list.add(Component.translatable("ib.tooltip.glass_dome.1"));
			list.add(Component.translatable("ib.tooltip.glass_dome.2"));
		} else if(block == ModBlocks.INSTANT_FARM) {
			list.add(Component.translatable("ib.tooltip.farm"));
		} else if(block == ModBlocks.INSTANT_SKYDIVE) {
			list.add(Component.translatable("ib.tooltip.skydive.1"));
			list.add(Component.translatable("ib.tooltip.skydive.2", Config.Common.SKYDIVE_MIN, Config.Common.SKYDIVE_MAX));
		} else if(block == ModBlocks.INSTANT_GRINDER) {
			list.add(Component.translatable("ib.tooltip.grinder.1"));
			list.add(Component.translatable("ib.tooltip.grinder.2"));
		} else if(block == ModBlocks.INSTANT_POOL) {
			list.add(Component.translatable("ib.tooltip.pool"));
		} else if(block == ModBlocks.INSTANT_ESCAPE_LADDER) {
			list.add(Component.translatable("ib.tooltip.escape_ladder"));
		} else if(block == ModBlocks.INSTANT_WATER) {
			list.add(Component.translatable("ib.tooltip.water"));
			list.add(Component.translatable("ib.tooltip.water.max", Config.Common.MAX_LIQUID));
			list.add(Component.translatable("ib.tooltip.liquid.mode", (Config.Common.SIMPLE_LIQUID ? "Simple" : "Full")));
		} else if(block == ModBlocks.INSTANT_LAVA) {
			list.add(Component.translatable("ib.tooltip.lava"));
			list.add(Component.translatable("ib.tooltip.lava.max", Config.Common.MAX_LIQUID));
			list.add(Component.translatable("ib.tooltip.liquid.mode", (Config.Common.SIMPLE_LIQUID ? "Simple" : "Full")));
		} else if(block == ModBlocks.INSTANT_SUCTION) {
			list.add(Component.translatable("ib.tooltip.suction"));
			list.add(Component.translatable("ib.tooltip.suction.max", Config.Common.MAX_FILL));
		} else if(block == ModBlocks.INSTANT_RAIL) {
			list.add(Component.translatable("ib.tooltip.rail", Config.Common.RAILS_AMOUNT));
		} else if(block == ModBlocks.INSTANT_STATUE) {
			list.add(Component.translatable("ib.tooltip.statue"));
		} else if(block == ModBlocks.INSTANT_HARVEST) {
			list.add(Component.translatable("ib.tooltip.harvest"));
			list.add(Component.translatable("ib.tooltip.radius", Config.Common.RADIUS_HARVEST));
		} else if(block == ModBlocks.INSTANT_LIGHT) {
			list.add(Component.translatable("ib.tooltip.light"));
			list.add(Component.translatable("ib.tooltip.radius", Config.Common.RADIUS_LIGHT));
		} else if(block == ModBlocks.INSTANT_SCHEMATIC) {
			list.add(Component.translatable("ib.tooltip.schematic"));
		} else if(block == ModBlocks.INSTANT_TREE) {
			list.add(Component.translatable("ib.tooltip.tree"));
		} else if(block == ModBlocks.COLOR) {
			list.add(Component.translatable("ib.tooltip.color.1"));
			list.add(Component.translatable("ib.tooltip.color.2"));
			list.add(Component.translatable("ib.tooltip.creative"));
		} else if(block == ModBlocks.SKYDIVE_TP) {
			list.add(Component.translatable("ib.tooltip.skydive_tp.1"));
			list.add(Component.translatable("ib.tooltip.skydive_tp.2", Config.Common.SKYDIVE_MAX + 2));
			list.add(Component.translatable("ib.tooltip.creative"));
		}
		if(block instanceof InstantBlock) {
			list.add(Component.translatable("ib.tooltip.activate"));
		}
	}
}