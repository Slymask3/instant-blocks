package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantLiquidBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.util.ForgeSoundType;

public class InstantWaterBlock extends InstantLiquidBlock {
    public InstantWaterBlock() {
        super(Block.Properties.of(Material.WATER)
                .strength(1.5F, 2000F)
                .sound(new ForgeSoundType(1.0F,1.0F, () -> SoundEvents.BUCKET_FILL,() -> SoundEvents.BUCKET_EMPTY,() -> SoundEvents.BUCKET_EMPTY,() -> SoundEvents.BUCKET_EMPTY,() -> SoundEvents.BUCKET_EMPTY))
                .noOcclusion()
                .isSuffocating((state, world, pos) -> false)
                .isValidSpawn((state, world, pos, entityType) -> false)
                .isRedstoneConductor((state, world, pos) -> false)
                .isViewBlocking((state, world, pos) -> false)
        , Config.Common.DISABLE_WATER, Blocks.AIR, Blocks.WATER);
		setErrorMessage(Strings.ERROR_WATER_MAX.replace("%i%",String.valueOf(Config.Common.MAX_LIQUID.get())));
		this.create = Strings.CREATE_WATER;
		this.create1 = Strings.CREATE_WATER_1;
    }

    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        entity.clearFire();
    }
}