package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInstantRail extends BlockRailBase {

	private int direction = 0;
	
	public BlockInstantRail() {
		super(false);
		setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
		setBlockName("instantblocks:" + Names.Blocks.IB_RAIL);
		setHardness(1.5F);
		setResistance(2000F);
		setStepSound(Block.soundTypeWood);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (direction==0) {
        	return Blocks.rail.getIcon(side, meta);
        } else if (direction==1) {
        	return Blocks.rail.getIcon(side, meta);
        } else if (direction==2) {
        	return ModBlocks.ibUp.getIcon(side, meta);
        } else if (direction==3) {
        	return Blocks.ladder.getIcon(side, meta);
        }
        return blockIcon;
    }
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        int meta = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        
        if(meta==0) {
        	world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        	direction = 0;
        } else if(meta==1) {
        	world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        	direction = 1;
        } else if(meta==2) {
        	world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        	direction = 2;
        } else if(meta==3) {
        	world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        	direction = 3;
        } else {
        	world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        	direction = 0;
        }
    }
	
//	public boolean isOpaqueCube()
//    {
//        return false;
//    }
//	
//	public boolean renderAsNormalBlock()
//    {
//        return false;
//    }

    /** getRenderType() values
     * -1: BlockSign
	 *  1: BlockFlower, BlockReed
     *  2: BlockTorch
     *  3: BlockFire
     *  4: BlockFluids
	 *  5: BlockRedstoneWire
	 *  6: BlockCrops
	 *  7: BlockDoor
	 *  8: BlockLadder
	 *  9: BlockMinecartTrack
	 * 10: BlockStairs
	 * 11: BlockFence
	 * 12: BlockLever
	 * 13: BlockCactus
	 * 14: BlockBed
	 * 15: BlockRedstoneRepeater
     **/
//    public int getRenderType()
//    {
//        return 15;
//    }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        LogHelper.info("meta == " + meta + ", direction == " + direction);
    }
	
	/*@Override
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        //
    }*/
	
	public boolean isFlexibleRail(IBlockAccess world, int y, int x, int z)
    {
        return false;
    }

    /**
     * Returns true if the rail can make up and down slopes.
     * Used by placement logic.
     * @param world The world.
     * @param x The rail X coordinate.
     * @param y The rail Y coordinate.
     * @param z The rail Z coordinate.
     * @return True if the rail can make slopes.
     */
    public boolean canMakeSlopes(IBlockAccess world, int x, int y, int z)
    {
        return false;
    }
    
    public int getBasicRailMetadata(IBlockAccess world, EntityMinecart cart, int x, int y, int z)
    {
    	return world.getBlockMetadata(x, y, z);
//        int meta = world.getBlockMetadata(x, y, z);
//        if(isPowered())
//        {
//            meta = meta & 7;
//        }
//        return meta;
    }
}
