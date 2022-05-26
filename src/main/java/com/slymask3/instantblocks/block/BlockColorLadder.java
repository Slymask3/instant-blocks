package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntityColorLadder;
import com.slymask3.instantblocks.util.IBHelper;
import com.slymask3.instantblocks.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockColorLadder extends BlockLadder implements ITileEntityProvider {
	public BlockColorLadder() {
		super();
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName(Reference.MOD_ID + ":" + Names.Blocks.COLOR_LADDER);
        setHardness(0.5F);
        setStepSound(Block.soundTypeLadder);
        setBlockTextureName(Textures.ColorLadder.SIDE);
	}

	public static IIcon side;
    public static IIcon blank;
    
	public void registerBlockIcons(IIconRegister ir) {
		side = ir.registerIcon(Textures.ColorLadder.SIDE);
		blank = ir.registerIcon(Textures.ColorLadder.BLANK);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if(side==2) {
			if(meta==2) {
	        	return this.side;
			} else {
	        	return blank;
			}
        } else if(side==3) {
			if(meta==3) {
	        	return this.side;
			} else {
	        	return blank;
			}
        } else if(side==4) {
        	if(meta==4) {
	        	return this.side;
			} else {
	        	return blank;
			}
        }  else if(side==5) {
        	if(meta==5) {
	        	return this.side;
			} else {
	        	return blank;
			}
        } else {
        	return blank;
        }
        
    }
	
	public int getRenderType()
    {
        return 0;
    }
	
	public int quantityDropped(Random random) {
		return 0;
	}

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(final World world, int meta) {
		return new TileEntityColorLadder();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess access, int x, int y, int z) {
		return ((TileEntityColorLadder) access.getTileEntity(x, y, z)).color;
	}
	
	@SideOnly(Side.CLIENT)
    public String getItemIconName() {
        return Textures.ColorLadder.ITEM;
    }

	public void func_149797_b(int meta) {
        float f = 0.125F;

        if(meta == 2)
        {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }

        if(meta == 3)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }

        if(meta == 4)
        {
            this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if(meta == 5)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
    }
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
		
		int rgb = ((r & 0xFF) << 16) + ((g & 0xFF) << 8) + (b & 0xFF);
		
		if(IBHelper.isServer(world)) {
			((TileEntityColorLadder) world.getTileEntity(x, y, z)).color = rgb;
			world.markBlockForUpdate(x, y, z);
		}
		
		LogHelper.info("meta == "+world.getBlockMetadata(x, y, z));
	}
}
