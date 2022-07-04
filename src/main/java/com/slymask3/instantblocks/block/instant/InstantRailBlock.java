package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class InstantRailBlock extends InstantBlock {
	public InstantRailBlock() {
		super(Block.Properties.of(Material.DECORATION)
				.strength(0.5F)
				.sound(SoundType.LADDER)
				.noOcclusion()
				.isSuffocating((state, world, pos) -> false)
				.isValidSpawn((state, world, pos, entityType) -> false)
				.isRedstoneConductor((state, world, pos) -> false)
				.isViewBlocking((state, world, pos) -> false)
		, Config.Common.DISABLE_RAIL);
        setCreateMessage(Strings.CREATE_RAIL);
		setDirectional(true);
	}

	public VoxelShape getShape(BlockState p_54372_, BlockGetter p_54373_, BlockPos p_54374_, CollisionContext p_54375_) {
		return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);
    	for(int i = 0; i<= Config.Common.RAILS_AMOUNT.get(); i++) {
			Builder.Single.setup(world,x,y-1,z).offset(direction,i,0,0,0).setStone().build();
			Builder.Single.setup(world,x,y,z).offset(direction,i,0,0,0).setBlock(Blocks.RAIL).build();
			Builder.Single.setup(world,x,y+1,z).offset(direction,i,0,0,0).setBlock(Blocks.AIR).build();
    	}
		return true;
    }
}