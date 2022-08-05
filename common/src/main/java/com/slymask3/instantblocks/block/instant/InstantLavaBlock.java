package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantLiquidBlock;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class InstantLavaBlock extends InstantLiquidBlock {
	public InstantLavaBlock() {
		super(Block.Properties.of(Material.LAVA)
				.strength(0.5F)
				.sound(new LiquidSoundType(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA))
				.lightLevel((par1) -> 15)
		, Blocks.AIR, Blocks.LAVA);
		this.setErrorMessage(Strings.ERROR_LAVA_MAX);
		this.setCreateMessages(Strings.CREATE_LAVA,Strings.CREATE_LAVA_1);
		this.setParticle(ParticleTypes.LARGE_SMOKE);
	}

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_LAVA();
	}

	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        entity.hurt(DamageSource.LAVA, 1);
        entity.setSecondsOnFire(5);
	}
}