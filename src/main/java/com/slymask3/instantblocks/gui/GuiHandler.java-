package com.slymask3.instantblocks.gui;

import com.slymask3.instantblocks.gui.instant.*;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.tileentity.*;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	final TileEntity e = world.getTileEntity(x, y, z);
    	if(e == null) {
    		return null;
    	}
    	
        if(ID == GuiID.STATUE.ordinal()) {
        	return new GuiStatue(player, (TileEntityStatue) e, world, x, y, z);
        } else if(ID == GuiID.HARVEST.ordinal()) {
        	return new GuiHarvest(player, (TileEntityHarvest) e, world, x, y, z);
        } else if(ID == GuiID.SKYDIVE.ordinal()) {
        	return new GuiSkydive(player, (TileEntitySkydive) e, world, x, y, z);
        } else if(ID == GuiID.SCHEMATIC.ordinal()) {
        	return new GuiSchematic(player, (TileEntitySchematic) e, world, x, y, z);
        } else if(ID == GuiID.TREE.ordinal()) {
        	return new GuiTree(player, (TileEntityTree) e, world, x, y, z);
        }

        return null;
    }
}