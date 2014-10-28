package com.slymask3.instantblocks.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.utility.BuildHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemInstantWand extends ItemIB {
	private BuildHelper ibf = new BuildHelper();
	private ConfigurationHandler config = new ConfigurationHandler();
	private InstantBlocks ib = new InstantBlocks();
	public Item material;
	//public Block matBlock;
	//public int item;
	
	public ItemInstantWand(Item mat) {
		super();
        maxStackSize = 1;
        //setCreativeTab(InstantBlocks.tabInstantBlocks);
        material = mat;
        //item = id;
	}
	
	/*protected ItemInstantWand() {
		super();
        maxStackSize = 1;
        setCreativeTab(InstantBlocks.tabInstantBlocks);
        //item = id;
	}*/
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
	
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return material == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
	
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean par4) {
		int max = (is.getMaxDamage()) + 1;
		int dmg = (is.getMaxDamage() - is.getItemDamage()) + 1;
		
		list.add("Uses: " + dmg + "/" + max);
	}
}
