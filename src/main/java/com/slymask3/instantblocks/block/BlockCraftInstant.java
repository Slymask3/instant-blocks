package com.slymask3.instantblocks.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.tileentity.TileEntityInstantCraft;

public class BlockCraftInstant extends BlockContainer implements ITileEntityProvider {

	public BlockCraftInstant() {
		super(Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityInstantCraft();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		player.openGui(InstantBlocks.instance, GuiID.CRAFT.ordinal(), world, x, y, z);
		return true;
	}

}
