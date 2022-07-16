package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Material;

public class InstantPoolBlock extends InstantBlock {
    public InstantPoolBlock() {
		super(Properties.of(Material.STONE)
				.strength(1.5F)
				.sound(SoundType.STONE)
		);
        setCreateMessage(Strings.CREATE_POOL);
		setDirectional(true);
    }

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_POOL();
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		BlockState stone = Blocks.SMOOTH_STONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE);
		Block water = Blocks.WATER;
		Block slab = Blocks.SMOOTH_STONE_SLAB;
		Block glow = Blocks.GLOWSTONE;
		Block ladder = Blocks.LADDER;
		Block wood = Blocks.OAK_PLANKS;
		Block fence = Blocks.OAK_FENCE;
		Block air = Blocks.AIR;

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);
		
		/************************ Layer -5 to 5 : Air ************************/
		Builder.Multiple.setup(world,x-6,y-5,z-6,13,11,13).setBlock(air).build();
		
		/************************ Layer -5 to 0 : Stone ************************/
		Builder.Multiple.setup(world,x-6,y-5,z-6,13,6,13).setBlock(stone).build();
		
		/************************ Layer -4 to 0 : Water ************************/
		Builder.Multiple.setup(world,x-5,y-4,z-5,11,5,11).setBlock(water).build();
		
		/************************ Layer 1 : Stone Slab ************************/
		Builder.Multiple.setup(world,x-6,y+1,z-6,1,1,13).setBlock(slab).build();
		Builder.Multiple.setup(world,x+6,y+1,z-6,1,1,13).setBlock(slab).build();
		Builder.Multiple.setup(world,x-5,y+1,z-6,11,1,1).setBlock(slab).build();
		Builder.Multiple.setup(world,x-5,y+1,z+6,11,1,1).setBlock(slab).build();
		
		/************************ Layer -2 : Glowstone ************************/
		Builder.Single.setup(world,x-6,y-2,z-4).setBlock(glow).build();
		Builder.Single.setup(world,x-6,y-2,z-2).setBlock(glow).build();
		Builder.Single.setup(world,x-6,y-2,z).setBlock(glow).build();
		Builder.Single.setup(world,x-6,y-2,z+2).setBlock(glow).build();
		Builder.Single.setup(world,x-6,y-2,z+4).setBlock(glow).build();

		Builder.Single.setup(world,x+6,y-2,z-4).setBlock(glow).build();
		Builder.Single.setup(world,x+6,y-2,z-2).setBlock(glow).build();
		Builder.Single.setup(world,x+6,y-2,z).setBlock(glow).build();
		Builder.Single.setup(world,x+6,y-2,z+2).setBlock(glow).build();
		Builder.Single.setup(world,x+6,y-2,z+4).setBlock(glow).build();

		Builder.Single.setup(world,x-4,y-2,z-6).setBlock(glow).build();
		Builder.Single.setup(world,x-2,y-2,z-6).setBlock(glow).build();
		Builder.Single.setup(world,x,y-2,z-6).setBlock(glow).build();
		Builder.Single.setup(world,x+2,y-2,z-6).setBlock(glow).build();
		Builder.Single.setup(world,x+4,y-2,z-6).setBlock(glow).build();

		Builder.Single.setup(world,x-4,y-2,z+6).setBlock(glow).build();
		Builder.Single.setup(world,x-2,y-2,z+6).setBlock(glow).build();
		Builder.Single.setup(world,x,y-2,z+6).setBlock(glow).build();
		Builder.Single.setup(world,x+2,y-2,z+6).setBlock(glow).build();
		Builder.Single.setup(world,x+4,y-2,z+6).setBlock(glow).build();
		
		/************************ Layer -5 : Glowstone ************************/
		Builder.Single.setup(world,x-2,y-5,z-2).setBlock(glow).build();
		Builder.Single.setup(world,x-2,y-5,z+2).setBlock(glow).build();
		Builder.Single.setup(world,x+2,y-5,z-2).setBlock(glow).build();
		Builder.Single.setup(world,x+2,y-5,z+2).setBlock(glow).build();

		/************************ Layer 1 to 5 : Diving Board v2.0 ************************/
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,6,2,0).setBlock(wood).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,6,2,0).setBlock(wood).build();

		Builder.Single.setup(world,x,y+1,z).offset(direction,0,7,2,0).setBlock(ladder).setDirection(direction).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,7,2,0).setBlock(ladder).setDirection(direction).build();

		Builder.Single.setup(world,x,y+3,z).offset(direction,0,6,2,0).setBlock(slab).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,5,2,0).setBlock(slab).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,4,2,0).setBlock(slab).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,3,2,0).setBlock(slab).build();

		Builder.Single.setup(world,x,y+1,z).offset(direction,0,6,1,0).setBlock(fence).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,6,1,0).setBlock(fence).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,6,1,0).setBlock(fence).build();

		Builder.Single.setup(world,x,y+1,z).offset(direction,0,6,3,0).setBlock(fence).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,6,3,0).setBlock(fence).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,6,3,0).setBlock(fence).build();

		Builder.Single.setup(world,x,y+3,z).offset(direction,0,5,1,0).setBlock(fence).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,5,3,0).setBlock(fence).build();

		///////////////////////////////////////////////////////////

		Builder.Single.setup(world,x,y+1,z).offset(direction,0,6,0,2).setBlock(wood).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,6,0,2).setBlock(wood).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,6,0,2).setBlock(wood).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,6,0,2).setBlock(wood).build();

		Builder.Single.setup(world,x,y+1,z).offset(direction,0,7,0,2).setBlock(ladder).setDirection(direction).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,7,0,2).setBlock(ladder).setDirection(direction).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,7,0,2).setBlock(ladder).setDirection(direction).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,7,0,2).setBlock(ladder).setDirection(direction).build();

		Builder.Single.setup(world,x,y+5,z).offset(direction,0,6,0,2).setBlock(slab).build();
		Builder.Single.setup(world,x,y+5,z).offset(direction,0,5,0,2).setBlock(slab).build();
		Builder.Single.setup(world,x,y+5,z).offset(direction,0,4,0,2).setBlock(slab).build();
		Builder.Single.setup(world,x,y+5,z).offset(direction,0,3,0,2).setBlock(slab).build();

		Builder.Single.setup(world,x,y+1,z).offset(direction,0,6,0,1).setBlock(fence).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,6,0,1).setBlock(fence).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,6,0,1).setBlock(fence).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,6,0,1).setBlock(fence).build();
		Builder.Single.setup(world,x,y+5,z).offset(direction,0,6,0,1).setBlock(fence).build();

		Builder.Single.setup(world,x,y+1,z).offset(direction,0,6,0,3).setBlock(fence).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,6,0,3).setBlock(fence).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,6,0,3).setBlock(fence).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,6,0,3).setBlock(fence).build();
		Builder.Single.setup(world,x,y+5,z).offset(direction,0,6,0,3).setBlock(fence).build();

		Builder.Single.setup(world,x,y+5,z).offset(direction,0,5,0,1).setBlock(fence).build();
		Builder.Single.setup(world,x,y+5,z).offset(direction,0,5,0,3).setBlock(fence).build();

		return true;
	}
}