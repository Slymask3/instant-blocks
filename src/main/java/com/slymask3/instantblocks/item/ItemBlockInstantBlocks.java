package com.slymask3.instantblocks.item;

import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
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
		if (block == ModBlocks.ibFarm.get()) {
			list.add(new TextComponent("Creates a simple farm with wheat."));
			list.add(new TextComponent("5% chance with carrots."));
			list.add(new TextComponent("5% chance with potatoes."));
			list.add(new TextComponent("Right-click to activate."));
		} else {
			list.add(new TextComponent("Error loading description."));
		}
	}
}