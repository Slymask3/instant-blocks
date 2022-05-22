package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockColor extends Block implements ITileEntityProvider {

	public static Block blockTexture = Blocks.wool;
	
	public BlockColor() {
		super(Material.cloth);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName(Reference.MOD_ID + ":" + Names.Blocks.COLOR);
        setHardness(0.5F);
        setStepSound(Block.soundTypeCloth);
        setBlockTextureName(Textures.Color.SIDE);
	}

	public int quantityDropped(Random random) {
		return 0;
	}
	
	public int getRenderBlockPass() {
        return 0;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2) {
		return blockTexture.getIcon(par1, par2);
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
	
	//@SideOnly(Side.CLIENT)
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);

		int rgb = ((r & 0xFF) << 16) + ((g & 0xFF) << 8) + (b & 0xFF);
		
		if(!world.isRemote) {
			((TileEntityColor) world.getTileEntity(x, y, z)).color = rgb;
			world.markBlockForUpdate(x, y, z);
		}
	}
}