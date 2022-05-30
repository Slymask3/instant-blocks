package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.BuildHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockInstantPool extends BlockInstant {
    public BlockInstantPool() {
        super(Names.Blocks.IB_POOL, Material.rock, Block.soundTypeStone, 1.5F);
        setCreateMessage(Strings.CREATE_POOL);
        setBlockTextureName(Textures.Pool.TOP0);
		setDirectional(true);
    }
	
    public static IIcon top0;
    public static IIcon top1;
    public static IIcon top2;
    public static IIcon top3;
    
	public void registerBlockIcons(IIconRegister ir) {
		top0 = ir.registerIcon(Textures.Pool.TOP0); //NORTH
		top1 = ir.registerIcon(Textures.Pool.TOP1); //EAST
		top2 = ir.registerIcon(Textures.Pool.TOP2); //SOUTH
		top3 = ir.registerIcon(Textures.Pool.TOP3); //WEST
	}
    
	public IIcon getIcon(int side, int meta) {
		if(side == 0) {
			return Blocks.stone_slab.getIcon(1, 0);
		} else if(side == 1) {
			if(meta == 0) {
				return top0;
			} else if(meta == 1) {
				return top1;
			} else if(meta == 2) {
				return top2;
			} else if(meta == 3) {
				return top3;
			} else {
				return top0;
			}
		} else if(side == 2) {
			return Blocks.stone_slab.getIcon(1, 0);
		} else if(side == 3) {
			return Blocks.stone_slab.getIcon(1, 0);
		} else if(side == 4) {
			return Blocks.stone_slab.getIcon(1, 0);
		} else if(side == 5) {
			return Blocks.stone_slab.getIcon(1, 0);
		} else {
			return blockIcon;
		}
	}

	public void build(World world, int x, int y, int z, EntityPlayer player) {
		Block stone = Blocks.double_stone_slab;
		Block water = Blocks.water;
		Block slab = Blocks.stone_slab;
		Block glow = Blocks.glowstone;
		Block ladder = Blocks.ladder;
		Block wood = Blocks.planks;
		Block fence = Blocks.fence;
		Block air = Blocks.air;
		
		int meta = world.getBlockMetadata(x, y, z);
		
		/************************ Layer -5 to 5 : Air ************************/
		BuildHelper.build(world, x-6, y-5, z-6, air, 13, 11, 13);
		
		/************************ Layer -5 to 0 : Stone ************************/
		BuildHelper.build(world, x-6, y-5, z-6, stone, 13, 6, 13);
		
		/************************ Layer -4 to 0 : Water ************************/
		BuildHelper.build(world, x-5, y-4, z-5, water, 11, 5, 11);
		
		/************************ Layer 1 : Stone Slab ************************/
		BuildHelper.build(world, x-6, y+1, z-6, slab, 13, 1, 1);
		BuildHelper.build(world, x+6, y+1, z-6, slab, 13, 1, 1);
		BuildHelper.build(world, x-5, y+1, z-6, slab, 1, 1, 11);
		BuildHelper.build(world, x-5, y+1, z+6, slab, 1, 1, 11);
		
		/************************ Layer -2 : Glowstone ************************/
		BuildHelper.setBlock(world,x-6, y-2, z-4, glow);
		BuildHelper.setBlock(world,x-6, y-2, z-2, glow);
		BuildHelper.setBlock(world,x-6, y-2, z, glow);
		BuildHelper.setBlock(world,x-6, y-2, z+2, glow);
		BuildHelper.setBlock(world,x-6, y-2, z+4, glow);

		BuildHelper.setBlock(world,x+6, y-2, z-4, glow);
		BuildHelper.setBlock(world,x+6, y-2, z-2, glow);
		BuildHelper.setBlock(world,x+6, y-2, z, glow);
		BuildHelper.setBlock(world,x+6, y-2, z+2, glow);
		BuildHelper.setBlock(world,x+6, y-2, z+4, glow);

		BuildHelper.setBlock(world,x-4, y-2, z-6, glow);
		BuildHelper.setBlock(world,x-2, y-2, z-6, glow);
		BuildHelper.setBlock(world,x, y-2, z-6, glow);
		BuildHelper.setBlock(world,x+2, y-2, z-6, glow);
		BuildHelper.setBlock(world,x+4, y-2, z-6, glow);

		BuildHelper.setBlock(world,x-4, y-2, z+6, glow);
		BuildHelper.setBlock(world,x-2, y-2, z+6, glow);
		BuildHelper.setBlock(world,x, y-2, z+6, glow);
		BuildHelper.setBlock(world,x+2, y-2, z+6, glow);
		BuildHelper.setBlock(world,x+4, y-2, z+6, glow);
		
		/************************ Layer -5 : Glowstone ************************/
		BuildHelper.setBlock(world,x-2, y-5, z-2, glow);
		BuildHelper.setBlock(world,x-2, y-5, z+2, glow);
		BuildHelper.setBlock(world,x+2, y-5, z-2, glow);
		BuildHelper.setBlock(world,x+2, y-5, z+2, glow);

		/************************ Layer 1 to 5 : Diving Board v2.0 ************************/
		int metaLadder;
		switch(meta) {
			default:
			case 0: metaLadder = 3; break;
			case 1: metaLadder = 4; break;
			case 2: metaLadder = 2; break;
			case 3: metaLadder = 5; break;
		}

		BuildHelper.setBlockDirectional(world,x,y+1,z,wood,meta,0,6,2,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,wood,meta,0,6,2,0);

		BuildHelper.setBlockDirectional(world,x,y+1,z,ladder,meta,0,7,2,0,metaLadder,2);
		BuildHelper.setBlockDirectional(world,x,y+2,z,ladder,meta,0,7,2,0,metaLadder,2);

		BuildHelper.setBlockDirectional(world,x,y+3,z,slab,meta,0,6,2,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,slab,meta,0,5,2,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,slab,meta,0,4,2,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,slab,meta,0,3,2,0);

		BuildHelper.setBlockDirectional(world,x,y+1,z,fence,meta,0,6,1,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,fence,meta,0,6,1,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,meta,0,6,1,0);

		BuildHelper.setBlockDirectional(world,x,y+1,z,fence,meta,0,6,3,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,fence,meta,0,6,3,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,meta,0,6,3,0);

		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,meta,0,5,1,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,meta,0,5,3,0);

		///////////////////////////////////////////////////////////

		BuildHelper.setBlockDirectional(world,x,y+1,z,wood,meta,0,6,0,2);
		BuildHelper.setBlockDirectional(world,x,y+2,z,wood,meta,0,6,0,2);
		BuildHelper.setBlockDirectional(world,x,y+3,z,wood,meta,0,6,0,2);
		BuildHelper.setBlockDirectional(world,x,y+4,z,wood,meta,0,6,0,2);

		BuildHelper.setBlockDirectional(world,x,y+1,z,ladder,meta,0,7,0,2,metaLadder,2);
		BuildHelper.setBlockDirectional(world,x,y+2,z,ladder,meta,0,7,0,2,metaLadder,2);
		BuildHelper.setBlockDirectional(world,x,y+3,z,ladder,meta,0,7,0,2,metaLadder,2);
		BuildHelper.setBlockDirectional(world,x,y+4,z,ladder,meta,0,7,0,2,metaLadder,2);

		BuildHelper.setBlockDirectional(world,x,y+5,z,slab,meta,0,6,0,2);
		BuildHelper.setBlockDirectional(world,x,y+5,z,slab,meta,0,5,0,2);
		BuildHelper.setBlockDirectional(world,x,y+5,z,slab,meta,0,4,0,2);
		BuildHelper.setBlockDirectional(world,x,y+5,z,slab,meta,0,3,0,2);

		BuildHelper.setBlockDirectional(world,x,y+1,z,fence,meta,0,6,0,1);
		BuildHelper.setBlockDirectional(world,x,y+2,z,fence,meta,0,6,0,1);
		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,meta,0,6,0,1);
		BuildHelper.setBlockDirectional(world,x,y+4,z,fence,meta,0,6,0,1);
		BuildHelper.setBlockDirectional(world,x,y+5,z,fence,meta,0,6,0,1);

		BuildHelper.setBlockDirectional(world,x,y+1,z,fence,meta,0,6,0,3);
		BuildHelper.setBlockDirectional(world,x,y+2,z,fence,meta,0,6,0,3);
		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,meta,0,6,0,3);
		BuildHelper.setBlockDirectional(world,x,y+4,z,fence,meta,0,6,0,3);
		BuildHelper.setBlockDirectional(world,x,y+5,z,fence,meta,0,6,0,3);

		BuildHelper.setBlockDirectional(world,x,y+5,z,fence,meta,0,5,0,1);
		BuildHelper.setBlockDirectional(world,x,y+5,z,fence,meta,0,5,0,3);
	}
}