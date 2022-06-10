package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstantLiquid;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.ForgeSoundType;

import javax.annotation.Nonnull;

public class BlockInstantWater extends BlockInstantLiquid {
    public BlockInstantWater() {
        super(Block.Properties.of(Material.WATER)
                .strength(1.5F, 2000F)
                .sound(new ForgeSoundType(1.0F,1.0F, () -> SoundEvents.BUCKET_FILL,() -> SoundEvents.BUCKET_EMPTY,() -> SoundEvents.BUCKET_EMPTY,() -> SoundEvents.BUCKET_EMPTY,() -> SoundEvents.BUCKET_EMPTY))
                .noOcclusion()
                .isSuffocating((state, world, pos) -> false)
                .isValidSpawn((state, world, pos, entityType) -> false)
                .isRedstoneConductor((state, world, pos) -> false)
                .isViewBlocking((state, world, pos) -> false)
        , Blocks.AIR, Blocks.WATER);
		setErrorMessage(Strings.ERROR_WATER_MAX.replace("%i%",String.valueOf(Config.Common.MAX_LIQUID.get())));
		this.create = Strings.CREATE_WATER;
		this.create1 = Strings.CREATE_WATER_1;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(@Nonnull BlockState state, BlockState adjacentBlockState, @Nonnull Direction side) {
        return adjacentBlockState.is(this) || super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    @Nonnull
    public VoxelShape getVisualShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
        return true;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidState) {
        return true;
    }

    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        entity.clearFire();
    }
}