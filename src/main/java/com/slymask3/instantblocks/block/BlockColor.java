package com.slymask3.instantblocks.block;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockColor extends Block implements ITileEntityProvider {

	public BlockColor() {
		super(Material.cloth);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName("instantblocks:" + Names.Blocks.COLOR);
        setHardness(1.5F);
        setResistance(2000F);
        setStepSound(Block.soundTypeCloth);
	}

	public int quantityDropped(Random random) {
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2) {
		return Blocks.wool.getIcon(par1, par2);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, int meta) {
		return new TileEntityColor();
	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess access, int x, int y, int z) {
		return ((TileEntityColor) access.getTileEntity(x, y, z)).color;
	}

	/*@Override
	public boolean onBlockActivated(World world,int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		//((TileEntityColor) world.getTileEntity(x, y, z)).color = 0x00FFFF00;
		//world.markBlockForUpdate(x, y, z);
		
		return true;
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		//((TileEntityColor) world.getTileEntity(x, y, z)).color = 0x000000FF;
		//world.markBlockForUpdate(x, y, z);
	}*/
	
	//@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		int r = 0;
		int g = 0;
		int b = 0;
		
		//if(!world.isRemote) {
			Random rand = new Random();
			r = rand.nextInt(255);
			g = rand.nextInt(255);
			b = rand.nextInt(255);
		//}
			
			int rgb = ((r & 0xFF) << 16) + ((g & 0xFF) << 8) + (b & 0xFF);
			
			((TileEntityColor) world.getTileEntity(x, y, z)).color = rgb;
			world.markBlockForUpdate(x, y, z);
	
	}
	
}