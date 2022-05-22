package com.slymask3.instantblocks.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemInstantWand extends ItemIB {
	public Item material;
	
	public ItemInstantWand(Item mat) {
		super();
        maxStackSize = 1;
        material = mat;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
	
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return material == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
	
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean par4) {
		int max = (is.getMaxDamage()) + 1;
		int dmg = (is.getMaxDamage() - is.getItemDamage()) + 1;
		list.add("Uses: " + dmg + "/" + max);
	}
}
