package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
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
		);
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
		Block stone = Blocks.STONE;
		Block torch = Blocks.TORCH;
		Block air = Blocks.AIR;
		
		/************************ Layer 0 ************************/
		BuildHelper.setBlock(world,x-4,y,z+2,stone);
		BuildHelper.setBlock(world,x-4,y,z+1,stone);
		BuildHelper.setBlock(world,x-4,y,z,stone);
		BuildHelper.setBlock(world,x-4,y,z-1,stone);
		BuildHelper.setBlock(world,x-4,y,z-2,stone);

		BuildHelper.setBlock(world,x-3,y,z+3,stone);
		BuildHelper.setBlock(world,x-3,y,z+2,stone);
		BuildHelper.setBlock(world,x-3,y,z+1,stone);
		BuildHelper.setBlock(world,x-3,y,z,stone);
		BuildHelper.setBlock(world,x-3,y,z-1,stone);
		BuildHelper.setBlock(world,x-3,y,z-2,stone);
		BuildHelper.setBlock(world,x-3,y,z-3,stone);

		BuildHelper.setBlock(world,x-2,y,z+4,stone);
		BuildHelper.setBlock(world,x-2,y,z+3,stone);
		BuildHelper.setBlock(world,x-2,y,z+2,stone);
		BuildHelper.setBlock(world,x-2,y,z+1,stone);
		BuildHelper.setBlock(world,x-2,y,z,stone);
		BuildHelper.setBlock(world,x-2,y,z-1,stone);
		BuildHelper.setBlock(world,x-2,y,z-2,stone);
		BuildHelper.setBlock(world,x-2,y,z-3,stone);
		BuildHelper.setBlock(world,x-2,y,z-4,stone);

		BuildHelper.setBlock(world,x-1,y,z+4,stone);
		BuildHelper.setBlock(world,x-1,y,z+3,stone);
		BuildHelper.setBlock(world,x-1,y,z+2,stone);
		BuildHelper.setBlock(world,x-1,y,z+1,stone);
		BuildHelper.setBlock(world,x-1,y,z,stone);
		BuildHelper.setBlock(world,x-1,y,z-1,stone);
		BuildHelper.setBlock(world,x-1,y,z-2,stone);
		BuildHelper.setBlock(world,x-1,y,z-3,stone);
		BuildHelper.setBlock(world,x-1,y,z-4,stone);

		BuildHelper.setBlock(world,x,y,z+4,stone);
		BuildHelper.setBlock(world,x,y,z+3,stone);
		BuildHelper.setBlock(world,x,y,z+2,stone);
		BuildHelper.setBlock(world,x,y,z+1,stone);
		BuildHelper.setBlock(world,x,y,z,stone); //0, 0, 0
		BuildHelper.setBlock(world,x,y,z-1,stone);
		BuildHelper.setBlock(world,x,y,z-2,stone);
		BuildHelper.setBlock(world,x,y,z-3,stone);
		BuildHelper.setBlock(world,x,y,z-4,stone);

		BuildHelper.setBlock(world,x+1,y,z+4,stone);
		BuildHelper.setBlock(world,x+1,y,z+3,stone);
		BuildHelper.setBlock(world,x+1,y,z+2,stone);
		BuildHelper.setBlock(world,x+1,y,z+1,stone);
		BuildHelper.setBlock(world,x+1,y,z,stone);
		BuildHelper.setBlock(world,x+1,y,z-1,stone);
		BuildHelper.setBlock(world,x+1,y,z-2,stone);
		BuildHelper.setBlock(world,x+1,y,z-3,stone);
		BuildHelper.setBlock(world,x+1,y,z-4,stone);

		BuildHelper.setBlock(world,x+2,y,z+4,stone);
		BuildHelper.setBlock(world,x+2,y,z+3,stone);
		BuildHelper.setBlock(world,x+2,y,z+2,stone);
		BuildHelper.setBlock(world,x+2,y,z+1,stone);
		BuildHelper.setBlock(world,x+2,y,z,stone);
		BuildHelper.setBlock(world,x+2,y,z-1,stone);
		BuildHelper.setBlock(world,x+2,y,z-2,stone);
		BuildHelper.setBlock(world,x+2,y,z-3,stone);
		BuildHelper.setBlock(world,x+2,y,z-4,stone);

		BuildHelper.setBlock(world,x+3,y,z+3,stone);
		BuildHelper.setBlock(world,x+3,y,z+2,stone);
		BuildHelper.setBlock(world,x+3,y,z+1,stone);
		BuildHelper.setBlock(world,x+3,y,z,stone);
		BuildHelper.setBlock(world,x+3,y,z-1,stone);
		BuildHelper.setBlock(world,x+3,y,z-2,stone);
		BuildHelper.setBlock(world,x+3,y,z-3,stone);
		
		BuildHelper.setBlock(world,x+4,y,z+2,stone);
		BuildHelper.setBlock(world,x+4,y,z+1,stone);
		BuildHelper.setBlock(world,x+4,y,z,stone);
		BuildHelper.setBlock(world,x+4,y,z-1,stone);
		BuildHelper.setBlock(world,x+4,y,z-2,stone);
		
		/************************ Layer 1 ************************/
		BuildHelper.setBlock(world,x-4,y+1,z+2,glass);
		BuildHelper.setBlock(world,x-4,y+1,z+1,glass);
		BuildHelper.setBlock(world,x-4,y+1,z,glass);
		BuildHelper.setBlock(world,x-4,y+1,z-1,glass);
		BuildHelper.setBlock(world,x-4,y+1,z-2,glass);

		BuildHelper.setBlock(world,x-3,y+1,z+3,glass);
		BuildHelper.setBlock(world,x-3,y+1,z+2,glass);
		BuildHelper.setBlock(world,x-3,y+1,z+1,air);
		BuildHelper.setBlock(world,x-3,y+1,z,torch);
		BuildHelper.setBlock(world,x-3,y+1,z-1,air);
		BuildHelper.setBlock(world,x-3,y+1,z-2,glass);
		BuildHelper.setBlock(world,x-3,y+1,z-3,glass);

		BuildHelper.setBlock(world,x-2,y+1,z+4,glass);
		BuildHelper.setBlock(world,x-2,y+1,z+3,glass);
		BuildHelper.setBlock(world,x-2,y+1,z+2,air);
		BuildHelper.setBlock(world,x-2,y+1,z+1,air);
		BuildHelper.setBlock(world,x-2,y+1,z,air);
		BuildHelper.setBlock(world,x-2,y+1,z-1,air);
		BuildHelper.setBlock(world,x-2,y+1,z-2,air);
		BuildHelper.setBlock(world,x-2,y+1,z-3,glass);
		BuildHelper.setBlock(world,x-2,y+1,z-4,glass);

		BuildHelper.setBlock(world,x-1,y+1,z+4,glass);
		BuildHelper.setBlock(world,x-1,y+1,z+3,air);
		BuildHelper.setBlock(world,x-1,y+1,z+2,air);
		BuildHelper.setBlock(world,x-1,y+1,z+1,air);
		BuildHelper.setBlock(world,x-1,y+1,z,air);
		BuildHelper.setBlock(world,x-1,y+1,z-1,air);
		BuildHelper.setBlock(world,x-1,y+1,z-2,air);
		BuildHelper.setBlock(world,x-1,y+1,z-3,air);
		BuildHelper.setBlock(world,x-1,y+1,z-4,glass);

		BuildHelper.setBlock(world,x,y+1,z+4,glass);
		BuildHelper.setBlock(world,x,y+1,z+3,air);
		BuildHelper.setBlock(world,x,y+1,z+2,torch);
		BuildHelper.setBlock(world,x,y+1,z+1,air);
		BuildHelper.setBlock(world,x,y+1,z,air);
		BuildHelper.setBlock(world,x,y+1,z-1,air);
		BuildHelper.setBlock(world,x,y+1,z-2,torch);
		BuildHelper.setBlock(world,x,y+1,z-3,air);
		BuildHelper.setBlock(world,x,y+1,z-4,glass);

		BuildHelper.setBlock(world,x+1,y+1,z+4,glass);
		BuildHelper.setBlock(world,x+1,y+1,z+3,air);
		BuildHelper.setBlock(world,x+1,y+1,z+2,air);
		BuildHelper.setBlock(world,x+1,y+1,z+1,air);
		BuildHelper.setBlock(world,x+1,y+1,z,air);
		BuildHelper.setBlock(world,x+1,y+1,z-1,air);
		BuildHelper.setBlock(world,x+1,y+1,z-2,air);
		BuildHelper.setBlock(world,x+1,y+1,z-3,air);
		BuildHelper.setBlock(world,x+1,y+1,z-4,glass);

		BuildHelper.setBlock(world,x+2,y+1,z+4,glass);
		BuildHelper.setBlock(world,x+2,y+1,z+3,glass);
		BuildHelper.setBlock(world,x+2,y+1,z+2,air);
		BuildHelper.setBlock(world,x+2,y+1,z+1,air);
		BuildHelper.setBlock(world,x+2,y+1,z,air);
		BuildHelper.setBlock(world,x+2,y+1,z-1,air);
		BuildHelper.setBlock(world,x+2,y+1,z-2,air);
		BuildHelper.setBlock(world,x+2,y+1,z-3,glass);
		BuildHelper.setBlock(world,x+2,y+1,z-4,glass);

		BuildHelper.setBlock(world,x+3,y+1,z+3,glass);
		BuildHelper.setBlock(world,x+3,y+1,z+2,glass);
		BuildHelper.setBlock(world,x+3,y+1,z+1,air);
		BuildHelper.setBlock(world,x+3,y+1,z,torch);
		BuildHelper.setBlock(world,x+3,y+1,z-1,air);
		BuildHelper.setBlock(world,x+3,y+1,z-2,glass);
		BuildHelper.setBlock(world,x+3,y+1,z-3,glass);
		
		BuildHelper.setBlock(world,x+4,y+1,z+2,glass);
		BuildHelper.setBlock(world,x+4,y+1,z+1,glass);
		BuildHelper.setBlock(world,x+4,y+1,z,glass);
		BuildHelper.setBlock(world,x+4,y+1,z-1,glass);
		BuildHelper.setBlock(world,x+4,y+1,z-2,glass);
		
		/************************ Layer 2 ************************/
		BuildHelper.setBlock(world,x-4,y+2,z+2,glass);
		BuildHelper.setBlock(world,x-4,y+2,z+1,glass);
		BuildHelper.setBlock(world,x-4,y+2,z,glass);
		BuildHelper.setBlock(world,x-4,y+2,z-1,glass);
		BuildHelper.setBlock(world,x-4,y+2,z-2,glass);

		BuildHelper.setBlock(world,x-3,y+2,z+3,glass);
		BuildHelper.setBlock(world,x-3,y+2,z+2,glass);
		BuildHelper.setBlock(world,x-3,y+2,z+1,air);
		BuildHelper.setBlock(world,x-3,y+2,z,air);
		BuildHelper.setBlock(world,x-3,y+2,z-1,air);
		BuildHelper.setBlock(world,x-3,y+2,z-2,glass);
		BuildHelper.setBlock(world,x-3,y+2,z-3,glass);

		BuildHelper.setBlock(world,x-2,y+2,z+4,glass);
		BuildHelper.setBlock(world,x-2,y+2,z+3,glass);
		BuildHelper.setBlock(world,x-2,y+2,z+2,air);
		BuildHelper.setBlock(world,x-2,y+2,z+1,air);
		BuildHelper.setBlock(world,x-2,y+2,z,air);
		BuildHelper.setBlock(world,x-2,y+2,z-1,air);
		BuildHelper.setBlock(world,x-2,y+2,z-2,air);
		BuildHelper.setBlock(world,x-2,y+2,z-3,glass);
		BuildHelper.setBlock(world,x-2,y+2,z-4,glass);

		BuildHelper.setBlock(world,x-1,y+2,z+4,glass);
		BuildHelper.setBlock(world,x-1,y+2,z+3,air);
		BuildHelper.setBlock(world,x-1,y+2,z+2,air);
		BuildHelper.setBlock(world,x-1,y+2,z+1,air);
		BuildHelper.setBlock(world,x-1,y+2,z,air);
		BuildHelper.setBlock(world,x-1,y+2,z-1,air);
		BuildHelper.setBlock(world,x-1,y+2,z-2,air);
		BuildHelper.setBlock(world,x-1,y+2,z-3,air);
		BuildHelper.setBlock(world,x-1,y+2,z-4,glass);

		BuildHelper.setBlock(world,x,y+2,z+4,glass);
		BuildHelper.setBlock(world,x,y+2,z+3,air);
		BuildHelper.setBlock(world,x,y+2,z+2,air);
		BuildHelper.setBlock(world,x,y+2,z+1,air);
		BuildHelper.setBlock(world,x,y+2,z,air);
		BuildHelper.setBlock(world,x,y+2,z-1,air);
		BuildHelper.setBlock(world,x,y+2,z-2,air);
		BuildHelper.setBlock(world,x,y+2,z-3,air);
		BuildHelper.setBlock(world,x,y+2,z-4,glass);

		BuildHelper.setBlock(world,x+1,y+2,z+4,glass);
		BuildHelper.setBlock(world,x+1,y+2,z+3,air);
		BuildHelper.setBlock(world,x+1,y+2,z+2,air);
		BuildHelper.setBlock(world,x+1,y+2,z+1,air);
		BuildHelper.setBlock(world,x+1,y+2,z,air);
		BuildHelper.setBlock(world,x+1,y+2,z-1,air);
		BuildHelper.setBlock(world,x+1,y+2,z-2,air);
		BuildHelper.setBlock(world,x+1,y+2,z-3,air);
		BuildHelper.setBlock(world,x+1,y+2,z-4,glass);

		BuildHelper.setBlock(world,x+2,y+2,z+4,glass);
		BuildHelper.setBlock(world,x+2,y+2,z+3,glass);
		BuildHelper.setBlock(world,x+2,y+2,z+2,air);
		BuildHelper.setBlock(world,x+2,y+2,z+1,air);
		BuildHelper.setBlock(world,x+2,y+2,z,air);
		BuildHelper.setBlock(world,x+2,y+2,z-1,air);
		BuildHelper.setBlock(world,x+2,y+2,z-2,air);
		BuildHelper.setBlock(world,x+2,y+2,z-3,glass);
		BuildHelper.setBlock(world,x+2,y+2,z-4,glass);

		BuildHelper.setBlock(world,x+3,y+2,z+3,glass);
		BuildHelper.setBlock(world,x+3,y+2,z+2,glass);
		BuildHelper.setBlock(world,x+3,y+2,z+1,air);
		BuildHelper.setBlock(world,x+3,y+2,z,air);
		BuildHelper.setBlock(world,x+3,y+2,z-1,air);
		BuildHelper.setBlock(world,x+3,y+2,z-2,glass);
		BuildHelper.setBlock(world,x+3,y+2,z-3,glass);
		
		BuildHelper.setBlock(world,x+4,y+2,z+2,glass);
		BuildHelper.setBlock(world,x+4,y+2,z+1,glass);
		BuildHelper.setBlock(world,x+4,y+2,z,glass);
		BuildHelper.setBlock(world,x+4,y+2,z-1,glass);
		BuildHelper.setBlock(world,x+4,y+2,z-2,glass);
		
		/************************ Layer 3 ************************/
		BuildHelper.setBlock(world,x-4,y+3,z+2,glass);
		BuildHelper.setBlock(world,x-4,y+3,z+1,glass);
		BuildHelper.setBlock(world,x-4,y+3,z,glass);
		BuildHelper.setBlock(world,x-4,y+3,z-1,glass);
		BuildHelper.setBlock(world,x-4,y+3,z-2,glass);

		BuildHelper.setBlock(world,x-3,y+3,z+3,glass);
		BuildHelper.setBlock(world,x-3,y+3,z+2,glass);
		BuildHelper.setBlock(world,x-3,y+3,z+1,air);
		BuildHelper.setBlock(world,x-3,y+3,z,air);
		BuildHelper.setBlock(world,x-3,y+3,z-1,air);
		BuildHelper.setBlock(world,x-3,y+3,z-2,glass);
		BuildHelper.setBlock(world,x-3,y+3,z-3,glass);

		BuildHelper.setBlock(world,x-2,y+3,z+4,glass);
		BuildHelper.setBlock(world,x-2,y+3,z+3,glass);
		BuildHelper.setBlock(world,x-2,y+3,z+2,air);
		BuildHelper.setBlock(world,x-2,y+3,z+1,air);
		BuildHelper.setBlock(world,x-2,y+3,z,air);
		BuildHelper.setBlock(world,x-2,y+3,z-1,air);
		BuildHelper.setBlock(world,x-2,y+3,z-2,air);
		BuildHelper.setBlock(world,x-2,y+3,z-3,glass);
		BuildHelper.setBlock(world,x-2,y+3,z-4,glass);

		BuildHelper.setBlock(world,x-1,y+3,z+4,glass);
		BuildHelper.setBlock(world,x-1,y+3,z+3,air);
		BuildHelper.setBlock(world,x-1,y+3,z+2,air);
		BuildHelper.setBlock(world,x-1,y+3,z+1,air);
		BuildHelper.setBlock(world,x-1,y+3,z,air);
		BuildHelper.setBlock(world,x-1,y+3,z-1,air);
		BuildHelper.setBlock(world,x-1,y+3,z-2,air);
		BuildHelper.setBlock(world,x-1,y+3,z-3,air);
		BuildHelper.setBlock(world,x-1,y+3,z-4,glass);

		BuildHelper.setBlock(world,x,y+3,z+4,glass);
		BuildHelper.setBlock(world,x,y+3,z+3,air);
		BuildHelper.setBlock(world,x,y+3,z+2,air);
		BuildHelper.setBlock(world,x,y+3,z+1,air);
		BuildHelper.setBlock(world,x,y+3,z,air);
		BuildHelper.setBlock(world,x,y+3,z-1,air);
		BuildHelper.setBlock(world,x,y+3,z-2,air);
		BuildHelper.setBlock(world,x,y+3,z-3,air);
		BuildHelper.setBlock(world,x,y+3,z-4,glass);

		BuildHelper.setBlock(world,x+1,y+3,z+4,glass);
		BuildHelper.setBlock(world,x+1,y+3,z+3,air);
		BuildHelper.setBlock(world,x+1,y+3,z+2,air);
		BuildHelper.setBlock(world,x+1,y+3,z+1,air);
		BuildHelper.setBlock(world,x+1,y+3,z,air);
		BuildHelper.setBlock(world,x+1,y+3,z-1,air);
		BuildHelper.setBlock(world,x+1,y+3,z-2,air);
		BuildHelper.setBlock(world,x+1,y+3,z-3,air);
		BuildHelper.setBlock(world,x+1,y+3,z-4,glass);

		BuildHelper.setBlock(world,x+2,y+3,z+4,glass);
		BuildHelper.setBlock(world,x+2,y+3,z+3,glass);
		BuildHelper.setBlock(world,x+2,y+3,z+2,air);
		BuildHelper.setBlock(world,x+2,y+3,z+1,air);
		BuildHelper.setBlock(world,x+2,y+3,z,air);
		BuildHelper.setBlock(world,x+2,y+3,z-1,air);
		BuildHelper.setBlock(world,x+2,y+3,z-2,air);
		BuildHelper.setBlock(world,x+2,y+3,z-3,glass);
		BuildHelper.setBlock(world,x+2,y+3,z-4,glass);

		BuildHelper.setBlock(world,x+3,y+3,z+3,glass);
		BuildHelper.setBlock(world,x+3,y+3,z+2,glass);
		BuildHelper.setBlock(world,x+3,y+3,z+1,air);
		BuildHelper.setBlock(world,x+3,y+3,z,air);
		BuildHelper.setBlock(world,x+3,y+3,z-1,air);
		BuildHelper.setBlock(world,x+3,y+3,z-2,glass);
		BuildHelper.setBlock(world,x+3,y+3,z-3,glass);
		
		BuildHelper.setBlock(world,x+4,y+3,z+2,glass);
		BuildHelper.setBlock(world,x+4,y+3,z+1,glass);
		BuildHelper.setBlock(world,x+4,y+3,z,glass);
		BuildHelper.setBlock(world,x+4,y+3,z-1,glass);
		BuildHelper.setBlock(world,x+4,y+3,z-2,glass);
		
		/************************ Layer 4 ************************/
		BuildHelper.setBlock(world,x-3,y+4,z+1,glass);
		BuildHelper.setBlock(world,x-3,y+4,z,glass);
		BuildHelper.setBlock(world,x-3,y+4,z-1,glass);
		
		BuildHelper.setBlock(world,x-2,y+4,z+2,glass);
		BuildHelper.setBlock(world,x-2,y+4,z+1,air);
		BuildHelper.setBlock(world,x-2,y+4,z,air);
		BuildHelper.setBlock(world,x-2,y+4,z-1,air);
		BuildHelper.setBlock(world,x-2,y+4,z-2,glass);

		BuildHelper.setBlock(world,x-1,y+4,z+3,glass);
		BuildHelper.setBlock(world,x-1,y+4,z+2,air);
		BuildHelper.setBlock(world,x-1,y+4,z+1,air);
		BuildHelper.setBlock(world,x-1,y+4,z,air);
		BuildHelper.setBlock(world,x-1,y+4,z-1,air);
		BuildHelper.setBlock(world,x-1,y+4,z-2,air);
		BuildHelper.setBlock(world,x-1,y+4,z-3,glass);

		BuildHelper.setBlock(world,x,y+4,z+3,glass);
		BuildHelper.setBlock(world,x,y+4,z+2,air);
		BuildHelper.setBlock(world,x,y+4,z+1,air);
		BuildHelper.setBlock(world,x,y+4,z,air);
		BuildHelper.setBlock(world,x,y+4,z-1,air);
		BuildHelper.setBlock(world,x,y+4,z-2,air);
		BuildHelper.setBlock(world,x,y+4,z-3,glass);

		BuildHelper.setBlock(world,x+1,y+4,z+3,glass);
		BuildHelper.setBlock(world,x+1,y+4,z+2,air);
		BuildHelper.setBlock(world,x+1,y+4,z+1,air);
		BuildHelper.setBlock(world,x+1,y+4,z,air);
		BuildHelper.setBlock(world,x+1,y+4,z-1,air);
		BuildHelper.setBlock(world,x+1,y+4,z-2,air);
		BuildHelper.setBlock(world,x+1,y+4,z-3,glass);

		BuildHelper.setBlock(world,x+2,y+4,z+2,glass);
		BuildHelper.setBlock(world,x+2,y+4,z+1,air);
		BuildHelper.setBlock(world,x+2,y+4,z,air);
		BuildHelper.setBlock(world,x+2,y+4,z-1,air);
		BuildHelper.setBlock(world,x+2,y+4,z-2,glass);

		BuildHelper.setBlock(world,x+3,y+4,z+1,glass);
		BuildHelper.setBlock(world,x+3,y+4,z,glass);
		BuildHelper.setBlock(world,x+3,y+4,z-1,glass);
		
		/************************ Layer 5 ************************/
		BuildHelper.setBlock(world,x-2,y+5,z+1,glass);
		BuildHelper.setBlock(world,x-2,y+5,z,glass);
		BuildHelper.setBlock(world,x-2,y+5,z-1,glass);

		BuildHelper.setBlock(world,x-1,y+5,z+2,glass);
		BuildHelper.setBlock(world,x-1,y+5,z+1,air);
		BuildHelper.setBlock(world,x-1,y+5,z,air);
		BuildHelper.setBlock(world,x-1,y+5,z-1,air);
		BuildHelper.setBlock(world,x-1,y+5,z-2,glass);

		BuildHelper.setBlock(world,x,y+5,z+2,glass);
		BuildHelper.setBlock(world,x,y+5,z+1,air);
		BuildHelper.setBlock(world,x,y+5,z,air);
		BuildHelper.setBlock(world,x,y+5,z-1,air);
		BuildHelper.setBlock(world,x,y+5,z-2,glass);

		BuildHelper.setBlock(world,x+1,y+5,z+2,glass);
		BuildHelper.setBlock(world,x+1,y+5,z+1,air);
		BuildHelper.setBlock(world,x+1,y+5,z,air);
		BuildHelper.setBlock(world,x+1,y+5,z-1,air);
		BuildHelper.setBlock(world,x+1,y+5,z-2,glass);

		BuildHelper.setBlock(world,x+2,y+5,z+1,glass);
		BuildHelper.setBlock(world,x+2,y+5,z,glass);
		BuildHelper.setBlock(world,x+2,y+5,z-1,glass);
		
		/************************ Layer 5 (2) ************************/
		BuildHelper.setBlock(world,x-1,y+5,z+1,glass);
		BuildHelper.setBlock(world,x-1,y+5,z,glass);
		BuildHelper.setBlock(world,x-1,y+5,z-1,glass);

		BuildHelper.setBlock(world,x,y+5,z+1,glass);
		BuildHelper.setBlock(world,x,y+5,z,glass);
		BuildHelper.setBlock(world,x,y+5,z-1,glass);

		BuildHelper.setBlock(world,x+1,y+5,z+1,glass);
		BuildHelper.setBlock(world,x+1,y+5,z,glass);
		BuildHelper.setBlock(world,x+1,y+5,z-1,glass);

		return true;
	}
}