package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.tileentity.TileEntityInstantRail;
import com.slymask3.instantblocks.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInstantRail extends BlockIB {

	public BlockInstantRail() {
		super(ModBlocks.ibRail, Names.Blocks.IB_RAIL, Material.circuits, Block.soundTypeMetal, 0.5F);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        //setOverrideBlockTexture(Blocks.stone.getIcon(1, 0);
	}
	
	public static IIcon top0;
    public static IIcon top1;
    //public static IIcon top2;
    //public static IIcon top3;
    
	public void registerBlockIcons(IIconRegister ir) {
		top0 = ir.registerIcon("instantblocks:rail_0"); //NORTH
		top1 = ir.registerIcon("instantblocks:rail_1"); //EAST
		//top2 = ir.registerIcon("instantblocks:pool_top_2"); //SOUTH
		//top3 = ir.registerIcon("instantblocks:pool_top_3"); //WEST
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta==0) {
        	return top0;
        } else if (meta==1) {
        	return top1;
        } else if (meta==2) {
        	return Blocks.wool.getIcon(side, meta);
        } else if (meta==3) {
        	return Blocks.wool.getIcon(side, meta);
        }
    	//LogHelper.info("(getIcon) TI.direction == " + ((TileEntityInstantRail) world.getTileEntity(x, y, z)).direction);
        return blockIcon;
    }
	
//	@SideOnly(Side.CLIENT)
//    public IIcon getIcon(int side, int meta) {
//        return Blocks.wool.getIcon(0, 4);
//    }
	
//	public int getRenderType() {
//		return 0;
//	}
	
//	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
//        int meta = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
//        
//        if(meta==0) {
//        	world.setBlockMetadataWithNotify(x, y, z, 0, 2);
//        	((TileEntityInstantRail) world.getTileEntity(x, y, z)).direction = 0;
//        	//this.direction = 0;
//        } else if(meta==1) {
//        	world.setBlockMetadataWithNotify(x, y, z, 1, 2);
//        	((TileEntityInstantRail) world.getTileEntity(x, y, z)).direction = 1;
//        	//this.direction = 1;
//        } else if(meta==2) {
//        	world.setBlockMetadataWithNotify(x, y, z, 0, 2);
//        	((TileEntityInstantRail) world.getTileEntity(x, y, z)).direction = 2;
//        	//this.direction = 2;
//        } else if(meta==3) {
//        	world.setBlockMetadataWithNotify(x, y, z, 1, 2);
//        	((TileEntityInstantRail) world.getTileEntity(x, y, z)).direction = 3;
//        	//this.direction = 3;
//        } else {
//        	world.setBlockMetadataWithNotify(x, y, z, 0, 2);
//        	((TileEntityInstantRail) world.getTileEntity(x, y, z)).direction = 0;
//        }
//    	LogHelper.info("TI.direction == " + ((TileEntityInstantRail) world.getTileEntity(x, y, z)).direction);
//    }

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		int meta = MathHelper.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 2);

		System.out.println("meta = " + meta);
	}
	
//	@Override
//	public void onBlockAdded(World world, int x, int y, int z) {
//        int meta = world.getBlockMetadata(x, y, z);
//        LogHelper.info("meta == " + meta + ", direction == " + direction + ", TIdirection == " + ((TileEntityInstantRail) world.getTileEntity(x, y, z)).direction);
//        
//        this.tile = (TileEntityInstantRail) world.getTileEntity(x, y, z);
//	}
	
//	public boolean isFlexibleRail(IBlockAccess world, int y, int x, int z)
//    {
//        return false;
//    }

//    /**
//     * Returns true if the rail can make up and down slopes.
//     * Used by placement logic.
//     * @param world The world.
//     * @param x The rail X coordinate.
//     * @param y The rail Y coordinate.
//     * @param z The rail Z coordinate.
//     * @return True if the rail can make slopes.
//     */
//    public boolean canMakeSlopes(IBlockAccess world, int x, int y, int z)
//    {
//        return false;
//    }
    
//    public int getBasicRailMetadata(IBlockAccess world, EntityMinecart cart, int x, int y, int z)
//    {
//    	return world.getBlockMetadata(x, y, z);
////        int meta = world.getBlockMetadata(x, y, z);
////        if(isPowered())
////        {
////            meta = meta & 7;
////        }
////        return meta;
//    }

//	@Override
//	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
//		return new TileEntityInstantRail();
//	}
	
	
	
	//BLOCKCARPET SHIT
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        byte b0 = 0;
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)p_149668_2_ + this.minX, (double)p_149668_3_ + this.minY, (double)p_149668_4_ + this.minZ, (double)p_149668_2_ + this.maxX, (double)((float)p_149668_3_ + (float)b0 * f), (double)p_149668_4_ + this.maxZ);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        this.func_150089_b(0);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        this.func_150089_b(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
    }
    
    protected void func_150089_b(int p_150089_1_)
    {
        byte b0 = 0;
        float f = (float)(1 * (1 + b0)) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
    {
        return !p_149718_1_.isAirBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_);
    }
   
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        return p_149646_5_ == 1 ? true : super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
    }
	
}
