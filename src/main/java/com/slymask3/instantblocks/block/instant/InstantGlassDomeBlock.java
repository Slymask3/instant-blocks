package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class InstantGlassDomeBlock extends InstantBlock {
	public InstantGlassDomeBlock() {
		super(Block.Properties.of(Material.GLASS)
				.strength(0.5F, 2000F)
				.sound(SoundType.GLASS)
				.noOcclusion()
				.isSuffocating((state, world, pos) -> false)
				.isValidSpawn((state, world, pos, entityType) -> false)
				.isRedstoneConductor((state, world, pos) -> false)
				.isViewBlocking((state, world, pos) -> false)
		, Config.Common.DISABLE_GLASS_DOME);
		setCreateMessage(Strings.CREATE_DOME);
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

	public boolean build(Level world, int x, int y, int z, Player player) {
		Block glass = Blocks.GLASS;
		Block torch = Blocks.TORCH;
		Block air = Blocks.AIR;

		int radius = Config.Common.RADIUS_DOME.get();

		Builder.Circle.setup(world,x,y,z,radius).setBlock(Builder.BlockType.stone()).build();
		Builder.Sphere.setup(world,x,y+1,z,radius).setOuter(Builder.BlockType.block(glass)).setInner(Builder.BlockType.block(air)).setHalf().build();

		for(int i=3; i<radius; i=i+6) {
			Builder.Single.setup(world,x+i,y+1,z).setBlock(torch).build();
			Builder.Single.setup(world,x-i,y+1,z).setBlock(torch).build();
			Builder.Single.setup(world,x,y+1,z+i).setBlock(torch).build();
			Builder.Single.setup(world,x,y+1,z-i).setBlock(torch).build();
		}

		return true;
	}
}