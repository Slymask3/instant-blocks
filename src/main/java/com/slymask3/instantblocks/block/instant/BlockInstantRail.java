package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.BuildHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockInstantRail extends BlockInstant {
	public BlockInstantRail() {
		super(Names.Blocks.IB_RAIL, Material.circuits, Block.soundTypeMetal, 0.5F);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        setCreateMessage(Strings.CREATE_RAIL);
        setBlockTextureName(Textures.Rail.TOP0);
	}
	
	public static IIcon top0;
    public static IIcon top1;
    public static IIcon top2;
    public static IIcon top3;
    public static IIcon blank;
    
	public void registerBlockIcons(IIconRegister ir) {
		top0 = ir.registerIcon(Textures.Rail.TOP0); //NORTH
		top1 = ir.registerIcon(Textures.Rail.TOP1); //EAST
		top2 = ir.registerIcon(Textures.Rail.TOP2); //SOUTH
		top3 = ir.registerIcon(Textures.Rail.TOP3); //WEST
		blank = ir.registerIcon(Textures.Rail.BLANK);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if(side==1 || side==0) {
			if(meta==0) {
	        	return top0;
	        } else if(meta==1) {
	        	return top1;
	        } else if(meta==2) {
	        	return top2;
	        } else if(meta==3) {
	        	return top3;
	        }
        } else {
        	return blank;
        }
        return blockIcon;
    }
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		int meta = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        byte b0 = 0;
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)p_149668_2_ + this.minX, (double)p_149668_3_ + this.minY, (double)p_149668_4_ + this.minZ, (double)p_149668_2_ + this.maxX, (double)((float)p_149668_3_ + (float)b0 * f), (double)p_149668_4_ + this.maxZ);
    }
	
    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public String getItemIconName()
    {
        return Textures.Rail.ITEM;
    }

	public void build(World world, int x, int y, int z, EntityPlayer player) {
    	int meta = world.getBlockMetadata(x, y, z);
    	for(int i = 0; i<= Config.RAILS_AMOUNT; i++) {
	    	BuildHelper.setBlockDirectional(world, x, y-1, z, Blocks.stone, meta, i, 0, 0, 0);
	    	BuildHelper.setBlockDirectional(world, x, y, z, Blocks.rail, meta, i, 0, 0, 0);
	    	BuildHelper.setBlockDirectional(world, x, y+1, z, Blocks.air, meta, i, 0, 0, 0);
    	}
    }
}
