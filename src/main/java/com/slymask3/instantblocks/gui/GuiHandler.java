package com.slymask3.instantblocks.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.tileentity.TileEntityHarvest;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.tileentity.TileEntityStatue;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	final TileEntity e = world.getTileEntity(x, y, z);
    	if (e == null) {
    		return null;
    	}
    	
        if (ID == GuiID.STATUE.ordinal()) {
            //return new ContainerCraftingTablet(player.inventory, world, x, y, z);
        	//return new GuiStatueMenu(player, (TileEntityInstantStatue) e);
        }// else if (ID == GuiIds.DISENCHANTER.ordinal()) {
        //    return new ContainerDisenchanter(player.inventory, (TileEntityDisenchanter) world.getTileEntity(x, y, z));
        //}

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	final TileEntity e = world.getTileEntity(x, y, z);
    	if (e == null) {
    		return null;
    	}
    	
        if (ID == GuiID.STATUE.ordinal()) {
            //return new GuiCraftingTablet(new ContainerCraftingTablet(player.inventory, world, x, y, z));
        	return new GuiStatue(player, (TileEntityStatue) e, world, x, y, z);
        } else if (ID == GuiID.HARVEST.ordinal()) {
        	return new GuiHarvest(player, (TileEntityHarvest) e, world, x, y, z);
        } else if (ID == GuiID.SKYDIVE.ordinal()) {
        	return new GuiSkydive(player, (TileEntitySkydive) e, world, x, y, z);
        }

        return null;
    }
}