package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.reference.Strings;
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
		super(Properties.of(Material.DECORATION)
				.strength(0.5F)
				.sound(SoundType.LADDER)
				.noOcclusion()
				.isSuffocating((state, world, pos) -> false)
				.isValidSpawn((state, world, pos, entityType) -> false)
				.isRedstoneConductor((state, world, pos) -> false)
				.isViewBlocking((state, world, pos) -> false)
		);
        setCreateMessage(Strings.CREATE_RAIL);
		setDirectional(true);
	}

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_RAIL();
	}

	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Builder builder = Builder.setup(world,x,y,z);
		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);
    	for(int i = 0; i<= Common.CONFIG.RAILS_AMOUNT(); i++) {
			Single.setup(builder,world,x,y-1,z).offset(direction,i,0,0,0).setStone().queue(i);
			Single.setup(builder,world,x,y,z).offset(direction,i,0,0,0).setBlock(Blocks.RAIL).queue(i);
			Single.setup(builder,world,x,y+1,z).offset(direction,i,0,0,0).setBlock(Blocks.AIR).queue(i);
    	}
		builder.build();
		return true;
    }
}