package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class SkydiveTPBlock extends Block {
	public SkydiveTPBlock() {
		super(Block.Properties.of(Material.METAL)
				.strength(1.5F)
				.sound(SoundType.METAL)
		);
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		Helper.teleport(world,player,pos.getX(), Helper.getMaxSkydive(world) + 2,pos.getZ(), true);
		return InteractionResult.SUCCESS;
	}
}