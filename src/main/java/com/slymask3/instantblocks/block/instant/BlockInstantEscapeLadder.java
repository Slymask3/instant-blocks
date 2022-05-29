package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import static net.minecraftforge.common.util.ForgeDirection.*;

public class BlockInstantEscapeLadder extends BlockInstant {
	public int side = 0;
	
    public BlockInstantEscapeLadder() {
        super(Names.Blocks.IB_ESCAPE_LADDER, Material.circuits, Block.soundTypeLadder, 0.4F);
        setTextures(Textures.EscapeLadder.SIDE);
        setTickRandomly(true);
        setBlockTextureName(Textures.EscapeLadder.SIDE);
    }

	public boolean canActivate(World world, int x, int y, int z, EntityPlayer player) {
		if(world.canBlockSeeTheSky(x, y+1, z)) {
			IBHelper.msg(player, Strings.ERROR_ESCAPE_LADDER, Colors.c);
			return false;
		}
		return true;
	}

	public void build(World world, int x, int y, int z, EntityPlayer player) {
		Block stone = Blocks.stone;
		Block ladder = Blocks.ladder;
		Block torch = Blocks.torch;
		Block air = Blocks.air;

		int i = y - 1;
		while(!world.canBlockSeeTheSky(x, i+1, z)) {
			i++;
			BuildHelper.build(world, x-1, y-1, z-1, stone, 3, 1, 3);
			BuildHelper.build(world, x-1, i, z-1, stone, 3, 1, 3);
			BuildHelper.setBlock(world,x, i, z, air);

			if(side == 2) {
				BuildHelper.setBlock(world,x, i, z, ladder, 2, 0);

				BuildHelper.setBlock(world,x, y, z-1, air);
				BuildHelper.setBlock(world,x, y+1, z-1, air);

				for(int m = y + 6; m < i; m = m + 6) {
					BuildHelper.setBlock(world,x, m, z-1, torch);
				}
			} else if(side == 3) {
				BuildHelper.setBlock(world,x, i, z, ladder, 3, 0);

				BuildHelper.setBlock(world,x, y, z+1, air);
				BuildHelper.setBlock(world,x, y+1, z+1, air);

				for(int m = y + 6; m < i; m = m + 6) {
					BuildHelper.setBlock(world,x, m, z+1, torch);
				}
			} else if(side == 4) {
				BuildHelper.setBlock(world,x, i, z, ladder, 4, 0);

				BuildHelper.setBlock(world,x-1, y, z, air);
				BuildHelper.setBlock(world,x-1, y+1, z, air);

				for(int m = y + 6; m < i; m = m + 6) {
					BuildHelper.setBlock(world,x-1, m, z, torch);
				}
			} else if(side == 5) {
				BuildHelper.setBlock(world,x, i, z, ladder, 5, 0);

				BuildHelper.setBlock(world,x+1, y, z, air);
				BuildHelper.setBlock(world,x+1, y+1, z, air);

				for(int m = y + 6; m < i; m = m + 6) {
					BuildHelper.setBlock(world,x+1, m, z, torch);
				}
			}
		}
		setCreateMessage(Strings.CREATE_ESCAPE_LADDER.replace("%i%",String.valueOf(i-y)));
	}

	// BlockLadder.class

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		this.setBlockBoundsBasedOnState(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
		return super.getCollisionBoundingBoxFromPool(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
		this.func_149797_b(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
	}

	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
	{
		this.setBlockBoundsBasedOnState(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
		return super.getSelectedBoundingBoxFromPool(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
	}

	public void func_149797_b(int par1) {
		float f = 0.125F;
		if(par1 == 2) {
			this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
			side = 2;
		}
		if(par1 == 3) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
			side = 3;
		}
		if(par1 == 4) {
			this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			side = 4;
		}
		if(par1 == 5) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
			side = 5;
		}
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
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return 8;
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
		return p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_, p_149742_4_, EAST ) ||
				p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_, p_149742_4_, WEST ) ||
				p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ - 1, SOUTH) ||
				p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ + 1, NORTH);
	}

	/**
	 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
	 */
	public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
		int j1 = p_149660_9_;
		if((p_149660_9_ == 0 || p_149660_5_ == 2) && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ + 1, NORTH)) {
			j1 = 2;
		}
		if((j1 == 0 || p_149660_5_ == 3) && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ - 1, SOUTH)) {
			j1 = 3;
		}
		if((j1 == 0 || p_149660_5_ == 4) && p_149660_1_.isSideSolid(p_149660_2_ + 1, p_149660_3_, p_149660_4_, WEST)) {
			j1 = 4;
		}
		if((j1 == 0 || p_149660_5_ == 5) && p_149660_1_.isSideSolid(p_149660_2_ - 1, p_149660_3_, p_149660_4_, EAST)) {
			j1 = 5;
		}
		return j1;
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor Block
	 */
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
		int l = p_149695_1_.getBlockMetadata(p_149695_2_, p_149695_3_, p_149695_4_);
		boolean flag = false;
		if(l == 2 && p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_, p_149695_4_ + 1, NORTH)) {
			flag = true;
		}
		if(l == 3 && p_149695_1_.isSideSolid(p_149695_2_, p_149695_3_, p_149695_4_ - 1, SOUTH)) {
			flag = true;
		}
		if(l == 4 && p_149695_1_.isSideSolid(p_149695_2_ + 1, p_149695_3_, p_149695_4_, WEST)) {
			flag = true;
		}
		if(l == 5 && p_149695_1_.isSideSolid(p_149695_2_ - 1, p_149695_3_, p_149695_4_, EAST)) {
			flag = true;
		}
		if(!flag) {
			this.dropBlockAsItem(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, l, 0);
			p_149695_1_.setBlockToAir(p_149695_2_, p_149695_3_, p_149695_4_);
		}
		super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
	}

	@Override
	public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity)
	{
		return true;
	}
}