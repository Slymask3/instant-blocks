package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.block.entity.ColorBlockEntity;
import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.util.ColorHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ColorLayerBlock extends FaceAttachedHorizontalDirectionalBlock implements EntityBlock {
	public ColorLayerBlock() {
		super(Properties.of(Material.WOOL)
				.strength(0.8F)
				.sound(SoundType.WOOL)
		);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.FLOOR));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ColorBlockEntity(pos,state);
	}

	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState state1, boolean var5) {
		super.onPlace(state,world,pos,state1,var5);
		if(Helper.isServer(world)) {
			BlockEntity entity = world.getBlockEntity(pos);
			if(entity instanceof ColorBlockEntity) {
				((ColorBlockEntity)entity).color = ColorHelper.generateRandomColor().getRGB();
			}
		}
		world.sendBlockUpdated(pos,state,state,2);
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		world.sendBlockUpdated(pos,state,state,2);
		return InteractionResult.FAIL;
	}

	protected static final VoxelShape SHAPE_FLOOR = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
	protected static final VoxelShape SHAPE_CEILING = Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
	protected static final VoxelShape SHAPE_WALL_EAST = Block.box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0);
	protected static final VoxelShape SHAPE_WALL_WEST = Block.box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0);
	protected static final VoxelShape SHAPE_WALL_SOUTH = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0);
	protected static final VoxelShape SHAPE_WALL_NORTH = Block.box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0);

	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		AttachFace face = state.getValue(FACE);
		switch(face) {
			case FLOOR:
				return SHAPE_FLOOR;
			case CEILING:
				return SHAPE_CEILING;
			case WALL:
			default:
				Direction direction = state.getValue(FACING);
				return switch(direction) {
					case EAST -> SHAPE_WALL_EAST;
					case WEST -> SHAPE_WALL_WEST;
					case SOUTH -> SHAPE_WALL_SOUTH;
					default -> SHAPE_WALL_NORTH;
				};
		}
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, FACE);
	}

	public static BlockState getStateForDirection(Direction direction) {
		Direction stateDirection = switch(direction) {
			case UP, DOWN -> Direction.NORTH;
			default -> direction;
		};
		AttachFace stateAttach = switch(direction) {
			case DOWN -> AttachFace.FLOOR;
			case UP -> AttachFace.CEILING;
			case NORTH, SOUTH, WEST, EAST -> AttachFace.WALL;
		};
		return ModBlocks.COLOR_LAYER.defaultBlockState().setValue(FACING, stateDirection).setValue(FACE, stateAttach);
	}
}