package com.slymask3.instantblocks.block;

import com.mojang.math.Vector3f;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.instant.InstantSuctionBlock;
import com.slymask3.instantblocks.block.instant.InstantWaterBlock;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Random;

public abstract class InstantLiquidBlock extends InstantBlock {
	private ArrayList<BlockPos> posList;
	private Block blockCheck;
	private final Block blockReplace;
	private String create;
	private String create1;
	private ParticleOptions particle;

	public static final DustParticleOptions WHITE_DUST = new DustParticleOptions(new Vector3f(Vec3.fromRGB24(0xFFFFFF)), 1.0F);

    public InstantLiquidBlock(Properties properties, Block blockCheck, Block blockReplace) {
        super(properties);
		this.posList = new ArrayList<>();
		this.blockCheck = blockCheck;
		this.blockReplace = blockReplace;
		this.particle = null;
    }

	public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
		if(this.particle != null) {
			for(int i=0; i<4; i++) {
				world.addParticle(this.particle, (double)pos.getX() + Math.random(), (double)pos.getY() + 1.2D, (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return this.isSuction() ? Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) : Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
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
		return !this.isSuction();
	}

	public void setCreateMessages(String plural, String singular) {
		this.create = plural;
		this.create1 = singular;
	}

	public void setParticle(ParticleOptions particle) {
		this.particle = particle;
	}

	private int getMax() {
		return isSuction() ? Common.CONFIG.MAX_FILL() : Common.CONFIG.MAX_LIQUID();
	}

	private Block getMainReplaceBlock() {
		if(isSuction()) {
			return blockCheck == Blocks.LAVA ? ModBlocks.INSTANT_LAVA : ModBlocks.INSTANT_WATER;
		}
		return blockReplace;
	}

	private boolean isSuction() {
		return this instanceof InstantSuctionBlock;
	}

	private boolean isWater() {
		return this instanceof InstantWaterBlock;
	}

	public boolean canActivate(Level world, BlockPos pos, Player player) {
		if(Helper.isNether(world) && blockReplace.equals(Blocks.WATER) && !Common.CONFIG.ALLOW_WATER_IN_NETHER()) {
			Helper.sendMessage(player, Strings.ERROR_WATER_DISABLED);
			return false;
		}
		checkForBlock(world,pos);
		if(isSuction() && posList.isEmpty()) {
			Helper.sendMessage(player, Strings.ERROR_NO_LIQUID);
			Helper.showParticles(world, pos, ClientHelper.Particles.NO_LIQUID);
			return false;
		}
		if(posList.size() >= getMax()) {
			Helper.sendMessage(player, errorMessage, ChatFormatting.RED + String.valueOf(isSuction() ? Common.CONFIG.MAX_FILL() : Common.CONFIG.MAX_LIQUID()));
			posList = new ArrayList<>();
			return false;
		} else {
			return true;
		}
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Builder builder = Builder.setup(world,x,y,z).setOrigin(isSuction() ? Builder.Origin.TO : Builder.Origin.FROM);
		for(BlockPos pos : posList) {
			BlockState state = world.getBlockState(pos);
			if(isSuction() && state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
				Single.setup(builder,world,pos).setBlock(state.setValue(BlockStateProperties.WATERLOGGED,false)).queue();
			} else if(!isSuction() && state.hasProperty(BlockStateProperties.WATERLOGGED) && !state.getValue(BlockStateProperties.WATERLOGGED)) {
				Single.setup(builder,world,pos).setBlock(state.setValue(BlockStateProperties.WATERLOGGED,true)).queue();
			} else {
				Single.setup(builder,world,pos).setBlock(blockReplace).queue();
			}
		}
		Single.setup(builder,world,x,y,z).setBlock(getMainReplaceBlock()).queue();
		if(posList.size() > 0) {
			setCreateMessage(create, String.valueOf(isSuction() ? posList.size() : posList.size()+1));
		} else {
			setCreateMessage(create1);
		}
		builder.build();
		posList = new ArrayList<>();
		if(isSuction()) {
			this.blockCheck = null;
		}
		return true;
	}

	private void checkForBlock(Level world, BlockPos pos) {
		check(world,pos.north(1));
		check(world,pos.east(1));
		check(world,pos.south(1));
		check(world,pos.west(1));
		if(!Common.CONFIG.SIMPLE_LIQUID() || isSuction()) {
			check(world,pos.below(1));
		}
		if(isSuction()) {
			check(world,pos.above(1));
		}
	}

	private void check(Level world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		Block blockCurrent = state.getBlock();
		if((isCorrectBlock(blockCurrent) || isWaterlogged(state) || isWaterloggable(state)) && posList.size() < getMax() && addPos(pos)) {
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

	private boolean isWaterlogged(BlockState state) {
		return isSuction() && (blockCheck == null || blockCheck.equals(Blocks.WATER))
			&& state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
	}

	private boolean isWaterloggable(BlockState state) {
		return isWater()
			&& state.hasProperty(BlockStateProperties.WATERLOGGED) && !state.getValue(BlockStateProperties.WATERLOGGED)
			&& ((state.hasProperty(BlockStateProperties.SLAB_TYPE) && !state.getValue(BlockStateProperties.SLAB_TYPE).equals(SlabType.DOUBLE)) || !state.hasProperty(BlockStateProperties.SLAB_TYPE));
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