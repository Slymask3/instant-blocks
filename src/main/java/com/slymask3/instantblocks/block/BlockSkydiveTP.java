package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.IBHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSkydiveTP extends Block {
	public BlockSkydiveTP() {
		super(Material.iron);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName(Reference.MOD_ID + ":" + Names.Blocks.SKYDIVE_TP);
        setHardness(1.5F);
        setStepSound(Block.soundTypeMetal);
        setBlockTextureName(Textures.SkydiveTP.SIDE);
	}

	public int quantityDropped(Random random) {
		return 0;
	}

	public static IIcon side;
	public static IIcon other;
    
	public void registerBlockIcons(IIconRegister ir) {
		side = ir.registerIcon(Textures.SkydiveTP.SIDE);
		other = ir.registerIcon(Textures.SkydiveTP.OTHER);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if(side==0 || side==1) {
        	return other;
        } else {
        	return this.side;
        }
    }

	@Override
	public boolean onBlockActivated(World world,int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		IBHelper.teleport(world,player,x,257,z, true);
		return true;
	}
}