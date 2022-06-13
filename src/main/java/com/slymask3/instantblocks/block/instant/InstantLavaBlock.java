package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantLiquidBlock;
import com.slymask3.instantblocks.handler.Config;
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
import net.minecraftforge.common.util.ForgeSoundType;

public class InstantLavaBlock extends InstantLiquidBlock {
	public InstantLavaBlock() {
		super(Block.Properties.of(Material.LAVA)
				.strength(1.5F, 2000F)
				.sound(new ForgeSoundType(1.0F,1.0F, () -> SoundEvents.BUCKET_FILL_LAVA,() -> SoundEvents.BUCKET_EMPTY_LAVA,() -> SoundEvents.BUCKET_EMPTY_LAVA,() -> SoundEvents.BUCKET_EMPTY_LAVA,() -> SoundEvents.BUCKET_EMPTY_LAVA))
				.lightLevel((par1) -> 15)
		, Blocks.AIR, Blocks.LAVA);
		setErrorMessage(Strings.ERROR_LAVA_MAX.replace("%i%",String.valueOf(Config.Common.MAX_LIQUID.get())));
		this.create = Strings.CREATE_LAVA;
		this.create1 = Strings.CREATE_LAVA_1;
		this.particle = ParticleTypes.LARGE_SMOKE;
	}

	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        entity.hurt(DamageSource.LAVA, 1);
        entity.setSecondsOnFire(5);
	}
}