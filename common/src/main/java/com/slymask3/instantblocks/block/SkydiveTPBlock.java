package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SkydiveTPBlock extends ColorBlock {
	public SkydiveTPBlock() {
		super();
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		Helper.teleport(world,player,pos.getX(), Helper.getMaxSkydive(world) + 2,pos.getZ());
		return InteractionResult.SUCCESS;
	}
}