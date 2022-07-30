package com.slymask3.instantblocks.block;

import com.mojang.math.Vector3f;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public abstract class InstantLiquidBlock extends InstantBlock {
	public ArrayList<BlockPos> posList;
	public Block blockCheck;
	public final Block blockReplace;
	public String create;
	public String create1;
	public boolean isSuction = false;
	public ParticleOptions particle = null;

	public static final DustParticleOptions WHITE_DUST = new DustParticleOptions(new Vector3f(Vec3.fromRGB24(0xFFFFFF)), 1.0F);

    public InstantLiquidBlock(Properties properties, Block blockCheck, Block blockReplace) {
        super(properties);
		this.posList = new ArrayList<>();
		this.blockCheck = blockCheck;
		this.blockReplace = blockReplace;
    }

	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
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
	public boolean skipRendering(@Nonnull BlockState state, BlockState adjacentBlockState, @Nonnull Direction side) {
		return adjacentBlockState.is(this) || super.skipRendering(state, adjacentBlockState, side);
	}

	@Override
	@Nonnull
	public VoxelShape getVisualShape(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public float getShadeBrightness(@Nonnull BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos) {
		return 1.0F;
	}

	@Override
	public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
		return !this.isSuction;
	}

	private int getMax() {
		return isSuction ? Common.CONFIG.MAX_FILL() : Common.CONFIG.MAX_LIQUID();
	}

	private Block getMainReplaceBlock() {
		if(isSuction) {
			return blockCheck == Blocks.WATER ? ModBlocks.INSTANT_WATER : ModBlocks.INSTANT_LAVA;
		}
		return blockReplace;
	}

	public boolean canActivate(Level world, BlockPos pos, Player player) {
		if(world.dimension().equals(Level.NETHER) && blockReplace.equals(Blocks.WATER) && !Common.CONFIG.ALLOW_WATER_IN_NETHER()) {
			Helper.sendMessage(player, Strings.ERROR_WATER_DISABLED);
			return false;
		}
		checkForBlock(world,pos);
		if(isSuction && posList.isEmpty()) {
			Helper.sendMessage(player, Strings.ERROR_NO_LIQUID,"",pos,ClientHelper.Particles.NO_LIQUID);
			return false;
		}
		if(posList.size() >= getMax()) {
			Helper.sendMessage(player, errorMessage, ChatFormatting.RED + String.valueOf(isSuction ? Common.CONFIG.MAX_FILL() : Common.CONFIG.MAX_LIQUID()));
			posList = new ArrayList<>();
			return false;
		} else {
			return true;
		}
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Builder builder = new Builder();
		for(BlockPos pos : posList) {
			Single.setup(builder,world,pos).setBlock(blockReplace).build();
		}
		Single.setup(builder,world,x,y,z).setBlock(getMainReplaceBlock()).build();
		if(posList.size() > 0) {
			setCreateMessage(create, String.valueOf(isSuction ? posList.size() : posList.size()+1));
		} else {
			setCreateMessage(create1);
		}
		builder.build();
		posList = new ArrayList<>();
		if(isSuction) {
			this.blockCheck = null;
		}
		return true;
	}

	private void checkForBlock(Level world, BlockPos pos) {
		check(world,pos.north(1));
		check(world,pos.east(1));
		check(world,pos.south(1));
		check(world,pos.west(1));
		if(!Common.CONFIG.SIMPLE_LIQUID() || isSuction) {
			check(world,pos.below(1));
		}
		if(isSuction) {
			check(world,pos.above(1));
		}
	}

	private void check(Level world, BlockPos pos) {
		Block blockCurrent = Helper.getBlock(world,pos);
		if(isCorrectBlock(blockCurrent) && posList.size() < getMax() && addPos(pos)) {
			if(blockCheck == null) {
				blockCheck = blockCurrent;
			}
			checkForBlock(world,pos);
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

	private boolean addPos(BlockPos pos) {
		if(!posList.contains(pos)) {
			posList.add(pos);
			return true;
		}
		return false;
	}

	public static class LiquidSoundType extends SoundType {
		public LiquidSoundType(SoundEvent breakSound, SoundEvent placeSound) {
			super(1.0F, 1.0F, breakSound, placeSound, placeSound, placeSound, placeSound);
		}
	}
}