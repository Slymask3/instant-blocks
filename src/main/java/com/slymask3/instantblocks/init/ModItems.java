package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.item.ItemInstantWand;
import com.slymask3.instantblocks.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ModItems {
	public static Item ibWandWood = new ItemInstantWand(Item.getItemFromBlock(Blocks.planks)).setMaxDamage(1).setUnlocalizedName(Names.Items.IB_WAND_WOOD);
	public static Item ibWandStone = new ItemInstantWand(Item.getItemFromBlock(Blocks.cobblestone)).setMaxDamage(3).setUnlocalizedName(Names.Items.IB_WAND_STONE);
	public static Item ibWandIron = new ItemInstantWand(Items.iron_ingot).setMaxDamage(7).setUnlocalizedName(Names.Items.IB_WAND_IRON);
	public static Item ibWandGold = new ItemInstantWand(Items.gold_ingot).setMaxDamage(15).setUnlocalizedName(Names.Items.IB_WAND_GOLD);
	public static Item ibWandDiamond = new ItemInstantWand(Items.diamond).setMaxDamage(51).setUnlocalizedName(Names.Items.IB_WAND_DIAMOND);
	
	public static void init() {
		GameRegistry.registerItem(ibWandWood, Names.Items.IB_WAND_WOOD);
		GameRegistry.registerItem(ibWandStone, Names.Items.IB_WAND_STONE);
		GameRegistry.registerItem(ibWandIron, Names.Items.IB_WAND_IRON);
		GameRegistry.registerItem(ibWandGold, Names.Items.IB_WAND_GOLD);
		GameRegistry.registerItem(ibWandDiamond, Names.Items.IB_WAND_DIAMOND);
	}
}