package com.slymask3.instantblocks.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.utility.BuildHelper;

public class BlockInstantPool extends BlockDirectional {
	private BuildHelper ibf = new BuildHelper();
	private ConfigurationHandler config = new ConfigurationHandler();
	private InstantBlocks ib = new InstantBlocks();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
    public BlockInstantPool() {
        super(Material.rock);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName("instantblocks:" + Names.Blocks.IB_POOL);
        setHardness(1.5F);
        setResistance(2000F);
        setStepSound(Block.soundTypeStone);
    }
    
    public int quantityDropped(Random r) {
        return 1;
    }
	
    public static IIcon[] textures = new IIcon[6];
    public static IIcon top0;
    public static IIcon top1;
    public static IIcon top2;
    public static IIcon top3;
    public static IIcon side;
    
	public void registerBlockIcons(IIconRegister ir) {
		/*textures[0] = ir.registerIcon("stoneslab_top");
		textures[1] = ir.registerIcon("instantblocks:pool_top_0");
		textures[2] = ir.registerIcon("stoneslab_top");
		textures[3] = ir.registerIcon("stoneslab_top");
		textures[4] = ir.registerIcon("stoneslab_top");
		textures[5] = ir.registerIcon("stoneslab_top");*/
		
		side = ir.registerIcon("stone_slab_top");
		
		top0 = ir.registerIcon("instantblocks:pool_top_0"); //NORTH
		top1 = ir.registerIcon("instantblocks:pool_top_1"); //EAST
		top2 = ir.registerIcon("instantblocks:pool_top_2"); //SOUTH
		top3 = ir.registerIcon("instantblocks:pool_top_3"); //WEST
	}
    
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return this.side;
		} else if (side == 1) {
			if (meta == 0) {
				return top0;
			} else if (meta == 1) {
				return top1;
			} else if (meta == 2) {
				return top2;
			} else if (meta == 3) {
				return top3;
			} else {
				return top0;
			}
		} else if (side == 2) {
			return this.side;
		} else if (side == 3) {
			return this.side;
		} else if (side == 4) {
			return this.side;
		} else if (side == 5) {
			return this.side;
		} else {
			return blockIcon;
		}
	}
    
    /*public static IIcon bottom;
	public static IIcon top;
	public static IIcon side;
	public static IIcon front;

	public void registerBlockIcons(IIconRegister ir) {
		bottom = ir.registerIcon("wood_spruce");
		top = ir.registerIcon("instantblocks:pool_top_0");
		side = ir.registerIcon("instantblocks:woodhouse_side");
		front = ir.registerIcon("instantblocks:woodhouse_front");
	}

	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return bottom;
		} else if (side == 1) {
			return top;
		} else if (side == 2) {
			if (meta == 2 || meta == 6) {
				return front;
			} else {
				return this.side;
			}
		} else if (side == 3) {
			if (meta == 0 || meta == 4) {
				return front;
			} else {
				return this.side;
			}
		} else if (side == 4) {
			if (meta == 1 || meta == 5) {
				return front;
			} else {
				return this.side;
			}
		} else if (side == 5) {
			if (meta == 3 || meta == 7) {
				return front;
			} else {
				return this.side;
			}
		} else {
			return blockIcon;
		}
	}*/

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        int meta = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 2);
    }
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	ItemStack is = player.getCurrentEquippedItem();
    	
		if (config.useWands == true) {
			if (is != null && (is.getItem() == mi.ibWandWood || is.getItem() == mi.ibWandStone || is.getItem() == mi.ibWandIron || is.getItem() == mi.ibWandGold || is.getItem() == mi.ibWandDiamond)) {
				is.damageItem(1, player);
				//player.triggerAchievement(ib.achPool);
			} else {
				ibf.msg(player, ibf.wandReq, Colors.c);
				return true;
			}
		}
		
		build(world, x, y, z);
    	
		ibf.keepBlocks(world, x, y, z, mb.ibPool);
		ibf.xp(world, player, config.xp);
		
		ibf.sound(world, config.sound, x, y, z);
		ibf.effectFull(world, "reddust", x, y, z);
		ibf.msg(player, ibf.poolCreate, Colors.a);
    	
		return true;
    }

	private void build(World world, int x, int y, int z) {
		/*int stone = Block.stoneDoubleSlab.blockID;
		int water = Block.waterMoving.blockID;
		int slab = Block.stoneSingleSlab.blockID;
		int glow = Block.glowStone.blockID;
		int slabD = Block.stoneDoubleSlab.blockID;
		int ladder = Block.ladder.blockID;
		int wood = Block.planks.blockID;
		int fence = Block.fence.blockID;
		int air = 0;*/
		
		Block stone = Blocks.double_stone_slab; //FIX THIS INTO DOUBLE STONE SLAB
		Block water = Blocks.water;
		Block slab = Blocks.stone_slab;
		Block glow = Blocks.glowstone;
		Block slabD = Blocks.double_stone_slab; //FIX THIS INTO DOUBLE STONE SLAB
		Block ladder = Blocks.ladder;
		Block wood = Blocks.planks;
		Block fence = Blocks.fence;
		Block air = Blocks.air;
		
		int meta = world.getBlockMetadata(x, y, z);
		
		/************************ Layer -5 to 5 : Air ************************/
		ibf.build(world, x-6, y-5, z-6, air, 13, 11, 13);
		
		/************************ Layer -5 to 0 : Stone ************************/
		ibf.build(world, x-6, y-5, z-6, stone, 13, 6, 13);
		
		/************************ Layer -4 to 0 : Water ************************/
		ibf.build(world, x-5, y-4, z-5, water, 11, 5, 11);
		
		/************************ Layer 1 : Stone Slab ************************/
		ibf.build(world, x-6, y+1, z-6, slab, 13, 1, 1);
		ibf.build(world, x+6, y+1, z-6, slab, 13, 1, 1);
		ibf.build(world, x-5, y+1, z-6, slab, 1, 1, 11);
		ibf.build(world, x-5, y+1, z+6, slab, 1, 1, 11);
		
		/************************ Layer -2 : Glowstone ************************/
		world.setBlock(x-6, y-2, z-4, glow);
		world.setBlock(x-6, y-2, z-2, glow);
		world.setBlock(x-6, y-2, z, glow);
		world.setBlock(x-6, y-2, z+2, glow);
		world.setBlock(x-6, y-2, z+4, glow);

		world.setBlock(x+6, y-2, z-4, glow);
		world.setBlock(x+6, y-2, z-2, glow);
		world.setBlock(x+6, y-2, z, glow);
		world.setBlock(x+6, y-2, z+2, glow);
		world.setBlock(x+6, y-2, z+4, glow);

		world.setBlock(x-4, y-2, z-6, glow);
		world.setBlock(x-2, y-2, z-6, glow);
		world.setBlock(x, y-2, z-6, glow);
		world.setBlock(x+2, y-2, z-6, glow);
		world.setBlock(x+4, y-2, z-6, glow);

		world.setBlock(x-4, y-2, z+6, glow);
		world.setBlock(x-2, y-2, z+6, glow);
		world.setBlock(x, y-2, z+6, glow);
		world.setBlock(x+2, y-2, z+6, glow);
		world.setBlock(x+4, y-2, z+6, glow);
		
		/************************ Layer -5 : Glowstone ************************/
		world.setBlock(x-2, y-5, z-2, glow);
		world.setBlock(x-2, y-5, z+2, glow);
		world.setBlock(x+2, y-5, z-2, glow);
		world.setBlock(x+2, y-5, z+2, glow);
		
		/************************ Layer 1 to 3 : Diving Board ************************/
		/*world.setBlock(x, y+1, z+6, slabD);
		world.setBlock(x, y+2, z+6, slabD);
		world.setBlock(x, y+3, z+6, slabD);
		world.setBlock(x, y+3, z+5, slab, 8, 0);
		world.setBlock(x, y+3, z+4, slab, 8, 0);
		world.setBlock(x, y+3, z+3, slab, 8, 0);
		world.setBlock(x, y+1, z+7, ladder, 3, 0);
		world.setBlock(x, y+2, z+7, ladder, 3, 0);
		world.setBlock(x, y+3, z+7, ladder, 3, 0);*/
		
		/************************ Layer 1 to 5 : Diving Board v2.0 ************************/
		if (meta == 0) {
			world.setBlock(x-2, y+1, z+6, wood, 0, 0);
			world.setBlock(x-2, y+2, z+6, wood, 0, 0);

			world.setBlock(x-2, y+1, z+7, ladder, 3, 0);
			world.setBlock(x-2, y+2, z+7, ladder, 3, 0);
		
			world.setBlock(x-2, y+3, z+6, slab, 0, 0);
			world.setBlock(x-2, y+3, z+5, slab, 0, 0);
			world.setBlock(x-2, y+3, z+4, slab, 0, 0);
			world.setBlock(x-2, y+3, z+3, slab, 0, 0);
		
			world.setBlock(x-1, y+1, z+6, fence);
			world.setBlock(x-1, y+2, z+6, fence);
			world.setBlock(x-1, y+3, z+6, fence);
		
			world.setBlock(x-3, y+1, z+6, fence);
			world.setBlock(x-3, y+2, z+6, fence);
			world.setBlock(x-3, y+3, z+6, fence);

			world.setBlock(x-1, y+3, z+5, fence);
			world.setBlock(x-3, y+3, z+5, fence);
		
			///////////////////////////////////////////////////////////
		
			world.setBlock(x+2, y+1, z+6, wood, 0, 0);
			world.setBlock(x+2, y+2, z+6, wood, 0, 0);
			world.setBlock(x+2, y+3, z+6, wood, 0, 0);
			world.setBlock(x+2, y+4, z+6, wood, 0, 0);

			world.setBlock(x+2, y+1, z+7, ladder, 3, 0);
			world.setBlock(x+2, y+2, z+7, ladder, 3, 0);
			world.setBlock(x+2, y+3, z+7, ladder, 3, 0);
			world.setBlock(x+2, y+4, z+7, ladder, 3, 0);
		
			world.setBlock(x+2, y+5, z+6, slab, 0, 0);
			world.setBlock(x+2, y+5, z+5, slab, 0, 0);
			world.setBlock(x+2, y+5, z+4, slab, 0, 0);
			world.setBlock(x+2, y+5, z+3, slab, 0, 0);
		
			world.setBlock(x+1, y+1, z+6, fence);
			world.setBlock(x+1, y+2, z+6, fence);
			world.setBlock(x+1, y+3, z+6, fence);
			world.setBlock(x+1, y+4, z+6, fence);
			world.setBlock(x+1, y+5, z+6, fence);
		
			world.setBlock(x+3, y+1, z+6, fence);
			world.setBlock(x+3, y+2, z+6, fence);
			world.setBlock(x+3, y+3, z+6, fence);
			world.setBlock(x+3, y+4, z+6, fence);
			world.setBlock(x+3, y+5, z+6, fence);

			world.setBlock(x+1, y+5, z+5, fence);
			world.setBlock(x+3, y+5, z+5, fence);
		} else if (meta == 1) {
			world.setBlock(x-6, y+1, z+2, wood, 0, 0);
			world.setBlock(x-6, y+2, z+2, wood, 0, 0);

			world.setBlock(x-7, y+1, z+2, ladder, 4, 0);
			world.setBlock(x-7, y+2, z+2, ladder, 4, 0);
		
			world.setBlock(x-6, y+3, z+2, slab, 0, 0);
			world.setBlock(x-5, y+3, z+2, slab, 0, 0);
			world.setBlock(x-4, y+3, z+2, slab, 0, 0);
			world.setBlock(x-3, y+3, z+2, slab, 0, 0);
		
			world.setBlock(x-6, y+1, z+1, fence);
			world.setBlock(x-6, y+2, z+1, fence);
			world.setBlock(x-6, y+3, z+1, fence);
		
			world.setBlock(x-6, y+1, z+3, fence);
			world.setBlock(x-6, y+2, z+3, fence);
			world.setBlock(x-6, y+3, z+3, fence);

			world.setBlock(x-5, y+3, z+1, fence);
			world.setBlock(x-5, y+3, z+3, fence);
		
			///////////////////////////////////////////////////////////
		
			world.setBlock(x-6, y+1, z-2, wood, 0, 0);
			world.setBlock(x-6, y+2, z-2, wood, 0, 0);
			world.setBlock(x-6, y+3, z-2, wood, 0, 0);
			world.setBlock(x-6, y+4, z-2, wood, 0, 0);

			world.setBlock(x-7, y+1, z-2, ladder, 4, 0);
			world.setBlock(x-7, y+2, z-2, ladder, 4, 0);
			world.setBlock(x-7, y+3, z-2, ladder, 4, 0);
			world.setBlock(x-7, y+4, z-2, ladder, 4, 0);
		
			world.setBlock(x-6, y+5, z-2, slab, 0, 0);
			world.setBlock(x-5, y+5, z-2, slab, 0, 0);
			world.setBlock(x-4, y+5, z-2, slab, 0, 0);
			world.setBlock(x-3, y+5, z-2, slab, 0, 0);
		
			world.setBlock(x-6, y+1, z-1, fence);
			world.setBlock(x-6, y+2, z-1, fence);
			world.setBlock(x-6, y+3, z-1, fence);
			world.setBlock(x-6, y+4, z-1, fence);
			world.setBlock(x-6, y+5, z-1, fence);
		
			world.setBlock(x-6, y+1, z-3, fence);
			world.setBlock(x-6, y+2, z-3, fence);
			world.setBlock(x-6, y+3, z-3, fence);
			world.setBlock(x-6, y+4, z-3, fence);
			world.setBlock(x-6, y+5, z-3, fence);

			world.setBlock(x-5, y+5, z-1, fence);
			world.setBlock(x-5, y+5, z-3, fence);
		} else if (meta == 2) {
			world.setBlock(x+2, y+1, z-6, wood, 0, 0);
			world.setBlock(x+2, y+2, z-6, wood, 0, 0);

			world.setBlock(x+2, y+1, z-7, ladder, 2, 0);
			world.setBlock(x+2, y+2, z-7, ladder, 2, 0);
		
			world.setBlock(x+2, y+3, z-6, slab, 0, 0);
			world.setBlock(x+2, y+3, z-5, slab, 0, 0);
			world.setBlock(x+2, y+3, z-4, slab, 0, 0);
			world.setBlock(x+2, y+3, z-3, slab, 0, 0);
		
			world.setBlock(x+1, y+1, z-6, fence);
			world.setBlock(x+1, y+2, z-6, fence);
			world.setBlock(x+1, y+3, z-6, fence);
		
			world.setBlock(x+3, y+1, z-6, fence);
			world.setBlock(x+3, y+2, z-6, fence);
			world.setBlock(x+3, y+3, z-6, fence);

			world.setBlock(x+1, y+3, z-5, fence);
			world.setBlock(x+3, y+3, z-5, fence);
		
			///////////////////////////////////////////////////////////
		
			world.setBlock(x-2, y+1, z-6, wood, 0, 0);
			world.setBlock(x-2, y+2, z-6, wood, 0, 0);
			world.setBlock(x-2, y+3, z-6, wood, 0, 0);
			world.setBlock(x-2, y+4, z-6, wood, 0, 0);

			world.setBlock(x-2, y+1, z-7, ladder, 2, 0);
			world.setBlock(x-2, y+2, z-7, ladder, 2, 0);
			world.setBlock(x-2, y+3, z-7, ladder, 2, 0);
			world.setBlock(x-2, y+4, z-7, ladder, 2, 0);
		
			world.setBlock(x-2, y+5, z-6, slab, 0, 0);
			world.setBlock(x-2, y+5, z-5, slab, 0, 0);
			world.setBlock(x-2, y+5, z-4, slab, 0, 0);
			world.setBlock(x-2, y+5, z-3, slab, 0, 0);
		
			world.setBlock(x-1, y+1, z-6, fence);
			world.setBlock(x-1, y+2, z-6, fence);
			world.setBlock(x-1, y+3, z-6, fence);
			world.setBlock(x-1, y+4, z-6, fence);
			world.setBlock(x-1, y+5, z-6, fence);
		
			world.setBlock(x-3, y+1, z-6, fence);
			world.setBlock(x-3, y+2, z-6, fence);
			world.setBlock(x-3, y+3, z-6, fence);
			world.setBlock(x-3, y+4, z-6, fence);
			world.setBlock(x-3, y+5, z-6, fence);

			world.setBlock(x-1, y+5, z-5, fence);
			world.setBlock(x-3, y+5, z-5, fence);
		} else if (meta == 3) {
			world.setBlock(x+6, y+1, z+2, wood, 0, 0);
			world.setBlock(x+6, y+2, z+2, wood, 0, 0);

			world.setBlock(x+7, y+1, z+2, ladder, 5, 0);
			world.setBlock(x+7, y+2, z+2, ladder, 5, 0);
		
			world.setBlock(x+6, y+3, z+2, slab, 0, 0);
			world.setBlock(x+5, y+3, z+2, slab, 0, 0);
			world.setBlock(x+4, y+3, z+2, slab, 0, 0);
			world.setBlock(x+3, y+3, z+2, slab, 0, 0);
		
			world.setBlock(x+6, y+1, z+1, fence);
			world.setBlock(x+6, y+2, z+1, fence);
			world.setBlock(x+6, y+3, z+1, fence);
		
			world.setBlock(x+6, y+1, z+3, fence);
			world.setBlock(x+6, y+2, z+3, fence);
			world.setBlock(x+6, y+3, z+3, fence);

			world.setBlock(x+5, y+3, z+1, fence);
			world.setBlock(x+5, y+3, z+3, fence);
		
			///////////////////////////////////////////////////////////
		
			world.setBlock(x+6, y+1, z-2, wood, 0, 0);
			world.setBlock(x+6, y+2, z-2, wood, 0, 0);
			world.setBlock(x+6, y+3, z-2, wood, 0, 0);
			world.setBlock(x+6, y+4, z-2, wood, 0, 0);

			world.setBlock(x+7, y+1, z-2, ladder, 5, 0);
			world.setBlock(x+7, y+2, z-2, ladder, 5, 0);
			world.setBlock(x+7, y+3, z-2, ladder, 5, 0);
			world.setBlock(x+7, y+4, z-2, ladder, 5, 0);
		
			world.setBlock(x+6, y+5, z-2, slab, 0, 0);
			world.setBlock(x+5, y+5, z-2, slab, 0, 0);
			world.setBlock(x+4, y+5, z-2, slab, 0, 0);
			world.setBlock(x+3, y+5, z-2, slab, 0, 0);
		
			world.setBlock(x+6, y+1, z-1, fence);
			world.setBlock(x+6, y+2, z-1, fence);
			world.setBlock(x+6, y+3, z-1, fence);
			world.setBlock(x+6, y+4, z-1, fence);
			world.setBlock(x+6, y+5, z-1, fence);
		
			world.setBlock(x+6, y+1, z-3, fence);
			world.setBlock(x+6, y+2, z-3, fence);
			world.setBlock(x+6, y+3, z-3, fence);
			world.setBlock(x+6, y+4, z-3, fence);
			world.setBlock(x+6, y+5, z-3, fence);

			world.setBlock(x+5, y+5, z-1, fence);
			world.setBlock(x+5, y+5, z-3, fence);
		}
	}
}