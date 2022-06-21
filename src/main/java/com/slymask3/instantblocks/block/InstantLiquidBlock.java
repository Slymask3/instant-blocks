package com.slymask3.instantblocks.block;

import com.mojang.math.Vector3f;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Random;

public abstract class InstantLiquidBlock extends InstantBlock {
	public ArrayList<Helper.Coords> coordsList;
	public Block blockCheck;
	public Block blockReplace;
	public String create;
	public String create1;
	public boolean isSuction = false;
	public ParticleOptions particle = null;

	public static final DustParticleOptions WHITE_DUST = new DustParticleOptions(new Vector3f(Vec3.fromRGB24(0xFFFFFF)), 1.0F);

    public InstantLiquidBlock(BlockBehaviour.Properties properties, Block blockCheck, Block blockReplace) {
        super(properties);
		this.coordsList = new ArrayList<>();
		this.blockCheck = blockCheck;
		this.blockReplace = blockReplace;
    }

	public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
		if(this.particle != null) {
			for(int i=0; i<8; i++) {
				world.addParticle(this.particle, (double)pos.getX() + Math.random(), (double)pos.getY() + 1.2D, (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
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
		return !this.isSuction;
	}

	@Override
	public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidState) {
		return !this.isSuction;
	}

	private int getMax() {
		return isSuction ? Config.Common.MAX_FILL.get() : Config.Common.MAX_LIQUID.get();
	}

	private Block getMainReplaceBlock() {
		if(isSuction) {
			return blockCheck == Blocks.WATER ? ModBlocks.INSTANT_WATER.get() : ModBlocks.INSTANT_LAVA.get();
		}
		return blockReplace;
	}

	public boolean canActivate(Level world, int x, int y, int z, Player player) {
		if(isSuction && Config.Client.SHOW_EFFECTS.get()) {
			world.addParticle(WHITE_DUST, (double)x + 0.5D, (double)y + 1.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.addParticle(WHITE_DUST, (double)x + 1.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.addParticle(WHITE_DUST, (double)x + 0.5D, (double)y + 0.5D, (double)z + 1.2D, 0.0D, 0.0D, 0.0D);
			world.addParticle(WHITE_DUST, (double)x + 0.5D, (double)y - 0.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.addParticle(WHITE_DUST, (double)x - 0.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.addParticle(WHITE_DUST, (double)x + 0.5D, (double)y + 0.5D, (double)z - 0.2D, 0.0D, 0.0D, 0.0D);
		}
		checkForBlock(world,x,y,z);
		if(isSuction && coordsList.isEmpty()) {
			Helper.sendMessage(player, Strings.ERROR_NO_LIQUID, ChatFormatting.RED);
			return false;
		}
		if(coordsList.size() >= getMax()) {
			Helper.sendMessage(player, errorMessage, ChatFormatting.RED);
			coordsList = new ArrayList<>();
			return false;
		} else {
			return true;
		}
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		for(Helper.Coords coords : coordsList) {
			BuildHelper.setBlock(world, coords.getX(), coords.getY(), coords.getZ(), blockReplace);
		}
		BuildHelper.setBlock(world,x, y, z, getMainReplaceBlock());
		if(coordsList.size() > 0) {
			setCreateMessage(create.replace("%i%",String.valueOf(isSuction ? coordsList.size() : coordsList.size()+1)).replace("%type%",blockCheck == Blocks.WATER ? "water" : "lava"));
		} else {
			setCreateMessage(create1.replace("%type%",blockCheck == Blocks.WATER ? "water" : "lava"));
		}
		coordsList = new ArrayList<>();
		if(isSuction) {
			this.blockCheck = null;
		}
		return true;
	}

	private void checkForBlock(Level world, int x, int y, int z) {
		check(world,x+1,y,z);
		check(world,x-1,y,z);
		check(world,x,y,z+1);
		check(world,x,y,z-1);
		if(!Config.Common.SIMPLE_LIQUID.get() || isSuction) {
			check(world,x,y-1,z);
		}
		if(isSuction) {
			check(world,x,y+1,z);
		}
	}

	private void check(Level world, int x, int y, int z) {
		Block blockCurrent = BuildHelper.getBlock(world,x,y,z);
		if(isCorrectBlock(blockCurrent) && coordsList.size() < getMax() && addCoords(x,y,z)) {
			if(blockCheck == null) {
				blockCheck = blockCurrent;
			}
			checkForBlock(world,x,y,z);
		}
	}

	private boolean isCorrectBlock(Block block) {
		if(blockCheck == null) {
			return block == Blocks.WATER || block == Blocks.LAVA;
		} else if(blockCheck == Blocks.AIR) {
			return block == blockCheck || block instanceof BushBlock;
		}
		return block == blockCheck;
	}

	private boolean addCoords(int x, int y, int z) {
		Helper.Coords coords = new Helper.Coords(x,y,z);
		if(!coordsList.contains(coords)) {
			coordsList.add(coords);
			return true;
		}
		return false;
	}
}