package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class InstantPoolBlock extends InstantBlock {
    public InstantPoolBlock() {
		super(Block.Properties.of(Material.STONE)
				.strength(1.5F, 2000F)
				.sound(SoundType.STONE)
		, Config.Common.DISABLE_POOL);
        setCreateMessage(Strings.CREATE_POOL);
		setDirectional(true);
    }

	public boolean build(Level world, int x, int y, int z, Player player) {
		Block stone = Blocks.SMOOTH_STONE;
		Block water = Blocks.WATER;
		Block slab = Blocks.SMOOTH_STONE_SLAB;
		Block glow = Blocks.GLOWSTONE;
		Block ladder = Blocks.LADDER;
		Block wood = Blocks.OAK_PLANKS;
		Block fence = Blocks.OAK_FENCE;
		Block air = Blocks.AIR;

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);
		
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
		Direction directionLadder = direction;

		BuildHelper.setBlockDirectional(world,x,y+1,z,wood,direction,0,6,2,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,wood,direction,0,6,2,0);

		BuildHelper.setBlockDirectional(world,x,y+1,z,ladder,direction,0,7,2,0, directionLadder,2);
		BuildHelper.setBlockDirectional(world,x,y+2,z,ladder,direction,0,7,2,0, directionLadder,2);

		BuildHelper.setBlockDirectional(world,x,y+3,z,slab,direction,0,6,2,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,slab,direction,0,5,2,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,slab,direction,0,4,2,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,slab,direction,0,3,2,0);

		BuildHelper.setBlockDirectional(world,x,y+1,z,fence,direction,0,6,1,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,fence,direction,0,6,1,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,direction,0,6,1,0);

		BuildHelper.setBlockDirectional(world,x,y+1,z,fence,direction,0,6,3,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,fence,direction,0,6,3,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,direction,0,6,3,0);

		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,direction,0,5,1,0);
		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,direction,0,5,3,0);

		///////////////////////////////////////////////////////////

		BuildHelper.setBlockDirectional(world,x,y+1,z,wood,direction,0,6,0,2);
		BuildHelper.setBlockDirectional(world,x,y+2,z,wood,direction,0,6,0,2);
		BuildHelper.setBlockDirectional(world,x,y+3,z,wood,direction,0,6,0,2);
		BuildHelper.setBlockDirectional(world,x,y+4,z,wood,direction,0,6,0,2);

		BuildHelper.setBlockDirectional(world,x,y+1,z,ladder,direction,0,7,0,2,directionLadder,2);
		BuildHelper.setBlockDirectional(world,x,y+2,z,ladder,direction,0,7,0,2,directionLadder,2);
		BuildHelper.setBlockDirectional(world,x,y+3,z,ladder,direction,0,7,0,2,directionLadder,2);
		BuildHelper.setBlockDirectional(world,x,y+4,z,ladder,direction,0,7,0,2,directionLadder,2);

		BuildHelper.setBlockDirectional(world,x,y+5,z,slab,direction,0,6,0,2);
		BuildHelper.setBlockDirectional(world,x,y+5,z,slab,direction,0,5,0,2);
		BuildHelper.setBlockDirectional(world,x,y+5,z,slab,direction,0,4,0,2);
		BuildHelper.setBlockDirectional(world,x,y+5,z,slab,direction,0,3,0,2);

		BuildHelper.setBlockDirectional(world,x,y+1,z,fence,direction,0,6,0,1);
		BuildHelper.setBlockDirectional(world,x,y+2,z,fence,direction,0,6,0,1);
		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,direction,0,6,0,1);
		BuildHelper.setBlockDirectional(world,x,y+4,z,fence,direction,0,6,0,1);
		BuildHelper.setBlockDirectional(world,x,y+5,z,fence,direction,0,6,0,1);

		BuildHelper.setBlockDirectional(world,x,y+1,z,fence,direction,0,6,0,3);
		BuildHelper.setBlockDirectional(world,x,y+2,z,fence,direction,0,6,0,3);
		BuildHelper.setBlockDirectional(world,x,y+3,z,fence,direction,0,6,0,3);
		BuildHelper.setBlockDirectional(world,x,y+4,z,fence,direction,0,6,0,3);
		BuildHelper.setBlockDirectional(world,x,y+5,z,fence,direction,0,6,0,3);

		BuildHelper.setBlockDirectional(world,x,y+5,z,fence,direction,0,5,0,1);
		BuildHelper.setBlockDirectional(world,x,y+5,z,fence,direction,0,5,0,3);

		return true;
	}
}