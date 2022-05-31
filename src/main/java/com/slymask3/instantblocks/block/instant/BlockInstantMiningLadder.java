package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockInstantMiningLadder extends BlockInstant {
    public BlockInstantMiningLadder() {
        super(Names.Blocks.IB_MINING_LADDER, Material.rock, Block.soundTypeStone, 1.5F);
        setBlockTextureName(Textures.MiningLadder.TOP0);
		setCreateMessage(Strings.CREATE_MINING_LADDER);
		setDirectional(true);
    }
	
    public static IIcon top0;
    public static IIcon top1;
    public static IIcon top2;
    public static IIcon top3;
    
	public void registerBlockIcons(IIconRegister ir) {
		top0 = ir.registerIcon(Textures.MiningLadder.TOP0); //NORTH
		top1 = ir.registerIcon(Textures.MiningLadder.TOP1); //EAST
		top2 = ir.registerIcon(Textures.MiningLadder.TOP2); //SOUTH
		top3 = ir.registerIcon(Textures.MiningLadder.TOP3); //WEST
	}
    
	public IIcon getIcon(int side, int meta) {
		if(side != 1) {
			return Blocks.stone.getIcon(0, 0);
		} else if(side == 1) {
			if(meta == 0) {
				return top0;
			} else if(meta == 1) {
				return top1;
			} else if(meta == 2) {
				return top2;
			} else if(meta == 3) {
				return top3;
			}
		}
		
		return blockIcon;
	}

	public boolean canActivate(World world, int x, int y, int z, EntityPlayer player) {
		if(y <= Config.MINING_LADDER_LAYER + 4) {
			IBHelper.msg(player, Strings.ERROR_LADDER.replace("%i%",String.valueOf(Config.MINING_LADDER_LAYER + 4)), Colors.c);
			return false;
		}
		return true;
	}

	public void build(World world, int x, int y, int z, EntityPlayer player) {
		Block ladder = Blocks.ladder;
		Block stone = Blocks.stone;
		Block torch = Blocks.torch;
		Block water = Blocks.water;
		Block sign = Blocks.wall_sign;

		int layer = Config.MINING_LADDER_LAYER;

		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 0) { //NORTH
			for(int c = y; c > layer-2; c--) {
				BuildHelper.build(world, x-2, c, z-1, stone, 3, 1, 5); //STONE
			}
			for(int c = y; c > layer-1; c--) {
				BuildHelper.setBlock(world,x-1, c, z, ladder, 5, 0); //LADDERS 5
			}
			for(int c = y; c > layer-1; c--) {
				BuildHelper.setBlock(world,x+1, c, z, Blocks.air); //AIR
			}
			for(int c = y; c > layer+1; c = c - 3) {
				c = c - 3;
				BuildHelper.setBlock(world,x, c, z, torch); //TORCHES
			}

			BuildHelper.setBlock(world,x, layer, z, Blocks.air); //MIDDLE AIR
			BuildHelper.setBlock(world,x, layer+1, z, torch); //MIDDLE TORCH
			BuildHelper.setBlock(world,x, layer+2, z, stone); //ABOVE MIDDLE TORCH
			BuildHelper.setBlock(world,x+1, layer+2, z, water); //WATER
			BuildHelper.setBlock(world,x+1, layer+1, z, sign, 2, 0); //SIGN 2
		} else if(meta == 1) { //EAST
			for(int c = y; c > layer-2; c--) {
				BuildHelper.build(world, x-1, c, z-2, stone, 5, 1, 3); //STONE
			}
			for(int c = y; c > layer-1; c--) {
				BuildHelper.setBlock(world,x, c, z-1, ladder, 3, 0); //LADDERS 3
			}
			for(int c = y; c > layer-1; c--) {
				BuildHelper.setBlock(world,x, c, z+1, Blocks.air); //AIR
			}
			for(int c = y; c > layer+1; c = c - 3) {
				c = c - 3;
				BuildHelper.setBlock(world,x, c, z, torch); //TORCHES
			}

			BuildHelper.setBlock(world,x, layer, z, Blocks.air); //MIDDLE AIR
			BuildHelper.setBlock(world,x, layer+1, z, torch); //MIDDLE TORCH
			BuildHelper.setBlock(world,x, layer+2, z, stone); //ABOVE MIDDLE TORCH
			BuildHelper.setBlock(world,x, layer+2, z+1, water); //WATER
			BuildHelper.setBlock(world,x, layer+1, z+1, sign, 5, 0); //SIGN 5
		} else if(meta == 2) { //SOUTH
			for(int c = y; c > layer-2; c--) {
				BuildHelper.build(world, x-2, c, z-1, stone, 3, 1, 5); //STONE
			}
			for(int c = y; c > layer-1; c--) {
				BuildHelper.setBlock(world,x+1, c, z, ladder, 4, 0); //LADDERS 4
			}
			for(int c = y; c > layer-1; c--) {
				BuildHelper.setBlock(world,x-1, c, z, Blocks.air); //AIR
			}
			for(int c = y; c > layer+1; c = c - 3) {
				c = c - 3;
				BuildHelper.setBlock(world,x, c, z, torch); //TORCHES
			}

			BuildHelper.setBlock(world,x, layer, z, Blocks.air); //MIDDLE AIR
			BuildHelper.setBlock(world,x, layer+1, z, torch); //MIDDLE TORCH
			BuildHelper.setBlock(world,x, layer+2, z, stone); //ABOVE MIDDLE TORCH
			BuildHelper.setBlock(world,x-1, layer+2, z, water); //WATER
			BuildHelper.setBlock(world,x-1, layer+1, z, sign, 3, 0); //SIGN 3
		} else if(meta == 3) { //WEST (ORIGINAL)
			for(int c = y; c > layer-2; c--) {
				BuildHelper.build(world, x-1, c, z-2, stone, 5, 1, 3); //STONE
			}
			for(int c = y; c > layer-1; c--) {
				BuildHelper.setBlock(world,x, c, z+1, ladder, 2, 0); //LADDERS 2
			}
			for(int c = y; c > layer-1; c--) {
				BuildHelper.setBlock(world,x, c, z-1, Blocks.air); //AIR
			}
			for(int c = y; c > layer+1; c = c - 3) {
				c = c - 3;
				BuildHelper.setBlock(world,x, c, z, torch); //TORCHES
			}

			BuildHelper.setBlock(world,x, layer, z, Blocks.air); //MIDDLE AIR
			BuildHelper.setBlock(world,x, layer+1, z, torch); //MIDDLE TORCH
			BuildHelper.setBlock(world,x, layer+2, z, stone); //ABOVE MIDDLE TORCH
			BuildHelper.setBlock(world,x, layer+2, z-1, water); //WATER
			BuildHelper.setBlock(world,x, layer+1, z-1, sign, 4, 0); //SIGN 4
		}

		BuildHelper.setBlock(world,x, layer-1, z, stone); //MIDDLE STONE
	}
}