package com.slymask3.instantblocks.creativetab;

import com.slymask3.instantblocks.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class InstantBlocksTab {
	
	public static final CreativeTabs INSTANTBLOCKS_TAB = new CreativeTabs("instantblocks") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModBlocks.ibWood);
		}
	};
}