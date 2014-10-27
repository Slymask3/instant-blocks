package com.slymask3.instantblocks.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import com.slymask3.instantblocks.item.ItemInstantWand;
import com.slymask3.instantblocks.reference.Names;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	public static Item ibWandWood = new ItemInstantWand(Item.getItemFromBlock(Blocks.planks)).setMaxDamage(1).setUnlocalizedName("wandWood");
	public static Item ibWandStone = new ItemInstantWand(Item.getItemFromBlock(Blocks.cobblestone)).setMaxDamage(3).setUnlocalizedName("wandStone");
	public static Item ibWandIron = new ItemInstantWand(Items.iron_ingot).setMaxDamage(7).setUnlocalizedName("wandIron");
	public static Item ibWandGold = new ItemInstantWand(Items.gold_ingot).setMaxDamage(15).setUnlocalizedName("wandGold");
	public static Item ibWandDiamond = new ItemInstantWand(Items.diamond).setMaxDamage(51).setUnlocalizedName("wandDiamond");
	
	public static void init() {
		GameRegistry.registerItem(ibWandWood, Names.Items.IB_WAND_WOOD);
		GameRegistry.registerItem(ibWandStone, Names.Items.IB_WAND_STONE);
		GameRegistry.registerItem(ibWandIron, Names.Items.IB_WAND_IRON);
		GameRegistry.registerItem(ibWandGold, Names.Items.IB_WAND_GOLD);
		GameRegistry.registerItem(ibWandDiamond, Names.Items.IB_WAND_DIAMOND);
	}
}
